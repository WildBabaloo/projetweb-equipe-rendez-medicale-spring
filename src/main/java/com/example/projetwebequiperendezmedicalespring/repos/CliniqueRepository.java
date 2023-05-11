package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Clinique;
import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CliniqueRepository extends CrudRepository<Clinique,Integer> {

    @Query("SELECT u FROM Clinique u WHERE u.id_clinique=:id_clinique")
    public Clinique getCliniquesById(@Param("id_clinique")int id_clinique);

    @Query("SELECT u FROM Clinique u WHERE u.nom=:nom")
    public Clinique getCliniquesByNom(@Param("nom")String nom);

    @Query("SELECT u FROM Patient u JOIN Medecin v ON u.id_patient = v.id_medecin WHERE v.clinique=:clinique")
    public Patient getPatientsByClinique(@Param("clinique")int clinique);

    @Query("SELECT u FROM Medecin u WHERE u.clinique=:clinique")
    public Medecin getMedecinByClinique(@Param("clinique")int clinique);

    @Query("SELECT u FROM RendezVous u WHERE u.clinique=:clinique")
    public RendezVous getRendezVousByClinique(@Param("clinique")int clinique);
}
