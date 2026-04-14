package com.example.msqueryjoin.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "library_temp")
public class LibraryTemp {
    @Id
    private String libraryId;
    private String name;

    public LibraryTemp() {
    }

    public LibraryTemp(String libraryId, String name) {
        this.libraryId = libraryId;
        this.name = name;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
