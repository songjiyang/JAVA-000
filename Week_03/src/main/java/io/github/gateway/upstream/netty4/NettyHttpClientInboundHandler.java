package io.github.gateway.upstream.netty4;

import io.github.gateway.upstream.UpstreamHandler;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;

import java.io.ByteArrayInputStream;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author songjiyang
 */
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