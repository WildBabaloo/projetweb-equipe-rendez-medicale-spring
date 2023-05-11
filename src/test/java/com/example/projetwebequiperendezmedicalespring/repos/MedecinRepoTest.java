package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.*;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.*;

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
    private RendezVousRepository repoRendezVous;

    @Autowired
    private PatientRepository repoPatient;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateMedecin(){
        List<Services> listeServices = new ArrayList<>();
        listeServices.add(repoService.findById(1).get());
        Clinique cliniqueMedecin = repoClinique.findById(2).get();
        Medecin medecin = new Medecin("Heal", "Healer", 13242, "wqeoqwe", "yesss@n0.com", "514-432-3210", 10, new Date(), listeServices , cliniqueMedecin);
        Medecin savedMedecin = repo.save(medecin);
        for (Services servicesMedecin : listeServices) {
            if (!cliniqueMedecin.getServices_offerts().contains(servicesMedecin)) {
                System.out.println("Added!");
                cliniqueMedecin.ajouter(servicesMedecin);
            }
        }

        for(Services servicesClinique : cliniqueMedecin.getServices_offerts()){
            System.out.println(servicesClinique.toString());
        }
        assertThat(savedMedecin).isNotNull();
        assertThat(savedMedecin.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateMedecinNoClinique(){
        List<Services> listeServices = new ArrayList<>();
        /*
        Services service = new Services("Covid", "Oh no");
        repoService.save(service);

         */
        listeServices.add(repoService.findById(1).get());
        Medecin medecin = new Medecin("Heal", "Healer", 132, "wqeoqwe", "yes@np.com", "514-432-3213", 10, new Date(), listeServices , null);
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
        int id = 12;
        List<RendezVous> rendezVousMedecin = repoRendezVous.findAllByMedecinId(id);
        for(RendezVous rendezVous : rendezVousMedecin){
            repoRendezVous.deleteById(rendezVous.getId());
        }

        List<Patient> listePatient = repoPatient.findAllByPatientByMedecinId(id);
        for (Patient patient : listePatient){
            patient.setMedecin(null);
            repoPatient.save(patient);
        }

        Medecin leMedecin = repo.findById(id).get();
        Clinique cliniqueMedecin = repoClinique.findById(leMedecin.getClinique().getId()).get();
        List<Medecin> listeCliniqueMedecins = repo.findAllByCliniqueId(cliniqueMedecin.getId());
        Set<Services> servicesToKeep = new HashSet<>();
        // Check to see if another medecin has the same service as him
        for(Medecin medecin : listeCliniqueMedecins){
            for (Services servicesMedecin : medecin.getServices_offerts()) {
                if (leMedecin.getServices_offerts().contains(servicesMedecin) && medecin.getId() != leMedecin.getId()) {
                    // Keep service because another medecin already has it other than him
                    System.out.println("Keep this service");
                    servicesToKeep.add(servicesMedecin);
                }
            }
        }

        // If services to keep size == leMedecin services size means no service to delete
        if(leMedecin.getServices_offerts().size() != servicesToKeep.size()){
            // Delete services that no other medecin other than him has
            for (Services leMedecinServices : leMedecin.getServices_offerts()){
                if(!servicesToKeep.contains(leMedecinServices)){
                    // remove the service from the clinique
                    System.out.println("DELETED!");
                    cliniqueMedecin.remove(leMedecinServices);
                }
            }
            repoClinique.save(cliniqueMedecin);
        }
        repo.deleteById(id);
    }

    @Test
    public void testVerifyLoginMedecin(){
        int numProf = 132;
        String password = "wqeoqwe";
        Medecin medecin = repo.verifyNumProfAndPasswordMedecin(numProf, password);
        System.out.println(medecin.toString());
        assertThat(medecin).isNotNull();
    }
}
