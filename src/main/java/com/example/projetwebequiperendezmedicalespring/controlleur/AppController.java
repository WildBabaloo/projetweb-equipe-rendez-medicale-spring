package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.service.CliniqueService;
import com.example.projetwebequiperendezmedicalespring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    CliniqueService cliservice;
    @Autowired
    PatientService patservice;
    @GetMapping("/")
    public String homePage(){return "index";}

    @GetMapping("/login")
    public String logPage(Model model){
        List<Clinique> listeCliniques = cliservice.findAllCliniques();
        model.addAttribute("listeCliniques",listeCliniques);
        List<Patient> listePatients = patservice.findAllPatient();
        model.addAttribute("listePatients", listePatients);
        return "Vues/login";
    }

    @GetMapping("/creer")
    public String creerCompte(){return "Vues/creer_compte";}

    @GetMapping("/presentation")
    public String presentation(){
        return "Vues/presentation";
    }

    @GetMapping("admin_index")
    public String admin_index(){return "Vues/Admin/admin_index";}

    @GetMapping("/creer_compte")
    public String page_creer_compte(Model model){
        model.addAttribute("patient", new Patient());
        model.addAttribute("medecin", new Medecin());
        return "Vues/creer_compte";
    }
}
