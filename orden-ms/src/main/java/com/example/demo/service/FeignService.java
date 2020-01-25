package com.example.demo.service;

import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.dto.CantidadDTO;

public interface FeignService {
	
	public CantidadDTO obtenerCantidadStockProducto(Long id);
	
	public void actualizarStock(ActualizarStockDTO actualizarStockDTO);
	
	public void actualizarStockOrdenBorrado(ActualizarStockDTO actualizarStockDTO);
}
