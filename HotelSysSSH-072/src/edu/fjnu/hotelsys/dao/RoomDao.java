/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.util.List;

import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.service.RoomQueryHelper;

/**
 * @author Administrator
 *
 */
public interface RoomDao {
    void add(Room room);
    Room getRoomById(Integer roomId);
    List<Room> loadAll();
    void delete(Integer roomId);
    void update(Room room);
    List<Room> loadRooms(RoomQueryHelper helper);
    int cntRooms(RoomQueryHelper helper);
    List<Room> getScopedRooms(RoomQueryHelper helper, int begin, int end);
}
