package com.example.projetwebequiperendezmedicalespring.entities;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_patient;
    @Column(length = 64, nullable = false)
    private String prenom;
    @Column(length = 64, nullable = false)
    private String nom;
    @Column(length = 25, nullable = false, unique = true)
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

    public Patient(int id_patient, String prenom, String nom, String numAss, String motPasse, String email) {
        this.id_patient = id_patient;
        this.prenom = prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.email = email;
    }

    public Patient(int id_patient, String prenom, String nom, String numAss, String motPasse, String email, Medecin medecin) {
        this.id_patient = id_patient;
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
        return id_patient;
    }

    public void setId(int id_patient) {
        this.id_patient = id_patient;
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
                "id_patient=" + id_patient +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", numAss='" + numAss + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", email='" + email + '\'' +
                ", medecin=" + medecin +
                '}';
    }
}
