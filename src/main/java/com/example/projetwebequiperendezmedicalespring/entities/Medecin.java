package com.example.projetwebequiperendezmedicalespring.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medecins")
public class Medecin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_medecin;
    @Column(length = 64, nullable = false)
    private String prenom;
    @Column(length = 64, nullable = false)
    private String nom;
    @Column(length = 12, nullable = false, unique = true)
    private int numProf;
    @Column(length = 64, nullable = false)
    private String motPasse;
    @Column(length = 128, nullable = false, unique = true)
    private String email;
    @Column(length = 12, nullable = false, unique = true)
    private String numtele;
    @Column(length = 5, nullable = false)
    private int tarifconsultation;
    @Temporal(TemporalType.DATE)
    private Date disponibilite;
    @ManyToMany
    @JoinTable(
            name = "medecins_services",
            joinColumns = @JoinColumn(name = "medecin_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Services> services_offerts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "clinique_id", nullable = false)
    private Clinique clinique;


    public Medecin() {
    }

    public Medecin(int id_medecin, String prenom, String nom, int numProf, String motPasse, String email, String numtele, int tarifconsultation, Date disponibilite, Clinique clinique) {
        this.id_medecin = id_medecin;
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.tarifconsultation = tarifconsultation;
        this.disponibilite = disponibilite;
        this.clinique = clinique;
    }

    public Medecin(int id_medecin, String prenom, String nom, int numProf, String motPasse, String email, String numtele, int tarifconsultation, Date disponibilite, List<Services> services_offerts, Clinique clinique) {
        this.id_medecin = id_medecin;
        this.prenom = prenom;
        this.nom = nom;
        this.numProf = numProf;
        this.motPasse = motPasse;
        this.email = email;
        this.numtele = numtele;
        this.tarifconsultation = tarifconsultation;
        this.disponibilite = disponibilite;
        this.services_offerts = services_offerts;
        this.clinique = clinique;
    }

    public int getId() {
        return id_medecin;
    }

    public void setId(int id_medecin) {
        this.id_medecin = id_medecin;
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

    public int getNumProf() {
        return numProf;
    }

    public void setNumProf(int numProf) {
        this.numProf = numProf;
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

    public String getNumtele() {
        return numtele;
    }

    public void setNumtele(String numtele) {
        this.numtele = numtele;
    }

    public int getTarifconsultation() {
        return tarifconsultation;
    }

    public void setTarifconsultation(int tarifconsultation) {
        this.tarifconsultation = tarifconsultation;
    }

    public Date getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Date disponibilite) {
        this.disponibilite = disponibilite;
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

    public Clinique getClinique() {
        return clinique;
    }

    public void setClinique(Clinique clinique) {
        this.clinique = clinique;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id_medecin=" + id_medecin +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", numProf=" + numProf +
                ", motPasse='" + motPasse + '\'' +
                ", email='" + email + '\'' +
                ", numtele='" + numtele + '\'' +
                ", tarifconsultation=" + tarifconsultation +
                ", disponibilite=" + disponibilite +
                ", services_offerts=" + services_offerts +
                ", clinique=" + clinique +
                '}';
    }
}
