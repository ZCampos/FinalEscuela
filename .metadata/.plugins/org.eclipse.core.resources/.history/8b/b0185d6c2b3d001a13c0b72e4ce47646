package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Producto;
import com.example.demo.entidad.TipoProducto;
import com.example.demo.util.CustomRepository;

@Repository
public interface TipoProductoRepository extends CustomRepository<TipoProducto, Long>{

	public Optional<TipoProducto> findByCodigo(String codigo);
}
