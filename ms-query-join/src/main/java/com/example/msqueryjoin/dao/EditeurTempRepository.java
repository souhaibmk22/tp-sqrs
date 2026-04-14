package com.example.msqueryjoin.dao;

import com.example.msqueryjoin.Entities.EditeurTemp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EditeurTempRepository extends MongoRepository<EditeurTemp, String> {
}
