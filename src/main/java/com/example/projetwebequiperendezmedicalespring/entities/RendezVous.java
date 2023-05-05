package com.example.projetwebequiperendezmedicalespring.entities;

import java.util.Date;

/**
 *
 * @author Ligtas
 */
public class RendezVous{
    private int id;
    private int id_patient;
    private int id_medecin;
    private int id_clinique;
    private Date date;
    private String raison;
    private String description;

    public RendezVous() {
    }

    public RendezVous(int id, int id_patient, int id_medecin, int id_clinique, Date date, String raison, String description) {
        this.id = id;
        this.id_patient = id_patient;
        this.id_medecin = id_medecin;
        this.id_clinique = id_clinique;
        this.date = date;
        this.raison = raison;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getRaison() {
        return raison;
    }

    public int getId() {
        return id;
    }

    public int getId_patient() {
        return id_patient;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public int getId_clinique() {
        return id_clinique;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public void setId_clinique(int id_clinique) {
        this.id_clinique = id_clinique;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", id_patient=" + id_patient + ", id_medecin=" + id_medecin + ", id_clinique=" + id_clinique + ", date=" + date + ", raison=" + raison + "description=" + description +'}';
    }




}
