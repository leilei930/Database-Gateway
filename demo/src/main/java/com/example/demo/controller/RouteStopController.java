package com.example.demo.controller;

import com.example.demo.model.RouteStop;
import com.example.demo.model.RouteStopData;
import com.example.demo.model.StopData;
import com.example.demo.service.ApiService;
import com.example.demo.service.RouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteStopController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private RouteStopService routeStopService;

    @GetMapping("/fetchRoute")
    public String fetchRoute() throws Exception {
        apiService.fetchRouteData();
        return "Route data saved successfully!";
    }

    @GetMapping("/fetchStop")
    public String fetchStop() throws Exception {
        apiService.fetchStopData();
        return "Stop data saved successfully!";
    }

    @GetMapping("/route/{routeId}")
    public List<StopData> getRouteStopsByStop(@PathVariable String routeId) {
        return apiService.findRouteStopsByRoute(routeId);
    }


}