/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fjnu.hotelsys.domain.Hotel;

/**
 * @author Administrator
 *
 */
public class HotelDaoHibernateImpl extends HibernateDaoSupport implements HotelDao {

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.HotelDao#add(edu.fjnu.hotelsys.domain.Hotel)
	 */
	@Override
	public void add(Hotel hotel) {
        this.getHibernateTemplate().save(hotel);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.HotelDao#loadall()
	 */
	@Override
	public List<Hotel> loadall() {
		return this.getHibernateTemplate().find("from Hotel h order by h.hotelNo desc");
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.HotelDao#getHotelByNo(java.lang.Integer)
	 */
	@Override
	public Hotel getHotelByNo(Integer hotelNo) {
		return this.getHibernateTemplate().get(Hotel.class, hotelNo);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.HotelDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer hotelNo) {
		Hotel hotel=this.getHibernateTemplate().load(Hotel.class, hotelNo);
		this.getHibernateTemplate().delete(hotel);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.HotelDao#update(edu.fjnu.hotelsys.domain.Hotel)
	 */
	@Override
	public void update(Hotel hotel) {
       this.getHibernateTemplate().update(hotel);
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.HotelDao#getHotelPic(java.lang.Integer)
	 */
	@Override
	public byte[] getHotelPic(Integer hotelNo) {
		Hotel hotel=this.getHibernateTemplate().load(Hotel.class, hotelNo);
	    byte[] pic=hotel.getHotelPic();
		return pic;
	}

}
