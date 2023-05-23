package com.example.projetwebequiperendezmedicalespring.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rendezvous")
public class RendezVous{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rendezvous;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_medecin", nullable = false)
    private Medecin medecin;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_clinique", nullable = false)
    private Clinique clinique;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(nullable = false)
    private String raison;
    private String description;

    public RendezVous() {
    }

    public RendezVous(int id_rendezvous, Patient patient, Medecin medecin, Clinique clinique, Date date, String raison, String description) {
        this.id_rendezvous = id_rendezvous;
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
        return id_rendezvous;
    }

    public void setId(int id_rendezvous) {
        this.id_rendezvous = id_rendezvous;
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
                "id_rendezvous" + id_rendezvous +
                ", patient=" + patient +
                ", medecin=" + medecin +
                ", clinique=" + clinique +
                ", date=" + date +
                ", raison='" + raison + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
