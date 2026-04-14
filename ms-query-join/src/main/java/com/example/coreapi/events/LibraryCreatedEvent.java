package com.example.coreapi.events;

public class LibraryCreatedEvent {
    private String libraryId;
    private String name;

    public LibraryCreatedEvent() {
    }

    public LibraryCreatedEvent(String libraryId, String name) {
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
