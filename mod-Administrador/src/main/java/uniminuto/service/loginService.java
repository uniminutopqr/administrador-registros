package uniminuto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uniminuto.model.UserCredentials;

@Service("clienteService")
public class loginService {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(loginService.class);
	
	@Autowired
	private RestTemplate resttemplate;	
	
	private final String GET_URL_LOGIN = "http://localhost:8082/cxf/uniminuto/login";
	
	
	public void loginService(RestTemplate restTemplate) {
		this.resttemplate = restTemplate;
	}
	
	public String sendCredentials(UserCredentials userCred) {
		HttpHeaders headers = new HttpHeaders();
		log.info(" Usuario a verificar: " +userCred.getUsername());
		headers.set("usuario", userCred.getUsername());
		headers.set("password", userCred.getPassword());
		headers.set("Content-Type", "application/json");
		headers.set("Accept", "application/json");
		log.info(" Cantida de headers  " + headers.size());
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> response = resttemplate.exchange(GET_URL_LOGIN, HttpMethod.GET, entity, String.class);
		log.info(" Body Response ***** : "+ response.getBody());
		return response.getBody();
	}	

}
