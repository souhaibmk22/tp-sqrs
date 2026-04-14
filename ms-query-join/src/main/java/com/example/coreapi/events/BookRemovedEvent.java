package com.example.coreapi.events;

public class BookRemovedEvent {
    private String libraryId;
    private String isbn;

    public BookRemovedEvent() {
    }

    public BookRemovedEvent(String libraryId, String isbn) {
        this.libraryId = libraryId;
        this.isbn = isbn;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
