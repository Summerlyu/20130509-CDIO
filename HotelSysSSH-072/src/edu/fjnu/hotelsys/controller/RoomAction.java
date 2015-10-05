/**
 * 
 */
package edu.fjnu.hotelsys.controller;

import java.util.List;

import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.service.HotelService;
import edu.fjnu.hotelsys.service.HotelServiceImpl;
import edu.fjnu.hotelsys.service.RoomQueryHelper;
import edu.fjnu.hotelsys.service.RoomService;
import edu.fjnu.hotelsys.service.RoomServiceImpl;
import edu.fjnu.hotelsys.utils.Page;

/**
 * @author Administrator
 * 
 */
public class RoomAction extends BaseAction {

	private Room room = null;
	private List<Hotel> hotelList;
	private List<Room>  roomList;
	private RoomQueryHelper helper=null;	
	private Page page=null;	
	
	private HotelService hotelService;
	private RoomService  roomService;	
	
	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public RoomQueryHelper getHelper() {
		return helper;
	}

	public void setHelper(RoomQueryHelper helper) {
		this.helper = helper;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String input() {
		
		hotelList=hotelService.loadall();
		
		room=new Room();
//		room.setRoomNo("123456");
//		room.setRoomMemo("jkajfkladjf");
//		Hotel hotel=new Hotel();
//		hotel.setHotelNo(201);
//		room.setHotel(hotel);
		
//		room.setRoomType("c");
//		room.setRoomStatus("c");
		room.setRoomEquip(new String[]{"a","d","e"});
		
		return "inputPage";
	}
	
	public String create(){
		
		roomService.createRoom(room);
		
		return "loadAllAction";
	}
	
	public String loadAll(){
		
		roomList=roomService.loadAll();
		
		return "loadAllPage";
	}
	
	public String forUpdate(){
		
		room=roomService.getRoomById(room.getRoomId());
		hotelList=hotelService.loadall();
		
		return "updatePage";
	}
	
	public String update(){
		

		  roomService.updateRoom(room);

		
		return "loadAllAction";
	}
	
	public String remove(){
		roomService.removeRoom(room.getRoomId());
		
		return "loadAllAction";
	}
	
	public String loadPaged(){
		
		hotelList=hotelService.loadall();
		
		if(page==null){
			page=new Page();
			page.setPageNo(1);
		}
		
		page=roomService.loadPagedRooms(page, helper);		
			
		return "loadPagedRoomPage";
	}

}
