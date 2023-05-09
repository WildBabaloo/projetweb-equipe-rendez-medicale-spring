package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Services;
import com.example.projetwebequiperendezmedicalespring.service.MedecinService;
import com.example.projetwebequiperendezmedicalespring.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MedecinController {

    @Autowired
    MedecinService service;

    @Autowired
    ServicesService serviceServices;

    @GetMapping("/medecins")
    public String afficherMedecins(Model model){
        List<Medecin> listeMedecins = service.findAllMedecins();
        model.addAttribute("listeMedecins", listeMedecins);
        return "listeMedecinModifierSupprimer";
    }

    @GetMapping("/medecins/new")
    public String showNewMedecinForm(Model model){
        model.addAttribute("medecin", new Medecin());
        List<Services> listeServices = serviceServices.findAllServices();
        model.addAttribute("listeServices", listeServices);
        return "ajouter";
    }

    @GetMapping("/medecins/save")
    public String saveMedecin(Medecin medecin, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le medecin à été ajouté!");
        service.ajouterMedecin(medecin);
        // TO DO
        //Find clinique of that medecin and add his service to the liste of services offered by the clinique
        // And check if that service is not already in the list.
        // If in list add Else dont
        return "redirect:/medecins";
    }

    @GetMapping("/medecins/edit/{id}")
    public String updateMedecin(@PathVariable(name = "id") Integer id, Model model){
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("pageTitle", "Editer le medecin dont l'id est " +id);
        model.addAttribute("medecin", medecin);
        List<Services> listeServices = serviceServices.findAllServices();
        model.addAttribute("listeServices", listeServices);
        // Have to add optionMedecin if not possible then make a seperate page for add a medecin only
        return "ajouter";
    }



    @GetMapping("medecins/delete/{id}")
    public String deleteMedecin(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes){
        service.deleteMedecin(id);
        redirectAttributes.addFlashAttribute("message", "Le medecin dont l'id est " +id+ " à été supprimé");
        // TO DO
        //Find clinique of that medecin and remove his service to the liste of services offered by the clinique
        // And check if that service is not already in the list.
        // If no other person has this service then remove Else keep
        return "redirect:/medecins";
    }

}
