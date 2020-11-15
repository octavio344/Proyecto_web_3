package com.edu.iua.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{
	Optional<Producto> findByCodigoExterno(String codigoExterno);
}
