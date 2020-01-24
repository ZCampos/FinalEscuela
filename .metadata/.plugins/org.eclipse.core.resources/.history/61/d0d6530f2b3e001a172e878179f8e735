package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidad.Compania;
import com.example.demo.entidad.Persona;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.respository.CompaniaRepository;
import com.example.demo.respository.PersonaRepository;
import com.example.demo.service.CompaniaService;

@Transactional(readOnly = true)
@Service
public class CompaniaServiceImpl implements CompaniaService {

	@Autowired
	private CompaniaRepository companiaRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Iterable<Compania> obtenerCompanias() {
		return companiaRepository.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public Compania asociarCompaniaPersona (Long idCompania, Long idPersona) throws ResourceNotFoundException {
		Persona persona = personaRepository.findById(idPersona)
				.orElseThrow(() -> new ResourceNotFoundException("No se encontro la persona con el id indicado"));
		Compania compania = companiaRepository.findById(idCompania)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro la compania con el id indicado"));
		persona.setCompania(compania);
		personaRepository.save(persona);
		return companiaRepository.findById(idCompania).get();
	}

}
