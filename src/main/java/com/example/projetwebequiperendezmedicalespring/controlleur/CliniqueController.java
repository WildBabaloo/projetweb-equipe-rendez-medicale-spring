package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.service.CliniqueNotFoundException;
import com.example.projetwebequiperendezmedicalespring.service.CliniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CliniqueController {

    @Autowired
    CliniqueService cliservice;
    @GetMapping("/cliniques")
    public String cliPage(){
        return "Vues/Clinique/clinique_index";
    }

    @GetMapping("/clilistpat/{id}")
    public String clilistPatPage(@PathVariable("id")int id, Model model){
        Iterable<Patient> listePatients = cliservice.afficherPatientsByClinique(id);
        model.addAttribute("listePatients",listePatients);
        return "Vues/Clinique/liste_patients";
    }

    @GetMapping("/clilistmed/{id}")
    public String clilistMedPage(@PathVariable("id")int id, Model model){
        Iterable<Medecin> listeMedecins = cliservice.afficherMedecinsByClinique(id);
        model.addAttribute("listeMedecins",listeMedecins);
        return "Vues/Clinique/liste_medecins";
    }

    @GetMapping("/clicompte/{id}")
    public String compteCliPage(@PathVariable("id")int id, Model model){
        Iterable<Clinique> clinique = cliservice.afficherCliniqueById(id);
        model.addAttribute("clinique",clinique);
        return "Vues/Clinique/compte_clinique";
    }

    @GetMapping("/histrdv/{id}")
    public String histrdvPage(@PathVariable("id")int id, Model model){
        Iterable<RendezVous> listeRendezVous = cliservice.afficherRendezVousByClinique(id);
        model.addAttribute("listeRendezVous",listeRendezVous);
        return "Vues/Clinique/historique_rdv";
    }

    @GetMapping("/modcli/{id}")
    public String modCliPage(@PathVariable("id")int id, Model model, RedirectAttributes redirectAttributes){
        try{
            Clinique clinique = cliservice.get(id);

            model.addAttribute("pageTitle","Editer Clinique (ID: " + id + ")");
            model.addAttribute("clinique",clinique);
            return "Vues/Clinique/modifier_clinique";
        } catch (CliniqueNotFoundException e){
            redirectAttributes.addFlashAttribute("message","On ne peut pas trouver une clinique avec l'id " + id);
        }
        return "Vues/Clinique/compte_clinique";
    }


}
