package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidad.Producto;
import com.example.demo.entidad.TipoProducto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.TipoProductoRepository;
import com.example.demo.service.ProductoService;

@Transactional(readOnly = true)
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private TipoProductoRepository tipoProductoRepository;

	@Override
	public Iterable<Producto> obtenerProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Producto obtenerProductoPorId(Long id) throws ResourceNotFoundException {
		return productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no registrado en la bd"));
	}

	@Transactional(readOnly = false)
	@Override
	public Producto guardarProducto(Producto producto) {
		TipoProducto tipoProducto = tipoProductoRepository.findByCodigo(producto.getTipoProducto().getCodigo()).get();
		producto.setTipoProducto(tipoProducto);
		return productoRepository.save(producto);
	}

}
