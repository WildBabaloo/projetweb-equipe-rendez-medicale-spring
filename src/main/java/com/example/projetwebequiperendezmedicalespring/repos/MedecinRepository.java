package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MedecinRepository extends JpaRepository<Medecin,Integer> {

    @Query("SELECT p from Patient p JOIN p.medecin m where m.id_medecin = ?1")
    public List<Patient> getPatientsByMedecin(@Param("medecin")int id);

    @Query("SELECT u FROM Patient u WHERE u.nom LIKE %?1% OR u.prenom  LIKE %?1%")
    public List<Patient> findAllByNom(String keyword);

    @Query("SELECT u FROM Medecin u WHERE u.nom= :nom")
    public Medecin getMedecinByNom(@Param("nom")String nom);
    @Query("SELECT u FROM Medecin u WHERE u.numProf= :numProf")
    public Medecin getMedecinByNumProf(@Param("numProf")int numProf);
    @Query("SELECT u FROM Medecin u WHERE u.id_medecin= :id_medecin")
    public Medecin getMedecinById(@Param("id_medecin")int id_medecin);
    @Query("SELECT r from RendezVous r JOIN r.medecin m where m.id_medecin = ?1")
    public List<RendezVous> getRendezVousByMedId(@Param("medecin")int medecin);

    @Query("SELECT m from Medecin m JOIN m.clinique c where c.id_clinique = ?1")
    public List<Medecin> findAllByCliniqueId(int id);

    @Query("SELECT m from Medecin m WHERE m.numProf=:numProf and m.motPasse=:motPasse")
    public Medecin verifyNumProfAndPasswordMedecin(@Param("numProf") int numProf, @Param("motPasse") String motPasse);
}
