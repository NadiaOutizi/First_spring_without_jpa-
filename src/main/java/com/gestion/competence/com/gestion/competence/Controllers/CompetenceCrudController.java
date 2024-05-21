package com.gestion.competence.com.gestion.competence.Controllers;

import com.gestion.competence.com.gestion.competence.Services.CompetenceService;
import com.gestion.competence.com.gestion.competence.entities.Competence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Controller
@RequestMapping("/competences")

public class CompetenceCrudController {

    @Autowired
   final private CompetenceService service;

    public CompetenceCrudController(CompetenceService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Competence> getAllCompetences() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Competence getCompetenceById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/create")
    public Competence createCompetence(@RequestBody Competence competence) {
        return service.save(competence);
    }

    @PutMapping("/{id}")
    public Competence updateCompetence(@PathVariable int id, @RequestBody Competence competence) {
        Competence existingCompetence = service.findById(id);
        if (existingCompetence != null) {
            competence.setId(id);
            return service.save(competence);
        } else {
            return null; // or handle not found case
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable int id) {
        service.delete(id);
    }
}
