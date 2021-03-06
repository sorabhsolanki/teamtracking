package com.teamtrack.location.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.teamtrack.cache.CustomCache;
import com.teamtrack.dto.Location;
import com.teamtrack.dto.Locations;
import com.teamtrack.dto.RestResponse;
import com.teamtrack.util.CommonHelper;

@Path("/location")
public class LocationController {

	private Object lock = new Object();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome() {
		return "Location service is up and running";
	}

	@GET
	@Path("/pushaccepted/{lat}/{lon}/{userName}/{roomNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Location pushAccepted(@PathParam("lat") double lattitude,
			@PathParam("lon") double longitude,
			@PathParam("userName") String userName,
			@PathParam("roomNumber") int roomNumber) {
		
		synchronized (lock) {
			CustomCache customCache = CustomCache.getCacheInstance();
			Location loc = (Location) customCache.getCache().get(roomNumber);
			if (loc != null) {
				List<Locations> locations = loc.getLocations();
				Locations l1 = new Locations();
				l1.setUserName(userName);
				l1.setLatitude(lattitude);
				l1.setLongitude(longitude);
				l1.setDistance(0);
				l1.setAvailable(true);
				locations.add(l1);
				
				loc.setLocations(locations);

				customCache.getCache().put(roomNumber, loc);

				return loc;
			}
		}
		return null;
	}

	@GET
	@Path("/tracklocation/{lat}/{lon}/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse track(@PathParam("lat") double lattitude,
			@PathParam("lon") double longitude,
			@PathParam("userName") String userName) throws IOException {
		// get room number randomly
		int roomNumber = CommonHelper.getRandom();
		// notify to all other user-- push notification
		List<String> list = getUserList();
		for(String user: list){
			if(!user.equalsIgnoreCase(userName)){
				String message = "Please accept to give your location for tracking!!!" ;
				String title = "Track location initiated by " + userName;
				CommonHelper helper = new CommonHelper();
				helper.sendPushNotification(user, title, message,roomNumber);
			}
		}
		
		// save current user
		Location location = new Location();

		List<Locations> locations = new ArrayList<>();

		Locations l1 = new Locations();
		l1.setUserName(userName);
		l1.setLatitude(lattitude);
		l1.setLongitude(longitude);
		l1.setDistance(0);
		l1.setAvailable(true);
		locations.add(l1);
		location.setLocations(locations);

		// put room to location into cache
		CustomCache customCache = CustomCache.getCacheInstance();
		customCache.getCache().put(roomNumber, location);

		RestResponse resp = new RestResponse();
		resp.setErrorCode(200);
		resp.setRoomNumber(roomNumber);
		System.out.println(location);

		return resp;
	}

	@GET
	@Path("/checklocation/{roomNumber}/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Location checkLocation(@PathParam("roomNumber") int roomNumber, @PathParam("userName") String userName) {
		CustomCache customCache = CustomCache.getCacheInstance();
		Location location = (Location) customCache.getCache().get(roomNumber);
		if (location != null) {
			// calculate distance relative to current user. 
			List<Locations> locations = location.getLocations();
			locations = CommonHelper.calculateDistance(locations, userName);
			return location;
		}
		return null;
	}
	
	@GET
	@Path("/broadcast/{message}/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String broadcast(@PathParam("message") String message,
			@PathParam("userName") String userName) throws IOException {
		// broadcast to all other user-- push notification
		List<String> list = getUserList();
		for(String user: list){
			if(!user.equalsIgnoreCase(userName)){
				String title = "Broadcast message from " + userName;
				CommonHelper helper = new CommonHelper();
				helper.sendPushNotification(user, title, userName + " says : " + message, 0);
			}
		}
		return "";
	}

	public List<String> getUserList() throws IOException {
		CommonHelper helper = new CommonHelper();
		String users = helper.getPropValues("config.properties", "users");
		return Arrays.asList(users.split(":"));
	}

}
