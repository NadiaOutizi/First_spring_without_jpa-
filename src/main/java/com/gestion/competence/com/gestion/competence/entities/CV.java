package com.gestion.competence.com.gestion.competence.entities;

import java.util.ArrayList;
import java.util.List;

public class CV {
    private int id;
    private String name;
    private User user;
    private List<Competence> competences;


    public CV(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.competences = new ArrayList<Competence>();
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return this.user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
