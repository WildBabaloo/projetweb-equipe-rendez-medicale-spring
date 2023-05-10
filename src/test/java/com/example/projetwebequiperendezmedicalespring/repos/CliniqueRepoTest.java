package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.*;
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
    private RendezVousRepository repoRendezVous;

    @Autowired
    private PatientRepository repoPatient;

    @Autowired
    private MedecinRepository repoMedecin;
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
         /*
        Services service = new Services("Covid", "Oh no");
        repoService.save(service);
         */
        listeService.add(repoService.findById(1).get());
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
        List<RendezVous> rendezVousClinique = repoRendezVous.findAllByCliniqueId(1);
        for(RendezVous rendezVous : rendezVousClinique){
            repoRendezVous.deleteById(rendezVous.getId());
        }

        List<Medecin> listeMedecin = repoMedecin.findAllByCliniqueId(1);
        for (Medecin medecin: listeMedecin){
            medecin.setClinique(null);
            repoMedecin.save(medecin);
        }

        repo.deleteById(1);
    }

}
