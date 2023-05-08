package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CliniqueRepoTest {

    @Autowired
    private CliniqueRepository repo;

    @Autowired
    private  ServicesRepository repoService;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateClinique(){
        List<Services> listeService = new ArrayList<>();
        Clinique clinique = new Clinique("Clinique", "234 rue des yes", "no@no.com", "514-324-4328", listeService);
        Clinique savedClinique = repo.save(clinique);
        assertThat(savedClinique).isNotNull();
        assertThat(savedClinique.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateCliniqueWithService(){
        List<Services> listeService = new ArrayList<>();
        Services service = new Services("Covid", "Oh no");
        repoService.save(service);
        listeService.add(service);
        Clinique clinique = new Clinique("ew", "324 yes street", "yes@yes.com", "413-432-2344", listeService);
        Clinique savedClinique = repo.save(clinique);
        assertThat(savedClinique).isNotNull();
        assertThat(savedClinique.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdateClinique(){
        Clinique clinique = repo.findById(1).get();
        clinique.setNom("YEP");
        repo.save(clinique);
    }

    // TO DO
    @Test
    public void testDeleteClinique(){
        repo.deleteById(1);
    }

}
