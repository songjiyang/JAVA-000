学习笔记


通过参考老师的代码，很有启发式的学习了Netty如何使用，但还有觉得有些问题，所以对此改造, 如有问题，请助教老师多多指出。

1. outbound在netty中的概念是一个出站行为，而我们的HttpOutboundHandler更像一个入站行为的处理方法，所以命名为outbound不合适，所以我将这部分命名为upstreamHandler(对应nginx)
2. HttpInboundInitializer这个名看起来像初始化inbound的一些东西，但实际是用来初始化channel, 所以改成GatewayChannelInitializer
3. HttpInboundServer也不是一个入站Server, 直接将代码放入NettyServerApplication
4. 原HttpOutboundHandler中的proxyService和httpClient会造成内存泄露，所以改成static, 让所有实例共享一个线程池和连接池
5. NamedThreadFactory的threadNumber变量设置为static的，所以实例共享一个变量，不然会存在相同的线程名称出现





#### 1. 整合你上次作业的httpclient/okhttp
- 改造了老师写的httpClient,  具体代码可以查看文件
#### 2.  使用netty实现后端http访问（代替上一步骤）

- 每次调用handle方法，会创建一个netty客户端，并发送一个请求，这个客户端会保存当前请求ChannelHandlerContext到NettyHttpClientInboundHandler中
```java
public class NettyUpstreamClient extends UpstreamHandler {
    Bootstrap b = new Bootstrap();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    public NettyUpstreamClient() {

        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);


    }

    public void connectAndSend(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        try {
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new NettyHttpClientInboundHandler(ctx));
                }
            });
            URI uri = new URI(this.backendUrl);
            ChannelFuture f = b.connect(uri.getHost(), uri.getPort()).sync();
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, this.backendUrl + fullRequest.uri());
            request.headers().add(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            request.headers().add(HTTP.TARGET_HOST, uri.getHost() + ":" + uri.getPort());
            HttpHeaders headers = fullRequest.headers();
            for (Map.Entry<String, String> entry : headers) {
                if ("nio".equals(entry.getKey())) {
                    request.headers().add(entry.getKey(), entry.getValue());
                }
            }
            f.channel().writeAndFlush(request);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        connectAndSend(fullRequest, ctx);
    }
}
```

- Netty客户端在收到入站事件会通过HttpResponseDecoder和HttpObjectAggregator封装成FullHttpResponse，然后调用原请求的ChannelHandlerContext将响应返回去

```java
public class NettyHttpClientInboundHandler extends ChannelInboundHandlerAdapter {

    private final ChannelHandlerContext originCtx;

    public NettyHttpClientInboundHandler(ChannelHandlerContext originCtx) {
        this.originCtx = originCtx;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpResponse fullHttpResponse = (FullHttpResponse) msg;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ByteBufUtil.getBytes(fullHttpResponse.content()));
            UpstreamHandler.handleResponse(null, originCtx, byteArrayInputStream);
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
```
#### 3.  实现过滤器

- 首先依据老师提供的HttpRequestFilter接口实现了一个打印HTTP日志的过滤器实现类, 功能非常简单，从fullRequest中获取HTTP方法和uri进行打印
- 另外实现了一个增加姓名header的过滤器

```java
public class HttpRequestLoggingFilter implements HttpRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestLoggingFilter.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        HttpMethod method = fullRequest.method();
        logger.info("{} {}", method.toString(), uri);

    }
}
public class HttpRequestAddNameFilter implements HttpRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestAddNameFilter.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = fullRequest.headers();
        headers.add("nio", "songjiyang");
    }
}
```


- 实现一个InBound处理器HttpInboundFilterHandler, 这个类初始化会构建一个过滤器列表，然后在有channel入站事件时，循环调用过滤器列表，最后触发channelRead进入pipeline中的下一个inbound处理器
```java
public class HttpInboundFilterHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpInboundFilterHandler.class);

    private final List<HttpRequestFilter> filterChain;

    public HttpInboundFilterHandler()  {
        // 后续可以使用xml或者spring注解方式加载所以filter, 此处直接new
        this.filterChain = new ArrayList<>();
        filterChain.add(new HttpRequestLoggingFilter());
        filterChain.add(new HttpRequestAddNameFilter());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            for (HttpRequestFilter filter : filterChain) {
                filter.filter(fullRequest, ctx);
            }
            ctx.fireChannelRead(fullRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
```

#### 3.  实现路由

- 首先实现了老师写的接口，HttpRobinEndpointRouter
- 然后将具体的URL替换为路由方法获取的URL, 因为代码太多，所以只粘贴片段, 下面是使用具体的路由器，并加载可用服务列表（这块使用了硬编码，在具体服务发现的系统中可以拿到这个列表)

```
    private final HttpEndpointRouter router = new HttpRobinEndpointRouter();

    protected List<String> backendUrlList = new ArrayList<>();
    public UpstreamHandler() {

        // 后续可以从配置文件，后者任意地方加载这个url, 此处先硬编码

        backendUrlList.add("http://localhost:8088");
        backendUrlList.add("http://localhost:8089");
        backendUrlList.add("http://localhost:8091");
    }

    public String route() {
        return router.route(backendUrlList);
    }

```

- 在httpClient中, 使用route方法来获取url

```
    @Override
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.route() + fullRequest.uri();
        proxyService.submit(() -> fetchGet(fullRequest, ctx, url));
    }
```

