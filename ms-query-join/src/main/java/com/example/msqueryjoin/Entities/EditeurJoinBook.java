package com.example.msqueryjoin.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "editeur_join_book")
public class EditeurJoinBook {

    @Id
    private String id;
    private String libraryId;
    private String libraryName;
    private String editeurId;
    private String isbn;
    private String editeurName;
    private String bookTitle;

    public EditeurJoinBook() {
    }

    public EditeurJoinBook(String id, String libraryId, String libraryName, String editeurId, String isbn, String editeurName, String bookTitle) {
        this.id = id;
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.editeurId = editeurId;
        this.isbn = isbn;
        this.editeurName = editeurName;
        this.bookTitle = bookTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getEditeurId() {
        return editeurId;
    }

    public void setEditeurId(String editeurId) {
        this.editeurId = editeurId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditeurName() {
        return editeurName;
    }

    public void setEditeurName(String editeurName) {
        this.editeurName = editeurName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
