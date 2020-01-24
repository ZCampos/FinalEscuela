package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CompaniaDTO;
import com.example.demo.dto.CompaniaReducidaDTO;
import com.example.demo.entidad.Compania;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.service.CompaniaService;

@RestController
public class CompaniaController {

	@Autowired
	private CompaniaService companiaService;

	@GetMapping("/companias")
	public List<CompaniaDTO> obtenerCompanias() {
		ModelMapper modelMapper = new ModelMapper();
		return StreamSupport.stream(companiaService.obtenerCompanias().spliterator(), false)
				.map(c -> modelMapper.map(c, CompaniaDTO.class)).collect(Collectors.toList());
	}

	@PutMapping("/compania/{idCompania}/persona/{idPersona}")
	public CompaniaDTO asociarCompaniaPersona(@PathVariable("idCompania") Long idCompania,
			@PathVariable("idPersona") Long idPersona) throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		Compania compania = companiaService.asociarCompaniaPersona(idCompania, idPersona);
		CompaniaDTO response = modelMapper.map(compania, CompaniaDTO.class);
		return response;
	}

	@PutMapping("/compania/{idCompania}")
	public CompaniaDTO actualizarCompani(@PathVariable("idCompania") Long idCompania,
			@RequestBody CompaniaReducidaDTO companiaReduDto) {
		return null;
	}

}
