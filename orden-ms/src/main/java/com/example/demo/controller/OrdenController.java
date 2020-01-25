package com.example.demo.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.dto.OrdenDTO;
import com.example.demo.dto.OrdenDetalleReducidoDTO;
import com.example.demo.dto.OrdenReducidaDTO;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.entidad.Orden;
import com.example.demo.entidad.OrdenDetalle;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.ValidacionException;
import com.example.demo.feign.ProductoClient;
import com.example.demo.service.FeignService;
import com.example.demo.service.OrdenService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OrdenController {
	@Autowired
	private OrdenService ordenService;
	@Autowired
	private ProductoClient productoClient;
	@Autowired
	private FeignService feignService;

	@ApiOperation(value = "Guardar una Orden de venta", 
			notes = "Para guardar una orden se verificará el stock de los almacenes de cada producto",
			response = OrdenDTO.class)
	@ApiResponses(value =  {
			@ApiResponse(code = 201, message = "Se registro correctamente la orden", 
					response = OrdenDTO.class),
			@ApiResponse(code = 404, message = "Recurso no encontrado", response = ResourceNotFoundException.class),
			@ApiResponse(code = 200, message = "Validación de negocio", response = ValidacionException.class)
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/orden/guardar")
	public OrdenDTO guardar(@Valid @RequestBody OrdenReducidaDTO ordenDTO)
			throws ValidacionException, ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BigDecimal total = new BigDecimal(0);
		Orden orden = modelMapper.map(ordenDTO, Orden.class);
		for (OrdenDetalle ordenDetalle : orden.getDetalle()) {
			//int cantidad = almacenClient.obtenerCantidadStockProducto(ordenDetalle.getIdProducto()).getCantidad();
			int cantidad = feignService.obtenerCantidadStockProducto(ordenDetalle.getIdProducto()).getCantidad();
			if (0 < cantidad && cantidad < ordenDetalle.getCantidad()) {
				throw new ValidacionException("Cantidad sobrepasa el stock actual");
			}

			ProductoDTO producto = productoClient.obtenerProductoPorId(ordenDetalle.getIdProducto());
			BigDecimal precio = producto.getPrecio();
			total = total.add(precio.multiply(new BigDecimal(cantidad)));
			ordenDetalle.setPrecio(precio);
		}
		orden.setTotal(total);
		orden.setFecha(new Date());
		Orden guardado = ordenService.guardar(orden);
		ActualizarStockDTO actualizarStockDTO = new ActualizarStockDTO();
		actualizarStockDTO.setDetalle(new ArrayList<OrdenDetalleReducidoDTO>());
		guardado.getDetalle().forEach(detalle -> {
			OrdenDetalleReducidoDTO d = new OrdenDetalleReducidoDTO(detalle.getIdProducto(), detalle.getCantidad());
			actualizarStockDTO.getDetalle().add(d);
		});
		//almacenClient.actualizarStock(actualizarStockDTO);
		feignService.actualizarStock(actualizarStockDTO);
		return modelMapper.map(guardado, OrdenDTO.class);
	}
	
	@GetMapping("/orden/listado/{fechaEnvio}")
	public List<OrdenDTO> listarPorFecha(@PathVariable String fechaEnvio) throws ParseException {
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDate = null;
		fechaDate = formato.parse(fechaEnvio);
		Iterable<Orden> ordenes = ordenService.listarOrdenesPorFecha(fechaDate);
		return StreamSupport.stream(ordenes.spliterator(), false).map(p -> mapper.map(p, OrdenDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/orden/detalle/{idProducto}")
	public List<OrdenDTO> listarOrdenDetalle(@PathVariable Long idProducto){
		ModelMapper mapper = new ModelMapper();
		Iterable<Orden> ordenes = ordenService.listarOrdenDetallePorProducto(idProducto);
		return StreamSupport.stream(ordenes.spliterator(), false).map(p -> mapper.map(p, OrdenDTO.class))
				.collect(Collectors.toList());
	}
	
	@DeleteMapping("/orden/{idOrden}")
	public String eliminarOrdenPorId(@PathVariable Long idOrden) throws ResourceNotFoundException {
		Orden orden = ordenService.borrarOrdenPorId(idOrden);
		
		ActualizarStockDTO actualizarStockDTO = new ActualizarStockDTO();
		actualizarStockDTO.setDetalle(new ArrayList<OrdenDetalleReducidoDTO>());
		orden.getDetalle().forEach(detalle -> {
			OrdenDetalleReducidoDTO ordenDetalleReducidoDTO = new OrdenDetalleReducidoDTO(detalle.getIdProducto(), detalle.getCantidad());
			actualizarStockDTO.getDetalle().add(ordenDetalleReducidoDTO);
		});
		feignService.actualizarStockOrdenBorrado(actualizarStockDTO);
		return "La orden con el Id" + idOrden + " fué eliminada";
	}
	
	@PutMapping("/orden/{idOrden}")
	public OrdenDTO actualizarFechaPorId(@RequestBody String FechaEnvio, @PathVariable Long idOrden) throws ResourceNotFoundException, ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date Fecha = formato.parse(FechaEnvio);
		ModelMapper mapper = new ModelMapper();
		return mapper.map(ordenService.actualizarFechaPorId(Fecha, idOrden), OrdenDTO.class);
	}
}
