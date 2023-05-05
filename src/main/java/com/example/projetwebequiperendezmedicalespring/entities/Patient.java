package com.example.projetwebequiperendezmedicalespring.entities;

public class Patient {
    private int id;
    private String prenom;
    private String nom;
    private String numAss;
    private String motPasse;
    private int id_clinique;

    public Patient() {
    }

    public Patient(String prenom ,String nom, String numAss, String motPasse) {
        this.prenom=prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
    }

    public Patient(String prenom , String nom, String numAss, String motPasse, int id_clinique) {
        this.prenom=prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.id_clinique = id_clinique;
    }

    public Patient(int id,String prenom ,String nom, String numAss, String motPasse) {
        this.id = id;
        this.prenom=prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
    }

    public Patient(int id,String prenom , String nom, String numAss, String motPasse, int id_clinique) {
        this.id = id;
        this.prenom=prenom;
        this.nom = nom;
        this.numAss = numAss;
        this.motPasse = motPasse;
        this.id_clinique = id_clinique;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNumAss() {
        return numAss;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public int getId_clinique() {
        return id_clinique;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumAss(String numAss) {
        this.numAss = numAss;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public void setId_clinique(int id_clinique) {
        this.id_clinique = id_clinique;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", numAss=" + numAss + ", motPasse=" + motPasse + ", id_clinique=" + id_clinique + '}';
    }



}
