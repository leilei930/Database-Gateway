package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stops")
public class StopData {
    @Id
    private String id;

    @JsonProperty("stop")
    private String stop;

    @JsonProperty("name_en")
    private String name_en;

    @JsonProperty("name_tc")
    private String name_tc;

    @JsonProperty("name_sc")
    private String name_sc;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("long")
    private Double lng;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    // Getters, setters, and constructors
}
