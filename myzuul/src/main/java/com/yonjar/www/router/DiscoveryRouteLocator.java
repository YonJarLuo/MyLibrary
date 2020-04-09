package com.yonjar.www.router;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author luoyj
 * @date 2020/4/8.
 * 动态路由,模仿DiscoveryClientRouteLocator
 * 把写在配置文件中的路由配置写到数据库中
 */
@Slf4j
public class DiscoveryRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulProperties properties;

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * SimpleRouteLocator有构造方法
     */
    public DiscoveryRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        //初始化数据
        this.properties = properties;
        log.info("servletPath:{}",servletPath);
    }

    @Override
    public void refresh() {
        //调用父类刷新方法
        doRefresh();
    }

    //主动重写父类的 加载路由信息方法
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {

        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        //从配置文件中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //自定义加载路由信息
        routesMap.putAll(getRouteList());

        //优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }


    /**
     * 从数据库读取zuul路由规则
     * @return
     */
    private LinkedHashMap<String, ZuulProperties.ZuulRoute> getRouteList() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> zuulRoutes = new LinkedHashMap<>();
        List<ZuulProperties.ZuulRoute> sysZuulRoutes = jdbcTemplate.query("select * from sys_zuul_route where del_flag = 0", new BeanPropertyRowMapper<>(ZuulProperties.ZuulRoute.class));

        for (ZuulProperties.ZuulRoute route: sysZuulRoutes) {

            // 为空跳过
            if (Strings.isNullOrEmpty(route.getPath()) && Strings.isNullOrEmpty(route.getUrl())) {
                continue;
            }

            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                BeanUtils.copyProperties(route,zuulRoute);
            } catch (Exception e) {
                log.error("数据库加载配置异常", e);
            }
            log.info("自定义的路由配置,path：{}，serviceId:{}，", zuulRoute.getPath(), zuulRoute.getServiceId());
            zuulRoutes.put(zuulRoute.getPath(), zuulRoute);

        }
        return zuulRoutes;

    }

}
