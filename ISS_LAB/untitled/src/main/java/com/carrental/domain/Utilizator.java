package com.carrental.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Table(name = "utilizatori")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tip_utilizator", discriminatorType = DiscriminatorType.STRING)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Client.class, name = "CLIENT"),
        @JsonSubTypes.Type(value = Angajat.class, name = "ANGAJAT"),
        @JsonSubTypes.Type(value = Administrator.class, name = "ADMIN")
})
public abstract class Utilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String parola;
    
    @Column(name = "rol")
    private String rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        if (rol != null && !rol.trim().isEmpty()) {
            return rol;
        }
        if (this instanceof Administrator) return "ADMIN";
        if (this instanceof Angajat) return "ANGAJAT";
        if (this instanceof Client) return "CLIENT";
        return "UNKNOWN";
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
