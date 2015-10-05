/**
 * 
 */
package edu.fjnu.hotelsys.test;

import edu.fjnu.hotelsys.dao.HotelDao;
import edu.fjnu.hotelsys.dao.HotelDaoJDBCImpl;
import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.utils.DBUtilsOld;

/**
 * @author Administrator
 *
 */
public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//System.out.println(DBUtilsOld.getInstance());
		//System.out.println(DBUtilsOld.getInstance().getConn());
		
		Hotel h=new Hotel();
		h.setHotelName("东海酒店1");
		h.setHotelAddr("东海路2号");
		h.setHotelPhone("0591-366666669");
		
		HotelDao hotelDao=new HotelDaoJDBCImpl();
//		hotelDao.add(h);
//		
//		for(Hotel hotel:hotelDao.loadall())
//			System.out.println(hotel);
		
		//System.out.println(hotelDao.getHotelByNo(3));
		//hotelDao.delete(3);
		//System.out.println(hotelDao.getHotelByNo(3));
		//System.out.println(hotelDao.getHotelByNo(13));
		
		Hotel hotel=hotelDao.getHotelByNo(2);
		System.out.println(hotel);
		
        hotel.setHotelName("东方大酒店");
        hotel.setHotelAddr("福州西洪路1号");
        
        hotelDao.update(hotel);
        System.out.println(hotelDao.getHotelByNo(2));
        
		
	}

}
