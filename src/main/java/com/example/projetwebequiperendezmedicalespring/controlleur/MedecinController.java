package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MedecinController {

    @Autowired
    MedecinService medservice;
    @GetMapping("/medecins")
    public String medPage(){
        return "Vues/Medecin/medecin_index";
    }

    @GetMapping("/medlistpat/{id}")
    public String medlistPatPage(@PathVariable("id")int id, Model model, RedirectAttributes redirectAttributes){
        Iterable<Patient> listePatients = medservice.afficherPatientByMedecin(id);
        model.addAttribute("listePatients",listePatients);
        return "Vues/Medecin/liste_patients";
    }

    @GetMapping("/medcompte/{id}")
    public String compteMedPage(@PathVariable("id")int id,Model model){
        Medecin medecin = medservice.afficherMedecinById(id);
        model.addAttribute("medecin",medecin);
        return "Vues/Medecin/compte_medecin";
    }

    @GetMapping("/mesrdv/{id}")
    public String rdvPage(@PathVariable("id")int id,Model model){
        Iterable<RendezVous> listeRendezVous = medservice.afficherRendezVousByMed(id);
        model.addAttribute("listeRendezVous",listeRendezVous);
        return "Vues/Medecin/mes_rdv";
    }

    @GetMapping("/conpat")
    public String conPage(){return "Vues/Medecin/contacter_patient";}
}
