package com.edu.iua.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.model.Camion;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
	Optional<Camion> findByCodigoExterno(String codigoExterno);
}
