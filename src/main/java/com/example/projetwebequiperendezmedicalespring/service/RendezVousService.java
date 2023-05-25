package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.PatientRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class RendezVousService {
    @Autowired
    private RendezVousRepository repo;
    @Autowired
    private PatientRepository repoPat;
    public List<RendezVous> afficherRendezVous(){
        return repo.findAll();
    }
    public RendezVous getId(int id){
        return repo.findById(id).get();
    }

    public RendezVous ajouterRendezvous(RendezVous rendezVous){
        return repo.save(rendezVous);
    }

    public void deleteRendezVous(Integer id){repo.deleteById(id);}

    public List<String> getAllDescriptions() {
        return repo.findAllDescriptions();
    }

    public List<String> getAllRaisons() {
        return repo.findAllRaisons();
    }


    public void deleteRendezVousByPatientId(Integer id){
        List<RendezVous> rendezVousPatient = repo.findAllByPatientId(id);
        for(RendezVous rendezVous : rendezVousPatient){
            repo.deleteById(rendezVous.getId());
        }
        repo.deleteById(id);
    }




}
