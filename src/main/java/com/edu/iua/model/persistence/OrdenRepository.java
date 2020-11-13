package com.edu.iua.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Long> {

}
