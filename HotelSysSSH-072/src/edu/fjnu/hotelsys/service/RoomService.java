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
	 * ���ݲ�ѯ����helper�����˷�����Ϣ���õ���Ӧ�ķ����б�
	 * @param helper
	 * @return
	 */
    List<Room> loadRooms(RoomQueryHelper helper);
    Page loadPagedRooms(Page page,RoomQueryHelper helper);

}
