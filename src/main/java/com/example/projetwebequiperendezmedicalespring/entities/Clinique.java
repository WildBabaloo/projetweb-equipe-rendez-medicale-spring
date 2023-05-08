package com.example.projetwebequiperendezmedicalespring.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliniques")
public class Clinique{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 64)
    private String nom;
    @Column(nullable = false)
    private String coordonnees;
    @Column(length = 128, nullable = false, unique = true)
    private String email;
    @Column(length = 12, nullable = false, unique = true)
    private String numtele;

    @ManyToMany
    @JoinTable(
            name = "cliniques_services",
            joinColumns = @JoinColumn(name = "clinique_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Services> services_offerts = new ArrayList<>();

    public Clinique() {
    }

    public Clinique(int id, String nom, String coordonnees, String email, String numtele) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.email = email;
        this.numtele = numtele;
    }

    public Clinique(int id, String nom, String coordonnees, String email, String numtele, List<Services> services_offerts) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.email = email;
        this.numtele = numtele;
        this.services_offerts = services_offerts;
    }

    public Clinique(String nom, String coordonnees, String email, String numtele, List<Services> services_offerts) {
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.email = email;
        this.numtele = numtele;
        this.services_offerts = services_offerts;
    }

    public Clinique(String nom, String coordonnees, String email, String numtele) {
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.email = email;
        this.numtele = numtele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtele() {
        return numtele;
    }

    public void setNumtele(String numtele) {
        this.numtele = numtele;
    }

    public List<Services> getServices_offerts() {
        return services_offerts;
    }

    public void setServices_offerts(List<Services> services_offerts) {
        this.services_offerts = services_offerts;
    }

    public void ajouter(Services services){
        this.services_offerts.add(services);
    }

    @Override
    public String toString() {
        return "Clinique{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", coordonnees='" + coordonnees + '\'' +
                ", email='" + email + '\'' +
                ", numtele='" + numtele + '\'' +
                ", services_offerts=" + services_offerts +
                '}';
    }
}
