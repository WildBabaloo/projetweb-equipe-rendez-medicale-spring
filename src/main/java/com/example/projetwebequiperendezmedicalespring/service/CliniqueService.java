package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.repos.CliniqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CliniqueService {

    @Autowired
    CliniqueRepository repo;

    public List<Clinique> findAllCliniques(){
        return repo.findAll();
    }

    public Clinique ajouterClinique(Clinique clinique){
        return repo.save(clinique);
    }
    public Clinique getClinique(Integer id){
        return repo.findById(id).get();
    }
    public void deleteClinique(Integer id){
        repo.deleteById(id);
    }
}
