package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.PatientRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository repo;

    @Autowired
    private RendezVousRepository repoRendezVous;

    public List<Patient> findAllPatient(){
        return repo.findAll();
    }
    public Patient ajouterPatient(Patient patient){
        return repo.save(patient);
    }
    public Patient getPatient(Integer id){
        return repo.findById(id).get();
    }
    public void deletePatient(Integer id){
        List<RendezVous> rendezVousPatient = repoRendezVous.findAllByPatientId(id);
        for(RendezVous rendezVous : rendezVousPatient){
            repoRendezVous.deleteById(rendezVous.getId());
        }
        repo.deleteById(id);
    }
    public Patient verifyPatientLogin(String numAss, String password){
        return repo.verifyNumAssAndPassword(numAss, password);
    }
}
