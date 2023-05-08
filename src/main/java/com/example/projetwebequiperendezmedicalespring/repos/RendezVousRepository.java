package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {
    @Query("SELECT r from RendezVous r JOIN r.patient p where p.id = ?1")
    public List<RendezVous> findAllByPatientId(int id);

    @Query("SELECT r from RendezVous r JOIN r.medecin m where m.id = ?1")
    public List<RendezVous> findAllByMedecinId(int id);

    @Query("SELECT r from RendezVous r JOIN r.clinique c where c.id = ?1")
    public List<RendezVous> findAllByCliniqueId(int id);

}
