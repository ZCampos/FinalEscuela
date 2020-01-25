package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.dto.CantidadDTO;

@FeignClient("almacen-ms")
public interface AlmacenClient {

	@GetMapping("/stock/acumulado/{idProducto}")
	public CantidadDTO obtenerCantidadStockProducto(@PathVariable("idProducto") Long idProducto);

	@PutMapping("/stock/actualizar")
	public void actualizarStock(@RequestBody ActualizarStockDTO actualizarStockDTO);
	
	@PutMapping("/stock/actualizar2")
	public void actualizarStockOrdenBorrado(@RequestBody ActualizarStockDTO actualizarStockDTO);
}
