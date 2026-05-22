/*
 * package com.agw.filters;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.cloud.gateway.filter.GatewayFilterChain; import
 * org.springframework.cloud.gateway.filter.GlobalFilter; import
 * org.springframework.core.Ordered; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.server.ServerWebExchange;
 * 
 * import reactor.core.publisher.Mono;
 * 
 * @Component public class LoggingFilter implements GlobalFilter, Ordered {
 * 
 * private static final Logger LOGGER =
 * LoggerFactory.getLogger(LoggingFilter.class);
 * 
 * @Override public Mono<Void> filter(ServerWebExchange exchange,
 * GatewayFilterChain chain) { String path =
 * exchange.getRequest().getURI().getQuery(); String method =
 * exchange.getRequest().getMethod().name();
 * 
 * LOGGER.info("Incoming request: {} {}", method, path);
 * 
 * return chain.filter(exchange).then(Mono.fromRunnable(() -> { int status =
 * exchange.getResponse().getStatusCode() != null ?
 * exchange.getResponse().getStatusCode().value() : 0;
 * LOGGER.info("Response status: {}", status); })); }
 * 
 * @Override public int getOrder() { return -1; }
 * 
 * }
 */