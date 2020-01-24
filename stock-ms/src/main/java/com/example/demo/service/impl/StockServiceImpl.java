package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ActualizarStockDTO;
import com.example.demo.entidad.Stock;
import com.example.demo.exceptions.ValidacionException;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;

@Transactional(readOnly = true)
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	@Override
	public int obtenerCantidadPorProducto(Long idProducto) throws ValidacionException {
		return stockRepository.contarCantidadProductosStock(idProducto)
				.orElseThrow(() -> new ValidacionException("No se encontro el Producto en la tabla stock"));
	}

	@Override
	public int obtenerCantidadPorProductoYTienda(Long idProducto, Long idTienda) throws ValidacionException {
		return stockRepository.contarCantidadProductosStockEnTienda(idProducto, idTienda)
				.orElseThrow(() -> new ValidacionException("No se encontro el Producto con la Tienda en la tabla stock"));
	}

	@Transactional(readOnly = false)
	public void actualizarStockPorId(Long idProducto, int cantidad) {
		Iterable<Stock> lista = stockRepository.findByIdProductoOrderByCantidadDesc(idProducto);
		for(Stock stock : lista) {
			if(cantidad == 0)
				break;
			if(cantidad >= stock.getCantidad()) {
				cantidad -= stock.getCantidad();
				stock.setCantidad(0L);
			}
			else {
				stock.setCantidad(stock.getCantidad() - cantidad);
				cantidad = 0;
			}
		}
		stockRepository.saveAll(lista);
	}

	@Transactional(readOnly = false)
	@Override
	public void actualizarStock(ActualizarStockDTO actualizarStockDTO) {
		actualizarStockDTO.getDetalle().forEach(detalle -> {
			actualizarStockPorId(detalle.getIdProducto(), detalle.getCantidad());
		});
		
	}

}
