package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RendezVousRepoTest {

    @Autowired
    private RendezVousRepository repo;

    @Autowired
    private PatientRepository repoPatient;

    @Autowired
    private MedecinRepository repoMedecin;

    @Autowired
    private CliniqueRepository repoClinique;

    @Test
    public void testCreateRendezVous(){
        RendezVous rendezVous = new RendezVous(repoPatient.findById(1).get(), repoMedecin.findById(1).get(), repoClinique.findById(1).get(), new Date(), "boo-boo", "i dont feel so good :(");
        RendezVous savedRdv = repo.save(rendezVous);
        assertThat(savedRdv).isNotNull();
        assertThat(savedRdv.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAllByPatientId(){
        List<RendezVous> rendezVousPatient = repo.findAllByPatientId(1);
        for(RendezVous rdv : rendezVousPatient){
            System.out.println(rdv.getId());
        }
        assertThat(rendezVousPatient).isNotNull();
        assertThat(rendezVousPatient.size()).isGreaterThan(0);
    }

    @Test
    public void testFindAllByMedecinId(){
        List<RendezVous> rendezVousMedecin = repo.findAllByMedecinId(1);
        for(RendezVous rdv : rendezVousMedecin){
            System.out.println(rdv.getId());
        }
        assertThat(rendezVousMedecin).isNotNull();
        assertThat(rendezVousMedecin.size()).isGreaterThan(0);
    }

    @Test
    public void testFindAllByCliniqueId(){
        List<RendezVous> rendezVousClinique = repo.findAllByMedecinId(1);
        for(RendezVous rdv : rendezVousClinique){
            System.out.println(rdv.getId());
        }
        assertThat(rendezVousClinique).isNotNull();
        assertThat(rendezVousClinique.size()).isGreaterThan(0);
    }

    @Test
    public void testDeleteAllByPatientId(){
        List<RendezVous> rendezVousPatient = repo.findAllByPatientId(1);
        for(RendezVous rdv : rendezVousPatient){
            repo.deleteById(rdv.getId());
        }
    }

    @Test
    public void testDeleteAllByMedecinId(){
        List<RendezVous> rendezVousMedecin = repo.findAllByMedecinId(1);
        for(RendezVous rdv : rendezVousMedecin){
            repo.deleteById(rdv.getId());
        }
    }

    @Test
    public void testDeleteAllByClinique(){
        List<RendezVous> rendezVousClinique = repo.findAllByCliniqueId(1);
        for(RendezVous rdv : rendezVousClinique){
            repo.deleteById(rdv.getId());
        }
    }

}
