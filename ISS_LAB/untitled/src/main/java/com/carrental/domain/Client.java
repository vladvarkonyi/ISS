package com.carrental.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Utilizator {
    private String nume;
    private String cnp;
    private String permisConducere;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPermisConducere() {
        return permisConducere;
    }

    public void setPermisConducere(String permisConducere) {
        this.permisConducere = permisConducere;
    }
}
