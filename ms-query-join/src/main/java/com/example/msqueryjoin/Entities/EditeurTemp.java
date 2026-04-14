package com.example.msqueryjoin.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "editeur_temp")
public class EditeurTemp {
    @Id
    private String editeurId;
    private String name;

    public EditeurTemp() {
    }

    public EditeurTemp(String editeurId, String name) {
        this.editeurId = editeurId;
        this.name = name;
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
}
