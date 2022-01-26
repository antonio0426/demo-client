package com.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DemoClientController {

	private static final Logger log = LoggerFactory.getLogger(DemoClientController.class);

	@GetMapping("/users")
	public Iterable<DemoDbUser> getAllusers() {

		log.info("start getAllUsers");

		String baseUrl = "http://demo-db-service/demodb/users";
		RestTemplate restTemplate = new RestTemplate();
		Iterable<DemoDbUser> response = null;

		try {
			response = restTemplate.getForObject(baseUrl, Iterable.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		log.info("end getAllUsers");

		return response;
	}

}
