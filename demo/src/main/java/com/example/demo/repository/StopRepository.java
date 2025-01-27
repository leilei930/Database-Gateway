package com.example.demo.repository;

import com.example.demo.model.StopData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StopRepository extends MongoRepository<StopData, String> {
    StopData findByStop(String stop);
}
