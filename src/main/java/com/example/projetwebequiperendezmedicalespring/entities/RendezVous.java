package com.example.projetwebequiperendezmedicalespring.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rendez_vous")
public class RendezVous{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_medecin", nullable = false)
    private Medecin medecin;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_clinique", nullable = false)
    private Clinique clinique;

    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(nullable = false)
    private String raison;
    private String description;

    public RendezVous() {
    }

    public RendezVous(int id, Patient patient, Medecin medecin, Clinique clinique, Date date, String raison, String description) {
        this.id = id;
        this.patient = patient;
        this.medecin = medecin;
        this.clinique = clinique;
        this.date = date;
        this.raison = raison;
        this.description = description;
    }

    public RendezVous(Patient patient, Medecin medecin, Clinique clinique, Date date, String raison, String description) {
        this.patient = patient;
        this.medecin = medecin;
        this.clinique = clinique;
        this.date = date;
        this.raison = raison;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Clinique getClinique() {
        return clinique;
    }

    public void setClinique(Clinique clinique) {
        this.clinique = clinique;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "id=" + id +
                ", patient=" + patient +
                ", medecin=" + medecin +
                ", clinique=" + clinique +
                ", date=" + date +
                ", raison='" + raison + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
