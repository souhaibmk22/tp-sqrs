package com.example.msqueryjoin.dao;


import com.example.msqueryjoin.Entities.EditeurJoinBook;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EditeurJoinBookRepository extends MongoRepository<EditeurJoinBook, String> {

    long deleteByIsbn(String isbn);
    List<EditeurJoinBook> findByEditeurId(String editeurId);

}
