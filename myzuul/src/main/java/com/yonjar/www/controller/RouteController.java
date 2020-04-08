package com.yonjar.www.controller;

import com.yonjar.www.service.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoyj
 * @date 2020/4/8.
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RefreshRouteService refreshRouteService;

    @GetMapping("/refreshRoute")
    public void refreshRoute(){
        refreshRouteService.refreshRoute();
        System.out.println("手动触发refreshRoute");
    }
}
