package com.example.projetwebequiperendezmedicalespring.entities;

import java.util.Date;

public class Medecin{
    private int id;
    private String prenom;
    private String nom;
    private int numProf;
    private String motPasse;
    private String email;
    private String numtele;
    private String specialite;
    private int tarifconsultation;
    private Date disponibilite;
    private int id_clinique;

    public Medecin() {
    }

    public Medecin(String prenom, String nom, int numProf, String motPasse, String email, String numtele, String specialite, int tarifconsultation, Date disponibilite) {
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.specialite = specialite;
        this.tarifconsultation = tarifconsultation;
        this.disponibilite = disponibilite;
    }
    public Medecin(String prenom, String nom, int numProf, String motPasse, String email, String numtele, String specialite, int tarifconsultation, Date disponibilite, int id_clinique) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.specialite = specialite;
        this.tarifconsultation = tarifconsultation;
        this.disponibilite = disponibilite;
        this.id_clinique = id_clinique;
    }
    public Medecin(int id, String prenom, String nom, int numProf, String motPasse, String email, String numtele, String specialite, int tarifconsultation, Date disponibilite) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.specialite = specialite;
        this.tarifconsultation = tarifconsultation;
        this.disponibilite = disponibilite;
    }

    public Medecin(int id, String prenom, String nom, int numProf, String motPasse, String email, String numtele, String specialite, int tarifconsultation, Date disponibilite, int id_clinique) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.specialite = specialite;
        this.tarifconsultation = tarifconsultation;
        this.disponibilite = disponibilite;
        this.id_clinique = id_clinique;
    }

    public Medecin(String prenom, String nom, int numProf, String motPasse, String email, String numtele, String specialite, int tarifconsultation, int id_clinique) {
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.specialite = specialite;
        this.tarifconsultation = tarifconsultation;
        //this.disponibilite = disponibilite;
        this.id_clinique = id_clinique;
    }

    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getNumProf() {
        return numProf;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public String getEmail() {
        return email;
    }

    public String getNumtele() {
        return numtele;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getTarifconsultation() {
        return tarifconsultation;
    }

    public Date getDisponibilite() {
        return disponibilite;
    }

    public int getId_clinique() {
        return id_clinique;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumProf(int numProf) {
        this.numProf = numProf;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtele(String numtele) {
        this.numtele = numtele;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setTarifconsultation(int tarifconsultation) {
        this.tarifconsultation = tarifconsultation;
    }

    public void setDisponibilite(Date disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setId_clinique(int id_clinique) {
        this.id_clinique = id_clinique;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", numProf=" + numProf +
                ", motPasse='" + motPasse + '\'' +
                ", email='" + email + '\'' +
                ", numtele='" + numtele + '\'' +
                ", specialite='" + specialite + '\'' +
                ", tarifconsultation=" + tarifconsultation +
                ", disponibilite=" + disponibilite +
                ", id_clinique=" + id_clinique +
                '}';
    }
}
