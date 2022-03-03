package com.micro.springboot.app.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.micro.springboot.app.productos.models.entity.Producto;
import com.micro.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@SuppressWarnings("unused")
	@Autowired
	private Environment environment;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll()
				.stream()
				.map( prod -> {
					//prod.setPort(Integer.parseInt(environment.getProperty("server.port")));
					//prod.setPort(port);
					prod.setPort(webServerAppCtxt.getWebServer().getPort());
					return prod;
				})
				.collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws InterruptedException {
		
		//Condicionales para simular una excepcion
		if(id == 10) {
			throw new IllegalStateException("Producto no encontrado");
		}
		
		if(id == 7) {
			TimeUnit.SECONDS.sleep(5L);
		}
		
		Producto prod = productoService.findById(id);
		//prod.setPort(Integer.parseInt(environment.getProperty("server.port")));
		//prod.setPort(port);
		prod.setPort(webServerAppCtxt.getWebServer().getPort());
		
		//Codigo para forzar una excepcion para que el servicio cliente la procese con Hystrix
		/*
		boolean ok = false;
		if(!ok) {
			throw new RuntimeException("No se pudo cargar el producto!");
		}
		*/
		
		/*
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		return prod;
	}

}
