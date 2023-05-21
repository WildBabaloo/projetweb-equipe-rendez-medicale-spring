package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message_Medecin_Repository extends JpaRepository<MessageMedecin, Integer> {

    @Query("SELECT m from MessageMedecin m JOIN m.medecinSender s where s.id_medecin = ?1")
    public List<MessageMedecin> findAllByMedecinId(int id);

    @Query("SELECT m from MessageMedecin m JOIN m.patientReceiver p where p.id_patient = ?1")
    public List<MessageMedecin> findAllByPatientId(int id);

}
