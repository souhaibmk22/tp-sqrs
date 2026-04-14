package com.example.msqueryjoin.projection;


import com.example.coreapi.events.BookAddedEvent;
import com.example.coreapi.events.BookRemovedEvent;
import com.example.coreapi.events.EditeurCreatedEvent;
import com.example.coreapi.events.LibraryCreatedEvent;
import com.example.msqueryjoin.Entities.EditeurJoinBook;
import com.example.msqueryjoin.Entities.EditeurTemp;
import com.example.msqueryjoin.Entities.LibraryTemp;
import com.example.msqueryjoin.dao.EditeurJoinBookRepository;
import com.example.msqueryjoin.dao.EditeurTempRepository;
import com.example.msqueryjoin.dao.LibraryTempRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditeurJoinBookProjection {

    @Autowired
    EditeurJoinBookRepository editeurJoinBookRepository;
    @Autowired
    EditeurTempRepository editeurTempRepository;
    @Autowired
    LibraryTempRepository libraryTempRepository;

    @EventHandler
    public void addEditeur(EditeurCreatedEvent event)
    {
        editeurTempRepository.save(new EditeurTemp(event.getEditeurId(), event.getName()));
    }

    @EventHandler
    public void addLibrary(LibraryCreatedEvent event) {
        libraryTempRepository.save(new LibraryTemp(event.getLibraryId(), event.getName()));
    }

    @EventHandler
    public void addBook(BookAddedEvent event)
    {
        EditeurJoinBook editeurJoinBook =new EditeurJoinBook();
        editeurJoinBook.setId(event.getEditeurId() + "_" + event.getIsbn());
        editeurJoinBook.setLibraryId(event.getLibraryId());
        editeurJoinBook.setLibraryName(libraryTempRepository.findById(event.getLibraryId())
                .map(LibraryTemp::getName)
                .orElse("UNKNOWN_LIBRARY"));
        editeurJoinBook.setEditeurId(event.getEditeurId());
        editeurJoinBook.setIsbn(event.getIsbn());
        editeurJoinBook.setBookTitle(event.getTitle());
        editeurJoinBook.setEditeurName(editeurTempRepository.findById(event.getEditeurId())
                .map(EditeurTemp::getName)
                .orElse("UNKNOWN_EDITEUR"));

        editeurJoinBookRepository.save(editeurJoinBook);
    }

    @EventHandler
    public void removeBook(BookRemovedEvent event){
        editeurJoinBookRepository.deleteByIsbn(event.getIsbn());
    }
}
