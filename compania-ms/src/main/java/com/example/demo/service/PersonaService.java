package com.example.demo.service;

import com.example.demo.entidad.Persona;
import com.example.demo.exceptions.ResourceNotFoundException;

public interface PersonaService {

	public Iterable<Persona> obtenerPersonas();

	public Persona guardarPersona(Persona persona) throws Exception;

	public Persona obtenerPersonaPorId(Long id) throws ResourceNotFoundException;
}
