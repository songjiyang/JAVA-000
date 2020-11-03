package io.github.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author songjiyang
 */
public class HttpRequestAddNameFilter implements HttpRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestAddNameFilter.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = fullRequest.headers();
        headers.add("nio", "songjiyang");
    }
}
