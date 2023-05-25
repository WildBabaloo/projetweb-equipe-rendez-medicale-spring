package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.service.PatientService;
import com.example.projetwebequiperendezmedicalespring.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RendezVousController {
    @Autowired
     RendezVousService service;

    @Autowired
    PatientService service1;


    @PostMapping("/formRendezVous")
    public String traiterFormulaireRendezVous(@ModelAttribute("rendezvous") RendezVous rendezVous) {

        service.ajouterRendezvous(rendezVous);
        return "redirect:/modifierRDV/"+rendezVous.getPatient().getId();
    }



}
