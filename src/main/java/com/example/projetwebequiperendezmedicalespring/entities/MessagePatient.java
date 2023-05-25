package com.example.projetwebequiperendezmedicalespring.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message_patient")
public class MessagePatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_message;

    @Column(nullable = false)
    private String sujet;

    @Column(length = 10000, nullable = false)
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column
    private String document;

    @OneToOne
    @JoinColumn(name = "patient_id_patient")
    private Patient patientSender;

    @OneToOne
    @JoinColumn(name = "medecin_id_medecin")
    private Medecin medecinReceiver;

    public MessagePatient() {

    }

    public MessagePatient(Integer id_message, String sujet, String message, Date date, String document, Patient patientSender, Medecin medecinReceiver) {
        this.id_message = id_message;
        this.sujet = sujet;
        this.message = message;
        this.date = date;
        this.document = document;
        this.patientSender = patientSender;
        this.medecinReceiver = medecinReceiver;
    }

    public Integer getId_message() {
        return id_message;
    }

    public void setId_message(Integer id_message) {
        this.id_message = id_message;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Patient getPatientSender() {
        return patientSender;
    }

    public void setPatientSender(Patient patientSender) {
        this.patientSender = patientSender;
    }

    public Medecin getMedecinReceiver() {
        return medecinReceiver;
    }

    public void setMedecinReceiver(Medecin medecinReceiver) {
        this.medecinReceiver = medecinReceiver;
    }

    @Override
    public String toString() {
        return "MessageMedecin{" +
                "id_message=" + id_message +
                ", sujet='" + sujet + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", document='" + document + '\'' +
                ", patientSender=" + patientSender +
                ", medecinReceiver=" + medecinReceiver +
                '}';
    }
}
