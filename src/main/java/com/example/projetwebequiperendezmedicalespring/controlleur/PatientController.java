package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.service.PatientNotFoundException;
import com.example.projetwebequiperendezmedicalespring.service.PatientService;
import com.example.projetwebequiperendezmedicalespring.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.repository.query.Param;
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
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    RendezVousService rdvservice;

    @PostMapping("/patientLogin")
    public String patientLogin(Model model, @RequestParam("numAss") String numAss, @RequestParam("passwordPatient") String password){
        if(service.verifyPatientLogin(numAss, password) != null){
            // Add cookies
            model.addAttribute("message", "Correct (To edit)");
            return "/Vues/Patient/patient_index";
        }
        model.addAttribute("message", "Wrong (To edit)");
        return "/Vues/login";
    }

    @GetMapping("/prendre_rdv")
    public String prendre_rdv(){return "/Vues/Patient/prendre_rdv";}

    @GetMapping("/patient_index/{id_patient}")
    public String cliPage(@PathVariable("id_patient")int id_patient,Model model, HttpServletResponse response){
        Patient patient = service.getPatient(id_patient);
        Cookie c = new Cookie("id_patient",String.valueOf(id_patient));
        c.setMaxAge(60*60);
        c.setSecure(true);
        c.setHttpOnly(true);
        c.setPath("/");
        response.addCookie(c);
        model.addAttribute("patient",patient);
        return "Vues/Patient/patient_index";
    }
    @GetMapping("/patient_compte/{id_patient}")
    public String listPatPage(@PathVariable("id_patient")int id_patient, Model model){
        List<Patient> listePatients = service.findAllPatient();
        model.addAttribute("listePatients",listePatients);
        return "Vues/Clinique/liste_patients";
    }

    @GetMapping("/modifierRDV/{id_patient}")
    public String afficherRendezVous(@PathVariable("id_patient")int id_patient, Model model){
        Patient patient = service.getPatient(id_patient);
        List<RendezVous> listeRendezVous = service.afficherRendezVousByPatient(id_patient);
        model.addAttribute("patient",patient);
        model.addAttribute("listeRendezVous",listeRendezVous);
        return "Vues/Patient/modifier_rdv";
    }

    @PostMapping("/rendezvous/save")
    public String saveRendezVous(RendezVous rendezVous, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le rendez vous à été ajouté!");
        rdvservice.ajouterRendezvous(rendezVous);
        return "redirect:/modifierRDV/" +rendezVous.getPatient().getId();
    }
    @GetMapping("/modifierRDV/modifier/{id_patient}")
    public String modRDVPage(@PathVariable("id_patient")int id_patient, Model model){
        RendezVous rendezVous = rdvservice.getId(id_patient);
        model.addAttribute("pageTitle","Editer rendez vous (ID: " + id_patient + ")");
        model.addAttribute("RendezVous",rendezVous);
        return "Vues/Patient/modifierLeRdv";
    }

    @GetMapping("/supprimer_rdv/delete/{id_rendezvous}")
    public String deleteRendezVous(@PathVariable(name = "id_rendezvous") Integer id, Model model, RedirectAttributes redirectAttributes){
        rdvservice.deleteRendezVous(id);
        redirectAttributes.addFlashAttribute("message", "Le rendez vous dont l'id est " +id+ " à été supprimé");
        return "redirect:Vues/Patient/modifierLeRdv";
    }
    @GetMapping("/contacter_medecin")
    public String contacter_medecin(){return "/Vues/Patient/contacter_medecin";}

    @GetMapping("/contacter_clinique")
    public String contacter_clinique(){return "/Vues/Patient/contacter_clinique";}

    @GetMapping("/adminPatients")
    public String afficherPatients(Model model){
        List<Patient> listePatients = service.findAllPatient();
        model.addAttribute("listePatients", listePatients);
        return "/Vues/Admin/listePatientModifierSupprimer";
    }

    @GetMapping("/adminPatients/new")
    public String showNewPatientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "ajouter";
    }

    @GetMapping("/adminPatients/save")
    public String savePatient(Patient patient, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le patient à été ajouté!");
        service.ajouterPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/adminPatients/edit/{id}")
    public String updatePatient(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model){
        Patient patient = service.getPatient(id);
        model.addAttribute("pageTitle", "Editer le patient dont l'id est " +id);
        model.addAttribute("patient", patient);
        // Have to add optionPatient if not possible then make a seperate page for add a patient only
        return "ajouter";
    }

    @GetMapping("/adminPatients/delete/{id}")
    public String deletePatient(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        service.deletePatient(id);
        redirectAttributes.addFlashAttribute("message", "Le patient dont l'id est " +id+ " à été supprimé");
        return "redirect:/adminPatients";
    }

}
