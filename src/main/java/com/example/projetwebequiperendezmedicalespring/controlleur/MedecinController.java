package com.example.projetwebequiperendezmedicalespring.controlleur;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.projetwebequiperendezmedicalespring.entities.Services;
import com.example.projetwebequiperendezmedicalespring.service.ServicesService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MedecinController {

    @Autowired
    MedecinService service;

    @Autowired
    ServicesService serviceServices;

    @PostMapping("/medecinLogin")
    public String medecinLogin(Model model, @RequestParam("numProf") int numProf, @RequestParam("passwordMedecin") String password, HttpServletResponse response){
        // Check if numProf is int (FOR FUTURE ME TO DO BECAUSE I WAS SLEEPY)
        if(service.verifyMedecinLogin(numProf, password) != null){
            // Add cookies
            Medecin medecin = service.findMedecinNumProf(numProf);
            Cookie c = new Cookie("id",String.valueOf(medecin.getId()));
            c.setMaxAge(60*60);
            c.setSecure(true);
            c.setPath("/");
            response.addCookie(c);
            model.addAttribute("medecin",medecin);
            model.addAttribute("message", "Correct (To edit)");
            return "/Vues/Medecin/medecin_index";
        }
        model.addAttribute("message", "Wrong (To edit)");
        return "/Vues/login";
    }

    @GetMapping("/medecin_index/{id}")
    public String medecin_index(@PathVariable(name="id")Integer id, Model model){
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("medecin",medecin);
        return "/Vues/Medecin/medecin_index";
    }

    @GetMapping("/compte_medecin/{id}")
    public String compte_medecin(@PathVariable(name="id")Integer id, Model model){
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("medecin",medecin);
        return "/Vues/Medecin/compte_medecin";
    }

    @GetMapping("/contacter_patient/{id}")
    public String contacter_patient(@PathVariable(name="id")Integer id, Model model){
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("medecin",medecin);
        return "/Vues/Medecin/contacter_patient";
    }

    @GetMapping("/liste_patients/{id}")
    public String liste_patients(@PathVariable(name="id")Integer id, Model model){
        Medecin medecin = service.getMedecin(id);
        List<Patient> listePatients = service.afficherPatientByMedecin(id);
        model.addAttribute("listePatients",listePatients);
        model.addAttribute("medecin",medecin);
        return "/Vues/Medecin/liste_patients";
    }

    @GetMapping("/mes_rdv/{id}")
    public String mes_rdv(@PathVariable(name="id")Integer id, Model model){
        Medecin medecin = service.getMedecin(id);
        Iterable<RendezVous> listeRendezVous = service.afficherRendezVousByMed(id);
        model.addAttribute("listeRendezVous",listeRendezVous);
        model.addAttribute("medecin",medecin);
        return "/Vues/Medecin/mes_rdv";
    }

    @GetMapping("/adminMedecins")
    public String afficherMedecins(Model model){
        List<Medecin> listeMedecins = service.findAllMedecins();
        model.addAttribute("listeMedecins", listeMedecins);
        return "listeMedecinModifierSupprimer";
    }

    @GetMapping("/adminMedecins/new")
    public String showNewMedecinForm(Model model){
        model.addAttribute("medecin", new Medecin());
        List<Services> listeServices = serviceServices.findAllServices();
        model.addAttribute("listeServices", listeServices);
        return "ajouter";
    }

    @GetMapping("/adminMedecins/save")
    public String saveMedecin(Medecin medecin, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le medecin à été ajouté!");
        service.ajouterMedecin(medecin);
        // TO DO
        //Find clinique of that medecin and add his service to the liste of services offered by the clinique
        // And check if that service is not already in the list.
        // If in list add Else dont
        return "redirect:/medecins";
    }

    @GetMapping("/adminMedecins/edit/{id}")
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
