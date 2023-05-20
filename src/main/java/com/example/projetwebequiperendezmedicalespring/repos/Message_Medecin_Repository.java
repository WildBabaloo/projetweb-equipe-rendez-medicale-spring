package com.example.projetwebequiperendezmedicalespring.repos;

import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Message_Medecin_Repository extends CrudRepository<MessageMedecin, Integer> {

    // TO DO DELETE FORIEGN KEY CONTRAINT ON DELETE MEDECIN AND PATIENT

}
