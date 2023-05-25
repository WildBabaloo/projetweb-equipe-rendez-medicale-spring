package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.repos.Message_Medecin_Repository;
import com.example.projetwebequiperendezmedicalespring.repos.Message_Patient_Repository;
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

    @Autowired
    private Message_Medecin_Repository repoMessageMedecin;

    @Autowired
    private Message_Patient_Repository repoMessagePatient;

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
        List<MessagePatient> messagePatients = repoMessagePatient.findAllByPatientId(id);
        for(MessagePatient messagePatient : messagePatients){
            repoMessagePatient.deleteById(messagePatient.getId_message());
        }

        List<MessageMedecin> messageMedecins = repoMessageMedecin.findAllByPatientId(id);
        for(MessageMedecin messageMedecin : messageMedecins){
            System.out.println(messageMedecin.getMessage());
            repoMessageMedecin.deleteById(messageMedecin.getId_message());
        }

        repo.deleteById(id);

        List<RendezVous> rendezVousPatient = repoRendezVous.findAllByPatientId(id);
        for(RendezVous rendezVous : rendezVousPatient){
            repoRendezVous.deleteById(rendezVous.getId());
        }
    }

    public Patient findPatientNumAss(String numAss){
        return repo.getPatientByNumAss(numAss);
    }

    public Patient verifyPatientLogin(String numAss, String password){
        return repo.verifyNumAssAndPassword(numAss, password);
    }
}
