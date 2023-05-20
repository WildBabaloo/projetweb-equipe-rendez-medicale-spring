package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message_Patient_Repository extends JpaRepository<MessagePatient, Integer> {

    @Query("SELECT m from MessagePatient m JOIN m.patientSender p where p.id_patient = ?1")
    public List<MessagePatient> findAllByPatientId(int id);

    @Query("SELECT m from MessagePatient m JOIN m.medecinReceiver r where r.id_medecin = ?1")
    public List<MessageMedecin> findAllByMedecinId(int id);
}
