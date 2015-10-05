/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.util.List;

import edu.fjnu.hotelsys.domain.Hotel;

/**
 * @author Administrator
 *
 */
public interface HotelDao {
	
	void add(Hotel hotel);
	List<Hotel> loadall();
	Hotel getHotelByNo(Integer hotelNo);
	void delete(Integer hotelNo);
	void update(Hotel hotel);
	byte[] getHotelPic(Integer hotelNo);

}
