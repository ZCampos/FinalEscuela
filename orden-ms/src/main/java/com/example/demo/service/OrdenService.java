package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.OrdenReducidaDTO;
import com.example.demo.entidad.Orden;
import com.example.demo.exceptions.ResourceNotFoundException;


public interface OrdenService {
	public Orden guardar(Orden orden);

	public List<OrdenReducidaDTO> obtenerListaFecha(@PathVariable("fechaEnvio") Date fechaEnvio)throws ResourceNotFoundException ;
}
