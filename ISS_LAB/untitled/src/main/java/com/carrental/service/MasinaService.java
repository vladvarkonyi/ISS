package com.carrental.service;

import com.carrental.domain.Masina;
import com.carrental.repository.MasinaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasinaService {

    @Autowired
    private MasinaRepo masinaRepository;

    public List<Masina> findAll() {
        return masinaRepository.findAll();
    }

    public List<Masina> findMasiniDisponibile() {
        return masinaRepository.findByStatusDisponibilitate("Disponibil");
    }

    public Masina saveMasina(Masina masina) {
        return masinaRepository.save(masina);
    }

    public Masina updateMasina(Long id, Masina masinaDetails) {
        Masina masina = masinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Masina not found"));
        masina.setMarca(masinaDetails.getMarca());
        masina.setModel(masinaDetails.getModel());
        masina.setAn(masinaDetails.getAn());
        masina.setPretPeZi(masinaDetails.getPretPeZi());
        masina.setImagineUrl(masinaDetails.getImagineUrl());
        masina.setStatusDisponibilitate(masinaDetails.getStatusDisponibilitate());
        return masinaRepository.save(masina);
    }

    public void deleteMasina(Long id) {
        masinaRepository.deleteById(id);
    }
}
