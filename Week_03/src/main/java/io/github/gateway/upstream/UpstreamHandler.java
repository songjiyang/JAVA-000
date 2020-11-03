package io.github.gateway.upstream;

import io.github.gateway.router.HttpEndpointRouter;
import io.github.gateway.router.HttpRobinEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author songjiyang
 */
public abstract class UpstreamHandler {

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

    /**
     * 实现handle, 并调用handleResponse将响应返回
     *
     * @param fullRequest http请求
     * @param ctx         channel环境
     */
    public abstract void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx);

    public static void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final InputStream in) throws Exception {
        FullHttpResponse response = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[4096 * 4];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(buffer.toByteArray()));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", buffer.toByteArray().length);


        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            ctx.close();
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            } else {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            }
            ctx.flush();
        }

    }
}
