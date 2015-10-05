/**
 * 
 */
package edu.fjnu.hotelsys.test;

import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.domain.Room;

/**
 * @author Administrator
 *
 */
public class VOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Hotel h=new Hotel();
		h.setHotelName("东海酒店");
		h.setHotelAddr("东海路1号");
		h.setHotelPhone("0591-37447589");
		
		Room room=new Room();
		room.setHotel(h);
		room.setRoomEquip(new String[] {"E","F"});
		room.setRoomMemo("nothing ....");
		room.setRoomType("R");
		room.setRoomStatus("M");
		
		System.out.println(room);

	}

}
