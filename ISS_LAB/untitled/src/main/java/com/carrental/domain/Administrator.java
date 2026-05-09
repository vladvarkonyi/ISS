package com.carrental.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends Utilizator {


}