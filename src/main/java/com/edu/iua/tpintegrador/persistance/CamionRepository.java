package com.edu.iua.tpintegrador.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.tpintegrador.model.Camion;

@Repository
public interface CamionRepository extends JpaRepository<Camion, String> {
	
}
