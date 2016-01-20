package com.teamtrack.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Locations {

	private String userName;
	private String distance;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
	
}
