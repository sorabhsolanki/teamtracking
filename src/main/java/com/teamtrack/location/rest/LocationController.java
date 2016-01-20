package com.teamtrack.location.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.teamtrack.dto.Location;

@Path("/location")
public class LocationController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome(){
		return "Lcaotion service is up and running";
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Location getTrackInJSON() {

		Location location = new Location();
		location.setTitle("Enter Sandman");
		location.setSinger("Metallica");

		return location;

	}
	
}
