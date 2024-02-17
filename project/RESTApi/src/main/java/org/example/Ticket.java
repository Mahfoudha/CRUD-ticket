package org.example;

import java.util.Objects;

public class Ticket {
    private int id;
    private String nomFilm;
    private String date;
    private String heure;
    private String salle;
    private double prix;

    public Ticket() {}

    public Ticket(String nomFilm, String date, String heure, String salle, double prix) {
        this.nomFilm = nomFilm;
        this.date = date;
        this.heure = heure;
        this.salle = salle;
        this.prix = prix;
    }

    public Ticket(int id, String nomFilm, String date, String heure, String salle, double prix) {
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFilm() {
        return nomFilm;
    }

    public void setNomFilm(String nomFilm) {
        this.nomFilm = nomFilm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Double.compare(ticket.prix, prix) == 0 &&
                Objects.equals(nomFilm, ticket.nomFilm) &&
                Objects.equals(date, ticket.date) &&
                Objects.equals(heure, ticket.heure) &&
                Objects.equals(salle, ticket.salle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomFilm, date, heure, salle, prix);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", nomFilm='" + nomFilm + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", salle='" + salle + '\'' +
                ", prix=" + prix +
                '}';
    }
}
