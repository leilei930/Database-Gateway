package com.example.demo.repository;

import com.example.demo.model.RouteStop;
import com.example.demo.model.RouteStopData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RouteStopRepository extends MongoRepository<RouteStopData, String> {
    List<RouteStopData> findByRoute(String route);
}

