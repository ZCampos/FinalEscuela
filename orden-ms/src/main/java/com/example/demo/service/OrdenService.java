package com.example.demo.service;

import java.util.Date;
import com.example.demo.entidad.Orden;
import com.example.demo.exceptions.ResourceNotFoundException;


public interface OrdenService {
	public Orden guardar(Orden orden);

	public Iterable<Orden> listarOrdenesPorFecha(Date fechaEnvio);

	public Iterable<Orden> listarOrdenDetallePorProducto(Long idProducto);
	
	public Orden borrarOrdenPorId(Long idOrden) throws ResourceNotFoundException;

	public Orden actualizarFechaPorId(Date fecha, Long idOrden) throws ResourceNotFoundException;
	
}
