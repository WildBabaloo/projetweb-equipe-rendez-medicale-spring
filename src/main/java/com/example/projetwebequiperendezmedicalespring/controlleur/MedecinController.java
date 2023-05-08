package com.example.projetwebequiperendezmedicalespring.controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedecinController {

    @GetMapping("/medecins")
    public String medPage(){
        return "Vues/Medecin/medecin_index";
    }
}
