package com.example.projetwebequiperendezmedicalespring.controlleur;

import com.example.projetwebequiperendezmedicalespring.entities.*;
import com.example.projetwebequiperendezmedicalespring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    MedecinService medservice;

    @Autowired
    CliniqueService cliservice;
    @Autowired
    Message_PatientService messPservice;
    @Autowired
    Message_MedecinService messMservice;

    @Autowired
    RendezVousService rdvservice;

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

        if(numAss.equals("root") && password.equals("root")){
            return "/Vues/Admin/admin_index";
        }
        model.addAttribute("messageFailedLogin", "Mauvais mot de passe");
        return "redirect:/login";
    }

    @GetMapping("/deconnexionPatient/{id}")
    public String deconnexionPatient(@PathVariable(name="id") Integer id, HttpServletResponse response){
        Patient patient = service.getPatient(id);
        Cookie cookie = new Cookie("id", String.valueOf(patient.getId()));
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "Vues/login";
    }

    @GetMapping("/prendre_rdv/{id_patient}")
    public String prendre_rdv(@PathVariable(name="id_patient") int id_patient, Model model) {
        //List<String> descriptions = rdvservice.getAllDescriptions();
        //List<String> raisons = rdvservice.getAllRaisons();
        Patient patient = service.getPatient(id_patient);
        List<Clinique> cliniques = cliservice.findAllCliniques();
        List<Medecin> medecins = medservice.findAllMedecins();
        //model.addAttribute("descriptions", descriptions);
        //model.addAttribute("raisons", raisons);
        model.addAttribute("patient", patient);
        model.addAttribute("cliniques", cliniques);
        model.addAttribute("medecins", medecins);
        model.addAttribute("rendezvous", new RendezVous());
        return "/Vues/Patient/prendre_rdv";
    }
    @GetMapping("/patient_compte/{id_patient}")
    public String listPatPage(@PathVariable("id_patient")int id_patient, Model model){
        //List<Patient> listePatients = service.findAllPatient();
        //model.addAttribute("listePatients",listePatients);
        Patient patient = service.getPatient(id_patient);
        model.addAttribute("patient", patient);
        return "Vues/Patient/patient_compte";
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
    @GetMapping("/modifierRDV/modifier/{id_rendezvous}")
    public String modRDVPage(@PathVariable("id_rendezvous")int id_rendezvous, Model model){
        RendezVous rendezVous = rdvservice.getId(id_rendezvous);
        Patient patient = service.getPatient(rendezVous.getPatient().getId());
        model.addAttribute("pageTitle","Editer rendez vous (ID: " + id_rendezvous + ")");
        model.addAttribute("RendezVous",rendezVous);
        model.addAttribute("patient", patient);
        return "Vues/Patient/modifierLeRdv";
    }


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




    @PostMapping("/inscription")
    public String traiterFormulaireInscription(@ModelAttribute("patient") Patient patient) {
        if (service.existsByEmail(patient.getEmail())) {
            return "Vues/creer_compte";
        }
        service.ajouterPatient(patient);

        return "redirect:/login";
    }
    @GetMapping("/inscriptionMedecin")
    public String afficherFormulaireInscriptionMedecin(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "Vues/creer_compte";
    }

    @PostMapping("/inscriptionMed")
    public String traiterFormulaireInscriptionMedecin(@ModelAttribute("medecin") Medecin medecin) {
        if (service.existsByEmail(medecin.getEmail())) {
            return "Vues/creer_compte";
        }
        medservice.ajouterMedecinInscrip(medecin);

        return "redirect:/login";
    }

    @GetMapping("/supprimer_rdv/delete/{id_rendezvous}")
    public String deleteRendezVous(@PathVariable(name = "id_rendezvous") Integer id, Model model, RedirectAttributes redirectAttributes){
        RendezVous rendezVous = rdvservice.getId(id);
        int idPatient = rendezVous.getPatient().getId();
        rdvservice.deleteRendezVous(id);
        redirectAttributes.addFlashAttribute("message", "Le rendez vous dont l'id est " +id+ " à été supprimé");
        return "redirect:/modifierRDV/" +idPatient;
    }
    //@GetMapping("/patient/new/{id_patient}")
    ///public String afficherFormulairePatient(model model) {
    // Patient patient= new Patient();
    //model.addAttribute("patient", patient);
    // return "Vues/Patient/patient_index";
    //}

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

    @GetMapping("/contacter_clinique/{id}")
    public String contacter_clinique(@PathVariable(name="id")Integer id,Model model,@Param("keyword")String keyword){
        Patient patient = service.getPatient(id);
        List<Clinique> listeCliniques = new ArrayList<>();
        model.addAttribute("listeCliniques",listeCliniques);
        model.addAttribute("patient",patient);
        return "/Vues/Patient/contacter_clinique";
    }

    @GetMapping("/rechercher/cliniques/{id}")
    public String rechercherClinique(@PathVariable(name="id")Integer id, @Param("keyword")String keyword, Model model){
        List<Clinique> listeCliniques = cliservice.rechercherClinique(keyword);
        Patient patient = service.getPatient(id);
        model.addAttribute("patient",patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listeCliniques",listeCliniques);
        return "/Vues/Patient/contacter_clinique";
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
