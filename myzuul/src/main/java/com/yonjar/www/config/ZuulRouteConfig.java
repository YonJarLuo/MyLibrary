package com.yonjar.www.config;

import com.yonjar.www.router.DiscoveryRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author luoyj
 * @date 2020/4/8.
 * DiscoveryRouteLocator的配置类
 */
@Configuration
public class ZuulRouteConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public DiscoveryRouteLocator routeLocator() {
        DiscoveryRouteLocator routeLocator = new DiscoveryRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
        routeLocator.setJdbcTemplate(jdbcTemplate);
        return routeLocator;
    }



}
