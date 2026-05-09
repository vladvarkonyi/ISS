package com.carrental.controller;

import com.carrental.domain.Masina;
import com.carrental.service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/masini")
@CrossOrigin(origins = "http://localhost:4200")
public class MasinaController {

    @Autowired
    private MasinaService masinaService;

    @GetMapping
    public List<Masina> getAllMasini() {
        return masinaService.findAll();
    }

    @GetMapping("/disponibile")
    public List<Masina> getMasiniDisponibile() {
        return masinaService.findMasiniDisponibile();
    }

    @PostMapping
    public ResponseEntity<Masina> addMasina(@RequestBody Masina masina) {
        Masina masinaSalvata = masinaService.saveMasina(masina);
        return ResponseEntity.ok(masinaSalvata);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Masina> updateMasina(@PathVariable Long id, @RequestBody Masina masinaDetails) {
        Masina updatedMasina = masinaService.updateMasina(id, masinaDetails);
        return ResponseEntity.ok(updatedMasina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasina(@PathVariable Long id) {
        masinaService.deleteMasina(id);
        return ResponseEntity.noContent().build();
    }
}
