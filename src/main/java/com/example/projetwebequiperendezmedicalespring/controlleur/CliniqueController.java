package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Services;
import com.example.projetwebequiperendezmedicalespring.service.CliniqueService;
import com.example.projetwebequiperendezmedicalespring.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CliniqueController {

    @Autowired
    CliniqueService service;

    @Autowired
    ServicesService serviceServices;

    @GetMapping("/cliniques")
    public String afficherCliniques(Model model){
        List<Clinique> listeCliniques = service.findAllCliniques();
        model.addAttribute("listeCliniques", listeCliniques);
        return "listeCliniqueModifierSupprimer";
    }

    @GetMapping("/cliniques/new")
    public String showNewCliniqueForm(Model model){
        model.addAttribute("clinique", new Clinique());
        return "ajouter";
    }

    @GetMapping("/cliniques/save")
    public String saveClinique(Clinique clinique, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le clinique à été ajouté!");
        service.ajouterClinique(clinique);
        return "redirect:/cliniques";
    }

    @GetMapping("/cliniques/edit/{id}")
    public String updateClinique(@PathVariable(name = "id") Integer id, Model model){
        Clinique clinique = service.getClinique(id);
        model.addAttribute("pageTitle", "Editer le clinique dont l'id est " +id);
        model.addAttribute("clinique", clinique);
        // Have to add optionClinique if not possible then make a seperate page for add a clinique only
        return "ajouter";
    }


    // TO DO
    // Delete foreign key constraints
    @GetMapping("cliniques/delete/{id}")
    public String deleteClinique(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes){
        service.deleteClinique(id);
        redirectAttributes.addFlashAttribute("message", "Le clinique dont l'id est " +id+ " à été supprimé");
        // TO DO
        // Put all clinique_id of Medecins to 0 meaning they have no clinique that they are in
        return "redirect:/cliniques";
    }
}
