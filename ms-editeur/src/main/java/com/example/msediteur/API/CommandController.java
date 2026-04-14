package com.example.msediteur.API;

import com.example.coreapi.DTO.EditeurDTO;
import com.example.coreapi.commands.EditeurCreationCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("command")
public class CommandController {

    @Autowired
    CommandGateway commandGateway;

    @PostMapping("editeur")
    public CompletableFuture<String> addEditeur(@RequestBody EditeurDTO editeurDTO){

        EditeurCreationCommand command = new EditeurCreationCommand();
        setField(command, "editeurId", readField(editeurDTO, "editeurId"));
        setField(command, "name", readField(editeurDTO, "name"));
        setField(command, "pays", readField(editeurDTO, "pays"));

        CompletableFuture<String> response = commandGateway.send(command);

        return response;
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
