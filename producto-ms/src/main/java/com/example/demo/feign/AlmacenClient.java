package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CantidadDTO;

@FeignClient("almacen-ms")
public interface AlmacenClient {

	@GetMapping("/stock/acumulado/{idProducto}")
	public CantidadDTO obtenerCantidadStockProducto(@PathVariable("idProducto") Long idProducto);
}
