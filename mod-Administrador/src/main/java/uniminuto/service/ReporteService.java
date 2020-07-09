package uniminuto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uniminuto.model.Reporte;
import uniminuto.model.UserCredentials;

@Service("reporteService")
public class ReporteService {
	
//	*********************************************************
//	Example web service report areas
//	*********************************************************
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReporteService.class);
	
	@Autowired
	private RestTemplate resttemplate;	
	
//	private final String GET_URL_CONSULTAR = "https://jsonplaceholder.typicode.com/posts/1/comments";
	private final String GET_URL_GENERAR_REPO = "http://localhost:8082/cxf/uniminuto/consultarRegistros";
	
	public void ReporteService(RestTemplate restTemplate) {
		this.resttemplate = restTemplate;
	}
	
//	public List<Reporte> findAllProducts(){
//		return Arrays.stream(resttemplate.getForObject(GET_URL_CONSULTAR, Reporte[].class))
//				.collect(Collectors.toList());
//	}
	
	public ArrayList<Reporte> sendReporte(Reporte repo) {
	HttpHeaders headers = new HttpHeaders();
		headers.set("areas", repo.getAreasUniminuto());
		headers.set("nivelSatisfaccion", repo.getNivelSatisfaccion());
		headers.set("fecha", repo.getFechainitemp());
		headers.set("fechaF", repo.getFechafintemp());
		headers.set("Content-Type", "application/json");
		headers.set("Accept", "application/json");
		log.info("Lo params object are: " + repo.toString());
		log.info("Lo params are: " + headers.toString());
	HttpEntity entity = new HttpEntity(headers);
	
	ResponseEntity<ArrayList> response = resttemplate.exchange(GET_URL_GENERAR_REPO, HttpMethod.GET, entity, ArrayList.class );
		log.info("Response web service " + response.getBody());
	return response.getBody();
	
	}
	
	
}
