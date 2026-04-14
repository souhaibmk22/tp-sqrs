package com.gym.mscommandlibrarybookstored.Aggregate;
import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    private String isbn;
    private String title;
    private String editeurId;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private LibraryAggregate library;

    public Book() {
    }

    public Book(String isbn, String title, String editeurId, LibraryAggregate library) {
        this.isbn = isbn;
        this.title = title;
        this.editeurId = editeurId;
        this.library = library;
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

    public LibraryAggregate getLibrary() {
        return library;
    }

    public void setLibrary(LibraryAggregate library) {
        this.library = library;
    }
}