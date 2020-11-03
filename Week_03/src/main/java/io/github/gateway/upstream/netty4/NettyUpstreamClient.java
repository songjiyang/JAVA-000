package io.github.gateway.upstream.netty4;

import io.github.gateway.upstream.UpstreamHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.apache.http.protocol.HTTP;

import java.net.URI;
import java.util.Map;

/**
 * @author songjiyang
 */
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
            String routeUrl = this.route();
            URI uri = new URI(routeUrl);
            ChannelFuture f = b.connect(uri.getHost(), uri.getPort()).sync();
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, routeUrl + fullRequest.uri());
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