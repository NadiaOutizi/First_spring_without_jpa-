package com.gestion.competence.com.gestion.competence.dao.repositories;

import com.gestion.competence.com.gestion.competence.entities.Experience;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExperienceRepository {
    List<Experience> findAll();
    Experience findById(int id);
    Experience save(Experience experience);
    void delete(int id);
}
