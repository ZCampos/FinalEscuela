package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdenReducidaDTO {
	private Long idCliente;
	@FutureOrPresent
	private Date fechaEnvio;
	private List<OrdenDetalleReducidoDTO> detalle;
}
