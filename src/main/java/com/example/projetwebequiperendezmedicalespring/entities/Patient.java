package com.example.projetwebequiperendezmedicalespring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 64, nullable = false)
    private String prenom;
    @Column(length = 64, nullable = false)
    private String nom;
    @Column(length = 12, nullable = false, unique = true)
    private String numAss;
    @Column(length = 64, nullable = false)
    private String motPasse;
    @Column(length = 128, nullable = false, unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    public Patient() {
    }

    public Patient(int id, String prenom, String nom, String numAss, String motPasse, String email) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.email = email;
    }

    public Patient(int id, String prenom, String nom, String numAss, String motPasse, String email, Medecin medecin) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.email = email;
        this.medecin = medecin;
    }

    public Patient(String prenom, String nom, String numAss, String motPasse, String email, Medecin medecin) {
        this.prenom = prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.email = email;
        this.medecin = medecin;
    }

    public Patient(String prenom, String nom, String numAss, String motPasse, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumAss() {
        return numAss;
    }

    public void setNumAss(String numAss) {
        this.numAss = numAss;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", numAss='" + numAss + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", email='" + email + '\'' +
                ", medecin=" + medecin +
                '}';
    }
}
