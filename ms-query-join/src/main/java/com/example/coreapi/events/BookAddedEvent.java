package com.example.coreapi.events;

public class BookAddedEvent {

    private String libraryId;
    private String isbn;
    private String title;
    private String editeurId;

    public BookAddedEvent() {
    }

    public BookAddedEvent(String libraryId, String isbn, String title, String editeurId) {
        this.libraryId = libraryId;
        this.isbn = isbn;
        this.title = title;
        this.editeurId = editeurId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditeurId() {
        return editeurId;
    }

    public void setEditeurId(String editeurId) {
        this.editeurId = editeurId;
    }
}
