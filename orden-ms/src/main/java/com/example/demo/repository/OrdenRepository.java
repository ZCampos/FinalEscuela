package com.example.demo.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.OrdenReducidaDTO;
import com.example.demo.entidad.Orden;
import com.example.demo.util.CustomRepository;

@Repository
public interface OrdenRepository extends CustomRepository<Orden, Long> {
	
	@Query("SELECT id FROM Orden o where o.fechaEnvio >= ?1")
	
	List<OrdenReducidaDTO> findByDate(Date fechaEnvio);
	
}
