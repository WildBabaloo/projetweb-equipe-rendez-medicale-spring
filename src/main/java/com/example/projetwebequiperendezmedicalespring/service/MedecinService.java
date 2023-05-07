package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.repos.MedecinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedecinService {

    @Autowired
    MedecinRepository repo;

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
