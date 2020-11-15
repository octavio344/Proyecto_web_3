package com.edu.iua.model.persistence;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.model.Chofer;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Long>{
	Optional<Chofer> findByCodigoExterno(String codigoExterno);
}
