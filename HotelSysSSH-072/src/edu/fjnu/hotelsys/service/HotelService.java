/**
 * 
 */
package edu.fjnu.hotelsys.service;

import java.util.List;

import edu.fjnu.hotelsys.domain.Hotel;

/**
 * @author Administrator
 *
 */
public interface HotelService {
	
	void createHotel(Hotel hotel);
	List<Hotel> loadall();
	void removeHotel(Integer hotelNo);
	Hotel getHotelByNo(Integer hotelNo);
	void update(Hotel hotel);
	byte[] loadPic(Integer hotelNo);

}
