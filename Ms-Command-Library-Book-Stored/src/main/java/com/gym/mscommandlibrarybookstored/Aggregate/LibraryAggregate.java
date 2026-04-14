package com.gym.mscommandlibrarybookstored.Aggregate;
import com.example.coreapi.commands.AddBookCommand;
import com.example.coreapi.commands.LibraryCreationCommand;
import com.example.coreapi.commands.RemoveBookCommand;
import com.example.coreapi.events.BookAddedEvent;
import com.example.coreapi.events.BookRemovedEvent;
import com.example.coreapi.events.LibraryCreatedEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Aggregate
@Entity
@Table(name = "libraries")
@Data @AllArgsConstructor @NoArgsConstructor
public class LibraryAggregate {

    @AggregateIdentifier
    @Id
    private String libraryId;
    private String name;

    @AggregateMember                          // ← Aggregate Member
    @OneToMany(mappedBy = "library",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    // ── Command Handlers ──────────────────────────────

    @CommandHandler
    public LibraryAggregate(LibraryCreationCommand cmd) {
        String libraryId = readField(cmd, "libraryId");
        String name = readField(cmd, "name");
        Assert.notNull(libraryId, "libraryId must not be null");
        Assert.notNull(name, "name must not be null");
        LibraryCreatedEvent event = new LibraryCreatedEvent();
        setField(event, "libraryId", libraryId);
        setField(event, "name", name);
        AggregateLifecycle.apply(
                event
        );
    }

    @CommandHandler
    public void handle(AddBookCommand cmd) {
        String libraryId = readField(cmd, "libraryId");
        String isbn = readField(cmd, "isbn");
        String title = readField(cmd, "title");
        String editeurId = readField(cmd, "editeurId");
        Assert.notNull(isbn, "isbn must not be null");
        Assert.notNull(title, "title must not be null");
        BookAddedEvent event = new BookAddedEvent();
        setField(event, "libraryId", libraryId);
        setField(event, "isbn", isbn);
        setField(event, "title", title);
        setField(event, "editeurId", editeurId);
        AggregateLifecycle.apply(
                event
        );
    }

    @CommandHandler
    public void handle(RemoveBookCommand cmd) {
        String libraryId = readField(cmd, "libraryId");
        String isbn = readField(cmd, "isbn");
        BookRemovedEvent event = new BookRemovedEvent();
        setField(event, "libraryId", libraryId);
        setField(event, "isbn", isbn);
        AggregateLifecycle.apply(
                event
        );
    }

    // ── Event Sourcing Handlers ───────────────────────

    @EventSourcingHandler
    public void on(LibraryCreatedEvent event) {
        this.libraryId = readField(event, "libraryId");
        this.name = readField(event, "name");
        this.books = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(BookAddedEvent event) {
        Book book = new Book();
        book.setIsbn(readField(event, "isbn"));
        book.setTitle(readField(event, "title"));
        book.setEditeurId(readField(event, "editeurId"));
        book.setLibrary(this);
        this.books.add(book);
    }

    @EventSourcingHandler
    public void on(BookRemovedEvent event) {
        String isbn = readField(event, "isbn");
        this.books.removeIf(b -> b.getIsbn().equals(isbn));
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

    private void setField(Object target, String name, String value) {
        try {
            Field field = target.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(target, value);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Cannot set field '" + name + "' in " + target.getClass().getSimpleName(), e);
        }
    }
}