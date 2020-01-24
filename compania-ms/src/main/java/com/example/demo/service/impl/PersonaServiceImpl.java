package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidad.Persona;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.respository.PersonaRepository;
import com.example.demo.service.PersonaService;

@Transactional(readOnly = true)
@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Iterable<Persona> obtenerPersonas() {
		return personaRepository.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public Persona guardarPersona(Persona persona) throws Exception {
		if (personaRepository.findByDni(persona.getDni()).isPresent()) {
			throw new Exception("Ya existe una persona con el dni indicado.");
		}
		Persona nuevo = personaRepository.save(persona);
		personaRepository.refresh(nuevo);
		return nuevo;
	}

	@Override
	public Persona obtenerPersonaPorId(Long id) throws ResourceNotFoundException {
		return personaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No se encontro el id %s en la BD", id)));
	}

}
