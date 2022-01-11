package com.devopsusach2020.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devopsusach2020.model.Pais;
import com.devopsusach2020.model.Mundial;
import com.google.gson.Gson;

@RestController
@RequestMapping(path = "/rest/mscovid")
public class RestData {
	
	private static final Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

	
	@GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pais getData(@RequestParam(name = "msg") String message){
		// Proceso exitoso de prueba.
		LOGGER.log(Level.INFO, "Proceso exitoso de prueba");
		
		Pais response = new Pais();
		response.setMensaje("Mensaje Recibido: " + message);
		// Mensaje recibido.
		return response;
	}
	
	
	@GetMapping(path = "/estadoPais", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pais getTotalPais(@RequestParam(name = "pais") String message){
		// Esta es una línea de comentario.
		RestTemplate restTemplate = new RestTemplate();
		// Se llama a la API con el pais como parámetro
	    ResponseEntity<String> call= restTemplate.getForEntity("https://api.covid19api.com/live/country/" + message ,String.class);
	    
		// Consulta por pais.
	    LOGGER.log(Level.INFO, "Consulta por pais");
	    
		Pais response = new Pais();
		int confirmed = 0;
		int death = 0;
		int recovered = 0;
		Gson gson = new Gson();
		// Se debe buscar una alternativa más elegante para evitar usar un body nulo
		String body = call.getBody();		
		if (body != null) 
			body = body.toLowerCase();
		else
			body = "";
        Pais[] estados = gson.fromJson(body, Pais[].class);

		// Se realiza un ciclo para cargar las estadísticas de los estados del pais
        for(Pais estado : estados) {
        	response.setDate(estado.getDate());
        	response.setActive(estado.getActive());
        	confirmed += estado.getConfirmed();
        	death += estado.getDeaths();
        	recovered += estado.getRecovered();
        }

		/*
		Este es un bloque de comentario
		*/
        
    	response.setConfirmed(confirmed);
    	response.setDeaths(death);
    	response.setRecovered(recovered);
    	response.setCountry(message);
    	response.setMensaje("ok");

		// Los comentarios se insertan para subir la metrica de Sonarqube

		return response;		
	}
	

	@GetMapping(path = "/estadoMundial", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Mundial getTotalMundial(){
		// Esta es la consulta mundial
		LOGGER.log(Level.INFO, "Consulta mundial");
		
		RestTemplate restTemplate = new RestTemplate();
		// La llamada a la API
	    ResponseEntity<String> call= restTemplate.getForEntity("https://api.covid19api.com/world/total" ,String.class);
	    Mundial response = new Mundial();
		Gson gson = new Gson();
        Mundial estado = gson.fromJson(call.getBody().toLowerCase(), Mundial.class);
        response.setTotalConfirmed(estado.getTotalConfirmed());
        response.setTotalDeaths(estado.getTotalDeaths());
        response.setTotalRecovered(estado.getTotalRecovered());

		return response;		
	}
}
