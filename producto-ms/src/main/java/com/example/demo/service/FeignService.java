package com.example.demo.service;

import com.example.demo.dto.CantidadDTO;

public interface FeignService {
	public CantidadDTO obtenerCantidadStockProducto(Long id);
}
