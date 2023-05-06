package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService service;

    @GetMapping("/patients")
    public String afficherPatients(Model model){
        List<Patient> listePatients = service.findAllPatient();
        model.addAttribute("listePatients", listePatients);
        return "listePatientModifierSupprimer";
    }

    @GetMapping("/patients/new")
    public String showNewPatientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "ajouter";
    }

    @GetMapping("/patients/save")
    public String savePatient(Patient patient, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le patient à été ajouté!");
        service.ajouterPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/edit/{id}")
    public String updatePatient(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model){
        Patient patient = service.getPatient(id);
        model.addAttribute("pageTitle", "Editer le patient dont l'id est " +id);
        model.addAttribute("patient", patient);
        // Have to add optionPatient if not possible then make a seperate page for add a patient only
        return "ajouter";
    }

    @GetMapping("patients/delete/{id}")
    public String deletePatient(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        service.deletePatient(id);
        redirectAttributes.addFlashAttribute("message", "L'utilisateur dont l'id est " +id+ " à été supprimé");
        return "redirect:/patients";
    }

}
