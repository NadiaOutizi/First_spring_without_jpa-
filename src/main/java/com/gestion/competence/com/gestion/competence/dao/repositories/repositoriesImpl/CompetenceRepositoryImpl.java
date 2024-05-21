package com.gestion.competence.com.gestion.competence.dao.repositories.repositoriesImpl;

import com.gestion.competence.com.gestion.competence.dao.JDBC.DbConnex;
import com.gestion.competence.com.gestion.competence.dao.repositories.CompetenceRepository;
import com.gestion.competence.com.gestion.competence.entities.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompetenceRepositoryImpl implements CompetenceRepository {

    @Autowired
    private DbConnex dbConnex;

    @Override
    public List<Competence> findAll() {
        return dbConnex.findAllCompetences();
    }

    @Override
    public Competence findById(int id) {
        return dbConnex.findCompetencesById(id);
    }

    @Override
    public Competence save(Competence competence) {
        return dbConnex.saveCompetences(competence);
    }

    @Override
    public void delete(int id) {
        dbConnex.deleteCompetences(id);
    }
}
