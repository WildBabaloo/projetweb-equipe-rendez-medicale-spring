package com.example.projetwebequiperendezmedicalespring.entities;


public class Clinique{
    private int id;
    private String nom;
    private String coordonnees;
    private String services_offerts;
    private String email;
    private String numtele;

    public Clinique() {
    }

    public Clinique(String nom, String coordonnees, String services_offerts, String email, String numtele) {
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.services_offerts = services_offerts;
        this.email = email;
        this.numtele = numtele;
    }

    public Clinique(int id, String nom, String coordonnees, String services_offerts, String email, String numtele) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.services_offerts = services_offerts;
        this.email = email;
        this.numtele = numtele;
    }

    @Override
    public String toString() {
        return "Clinique{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", coordonnees='" + coordonnees + '\'' +
                ", services_offerts='" + services_offerts + '\'' +
                ", email='" + email + '\'' +
                ", numtele='" + numtele + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public void setServices_offerts(String services_offerts) {
        this.services_offerts = services_offerts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtele(String numtele) {
        this.numtele = numtele;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public String getServices_offerts() {
        return services_offerts;
    }

    public String getEmail() {
        return email;
    }

    public String getNumtele() {
        return numtele;
    }
}
