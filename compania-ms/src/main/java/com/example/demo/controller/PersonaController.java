package com.example.demo.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.dto.PersonaReducidaDTO;
import com.example.demo.entidad.Persona;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.service.PersonaService;

@RestController
@RefreshScope
public class PersonaController {

	@Value("${igv}")
	private String igv;

	@GetMapping("/igv")
	public String getIgv() {
		return "El igv actual es: " + this.igv;
	}

	@Autowired
	private PersonaService personaService;

	@GetMapping("/personas")
	public Iterable<Persona> obtenerPersonas() {
		Iterable<Persona> data = personaService.obtenerPersonas();
		return data;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/personas")
	public PersonaDTO guardarPersona(@Valid @RequestBody PersonaReducidaDTO persona) throws Exception {
		ModelMapper mapper = new ModelMapper();
		Persona nuevo = personaService.guardarPersona(mapper.map(persona, Persona.class));
		return mapper.map(nuevo, PersonaDTO.class);
	}

	@GetMapping("/personas/{id}")
	public PersonaDTO obtenerPersonaPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(personaService.obtenerPersonaPorId(id), PersonaDTO.class);
	}

}
