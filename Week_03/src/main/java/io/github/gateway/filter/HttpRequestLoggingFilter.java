package io.github.gateway.filter;

import io.github.gateway.NettyServerApplication;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author songjiyang
 */
public class HttpRequestLoggingFilter implements HttpRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestLoggingFilter.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        HttpMethod method = fullRequest.method();
        logger.info("{} {}", method.toString(), uri);

    }
}
