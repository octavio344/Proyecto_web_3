package com.edu.iua.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.iua.model.Cisterna;

@Repository
public interface CisternaRepository extends JpaRepository<Cisterna, Long>{
	Optional<Cisterna> findByCodigoExterno(String codigoExterno);
}
