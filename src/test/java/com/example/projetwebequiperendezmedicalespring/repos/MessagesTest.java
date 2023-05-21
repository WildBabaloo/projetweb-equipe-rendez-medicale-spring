package com.example.projetwebequiperendezmedicalespring.repos;


import com.example.projetwebequiperendezmedicalespring.entities.MessageMedecin;
import com.example.projetwebequiperendezmedicalespring.entities.MessagePatient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MessagesTest {

    @Autowired
    private Message_Medecin_Repository repoMessageMedecin;

    @Autowired
    private Message_Patient_Repository repoMessagePatient;

    @Test
    public void testFindAllMessageMedecin(){
        List<MessageMedecin> messageMedecins = repoMessageMedecin.findAll();
        System.out.println(messageMedecins.size());
        for(MessageMedecin messageMedecin : messageMedecins){
            System.out.println(messageMedecin.getPatientReceiver());
        }
    }

    @Test
    public void testFindAllByMessagePatientId(){
        int id = 1;
        List<MessagePatient> messagePatients = repoMessagePatient.findAllByPatientId(id);
        System.out.println(messagePatients.size());
        for(MessagePatient messagePatient : messagePatients){
            System.out.println(messagePatient.getMessage());
        }
    }

}
