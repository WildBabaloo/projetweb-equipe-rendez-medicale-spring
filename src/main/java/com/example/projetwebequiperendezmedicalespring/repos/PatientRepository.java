package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p from Patient p JOIN p.medecin m where m.id_medecin = ?1")
    public List<Patient> findAllByPatientByMedecinId(int id);

    @Query("SELECT p from Patient p WHERE p.numAss = :numAss and p.motPasse = :password")
    public Patient verifyNumAssAndPassword(@Param("numAss") String numAss,@Param("password") String password);

    @Query("SELECT u FROM RendezVous u WHERE u.patient.id_patient=:patient")
    public List<RendezVous> getRendezVousByPatient(@Param("patient")int patient);

    @Query("SELECT r FROM RendezVous r JOIN r.patient c where c.id_patient = ?1")
    public List<RendezVous> findAll( String keyword);

}
