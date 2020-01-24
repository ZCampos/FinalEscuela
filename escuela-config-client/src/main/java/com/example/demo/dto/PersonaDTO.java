package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO {
	private Long id;
	private String nombre;
	@JsonProperty(value = "apellido_paterno")
	private String apellido1;
	@JsonProperty(value = "apellido_materno")
	private String apellido2;
	private String dni;
	private CompaniaReducidaDTO compania;
}
