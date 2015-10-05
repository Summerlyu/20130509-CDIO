/**
 * 
 */
package edu.fjnu.hotelsys.domain;

/**
 * @author ctd
 * 
 */
public class Room extends ValueObject {

	private Integer roomId;
	private String roomNo;
	private Hotel hotel;
	private String roomType;
	private String[] roomEquip; //¶àÏîÑ¡Ôñ
	private String   roomEquipStr;
	private String roomStatus;
	private String roomMemo;
	
	public String getRoomEquipStr() {
		return roomEquipStr;
	}

	public void setRoomEquipStr(String roomEquipStr) {
		this.roomEquipStr = roomEquipStr;
		this.roomEquip=this.roomEquipStr.split("\\|");
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String[] getRoomEquip() {
		return roomEquip;
	}

	public void setRoomEquip(String[] roomEquip) {
		this.roomEquip = roomEquip;
	
		StringBuffer sb=new StringBuffer();
		for(String equip:roomEquip)
		  sb.append(equip).append("|");
	    if(sb.length()>0) 
	    	sb.deleteCharAt(sb.length()-1);
	
		this.roomEquipStr=sb.toString();		
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getRoomMemo() {
		return roomMemo;
	}

	public void setRoomMemo(String roomMemo) {
		this.roomMemo = roomMemo;
	}

}
