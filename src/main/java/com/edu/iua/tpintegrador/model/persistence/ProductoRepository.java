package com.edu.iua.tpintegrador.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.tpintegrador.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{

}
