package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
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
}
