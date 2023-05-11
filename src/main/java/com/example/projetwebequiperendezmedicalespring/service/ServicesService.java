package com.example.projetwebequiperendezmedicalespring.service;

import com.example.projetwebequiperendezmedicalespring.entities.Services;
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

    public List<Services> findAllServices(){
        return service.findAll();
    }
}
