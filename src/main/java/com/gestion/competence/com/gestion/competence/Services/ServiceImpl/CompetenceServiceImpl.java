package com.gestion.competence.com.gestion.competence.Services.ServiceImpl;

import com.gestion.competence.com.gestion.competence.Services.CompetenceService;
import com.gestion.competence.com.gestion.competence.dao.repositories.CompetenceRepository;
import com.gestion.competence.com.gestion.competence.entities.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CompetenceServiceImpl implements CompetenceService {
    @Autowired
  private CompetenceRepository competenceRepository;


    @Override
    public List<Competence> findAll() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence save(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public void delete(int id) {
        competenceRepository.delete(id);
    }

    @Override
    public Competence findById(int id) {
        return competenceRepository.findById(id);
    }


}
