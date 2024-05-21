package com.gestion.competence.com.gestion.competence.Services;

import com.gestion.competence.com.gestion.competence.entities.Competence;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompetenceService {
    List<Competence> findAll();

    Competence save(Competence competence);

    void delete(int id);

    Competence findById(int id);
}
