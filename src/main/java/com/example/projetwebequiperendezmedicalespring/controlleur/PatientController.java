package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.service.MedecinService;
import com.example.projetwebequiperendezmedicalespring.service.Message_MedecinService;
import com.example.projetwebequiperendezmedicalespring.service.Message_PatientService;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    MedecinService medservice;

    @Autowired
    Message_PatientService messPservice;
    @Autowired
    Message_MedecinService messMservice;
    @PostMapping("/patientLogin")
    public String patientLogin(Model model, @RequestParam("numAss") String numAss, @RequestParam("passwordPatient") String password, HttpServletResponse response){
        if(service.verifyPatientLogin(numAss, password) != null){
            // Add cookies
            Patient patient = service.findPatientNumAss(numAss);
            Cookie c = new Cookie("id",String.valueOf(patient.getId()));
            c.setMaxAge(60*60);
            c.setSecure(true);
            c.setHttpOnly(true);
            c.setPath("/");
            response.addCookie(c);
            model.addAttribute("patient",patient);
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




    @GetMapping("/patient_compte")
    public String patient_compte(){return "/Vues/Patient/patient_compte";}

    @GetMapping("/modifier_rdv")
    public String modifier_rdv(){return "/Vues/Patient/modifier_rdv";}

    @GetMapping("/mes_messagesP/{id}")
    public String messagePat(@PathVariable("id")Integer id,Model model){
        Patient patient = service.getPatient(id);
        List<MessagePatient> listeMessagesP = messPservice.findMessagesByPat(id);
        List<MessageMedecin> listeMessagesM = messMservice.findMessagesByPat(id);
        model.addAttribute("patient",patient);
        model.addAttribute("listeMessagesP",listeMessagesP);
        model.addAttribute("listeMessagesM",listeMessagesM);
        return "Vues/Patient/mes_messages";
    }

    @GetMapping("/contacter_medecin/{id}")
    public String contacter_medecin(@PathVariable(name="id")Integer id,Model model){
        Patient patient = service.getPatient(id);
        String now = LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateNow = LocalDate.parse(now,formatter);
        List<Patient> listePatients = service.findAllPatient();
        List<Medecin> listeMedecins = medservice.findAllMedecins();
        MessagePatient messagePatient = new MessagePatient();
        model.addAttribute("dateNow",dateNow);
        model.addAttribute("messagePatient",messagePatient);
        model.addAttribute("listePatients",listePatients);
        model.addAttribute("listeMedecins",listeMedecins);
        model.addAttribute("patient",patient);
        return "/Vues/Patient/contacter_medecin";
    }

    @PostMapping("/messageP/send/{id}")
    public String sendMessage(@PathVariable(name = "id") Integer id, MessagePatient messagePatient, Model model, RedirectAttributes redirectAttributes){
        messPservice.ajouterMessageP(messagePatient);
        Patient patient = service.getPatient(id);
        model.addAttribute("patient",patient);
        return "/Vues/Patient/patient_index";
    }

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
        return "/Vues/Admin/ajouterModifierPatient";
    }

    @PostMapping("/adminPatients/save")
    public String savePatient(Patient patient, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le patient à été ajouté!");
        service.ajouterPatient(patient);
        return "redirect:/adminPatients";
    }

    @GetMapping("/adminPatients/edit/{id}")
    public String updatePatient(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model){
        Patient patient = service.getPatient(id);
        model.addAttribute("pageTitle", "Editer le patient dont l'id est " +id);
        model.addAttribute("patient", patient);
        // Have to add optionPatient if not possible then make a seperate page for add a patient only
        return "/Vues/Admin/ajouterModifierPatient";
    }

    @GetMapping("/adminPatients/delete/{id}")
    public String deletePatient(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        service.deletePatient(id);
        redirectAttributes.addFlashAttribute("message", "Le patient dont l'id est " +id+ " à été supprimé");
        return "redirect:/adminPatients";
    }

}
