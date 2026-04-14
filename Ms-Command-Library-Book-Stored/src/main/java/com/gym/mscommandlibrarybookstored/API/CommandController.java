package com.gym.mscommandlibrarybookstored.API;

import com.example.coreapi.commands.AddBookCommand;
import com.example.coreapi.commands.LibraryCreationCommand;
import com.example.coreapi.commands.RemoveBookCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("command")
public class CommandController {

    private final CommandGateway commandGateway;

    public CommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("library")
    public CompletableFuture<String> createLibrary(@RequestBody Map<String, Object> payload){
        String libraryId = readFromPayload(payload, "libraryId");
        String name = readFromPayload(payload, "name");

        if (libraryId == null || libraryId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "libraryId is required");
        }
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required");
        }

        LibraryCreationCommand command = new LibraryCreationCommand();
        setField(command, "libraryId", libraryId);
        setField(command, "name", name);
        CompletableFuture<String> response = commandGateway.send(command);

        return  response;
    }

    @PostMapping("/library/{libraryid}/book")
    public CompletableFuture<String> addBook(@PathVariable String libraryid,
                                             @RequestBody Map<String, Object> payload) {
        String isbn = readFromPayload(payload, "isbn");
        String title = readFromPayload(payload, "title");
        String editeurId = readFromPayload(payload, "editeurId");

        if (isbn == null || isbn.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "isbn is required");
        }
        if (title == null || title.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title is required");
        }
        if (editeurId == null || editeurId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "editeurId is required");
        }

        AddBookCommand command = new AddBookCommand();
        setField(command, "libraryId", libraryid);
        setField(command, "isbn", isbn);
        setField(command, "title", title);
        setField(command, "editeurId", editeurId);
        CompletableFuture<String> response = commandGateway.send(command);
        return response;
    }

    @DeleteMapping("/library/{libraryid}/{isbn}")
    public CompletableFuture<String> removeBook(@PathVariable String libraryid,
                                                @PathVariable String isbn) {
        RemoveBookCommand command = new RemoveBookCommand();
        setField(command, "libraryId", libraryid);
        setField(command, "isbn", isbn);
        CompletableFuture<String> response = commandGateway.send(command);
        return response;
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

    private String readFromPayload(Map<String, Object> payload, String name) {
        Object value = payload.get(name);
        if (value == null) {
            return null;
        }
        String str = value.toString().trim();
        return str.isEmpty() ? null : str;
    }
}


