package com.example.demo.service;

import com.example.demo.model.RouteStop;
import com.example.demo.repository.RouteStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteStopService {
    @Autowired
    private RouteStopRepository repository;


}
