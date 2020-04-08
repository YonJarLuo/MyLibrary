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
 * 另外由于spring容易监听了HeartbeatEvent事件，会触发刷新加载路由配置，所以就会看到日志每过30秒，刷新2次。如果再添加自定义的 RouteListener，就会刷3次
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
