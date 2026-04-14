package com.example.coreapi.DTO;

public class EditeurDTO {
    private String editeurId;
    private String name;
    private String pays;

    public EditeurDTO() {
    }

    public EditeurDTO(String editeurId, String name, String pays) {
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
