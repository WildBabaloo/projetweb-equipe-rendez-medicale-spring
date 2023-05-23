package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import com.example.projetwebequiperendezmedicalespring.repos.Message_Patient_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Message_PatientService {
    @Autowired
    private Message_Patient_Repository repo;

    public MessagePatient ajouterMessageP(MessagePatient messagePatient){
        return  repo.save(messagePatient);
    }

    public List<MessagePatient> afficherMessagesP(){
        return  (List<MessagePatient>) repo.findAll();
    }
}
