package com.example.projetwebequiperendezmedicalespring.controlleur;
import com.example.projetwebequiperendezmedicalespring.entities.*;
import com.example.projetwebequiperendezmedicalespring.service.*;
import org.springframework.beans.factory.annotation.Autowired;

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
public class MedecinController {

    @Autowired
    MedecinService service;

    @Autowired
    CliniqueService cliservice;
    @Autowired
    PatientService patservice;
    @Autowired
    ServicesService serviceServices;

    @Autowired
    Message_MedecinService messMservice;

    @PostMapping("/medecinLogin")
    public String medecinLogin(Model model, @RequestParam("numProf") int numProf, @RequestParam("passwordMedecin") String password, HttpServletResponse response){
        // Check if numProf is int (FOR FUTURE ME TO DO BECAUSE I WAS SLEEPY)
        if(service.verifyMedecinLogin(numProf, password) != null){
            // Add cookies
            Medecin medecin = service.findMedecinNumProf(numProf);
            Cookie c = new Cookie("id",String.valueOf(medecin.getId()));
            c.setMaxAge(60*60);
            c.setSecure(true);
            c.setHttpOnly(true);
            c.setPath("/");
            response.addCookie(c);
            model.addAttribute("medecin",medecin);
            model.addAttribute("message", "Correct (To edit)");
            return "/Vues/Medecin/medecin_index";
        }
        model.addAttribute("message", "Wrong (To edit)");
        return "/Vues/login";
    }

    @GetMapping("/deconnexion/{id}")
    public String deconnexionMed(@PathVariable(name="id")Integer id,HttpServletResponse response){
        Medecin medecin = service.afficherMedecinById(id);
        Cookie c = new Cookie("id",String.valueOf(medecin.getId()));
        c.setMaxAge(0);
        c.setSecure(true);
        c.setHttpOnly(true);
        c.setPath("/");
        response.addCookie(c);
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

    @GetMapping("/compte_medecin/modifier/{id}")
    public String modMedPage(@PathVariable("id")Integer id,Model model){
        Medecin medecin = service.getId(id);
        List<Clinique> listeCliniques = cliservice.findAllCliniques();
        List<Services> listeServices = serviceServices.findAllServices();
        String now = LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(now, formatter);
        model.addAttribute("date",date);
        model.addAttribute("pageTitle","Editer Medecin (ID: " + id + ")");
        model.addAttribute("listeServices",listeServices);
        model.addAttribute("medecin",medecin);
        return "Vues/Medecin/modifier_medecin";
    }

    @PostMapping("/medecins/save")
    public String modifierMedecin(Medecin medecin,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Le medecin à été modifié!");
        service.ajouterMedecin(medecin);
        return "redirect:/compte_medecin/"+medecin.getId();
    }

    @GetMapping("/contacter_patient/{id}")
    public String contacter_patient(@PathVariable(name="id")Integer id,Model model,Patient patient){
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("patient",patient);
        model.addAttribute("medecin",medecin);
        return "/Vues/Medecin/contacter_patient";
    }

    @GetMapping("/contacter_patient/search/{id}")
    public String showPatient(@PathVariable(name="id")Integer id,@Param("keyword") String keyword, Model model){
        List<Patient> listePatients = service.findPatientByNom(keyword);
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("medecin",medecin);
        model.addAttribute("listePatients",listePatients);
        model.addAttribute("keyword",keyword);
        return "Vues/Medecin/contacter_results";
    }

    @GetMapping("/messager_patient/{id}")
    public String messagePatient(Model model,@PathVariable("id")Integer id){
        Medecin medecin = service.getMedecin(id);
        Patient patient = patservice.getPatient(id);

        List<Patient> listePatients = patservice.findAllPatient();
        MessageMedecin messageMedecin = new MessageMedecin();
        model.addAttribute("messageMedecin",messageMedecin);
        model.addAttribute("listePatients",listePatients);
        model.addAttribute("medecin",medecin);
        return "Vues/Medecin/messager_patient";
    }

    @PostMapping("/messageM/send/{id}")
    public String sendMessage(MessageMedecin messageMedecin,Model model,RedirectAttributes redirectAttributes){
        messMservice.ajouterMessageM(messageMedecin);
        return "redirect:/Vues/Medecin/contacter_patient";
    }
    @GetMapping("/sendMessage/{id}")
    public String sendMessagePatient(@PathVariable(name = "id") Integer id,Model model ,RedirectAttributes redirectAttributes){
        return "redirect:/Vues/Medecin/messager_patient";
    }
    @GetMapping("/contacter_results/{id}")
    public String searchResult(Model model,@PathVariable(name="id")Integer id){
        Medecin medecin = service.getMedecin(id);
        model.addAttribute("medecin",medecin);
        return "Vues/Medecin/contacter_results";
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
