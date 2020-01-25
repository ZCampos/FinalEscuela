package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.entidad.OrdenDetalle;
import com.example.demo.util.CustomRepository;

@Repository
public interface OrdenDetalleRepository extends CustomRepository<OrdenDetalle, Long>{

//	@Query( "SELECT d.Orden FROM OrdenDetalle d WHERE d.idProducto = ?1")
//	List<Orden> listaDeOrdenes(Long idProducto);
	
}
