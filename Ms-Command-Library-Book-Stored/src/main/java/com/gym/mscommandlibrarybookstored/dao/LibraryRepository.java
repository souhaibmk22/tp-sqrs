package com.gym.mscommandlibrarybookstored.dao;

import com.gym.mscommandlibrarybookstored.Aggregate.LibraryAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<LibraryAggregate, String> {
}
