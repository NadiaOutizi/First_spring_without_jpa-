package com.gestion.competence.com.gestion.competence.Services.ServiceImpl;

import com.gestion.competence.com.gestion.competence.Services.CvService;
import com.gestion.competence.com.gestion.competence.dao.repositories.CvRepository;
import com.gestion.competence.com.gestion.competence.entities.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CvServiceImpl implements CvService {
    @Autowired
    private CvRepository cvRepository;



    @Override
    public List<CV> findAllCv() {
        return cvRepository.findAll();
    }
    @Override
    public CV save(CV cv) {

        return cvRepository.save(cv);
    }

    @Override
    public CV findById(int id) {
        return cvRepository.findCvbyId(id);
    }

    @Override
    public void deleteById(int id) {
        cvRepository.deleteCvbyId(id);

    }

    @Override
    public void delete(int id) {

        cvRepository.delete(id);
    }
}
