package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.dto.CantidadDTO;
import com.example.demo.exceptions.ValidacionException;
import com.example.demo.service.StockService;

@RestController
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/stock/acumulado/{idProducto}")
	public CantidadDTO obtenerCantidadStockProducto(@PathVariable("idProducto") Long idProducto)
			throws ValidacionException {
		CantidadDTO response = new CantidadDTO();
		response.setCantidad(stockService.obtenerCantidadPorProducto(idProducto));
		return response;
	}

	@GetMapping("/stock/producto/{idProducto}/tienda/{idTienda}")
	public CantidadDTO obtenerCantidadStockProductoEnTienda(@PathVariable("idProducto") Long idProducto,
			@PathVariable("idTienda") Long idTienda) throws ValidacionException {
		CantidadDTO response = new CantidadDTO();
		response.setCantidad(stockService.obtenerCantidadPorProductoYTienda(idProducto, idTienda));
		return response;
	}

	@PutMapping("/stock/actualizar")
	public void actualizarStock(@RequestBody ActualizarStockDTO actualizarStockDTO) {
		stockService.actualizarStock(actualizarStockDTO);
	}

}
