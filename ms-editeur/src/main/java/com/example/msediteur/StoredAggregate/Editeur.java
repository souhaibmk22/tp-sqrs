package com.example.msediteur.StoredAggregate;

import com.example.coreapi.commands.EditeurCreationCommand;
import com.example.coreapi.events.EditeurCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import jakarta.persistence.*;
import java.lang.reflect.Field;


@Aggregate
@Entity
public class Editeur {
    @AggregateIdentifier
    @Id
    private String editeurId;
    private String name;
    private String pays;

    public Editeur() {
    }

    @CommandHandler
    public  Editeur(EditeurCreationCommand cmd){
        String editeurId = readField(cmd, "editeurId");
        String name = readField(cmd, "name");
        String pays = readField(cmd, "pays");
        Assert.notNull(editeurId,"editeurId should be not null");
        Assert.notNull(name,"name should be not null");

        EditeurCreatedEvent event = new EditeurCreatedEvent();
        setField(event, "editeurId", editeurId);
        setField(event, "name", name);
        setField(event, "pays", pays);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EditeurCreatedEvent event)
    {
        this.editeurId = readField(event, "editeurId");
        this.name = readField(event, "name");
        this.pays = readField(event, "pays");
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

    public String getEditeurId() {
        return editeurId;
    }

    public void setEditeurId(String editeurId) {
        this.editeurId = editeurId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

}
