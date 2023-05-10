package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.CliniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CliniqueService {

    @Autowired
    CliniqueRepository repo;

    public List<Clinique> afficherCliniqueById(int id_clinique){
        return (List<Clinique>) repo.getCliniquesById(id_clinique);
    }

    public List<Clinique> afficherCliniqueByNom(String nom){
        return (List<Clinique>) repo.getCliniquesByNom(nom);
    }
    public List<Patient> afficherPatientsByClinique(int clinique){
        return (List<Patient>) repo.getPatientsByClinique(clinique);
    }

    public List<Medecin> afficherMedecinsByClinique(int clinique){
        return (List<Medecin>) repo.getMedecinByClinique(clinique);
    }

    public List<RendezVous> afficherRendezVousByClinique(int clinique){
        return (List<RendezVous>) repo.getRendezVousByClinique(clinique);
    }

    public Clinique get(int id) throws CliniqueNotFoundException{
        try{
            return repo.findById(id).get();
        } catch (NoSuchElementException exception){
            throw new CliniqueNotFoundException("On ne peut pas trouver un clinique avec l'id: "+id);
        }
    }
}
