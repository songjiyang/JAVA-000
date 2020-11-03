package io.github.gateway.inbound;

import io.github.gateway.filter.HttpRequestAddNameFilter;
import io.github.gateway.filter.HttpRequestFilter;
import io.github.gateway.filter.HttpRequestLoggingFilter;
import io.github.gateway.upstream.UpstreamHandler;
import io.github.gateway.upstream.netty4.NettyUpstreamClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songjiyang
 */
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
