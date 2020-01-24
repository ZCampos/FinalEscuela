package com.example.demo.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoReducidoDTO {

	@NotBlank
	private String nombre;
	@NotBlank
	private String codigo;
	@NotBlank
	private String descripcion;
	private BigDecimal precio;
	@JsonProperty(value = "codigo_producto")
	private String codigoProducto;
	@JsonProperty(value = "ruta_imagen")
	private String rutaImagen;
	@JsonProperty(value = "ruta_thumbnail")
	private String rutaThumbnail;
}
