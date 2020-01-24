package com.example.demo.service;

import com.example.demo.entidad.Compania;
import com.example.demo.exceptions.ResourceNotFoundException;

public interface CompaniaService {

	public Iterable<Compania> obtenerCompanias();
	
	public Compania asociarCompaniaPersona(Long idCompania, Long idPersona) throws ResourceNotFoundException;
}
