package com.example.projetwebequiperendezmedicalespring.rest;

import com.example.projetwebequiperendezmedicalespring.service.CliniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CliniqueRestController {

    @Autowired
    CliniqueService service;

    @PostMapping("/cliniques/check_email")
    public String verifierDoublonEmail(@Param("emailClinique")String emailClinique){
        return service.isEmailUnique(emailClinique)?"OK":"Doublon";
    }
}
