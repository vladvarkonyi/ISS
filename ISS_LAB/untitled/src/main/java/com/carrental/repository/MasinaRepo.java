package com.carrental.repository;

import com.carrental.domain.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MasinaRepo extends JpaRepository<Masina, Long> {
    List<Masina> findByStatusDisponibilitate(String status);
}
