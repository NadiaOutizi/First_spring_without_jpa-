package com.gestion.competence.com.gestion.competence.entities;

public class Experience {
    private int id;
    private String experience;
    private String description;
    private CV cv;


    public Experience(int id, String experience, String description,CV cv) {
        this.id = id;
        this.experience = experience;
        this.description = description;
        this.cv = cv;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public CV getCv() {
      return cv;
    }
}
