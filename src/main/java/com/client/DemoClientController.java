package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoClientController {

	private static final Logger log = LoggerFactory.getLogger(DemoClientController.class);

	@GetMapping("/users")
	public Iterable<DemoDbUser> getAllusers() {

		log.info("start getAllUsers");

		Socket pingSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			InetAddress address = InetAddress.getByName("demo-db-service");
			boolean reachable = address.isReachable(10000);

			log.info("Service is " + reachable + " with name");

			address = InetAddress.getByName("10.100.121.167");
			reachable = address.isReachable(10000);

			log.info("Service is " + reachable + " with ip");

			pingSocket = new Socket("demo-db-service", 9002);
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
			out.println("telnet");
			log.info(in.readLine());
			out.close();
			in.close();
			pingSocket.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

//
//		String baseUrl = "http://demo-db-service/demodb/users";
//		RestTemplate restTemplate = new RestTemplate();
//		Iterable<DemoDbUser> response = null;
//
//		try {
//			response = restTemplate.getForObject(baseUrl, Iterable.class);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
//		log.info("end getAllUsers");

		return null;
	}

}
