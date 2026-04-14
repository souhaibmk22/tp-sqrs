package com.gym.mscommandlibrarybookstored.dao;

import com.gym.mscommandlibrarybookstored.Aggregate.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
