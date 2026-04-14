package com.example.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class EditeurCreationCommand {
    @TargetAggregateIdentifier
    private String editeurId;
    private String name;
    private String pays;

    public EditeurCreationCommand() {
    }

    public EditeurCreationCommand(String editeurId, String name, String pays) {
        this.editeurId = editeurId;
        this.name = name;
        this.pays = pays;
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
