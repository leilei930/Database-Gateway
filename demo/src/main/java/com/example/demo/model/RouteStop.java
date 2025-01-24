package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
public class RouteStop {
    @Id
    private String id; // Maps to _id in MongoDB
    private String type;
    private String version;
    private String generatedTimestamp;
    private List<RouteStopData> data;
}
