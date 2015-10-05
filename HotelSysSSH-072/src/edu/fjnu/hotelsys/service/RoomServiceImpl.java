/**
 * 
 */
package edu.fjnu.hotelsys.service;

import java.util.List;

import edu.fjnu.hotelsys.dao.HotelDao;
import edu.fjnu.hotelsys.dao.HotelDaoJDBCImpl;
import edu.fjnu.hotelsys.dao.RoomDao;
import edu.fjnu.hotelsys.dao.RoomDaoJDBCImpl;
import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.utils.Page;
import edu.fjnu.hotelsys.utils.TransactionManager;

/**
 * @author Administrator
 * 
 */
public class RoomServiceImpl implements RoomService {
	
	private RoomDao   roomDao=null;
	private HotelDao  hotelDao=null;
		

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	

	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.fjnu.hotelsys.service.RoomService#createRoom(edu.fjnu.hotelsys.domain
	 * .Room)
	 */
	public void createRoom(Room room) {

			roomDao.add(room);

			Hotel hotel = hotelDao.getHotelByNo(room.getHotel().getHotelNo());
			hotel.setHotelRoomCount(hotel.getHotelRoomCount() + 1);
			hotelDao.update(hotel);

	}

	@Override
	public Room getRoomById(Integer roomId) {

		Room room = roomDao.getRoomById(roomId);
		return room;
		
	}

	@Override
	public List<Room> loadAll() {
		List<Room> roomList = roomDao.loadAll();
		return roomList;
	}

	@Override
	public void removeRoom(Integer roomId) {

			Hotel hotel = roomDao.getRoomById(roomId).getHotel();
			
			roomDao.delete(roomId);
			
			hotel.setHotelRoomCount(hotel.getHotelRoomCount() - 1);
			hotelDao.update(hotel);


	}

	@Override
	public void updateRoom(Room room) {


			Hotel oldHotel = roomDao.getRoomById(room.getRoomId()).getHotel();
			roomDao.update(room);

			if (!room.getHotel().getHotelNo().equals(oldHotel.getHotelNo())) {

				oldHotel.setHotelRoomCount(oldHotel.getHotelRoomCount() - 1);
				hotelDao.update(oldHotel);

				//if(true) throw new ApplicationException("fake error!");

				Hotel newHotel = hotelDao.getHotelByNo(room.getHotel()
						.getHotelNo());
				newHotel.setHotelRoomCount(newHotel.getHotelRoomCount() + 1);
				hotelDao.update(newHotel);
			}

	}

	@Override
	public List<Room> loadRooms(RoomQueryHelper helper) {
		TransactionManager manager = new TransactionManager();
		List<Room> roomList = null;
		try {
			manager.beginTransaction();
			RoomDao roomDao = new RoomDaoJDBCImpl();
			roomList = roomDao.loadRooms(helper);
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException("error!", e);
		}

		return roomList;
	}

	@Override
	public Page loadPagedRooms(Page page, RoomQueryHelper helper) {
		TransactionManager manager = new TransactionManager();
		try {
			manager.beginTransaction();
			RoomDao roomDao = new RoomDaoJDBCImpl();
			page.setTotalRecNum(roomDao.cntRooms(helper));
			page.setPageContent(roomDao.getScopedRooms(helper,
					page.getStartIndex(), page.getEndIndex()));
			manager.commitAndClose();
		} catch (Exception e) {
			manager.rollbackAndClose();
			throw new ApplicationException("error!", e);
		}
		return page;
	}

}
