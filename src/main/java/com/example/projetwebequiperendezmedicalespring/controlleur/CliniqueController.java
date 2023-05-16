package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.service.CliniqueNotFoundException;
import com.example.projetwebequiperendezmedicalespring.service.CliniqueService;
import com.example.projetwebequiperendezmedicalespring.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CliniqueController {

    @Autowired
    CliniqueService cliservice;

    @Autowired
    ServicesService serviceServices;

    @GetMapping("/clinique_index/{id_clinique}")
    public String cliPage(@PathVariable("id_clinique")int id_clinique,Model model, HttpServletResponse response){
        Clinique clinique = cliservice.getClinique(id_clinique);
        Cookie c = new Cookie("id_clinique",String.valueOf(id_clinique));
        c.setMaxAge(60*60);
        c.setSecure(true);
        c.setHttpOnly(true);
        c.setPath("/");
        response.addCookie(c);
        model.addAttribute("clinique",clinique);
        return "Vues/Clinique/clinique_index";
    }

    @GetMapping("/clilistpat/{id_clinique}")
    public String clilistPatPage(@PathVariable("id_clinique")int id_clinique, Model model){
        List<Patient> listePatients = cliservice.afficherPatientsByClinique(id_clinique);
        Clinique clinique = cliservice.getClinique(id_clinique);
        model.addAttribute("clinique",clinique);
        model.addAttribute("listePatients",listePatients);
        return "Vues/Clinique/liste_patients";
    }

    @GetMapping("/clilistmed/{id_clinique}")
    public String clilistMedPage(@PathVariable("id_clinique")int id_clinique, Model model){
        List<Medecin> listeMedecins = cliservice.afficherMedecinsByClinique(id_clinique);
        Clinique clinique = cliservice.getClinique(id_clinique);
        model.addAttribute("clinique",clinique);
        model.addAttribute("listeMedecins",listeMedecins);
        return "Vues/Clinique/liste_medecins";
    }

    @GetMapping("/clicompte/{id_clinique}")
    public String compteCliPage(@PathVariable("id_clinique")int id_clinique, Model model){
        Clinique clinique = cliservice.getClinique(id_clinique);
        model.addAttribute("clinique",clinique);
        return "Vues/Clinique/compte_clinique";
    }

    @PostMapping("/cliniques/save")
    public String saveClinique(Clinique clinique, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le clinique à été ajouté!");
        cliservice.ajouterClinique(clinique);
        return "redirect:/cliniques/{id}";
    }

    @GetMapping("/histrdv/{id_clinique}")
    public String histrdvPage(@PathVariable("id_clinique")int id_clinique, Model model){
        Clinique clinique = cliservice.getClinique(id_clinique);
        List<RendezVous> listeRendezVous = cliservice.afficherRendezVousByClinique(id_clinique);
        model.addAttribute("clinique",clinique);
        model.addAttribute("listeRendezVous",listeRendezVous);
        return "Vues/Clinique/historique_rdv";
    }

    @GetMapping("/clicompte/modifier/{id_clinique}")
    public String modCliPage(@PathVariable("id_clinique")int id_clinique, Model model){
        Clinique clinique = cliservice.getId(id_clinique);
        model.addAttribute("pageTitle","Editer Clinique (ID: " + id_clinique + ")");
        model.addAttribute("clinique",clinique);
        return "Vues/Clinique/modifier_clinique";
    }

//    @PostMapping("/clinique/save")
//    public String modifierClinique(Clinique clinique, RedirectAttributes redirectAttributes){
//        redirectAttributes.addFlashAttribute("message","La clinique " + clinique.getNom() + " a ete modifie avec success");
//        cliservice.ajouterClinique(clinique);
//        return "redirect:/Vues/clinique/compte_clinique";
//    }

    @GetMapping("cliniques/delete/{id_clinique}")
    public String deleteClinique(@PathVariable(name = "id_clinique") int id_clinique, RedirectAttributes redirectAttributes){
        cliservice.deleteClinique(id_clinique);
        redirectAttributes.addFlashAttribute("message", "Le clinique dont l'id est " +id_clinique+ " à été supprimé");
        return "redirect:/cliniques";
    }

}
