package com.teamtrack.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Locations {

	private String userName;
	private double distance;
	private String latitude;
	private String longitude;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Locations [userName=" + userName + ", distance=" + distance
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
