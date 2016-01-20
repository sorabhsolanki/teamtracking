/**
 * 
 */
package com.teamtrack.dto;

/**
 * @author parveenkumar
 * 
 */
public class PushNotificationDetails {
	String message;
	String token;
	String certificatePath;
	String alertTitle;
	String certificatePassword = "innovation";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getAlertTitle() {
		return alertTitle;
	}

	public void setAlertTitle(String alertTitle) {
		this.alertTitle = alertTitle;
	}

	public String getCertificatePassword() {
		return certificatePassword;
	}

	public void setCertificatePassword(String certificatePassword) {
		this.certificatePassword = certificatePassword;
	}

	@Override
	public String toString() {
		return "PushNotificationDetails [message=" + message + ", token="
				+ token + ", certificatePath=" + certificatePath
				+ ", alertTitle=" + alertTitle + ", certificatePassword="
				+ certificatePassword + "]";
	}
}
