package com.carrental.controller;

import com.carrental.domain.Utilizator;
import com.carrental.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilizatori")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilizatorController {

    @Autowired
    private UtilizatorService utilizatorService;

    @PostMapping("/login")
    public ResponseEntity<Utilizator> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String parola = credentials.get("parola");
        Utilizator utilizator = utilizatorService.login(email, parola);
        return ResponseEntity.ok(utilizator);
    }

    @GetMapping
    public List<Utilizator> getAllUtilizatori() {
        return utilizatorService.findAll();
    }

    @PostMapping
    public ResponseEntity<Utilizator> addUtilizator(@RequestBody Utilizator utilizator) {
        Utilizator savedUtilizator = utilizatorService.saveUtilizator(utilizator);
        return ResponseEntity.ok(savedUtilizator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilizator> updateUtilizator(@PathVariable Long id, @RequestBody Utilizator utilizatorDetails) {
        Utilizator updatedUtilizator = utilizatorService.updateUtilizator(id, utilizatorDetails);
        return ResponseEntity.ok(updatedUtilizator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilizator(@PathVariable Long id) {
        utilizatorService.deleteUtilizator(id);
        return ResponseEntity.noContent().build();
    }
}
