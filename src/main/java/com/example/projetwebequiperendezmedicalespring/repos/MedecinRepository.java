package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.Medecin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MedecinRepository extends CrudRepository<Medecin,Integer> {

//    @Query("SELECT u FROM Patient u WHERE u.patient_has_medecin.patient_id_patient")
}
