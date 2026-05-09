package com.carrental.service;

import com.carrental.domain.Utilizator;
import com.carrental.repository.UtilizatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizatorService {
    @Autowired
    private UtilizatorRepo utilizatorRepository;

    public Utilizator login(String email, String parola) {
        Utilizator utilizator = utilizatorRepository.findByEmailAndParola(email, parola);
        if (utilizator == null) {
            throw new RuntimeException("Invalid email or password");
        }
        return utilizator;
    }

    public List<Utilizator> findAll() {
        return utilizatorRepository.findAll();
    }

    public Utilizator saveUtilizator(Utilizator utilizator) {
        return utilizatorRepository.save(utilizator);
    }

    public Utilizator updateUtilizator(Long id, Utilizator utilizatorDetails) {
        Utilizator utilizator = utilizatorRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilizator not found"));
        utilizator.setEmail(utilizatorDetails.getEmail());
        utilizator.setParola(utilizatorDetails.getParola());
        utilizator.setRol(utilizatorDetails.getRol());
        return utilizatorRepository.save(utilizator);
    }

    public void deleteUtilizator(Long id) {
        utilizatorRepository.deleteById(id);
    }
}
