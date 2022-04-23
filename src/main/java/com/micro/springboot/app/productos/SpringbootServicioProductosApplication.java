package com.micro.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//EurekaClient para habilitar este servicio como cliente del servidor Eureka
//EntityScan para agregar clases (caso entidades) de paquetes externos al scanner de componentes
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.micro.springboot.app.commons.models.entity"})
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
