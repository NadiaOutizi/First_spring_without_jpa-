package com.gestion.competence.com.gestion.competence.entities;

import java.util.ArrayList;
import java.util.List;

public class Competence {
    private int id;
    private String competenceName;


    public Competence(int id, String competenceName) {
        this.id = id;
        this.competenceName = competenceName;

    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompetenceName() {
        return competenceName;
    }
    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }


}
