package com.example.demo.entidad;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidoPaterno;
	
	@Column
	private String apellidoMarterno;
	
	@Column
	private String dni;
	
	@Column
	private Date fechaIngreso;
	
	@Column
	private Boolean activo;
	
	@Column
	private Long idEmpresa;
	
}
