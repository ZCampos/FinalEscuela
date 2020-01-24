package com.example.demo.entidad;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long idCliente;
	@Column
	private Date fecha;
	@Column
	private Date fechaEnvio;
	@Column
	private BigDecimal total;

	@OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
	private List<OrdenDetalle> detalle;

}
