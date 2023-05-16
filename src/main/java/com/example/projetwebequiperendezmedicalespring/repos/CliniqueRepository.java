package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CliniqueRepository extends JpaRepository<Clinique,Integer> {

    @Query("SELECT u FROM Clinique u WHERE u.id_clinique=:id_clinique")
    public Clinique getCliniquesById(@Param("id_clinique")int id_clinique);

    @Query("SELECT u FROM Clinique u WHERE u.nom=:nom")
    public Clinique getCliniquesByNom(@Param("nom")String nom);

    @Query("SELECT u FROM Patient u  JOIN Medecin v ON u.id_patient = v.id_medecin WHERE v.clinique.id_clinique= ?1")
    public List<Patient> getPatientsByClinique(int id_clinique);

    @Query("SELECT u FROM Clinique u WHERE u.email = :email")
    public Clinique getCliniquesByEmail(@Param("email")String emailClinique);
    @Query("SELECT u FROM Medecin u WHERE u.clinique.id_clinique=:clinique")
    public List<Medecin> getMedecinByClinique(@Param("clinique")int clinique);

    @Query("SELECT u FROM RendezVous u WHERE u.clinique.id_clinique=:clinique")
    public List<RendezVous> getRendezVousByClinique(@Param("clinique")int clinique);



}
