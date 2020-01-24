package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.dto.CantidadDTO;
import com.example.demo.exceptions.ValidacionException;

@FeignClient("almacen-ms")
public interface AlmacenClient {

	@GetMapping("/stock/acumulado/{idProducto}")
	public CantidadDTO obtenerCantidadStockProducto(@PathVariable("idProducto") Long idProducto)
			throws ValidacionException;

	@PutMapping("/stock/actualizar")
	public void actualizarStock(@RequestBody ActualizarStockDTO actualizarStockDTO);
}
