package com.example.demo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {
	private Long id;
	private String nombre;
	private String codigo;
	private String descripcion;
	private BigDecimal precio;
	@JsonProperty(value = "tipo_producto")
	private TipoProductoDTO tipoProducto;
	@JsonProperty(value = "imagen_producto")
	private ImagenProductoDTO imagenProducto;
	private Boolean activo;
	@JsonProperty(value = "cantidad_stock")
	private int cantidadStock = 0;
}
