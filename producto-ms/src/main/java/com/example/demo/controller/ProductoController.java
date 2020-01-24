package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CantidadDTO;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.ProductoReducidoDTO;
import com.example.demo.entidad.ImagenProducto;
import com.example.demo.entidad.Producto;
import com.example.demo.entidad.TipoProducto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.ValidacionException;
import com.example.demo.service.FeignService;
import com.example.demo.service.ProductoService;

@RestController
public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@Autowired
	private FeignService feignService;

	@GetMapping("/productos")
	public List<ProductoDTO> obtenerProductos() {
		ModelMapper mapper = new ModelMapper();
		Iterable<Producto> productos = productoService.obtenerProductos();
		return StreamSupport.stream(productos.spliterator(), false).map(p -> mapper.map(p, ProductoDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/productos/{id}")
	public ProductoDTO obtenerProductoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
		ModelMapper mapper = new ModelMapper();
		ProductoDTO producto = mapper.map(productoService.obtenerProductoPorId(id), ProductoDTO.class);
		CantidadDTO cantidadDTO = feignService.obtenerCantidadStockProducto(id);
		producto.setCantidadStock(cantidadDTO.getCantidad());
		return producto;
	}

	@PostMapping("/productos")
	public ProductoDTO guardarProducto(@Valid @RequestBody ProductoReducidoDTO productoReducidoDTO)
			throws ValidacionException, ResourceNotFoundException {
		ModelMapper mapper = new ModelMapper();
		Producto producto = mapper.map(productoReducidoDTO, Producto.class);

		TipoProducto tipoProducto = new TipoProducto();
		tipoProducto.setCodigo(productoReducidoDTO.getCodigoProducto());

		ImagenProducto imagenProducto = new ImagenProducto();
		imagenProducto.setRutaImagen(productoReducidoDTO.getRutaImagen());
		imagenProducto.setRutaThumbnail(productoReducidoDTO.getRutaThumbnail());

		producto.setTipoProducto(tipoProducto);
		producto.setImagenProducto(imagenProducto);

		return mapper.map(productoService.guardarProducto(producto), ProductoDTO.class);
	}

}
