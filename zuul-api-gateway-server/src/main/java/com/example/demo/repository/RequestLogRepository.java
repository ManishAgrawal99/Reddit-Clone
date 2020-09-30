package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.RequestLog;

public interface RequestLogRepository extends MongoRepository<RequestLog, String> {

}
