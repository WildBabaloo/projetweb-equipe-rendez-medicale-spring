package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PatientRepoTest {

    @Autowired
    private PatientRepository repo;

    @Autowired
    private MedecinRepository repoMedecin;

    @Autowired
    private CliniqueRepository repoClinique;

    @Autowired
    private  ServicesRepository repoService;

    @Autowired
    private RendezVousRepository repoRendezVous;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Message_Medecin_Repository repoMessageMedecin;

    @Autowired
    private Message_Patient_Repository repoMessagePatient;


    @Test
    public void testCreatePatient(){
        Patient patient = new Patient("John", "Doe", "12343", "password", "joh.does@example.com", repoMedecin.findById(1).get());

        Patient savedPatient = repo.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);


    }

    @Test
    public void testCreatePatientNoMedecin(){
        Patient patient = new Patient("John", "Doe", "12345678", "password", "john.doer@example.com", null);
        Patient savedPatient = repo.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdatePatient(){
        Patient patient = repo.findById(1).get();
        patient.setNom("YEP");
        repo.save(patient);
    }

    @Test
    public void testDeletePatient(){
        int id = 6;

        List<MessagePatient> messagePatients = repoMessagePatient.findAllByPatientId(id);
        for(MessagePatient messagePatient : messagePatients){
            repoMessagePatient.deleteById(messagePatient.getId_message());
        }

        List<MessageMedecin> messageMedecins = repoMessageMedecin.findAllByPatientId(id);
        for(MessageMedecin messageMedecin : messageMedecins){
            System.out.println(messageMedecin.getMessage());
            repoMessageMedecin.deleteById(messageMedecin.getId_message());
        }

        List<RendezVous> rendezVousPatient = repoRendezVous.findAllByPatientId(id);
        for(RendezVous rendezVous : rendezVousPatient){
            repoRendezVous.deleteById(rendezVous.getId());
        }
        Patient patient = repo.findById(id).get();
        System.out.println(patient.getNom());
        repo.deleteById(id);

    }

    @Test
    public void testFindByIdPatient(){
        Patient patient = repo.findById(2).get();
        System.out.println(patient.getNom());
    }


    @Test
    public void testVerifyLoginPatient(){
        String numAss = "12343";
        String password = "password";
        Patient patient = repo.verifyNumAssAndPassword(numAss, password);
        System.out.println(patient.toString());
        assertThat(patient).isNotNull();

    }

}
