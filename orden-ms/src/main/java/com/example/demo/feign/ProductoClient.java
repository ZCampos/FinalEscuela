package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.exceptions.ResourceNotFoundException;

@FeignClient("producto-ms")
public interface ProductoClient {

	@GetMapping("/productos/{id}")
	public ProductoDTO obtenerProductoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException;
}
