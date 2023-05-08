package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MedecinRepoTest {

    @Autowired
    private MedecinRepository repo;

    @Autowired
    private CliniqueRepository repoClinique;

    @Autowired
    private  ServicesRepository repoService;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateMedecin(){
        List<Services> listeServices = new ArrayList<>();
        listeServices.add(repoService.findById(1).get());
        Medecin medecin = new Medecin("Heal", "Healer", 132, "wqeoqwe", "yes@np.com", "514-432-3213", 10, new Date(), listeServices , repoClinique.findById(1).get());
        Medecin savedMedecin = repo.save(medecin);
        assertThat(savedMedecin).isNotNull();
        assertThat(savedMedecin.getId()).isGreaterThan(0);

    }

    @Test
    public void testUpdateMedecin(){
        Medecin Medecin = repo.findById(1).get();
        Medecin.setNom("YEP");
        repo.save(Medecin);
    }

    // TO DO
    @Test
    public void testDeleteMedecin(){
        repo.deleteById(1);
    }
}
