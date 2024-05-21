package com.gestion.competence.com.gestion.competence.Controllers;

import com.gestion.competence.com.gestion.competence.Handlers.CustomException;
import com.gestion.competence.com.gestion.competence.Services.CvService;
import com.gestion.competence.com.gestion.competence.entities.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cvs")
public class CvCrudController {

    private CvService service;


    @Autowired
    public CvCrudController(CvService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CV>>getAllCvs() {

        List<CV> cvs = service.findAllCv();
        if (cvs.isEmpty()) {
            throw new CustomException("there is no cv found");
        }
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CV> getCvById(@PathVariable int id) {
        CV cv = service.findById(id);
        if (cv != null) {
            return new ResponseEntity<>(cv, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CV> createCv(@RequestBody CV cv) {
        CV createdCv = service.save(cv);
        return new ResponseEntity<>(createdCv, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<CV> updateCv(@PathVariable int id, @RequestBody CV cv) {
        CV existingCv = service.findById(id);
        if (existingCv != null) {
            cv.setId(id);
            CV updatedCv = service.save(cv);
            return new ResponseEntity<>(updatedCv, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCv(@PathVariable int id) {
        CV existingCv = service.findById(id);
        if (existingCv != null) {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
