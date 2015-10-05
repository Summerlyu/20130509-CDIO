/**
 * 
 */
package edu.fjnu.hotelsys.service;

import java.util.List;

import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.utils.Page;

/**
 * @author Administrator
 *
 */
public interface RoomService {
	
	void createRoom(Room room);
	Room getRoomById(Integer roomId);
	List<Room> loadAll();
	void removeRoom(Integer roomId);
	void updateRoom(Room room);
	/**
	 * 根据查询条件helper来过滤房间信息，得到对应的房间列表
	 * @param helper
	 * @return
	 */
    List<Room> loadRooms(RoomQueryHelper helper);
    Page loadPagedRooms(Page page,RoomQueryHelper helper);

}
