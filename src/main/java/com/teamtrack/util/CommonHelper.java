package com.teamtrack.util;

import java.util.List;
import java.util.Random;

import com.teamtrack.dto.Locations;

public class CommonHelper {

	private static final Random RANDOM = new Random();
	
	public static int getRandom(){
		return RANDOM.nextInt(10000);
	}
	
	/*
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	private static double distance(double lat1, double lat2, double lon1,double lon2) {

	    final int R = 6371; // Radius of the earth

	    Double latDistance = Math.toRadians(lat2 - lat1);
	    Double lonDistance = Math.toRadians(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}
	
	
	public static List<Locations> calculateDistance(List<Locations> locations, String currentUser){
		String longitude =null;
		String latitude = null;
		// loop to find out the latitude and longitude 
		for(Locations loc : locations){
			if(loc.getUserName().equals(currentUser)){
				longitude = loc.getLongitude();
				latitude = loc.getLatitude();
				loc.setDistance(0);
			}
		}
		
		//loop to update distance related above finding
		for(Locations loc : locations){
			if(!loc.getUserName().equals(currentUser)){
				double distance = distance(Double.parseDouble(latitude), Double.parseDouble(loc.getLatitude()), Double.parseDouble(longitude), Double.parseDouble(loc.getLongitude()));
				loc.setDistance(distance);
			}
		}
		return locations;
	}
}
