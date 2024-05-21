package com.gestion.competence.com.gestion.competence.dao.repositories;

import com.gestion.competence.com.gestion.competence.entities.CV;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CvRepository {


    List<CV> findAll();

    CV save(CV cv);

    void delete(int id);

    CV findCvbyId(int id);

    void deleteCvbyId(int id);
}
