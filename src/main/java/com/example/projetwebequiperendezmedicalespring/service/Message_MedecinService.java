package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.repos.Message_Medecin_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Message_MedecinService {
    @Autowired
    private Message_Medecin_Repository repo;

    public MessageMedecin ajouterMessageM(MessageMedecin messageMedecin){
        return repo.save(messageMedecin);
    }

    public List<MessageMedecin> findMessagesByPat(int id){
        return (List<MessageMedecin>) repo.findAllByPatientId(id);
    }
    public List<MessageMedecin> findMessagesByMed(int id){
        return (List<MessageMedecin>) repo.findAllByMedecinId(id);
    }
    public List<MessageMedecin> afficherMessagesM(){
        return (List<MessageMedecin>) repo.findAll();
    }
}
