package io.github.gateway.router;

import io.github.gateway.NettyServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpRobinEndpointRouter implements HttpEndpointRouter{

    private static final Logger logger = LoggerFactory.getLogger(HttpRobinEndpointRouter.class);
    private final static AtomicInteger next = new AtomicInteger(0);

    @Override
    public String route(List<String> endpoints) {

        if(endpoints == null ||  endpoints.size() == 0){
            throw new RuntimeException("无路由信息");
        }
        String hit = endpoints.get(next.getAndIncrement() % endpoints.size());
        logger.info("路由到{}节点", hit);
        return hit;

    }
    
}
