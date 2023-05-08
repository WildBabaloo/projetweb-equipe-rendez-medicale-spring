package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.Services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PatientRepoTest {

    @Autowired
    private PatientRepository repo;
    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreatePatient(){
        /*
        Services service = new Services("Covid", "The pandemic");
        List<Services> listServices = new ArrayList<>();
        listServices.add(service);
        Clinique clinique = new Clinique("Test", "32424 yes", "yes@no.com", "514-342-2355");
        Medecin medecin = new Medecin("Healer", "Heal", 1234, "healer@heal.com", "1234", 321, new Date(),listServices,clinique);
        Patient patient = new Patient("John", "Doe", "123456", "password", "john.doe@example.com", medecin);

        Patient savedPatient = repo.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);

         */
    }

}
