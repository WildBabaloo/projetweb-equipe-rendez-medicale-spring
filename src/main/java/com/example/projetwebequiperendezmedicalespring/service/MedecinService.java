package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.*;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.CliniqueRepository;
import com.example.projetwebequiperendezmedicalespring.repos.MedecinRepository;
import com.example.projetwebequiperendezmedicalespring.repos.PatientRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MedecinService {

    @Autowired
    MedecinRepository repo;

    @Autowired
    RendezVousRepository repoRendezVous;

    @Autowired
    PatientRepository repoPatient;

    @Autowired
    CliniqueRepository repoClinique;

    public List<Patient> afficherPatientByMedecin(int medecin){
        return (List<Patient>) repo.getPatientByMedecin(medecin);
    }

    public Medecin afficherMedecinById(int id){
        return repo.getMedecinById(id);
    }

    public List<RendezVous> afficherRendezVousByMed(int medecin){
        return (List<RendezVous>) repo.getRendezVousByMedId(medecin);
    }
    public List<Medecin> findAllMedecins(){
        return repo.findAll();
    }

    public Medecin ajouterMedecin(Medecin medecin){
        if(medecin.getClinique() != null){
            Clinique cliniqueMedecin = repoClinique.findById(medecin.getClinique().getId()).get();
            for (Services servicesMedecin : medecin.getServices_offerts()) {
                if (!cliniqueMedecin.getServices_offerts().contains(servicesMedecin)) {
                    System.out.println("Added service to the clinique!");
                    cliniqueMedecin.ajouter(servicesMedecin);
                }
            }

            for(Services servicesClinique : cliniqueMedecin.getServices_offerts()){
                System.out.println(servicesClinique.toString());
            }
        }
        return repo.save(medecin);
    }
    public Medecin getMedecin(Integer id){
        return repo.findById(id).get();
    }
    public void deleteMedecin(Integer id){
        // Delete all rendez-vous by his ID
        List<RendezVous> rendezVousMedecin = repoRendezVous.findAllByMedecinId(id);
        for(RendezVous rendezVous : rendezVousMedecin){
            repoRendezVous.deleteById(rendezVous.getId());
        }

        // Delete all patients associated to him
        List<Patient> listePatient = repoPatient.findAllByPatientByMedecinId(id);
        for (Patient patient : listePatient){
            patient.setMedecin(null);
            repoPatient.save(patient);
        }

        // Delete his service to the clinique if he is the only one who has it
        Medecin leMedecin = repo.findById(id).get();
        Clinique cliniqueMedecin = repoClinique.findById(leMedecin.getClinique().getId()).get();
        List<Medecin> listeCliniqueMedecins = repo.findAllByCliniqueId(cliniqueMedecin.getId());
        Set<Services> servicesToKeep = new HashSet<>();
        // Check to see if another medecin has the same service as him
        for(Medecin medecin : listeCliniqueMedecins){
            for (Services servicesMedecin : medecin.getServices_offerts()) {
                if (leMedecin.getServices_offerts().contains(servicesMedecin) && medecin.getId() != leMedecin.getId()) {
                    // Keep service because another medecin already has it other than him
                    System.out.println("Keep this service");
                    servicesToKeep.add(servicesMedecin);
                }
            }
        }

        // If services to keep size == leMedecin services size means no service to delete
        if(leMedecin.getServices_offerts().size() != servicesToKeep.size()){
            // Delete services that no other medecin other than him has
            for (Services leMedecinServices : leMedecin.getServices_offerts()){
                if(!servicesToKeep.contains(leMedecinServices)){
                    // remove the service from the clinique
                    System.out.println("DELETED!");
                    cliniqueMedecin.remove(leMedecinServices);
                }
            }
            repoClinique.save(cliniqueMedecin);
        }

        repo.deleteById(id);
    }
}
