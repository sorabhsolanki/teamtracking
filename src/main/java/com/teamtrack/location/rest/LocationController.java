package com.teamtrack.location.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.teamtrack.cache.CustomCache;
import com.teamtrack.dto.Location;
import com.teamtrack.dto.Locations;

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

		
		CustomCache customCache = CustomCache.getCacheInstance();
		customCache.getCache().put("sorabh", "solanki");
		String name = (String) customCache.getCache().get("sorabh");
		
		
		Location location = new Location();
		List<Locations> locations = location.getLocations();
		
		Locations loc = new Locations();
		loc.setUserName("sorabh");
		loc.setDistance("12");
	
		Locations loc1 = new Locations();
		loc1.setUserName("parveen");
		loc1.setDistance("15");
		
		locations.add(loc);
		locations.add(loc1);
		
		location.setLocations(locations);
		
		return location;

	}
	
}
