package com.gestion.competence.com.gestion.competence.dao.repositories.repositoriesImpl;

import com.gestion.competence.com.gestion.competence.dao.JDBC.DbConnex;
import com.gestion.competence.com.gestion.competence.dao.repositories.ExperienceRepository;
import com.gestion.competence.com.gestion.competence.entities.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceRepositoryImpl implements ExperienceRepository {
    @Autowired
    private DbConnex dbConnex;
    @Override
    public List<Experience> findAll() {
        return dbConnex.findAllExperiences() ;
    }

    @Override
    public Experience findById(int id) {
        return dbConnex.findExperiencebyId(id);
    }

    @Override
    public Experience save(Experience experience) {
        return dbConnex.saveExperience(experience);
    }

    @Override
    public void delete(int id) {
        dbConnex.deleteExperience(id);
    }
}
