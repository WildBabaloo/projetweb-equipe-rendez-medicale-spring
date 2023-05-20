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
        List<RendezVous> rendezVousPatient = repoRendezVous.findAllByPatientId(1);
        for(RendezVous rendezVous : rendezVousPatient){
            repoRendezVous.deleteById(rendezVous.getId());
        }
        repo.deleteById(5);
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
