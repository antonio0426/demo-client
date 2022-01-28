package com.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoClientController {

	private static final Logger log = LoggerFactory.getLogger(DemoClientController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/users")
	public Iterable<DemoDbUser> getAllusers() {

		log.info("start getAllUsers");

		List<ServiceInstance> instances = discoveryClient.getInstances("demo-db-service");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		log.info("Base url " + baseUrl);

		baseUrl += "/users";

		log.info("Url " + baseUrl);
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
