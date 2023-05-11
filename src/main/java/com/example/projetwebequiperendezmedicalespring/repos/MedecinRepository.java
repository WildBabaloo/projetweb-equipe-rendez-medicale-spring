package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MedecinRepository extends JpaRepository<Medecin,Integer> {

    @Query("SELECT u FROM Patient u WHERE u.medecin= :medecin")
    public Patient getPatientByMedecin(@Param("medecin")int medecin);

    @Query("SELECT u FROM Medecin u WHERE u.nom= :nom")
    public Medecin getMedecinByNom(@Param("nom")String nom);

    @Query("SELECT u FROM Medecin u WHERE u.id_medecin= :id_medecin")
    public Medecin getMedecinById(@Param("id_medecin")int id_medecin);

    @Query("SELECT u FROM RendezVous u WHERE u.medecin= :medecin")
    public RendezVous getRendezVousByMedId(@Param("medecin")int medecin);

    @Query("SELECT m from Medecin m JOIN m.clinique c where c.id_clinique = ?1")
    public List<Medecin> findAllByCliniqueId(int id);

    @Query("SELECT m from Medecin m WHERE m.numProf=:numProf and m.motPasse=:motPasse")
    public Medecin verifyNumProfAndPasswordMedecin(@Param("numProf") int numProf, @Param("motPasse") String motPasse);
}
