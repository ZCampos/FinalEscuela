package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrdenReducidaDTO;
import com.example.demo.entidad.Orden;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.OrdenRepository;
import com.example.demo.service.OrdenService;

@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepository;

	@Override
	public Orden guardar(Orden orden) {
		return ordenRepository.save(orden);
	}

	@Override
	public List<OrdenReducidaDTO> obtenerListaFecha(Date fechaEnvio) throws ResourceNotFoundException {
		return ordenRepository.findByDate(fechaEnvio);
		
	}

}
