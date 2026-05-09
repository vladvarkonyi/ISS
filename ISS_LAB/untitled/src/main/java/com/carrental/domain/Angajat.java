package com.carrental.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ANGAJAT")
public class Angajat extends Utilizator {
    private String pozitie;
    private String dataAngajarii;

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public String getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(String dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

}