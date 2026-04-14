package com.gym.mscommandlibrarybookstored.projection;

import com.example.coreapi.events.BookAddedEvent;
import com.example.coreapi.events.BookRemovedEvent;
import com.example.coreapi.events.LibraryCreatedEvent;
import com.gym.mscommandlibrarybookstored.Aggregate.Book;
import com.gym.mscommandlibrarybookstored.Aggregate.LibraryAggregate;
import com.gym.mscommandlibrarybookstored.dao.BookRepository;
import com.gym.mscommandlibrarybookstored.dao.LibraryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Component
public class LibraryProjection {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    public LibraryProjection(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void addLibrary(LibraryCreatedEvent event) {
        String libraryId = readField(event, "libraryId");
        String name = readField(event, "name");

        LibraryAggregate library = libraryRepository.findById(libraryId).orElseGet(LibraryAggregate::new);
        library.setLibraryId(libraryId);
        library.setName(name);
        if (library.getBooks() == null) {
            library.setBooks(new ArrayList<>());
        }
        libraryRepository.save(library);
    }

    @EventHandler
    public void addBook(BookAddedEvent event) {
        String libraryId = readField(event, "libraryId");
        String isbn = readField(event, "isbn");
        String title = readField(event, "title");
        String editeurId = readField(event, "editeurId");

        LibraryAggregate library = libraryRepository.findById(libraryId).orElse(null);
        if (library == null) {
            return;
        }

        Book book = bookRepository.findById(isbn).orElseGet(Book::new);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditeurId(editeurId);
        book.setLibrary(library);
        bookRepository.save(book);
    }

    @EventHandler
    public void removeBook(BookRemovedEvent event) {
        String isbn = readField(event, "isbn");
        bookRepository.deleteById(isbn);
    }

    private String readField(Object source, String name) {
        try {
            Field field = source.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Object value = field.get(source);
            return value == null ? null : value.toString();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Missing field '" + name + "' in " + source.getClass().getSimpleName(), e);
        }
    }
}
