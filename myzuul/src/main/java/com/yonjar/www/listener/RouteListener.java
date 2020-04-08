package com.yonjar.www.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author luoyj
 * @date 2020/4/8.
 * 监听器
 * 监听ApplicationEvent RoutesRefreshedEvent
 *
 * 我觉得不需要另外写这个监听器，因为zuul内置有监听器
 * 在spring-cloud-netflix-core-1.4.4.RELEASE-sources.jar中的org.springframework.cloud.netflix.zuul.zuulZuulServerAutoConfiguration.java配置了监听事件。
 */
@Slf4j
@Component
public class RouteListener implements ApplicationListener<ApplicationEvent> {
    @Autowired
    private ZuulHandlerMapping zuulHandlerMapping;
    private HeartbeatMonitor heartbeatMonitor = new HeartbeatMonitor();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent || event instanceof RefreshScopeRefreshedEvent || event instanceof RoutesRefreshedEvent){
            //主动手动刷新。上下文刷新，配置属性刷新
            zuulHandlerMapping.setDirty(true);
            log.info("手动刷新配置，被监听器监听到");
        }else if(event instanceof HeartbeatEvent){
            //心跳触发,将本地映射关系,关联到远程服务上 默认30秒刷新一次
            HeartbeatEvent heartbeatEvent = (HeartbeatEvent)event;
            if(heartbeatMonitor.update(heartbeatEvent.getValue())){
                zuulHandlerMapping.setDirty(true);
            }
        }
    }
}
