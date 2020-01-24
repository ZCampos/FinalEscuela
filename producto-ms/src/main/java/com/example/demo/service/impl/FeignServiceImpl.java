package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CantidadDTO;
import com.example.demo.feign.AlmacenClient;
import com.example.demo.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class FeignServiceImpl implements FeignService {
	@Autowired
	private AlmacenClient almacenClient;

	@HystrixCommand(fallbackMethod = "obtenerCantidadDefecto", groupKey = "obtenerCantidadStockProducto", commandKey = "obtenerCantidadStockProducto", threadPoolKey = "obtenerCantidadStockProducto", 
			commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5")}, 
			threadPoolProperties = {
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "5")})
	@Override
	public CantidadDTO obtenerCantidadStockProducto(Long id) {
		return almacenClient.obtenerCantidadStockProducto(id);
	}

	public CantidadDTO obtenerCantidadDefecto(Long id) {
		return new CantidadDTO(0);
	}
}
