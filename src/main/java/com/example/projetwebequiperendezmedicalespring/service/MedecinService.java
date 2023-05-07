package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MedecinService {

    @Autowired
    MedecinRepository repo;

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
        return repo.save(medecin);
    }
    public Medecin getMedecin(Integer id){
        return repo.findById(id).get();
    }
    public void deleteMedecin(Integer id){
        repo.deleteById(id);
    }
}
