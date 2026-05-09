package com.carrental.repository;

import com.carrental.domain.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizatorRepo extends JpaRepository<Utilizator, Long> {

    Utilizator findByEmailAndParola(String email, String parola);

}
