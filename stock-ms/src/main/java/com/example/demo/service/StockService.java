package com.example.demo.service;

import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.exceptions.ValidacionException;

public interface StockService {
	
	public int obtenerCantidadPorProducto(Long idProducto) throws ValidacionException;

	public int obtenerCantidadPorProductoYTienda(Long idProducto, Long idTienda) throws ValidacionException;

	public void actualizarStock(ActualizarStockDTO actualizarStockDTO);
}
