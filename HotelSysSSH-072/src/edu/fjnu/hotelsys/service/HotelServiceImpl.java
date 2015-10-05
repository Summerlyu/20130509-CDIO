/**
 * 
 */
package edu.fjnu.hotelsys.service;

import java.util.List;

import edu.fjnu.hotelsys.dao.HotelDao;
import edu.fjnu.hotelsys.dao.HotelDaoJDBCImpl;
import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.utils.TransactionManager;

/**
 * @author Administrator
 * 
 */
public class HotelServiceImpl implements HotelService {

	private HotelDao hotelDao = null;

	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.fjnu.hotelsys.service.HotelService#createHotel(edu.fjnu.hotelsys.
	 * domain.Hotel)
	 */
	public void createHotel(Hotel hotel) {

		hotelDao.add(hotel);

	}

	public List<Hotel> loadall() {

		List<Hotel> hotelList = null;
		hotelList = hotelDao.loadall();

		return hotelList;
	}

	public void removeHotel(Integer hotelNo) {

		hotelDao.delete(hotelNo);

	}

	public Hotel getHotelByNo(Integer hotelNo) {
		
		Hotel hotel = null;
		hotel = hotelDao.getHotelByNo(hotelNo);

		return hotel;
	}

	public void update(Hotel hotel) {

		hotelDao.update(hotel);

	}

	@Override
	public byte[] loadPic(Integer hotelNo) {

		byte[] loadPic = null;
		loadPic = hotelDao.getHotelPic(hotelNo);

		return loadPic;
	}

}
