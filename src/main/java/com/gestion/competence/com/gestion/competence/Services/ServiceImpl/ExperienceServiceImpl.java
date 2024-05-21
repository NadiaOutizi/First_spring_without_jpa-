package com.gestion.competence.com.gestion.competence.Services.ServiceImpl;

import com.gestion.competence.com.gestion.competence.Services.ExperienceService;
import com.gestion.competence.com.gestion.competence.dao.repositories.ExperienceRepository;
import com.gestion.competence.com.gestion.competence.entities.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;


    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience save(Experience experience) {
        return  experienceRepository.save(experience);
    }

    @Override
    public void delete(int id) {
        experienceRepository.delete(id);
    }

    @Override
    public Experience findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        experienceRepository.delete(id);
    }
}
