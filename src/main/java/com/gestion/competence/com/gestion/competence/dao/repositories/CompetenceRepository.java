package com.gestion.competence.com.gestion.competence.dao.repositories;

import com.gestion.competence.com.gestion.competence.entities.Competence;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface CompetenceRepository {

    List<Competence> findAll();

    Competence findById(int id);

    Competence save(Competence competence);

    void delete(int id);
}
