package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.MedecinRepository;
import com.example.projetwebequiperendezmedicalespring.repos.PatientRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedecinService {

    @Autowired
    MedecinRepository repo;

    @Autowired
    RendezVousRepository repoRendezVous;

    @Autowired
    PatientRepository repoPatient;

    public List<Medecin> findAllMedecins(){
        return repo.findAll();
    }

    public Medecin ajouterMedecin(Medecin medecin){
        return repo.save(medecin);
    }
    public Medecin getMedecin(Integer id){
        return repo.findById(id).get();
    }
    public void deleteMedecin(Integer id){
        List<RendezVous> rendezVousMedecin = repoRendezVous.findAllByMedecinId(1);
        for(RendezVous rendezVous : rendezVousMedecin){
            repoRendezVous.deleteById(rendezVous.getId());
        }

        List<Patient> listePatient = repoPatient.findAllByPatientByMedecinId(1);
        for (Patient patient : listePatient){
            patient.setMedecin(null);
            repoPatient.save(patient);
        }
        repo.deleteById(id);
    }
}
