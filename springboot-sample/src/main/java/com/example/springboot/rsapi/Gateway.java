package com.example.springboot.rsapi;

import java.net.UnknownHostException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gw")
public class Gateway {
	
	@RequestMapping("/message")
	public String message(@RequestParam(value="message", defaultValue="Default server message.") String message) {
		Client client = ClientBuilder.newBuilder()
				.register(JacksonJsonProvider.class)
				.build();

		String result = client.target("http://localhost:8080/api/message")
				.queryParam("message", message)
				.request()
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);
		
		return result;
	}
	
	@RequestMapping("/hostname")
	public String hostName() throws UnknownHostException {
		Client client = ClientBuilder.newBuilder()
				.register(JacksonJsonProvider.class)
				.build();

		String result = client.target("http://localhost:8080/api/hostname")
				.request()
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);
		
		return result;
	}
	
	@RequestMapping("/randomsum")
	public String sum(@RequestParam(value="index", defaultValue="128") int index) {
		Client client = ClientBuilder.newBuilder()
				.register(JacksonJsonProvider.class)
				.build();
		
		String result = client.target("http://localhost:8080/api/randomsum")
				.queryParam("index", index)
				.request()
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);

		return result;
	}
	
	@RequestMapping("/quote")
	public String quote(@RequestParam(value="symbol", defaultValue="ORCL") String symbol) {
		Client client = ClientBuilder.newBuilder()
				.register(JacksonJsonProvider.class)
				.build();

		String result = client.target("http://localhost:8080/api/quote")
				.queryParam("symbol", symbol)
				.request()
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);
		
		return result;
	}
}
