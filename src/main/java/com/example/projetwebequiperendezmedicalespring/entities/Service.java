package com.example.projetwebequiperendezmedicalespring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private String description;

    public Service(){
    }

    public Service(Integer id) {
        this.id = id;
    }

    public Service(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Service(Integer id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
