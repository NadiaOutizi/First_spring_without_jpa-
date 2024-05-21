package com.gestion.competence.com.gestion.competence.Services;

import com.gestion.competence.com.gestion.competence.entities.Competence;
import com.gestion.competence.com.gestion.competence.entities.Experience;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExperienceService {
    List<Experience> findAll();

    Experience save(Experience experience);

    void delete(int id);

    Experience findById(int id);

    void deleteById(int id);
}
