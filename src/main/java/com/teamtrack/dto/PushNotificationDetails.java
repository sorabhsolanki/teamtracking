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
	String customField = "roomNumber";
	int customFieldValue;
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

	public String getCustomField() {
		return customField;
	}

	public void setCustomField(String customField) {
		this.customField = customField;
	}

	public int getCustomFieldValue() {
		return customFieldValue;
	}

	public void setCustomFieldValue(int customFieldValue) {
		this.customFieldValue = customFieldValue;
	}

	@Override
	public String toString() {
		return "PushNotificationDetails [message=" + message + ", token="
				+ token + ", certificatePath=" + certificatePath
				+ ", alertTitle=" + alertTitle + ", customField=" + customField
				+ ", customFieldValue=" + customFieldValue
				+ ", certificatePassword=" + certificatePassword + "]";
	}
}
