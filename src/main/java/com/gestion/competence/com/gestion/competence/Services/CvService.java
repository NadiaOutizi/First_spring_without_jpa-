package com.gestion.competence.com.gestion.competence.Services;

import com.gestion.competence.com.gestion.competence.entities.CV;
import com.gestion.competence.com.gestion.competence.entities.Competence;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CvService {
    List<CV> findAllCv();
    CV save(CV  cv);


    CV findById(int id);

    void deleteById(int id);

    void delete(int id);


}

