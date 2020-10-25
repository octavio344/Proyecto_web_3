package com.edu.iua.tpintegrador.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.tpintegrador.model.Chofer;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Long>{
	
}
