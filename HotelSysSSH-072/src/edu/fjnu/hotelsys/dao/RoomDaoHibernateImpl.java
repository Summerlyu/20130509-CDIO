/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.service.RoomQueryHelper;

/**
 * @author Administrator
 *
 */
public class RoomDaoHibernateImpl extends HibernateDaoSupport implements
		RoomDao {

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#add(edu.fjnu.hotelsys.domain.Room)
	 */
	@Override
	public void add(Room room) {
      this.getHibernateTemplate().save(room);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#getRoomById(java.lang.Integer)
	 */
	@Override
	public Room getRoomById(Integer roomId) {
		return this.getHibernateTemplate().get(Room.class, roomId);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#loadAll()
	 */
	@Override
	public List<Room> loadAll() {
		String hql="from Room r order by r.roomId desc";
		return this.getHibernateTemplate().find(hql);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer roomId) {

		Room room=this.getHibernateTemplate().load(Room.class, roomId);
		this.getHibernateTemplate().delete(room);

	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#update(edu.fjnu.hotelsys.domain.Room)
	 */
	@Override
	public void update(Room room) {
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        this.getHibernateTemplate().saveOrUpdate(room);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#loadRooms(edu.fjnu.hotelsys.service.RoomQueryHelper)
	 */
	@Override
	public List<Room> loadRooms(RoomQueryHelper helper) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#cntRooms(edu.fjnu.hotelsys.service.RoomQueryHelper)
	 */
	@Override
	public int cntRooms(RoomQueryHelper helper) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#getScopedRooms(edu.fjnu.hotelsys.service.RoomQueryHelper, int, int)
	 */
	@Override
	public List<Room> getScopedRooms(RoomQueryHelper helper, int begin, int end) {
		// TODO Auto-generated method stub
		return null;
	}

}
