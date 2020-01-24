package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Stock;
import com.example.demo.util.CustomRepository;

@Repository
public interface StockRepository extends CustomRepository<Stock, Long> {

	Iterable<Stock> findByCantidad(Long cantidad);
	
	Iterable<Stock> findByIdProductoOrderByCantidadDesc(Long idProducto);

	@Query("select sum(s.cantidad) from Stock s where s.idProducto = ?1")
	Optional<Integer> contarCantidadProductosStock(Long idProducto);

	@Query("select sum(s.cantidad) from Stock s where s.idProducto = ?1 and s.idTienda = ?2")
	Optional<Integer> contarCantidadProductosStockEnTienda(Long idProducto, Long idTienda);
}
