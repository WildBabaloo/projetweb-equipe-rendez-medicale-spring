package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.service.PatientService;
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

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService service;

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

    @GetMapping("/patient_index")
    public String patient_index(){return "/Vues/Patient/patient_index";}

    @GetMapping("/patient_compte")
    public String patient_compte(){return "/Vues/Patient/patient_compte";}

    @GetMapping("/modifier_rdv")
    public String modifier_rdv(){return "/Vues/Patient/modifier_rdv";}

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
        return "redirect:/patients";
    }

}
