package com.example.demo.controller;

import com.example.demo.model.RouteStop;
import com.example.demo.service.ApiService;
import com.example.demo.service.RouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteStopController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private RouteStopService routeStopService;

    @GetMapping("/fetch-and-store")
    public String fetchAndStoreData() throws Exception {
        apiService.fetchRouteStopData();
        return "Data saved successfully!";
    }
}