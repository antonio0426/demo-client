package com.client;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoClientController {

	@GetMapping("/users")
	public Iterable<DemoDbUser> getAllusers() {

		String baseUrl = "http://demo-client-microservice/demodb/users";

		RestTemplate restTemplate = new RestTemplate();
		Iterable<DemoDbUser> response = null;

		try {
			response = restTemplate.getForObject(baseUrl, Iterable.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response);

		return response;
	}

}
