package com.smartmarket.request;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;

public class RequestSender<T> {
	
	private static Logger log =  Logger.getLogger(RequestSender.class.getName());
	
	public RequestSender() {
	}
	
	public String sendRequest(T t, String path) {
		
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(path);
		Response response = webResource.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(new Genson().serialize(t), MediaType.APPLICATION_JSON_TYPE));
		if (response.getStatus() != 200) {
			
			log.error("Connection refused, status: "+response.getStatus());
			return null;
			
		}
		return response.readEntity(String.class);
	}

}
