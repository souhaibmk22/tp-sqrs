package com.example.msqueryjoin.dao;

import com.example.msqueryjoin.Entities.LibraryTemp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryTempRepository extends MongoRepository<LibraryTemp, String> {
}
