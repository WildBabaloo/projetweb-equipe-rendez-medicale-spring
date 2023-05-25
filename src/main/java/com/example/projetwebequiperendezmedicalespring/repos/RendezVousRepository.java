package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {
    @Query("SELECT r from RendezVous r JOIN r.patient p where p.id_patient = ?1")
    public List<RendezVous> findAllByPatientId(int id);

    @Query("SELECT r from RendezVous r JOIN r.medecin m where m.id_medecin = ?1")
    public List<RendezVous> findAllByMedecinId(int id);

    @Query("SELECT r from RendezVous r JOIN r.clinique c where c.id_clinique = ?1")
    public List<RendezVous> findAllByCliniqueId(int id);

    @Query("SELECT DISTINCT r.description FROM RendezVous r")
    List<String> findAllDescriptions();


    @Query("SELECT DISTINCT r.raison FROM RendezVous r")
    List<String> findAllRaisons();




}
