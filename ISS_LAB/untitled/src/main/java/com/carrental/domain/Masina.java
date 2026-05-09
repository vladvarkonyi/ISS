package com.carrental.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "masini")
public class Masina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String model;
    private int an;
    private double pretPeZi;
    private String statusDisponibilitate;
    private String imagineUrl;

    public Masina(Long id, String marca, String model, int an, double pretPeZi, String statusDisponibilitate) {
        this.id = id;
        this.marca = marca;
        this.model = model;
        this.an = an;
        this.pretPeZi = pretPeZi;
        this.statusDisponibilitate = statusDisponibilitate;

    }

    public Masina() {

    }

    public Long getId() {
        return id;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public double getPretPeZi() {
        return pretPeZi;
    }

    public void setPretPeZi(double pretPeZi) {
        this.pretPeZi = pretPeZi;
    }

    public String getStatusDisponibilitate() {
        return statusDisponibilitate;
    }

    public void setStatusDisponibilitate(String statusDisponibilitate) {
        this.statusDisponibilitate = statusDisponibilitate;
    }

    public String getImagineUrl() {
        return imagineUrl;
    }

    public void setImagineUrl(String imagineUrl) {
        this.imagineUrl = imagineUrl;
    }
}