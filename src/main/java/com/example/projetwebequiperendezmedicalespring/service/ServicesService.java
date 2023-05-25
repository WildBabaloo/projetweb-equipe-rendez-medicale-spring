package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Patient;
import com.example.projetwebequiperendezmedicalespring.entities.RendezVous;
import com.example.projetwebequiperendezmedicalespring.entities.Services;
import com.example.projetwebequiperendezmedicalespring.repos.PatientRepository;
import com.example.projetwebequiperendezmedicalespring.repos.RendezVousRepository;
import com.example.projetwebequiperendezmedicalespring.repos.ServicesRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicesService {

    @Autowired
    ServicesRepository service;

    @Autowired
    RendezVousRepository repo;

    public List<Services> findAllServices(){
        return service.findAll();
    }

    public RendezVous ajouterRendezvous(RendezVous rendezVous){
        return repo.save(rendezVous);
    }
}
