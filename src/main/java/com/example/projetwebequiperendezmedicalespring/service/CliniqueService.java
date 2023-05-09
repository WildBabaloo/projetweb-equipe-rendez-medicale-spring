package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.CliniqueRepository;
import com.example.projetwebequiperendezmedicalespring.repos.MedecinRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CliniqueService {

    @Autowired
    CliniqueRepository repo;

    @Autowired
    RendezVousRepository repoRendezVous;

    @Autowired
    MedecinRepository repoMedecin;

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
}
