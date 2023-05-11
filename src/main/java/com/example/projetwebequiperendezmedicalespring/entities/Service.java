package com.example.projetwebequiperendezmedicalespring.entities;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_services;

    @Column(length = 64, nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private String description;

    public Service(){
    }

    public Service(Integer id_service) {
        this.id_services = id_services;
    }

    public Service(Integer id_services, String nom) {
        this.id_services = id_services;
        this.nom = nom;
    }

    public Service(Integer id_services, String nom, String description) {
        this.id_services = id_services;
        this.nom = nom;
        this.description = description;
    }

    public Integer getId() {
        return id_services;
    }

    public void setId(Integer id_services) {
        this.id_services = id_services;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id_services=" + id_services +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
