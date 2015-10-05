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
		h.setHotelName("�����Ƶ�1");
		h.setHotelAddr("����·2��");
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
		
        hotel.setHotelName("������Ƶ�");
        hotel.setHotelAddr("��������·1��");
        
        hotelDao.update(hotel);
        System.out.println(hotelDao.getHotelByNo(2));
        
		
	}

}
