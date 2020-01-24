package com.example.demo.service;

import com.example.demo.entidad.Producto;
import com.example.demo.exceptions.ResourceNotFoundException;

public interface ProductoService {
	
	public Iterable<Producto> obtenerProductos();
	
	public Producto obtenerProductoPorId(Long id) throws ResourceNotFoundException;
	
	public Producto guardarProducto(Producto producto);
}
