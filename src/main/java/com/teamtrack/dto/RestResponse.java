/**
 * 
 */
package com.teamtrack.dto;

/**
 * @author parveenkumar
 * 
 */
public class RestResponse {

	public int errorCode;
	public String errorMessage;
	public int roomNumber;
	

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public String toString() {
		return "RestResponse [errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + ", roomNumber=" + roomNumber + "]";
	}

}
