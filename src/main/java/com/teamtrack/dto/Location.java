package com.teamtrack.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Location {
	
	private Locations locations;

	public Locations getLocations() {
		return locations;
	}

	public void setLocations(Locations locations) {
		this.locations = locations;
	}

}
