package com.gestion.competence.com.gestion.competence.dao.repositories.repositoriesImpl;

import com.gestion.competence.com.gestion.competence.dao.JDBC.DbConnex;
import com.gestion.competence.com.gestion.competence.dao.repositories.CvRepository;
import com.gestion.competence.com.gestion.competence.entities.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CvRepositoryImpl implements CvRepository {

    @Autowired
    private DbConnex dbConnex;

    @Override
    public List<CV> findAll() {
         return dbConnex.findAllCVs();

    }

    @Override
    public CV save(CV cv) {
        return dbConnex.saveCV(cv);

    }

    @Override
    public void delete(int id) {
        dbConnex.deleteAllCVs(id);
    }

    @Override
    public CV findCvbyId(int id) {
        return dbConnex.findCvById(id);
    }

    @Override
    public void deleteCvbyId(int id) {
        dbConnex.deleteCvbyId(id);

    }
}
