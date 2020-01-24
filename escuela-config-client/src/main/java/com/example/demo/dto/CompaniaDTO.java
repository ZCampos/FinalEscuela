package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompaniaDTO {
	private Long id;
	private String nombre;
	private String ruc;
	@JsonProperty(value = "razon_social")
	private String razonSocial;
	private List<PersonaReducidaDTO> personas;
	@JsonProperty(value = "nro_empleados")
	private int nroEmpleados;
}
