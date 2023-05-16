package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.CliniqueRepository;
import com.example.projetwebequiperendezmedicalespring.repos.MedecinRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
//import jakarta.transaction.Transactional;
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

    @Autowired
    RendezVousRepository repoRendezVous;

    @Autowired
    MedecinRepository repoMedecin;

    public List<Clinique> afficherCliniqueById(int id_clinique){
        return (List<Clinique>) repo.getCliniquesById(id_clinique);
    }
    public List<Clinique> findAllCliniques(){
        return repo.findAll();
    }
    public List<Clinique> afficherCliniqueByNom(String nom){
        return (List<Clinique>) repo.getCliniquesByNom(nom);
    }
    public List<Patient> afficherPatientsByClinique(int id){
        return (List<Patient>) repo.getPatientsByClinique(id);
    }

    public boolean isEmailUnique(String emailClinique){
        Clinique clinique = repo.getCliniquesByEmail(emailClinique);
        if(clinique == null)return true;
        return false;
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
    public Clinique ajouterClinique(Clinique clinique){
        return repo.save(clinique);
    }
    public Clinique getClinique(Integer id){
        return repo.findById(id).get();
    }
    public void deleteClinique(Integer id){
        List<RendezVous> rendezVousClinique = repoRendezVous.findAllByCliniqueId(1);
        for(RendezVous rendezVous : rendezVousClinique){
            repoRendezVous.deleteById(rendezVous.getId());
        }

        List<Medecin> listeMedecin = repoMedecin.findAllByCliniqueId(1);
        for (Medecin medecin: listeMedecin){
            medecin.setClinique(null);
            repoMedecin.save(medecin);
        }

        repo.deleteById(id);
    }

    public Clinique getId(int id){
        return repo.findById(id).get();
    }
}
