/**
 * 
 */
package edu.fjnu.hotelsys.service;

import edu.fjnu.hotelsys.domain.ValueObject;

/**
 * @author ctd
 * 
 */
public class RoomQueryHelper extends ValueObject {

	private Integer hotelNo;
	private String roomType;
	private String roomStatus;

	public Integer getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(Integer hotelNo) {
		this.hotelNo = hotelNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}



}
