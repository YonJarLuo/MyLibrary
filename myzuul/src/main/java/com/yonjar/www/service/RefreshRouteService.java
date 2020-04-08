package com.yonjar.www.service;

import com.yonjar.www.router.DiscoveryRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author luoyj
 * @date 2020/4/8.
 * 事件 RoutesRefreshedEvent被监听器RouteListener 进行关联监听
 */
@Service
public class RefreshRouteService {
    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    private DiscoveryRouteLocator discoveryRouteLocator;

    /**
     * 动态路由实现 调用refreshRoute() 发布刷新路由事件
     */
    public void refreshRoute() {

        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(discoveryRouteLocator);
        //设置事件源 作用是让spring容器中的所有监听器都知道有这样一个事件发生了
        publisher.publishEvent(routesRefreshedEvent);
    }

}
