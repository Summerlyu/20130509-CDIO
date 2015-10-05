/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.exception.DataAccessException;
import edu.fjnu.hotelsys.service.RoomQueryHelper;
import edu.fjnu.hotelsys.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class RoomDaoJDBCImpl implements RoomDao {
	
	private static final String ADD_ROOM="insert into room values(seq_room.nextval,?,?,?,?,?,?)";
	private static final String GET_ROOM_BYID="select * from room where room_id=?";
	private static final String LOAD_ALL="select * from room order by room_id desc";
	private static final String DEL_ROOM_BYID="delete from room where room_id=?";
	private static final String UPDATE_ROOM="update room set room_no=?,room_type=?,room_equip=?,room_status=?,room_memo=?,hotel_no=? where room_id=?";

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.RoomDao#add(edu.fjnu.hotelsys.domain.Room)
	 */
	public void add(Room room) {
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		
		try {
		  pstmt=conn.prepareStatement(ADD_ROOM);
		  pstmt.setString(1,room.getRoomNo());
		  pstmt.setString(2,room.getRoomType());
		  
		  StringBuffer sb=new StringBuffer();
		  for(String equip:room.getRoomEquip())
		     sb.append(equip).append("|");
		  if(sb.length()>0) sb.deleteCharAt(sb.length()-1);
		  
		  pstmt.setString(3,sb.toString());
		  pstmt.setString(4, room.getRoomStatus());
		  pstmt.setString(5, room.getRoomMemo());
		  pstmt.setInt(6, room.getHotel().getHotelNo());
		  pstmt.executeUpdate();
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		
		

	}

	@Override
	public Room getRoomById(Integer roomId) {
		
		Connection conn=DBUtils.getInstance().getConn();
		System.out.println("roomDao:getRoomById "+conn);
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Room room=null;
		
		try {
			
		  pstmt=conn.prepareStatement(GET_ROOM_BYID);
		  pstmt.setInt(1,roomId);
		  rset=pstmt.executeQuery();
		  
		  if(rset.next()){
			 room=new Room();
			 room.setRoomId(roomId);
			 room.setRoomNo(rset.getString("room_no"));
			 room.setRoomType(rset.getString("room_type"));
			 room.setRoomStatus(rset.getString("room_status"));
			 room.setRoomMemo(rset.getString("room_memo"));
			 room.setRoomEquip(rset.getString("room_equip").split("\\|"));
			 HotelDao hotelDao=new HotelDaoJDBCImpl();
			 room.setHotel(hotelDao.getHotelByNo(rset.getInt("hotel_no")));
		  }
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		
		return room;
	}

	@Override
	public List<Room> loadAll() {
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		List<Room> roomList=null;
		
		try {
			
		  pstmt=conn.prepareStatement(LOAD_ALL);
		  rset=pstmt.executeQuery();
		  roomList=new ArrayList<Room>();
		  
		  while(rset.next()){
			 Room room=new Room();
			 room.setRoomId(rset.getInt("room_id"));
			 room.setRoomNo(rset.getString("room_no"));
			 room.setRoomType(rset.getString("room_type"));
			 room.setRoomStatus(rset.getString("room_status"));
			 room.setRoomMemo(rset.getString("room_memo"));
			 room.setRoomEquip(rset.getString("room_equip").split("\\|"));
			 HotelDao hotelDao=new HotelDaoJDBCImpl();
			 room.setHotel(hotelDao.getHotelByNo(rset.getInt("hotel_no")));
			 
			 roomList.add(room);
		  }
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		
		return roomList;
	}

	@Override
	public void delete(Integer roomId) {
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		
		try {
		  pstmt=conn.prepareStatement(DEL_ROOM_BYID);
		  pstmt.setInt(1, roomId);
		  pstmt.executeUpdate();
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}
		
	}

	@Override
	public void update(Room room) {

		Connection conn=DBUtils.getInstance().getConn();
		System.out.println("roomDao:updateRoom "+conn);
		PreparedStatement pstmt=null;
		
		try {
		  pstmt=conn.prepareStatement(UPDATE_ROOM);
		  pstmt.setString(1,room.getRoomNo());
		  pstmt.setString(2,room.getRoomType());
		  
		  StringBuffer sb=new StringBuffer();
		  for(String equip:room.getRoomEquip())
		     sb.append(equip).append("|");
		  if(sb.length()>0) sb.deleteCharAt(sb.length()-1);
		  
		  pstmt.setString(3,sb.toString());
		  pstmt.setString(4, room.getRoomStatus());
		  pstmt.setString(5, room.getRoomMemo());
		  pstmt.setInt(6, room.getHotel().getHotelNo());
		  pstmt.setInt(7, room.getRoomId());
		  pstmt.executeUpdate();
		  
 		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getMessage().contains("HOTELMGR.UK_HOTEL_ROOM_NO")){
				HotelDao hotelDao=new HotelDaoJDBCImpl();
				throw new DataAccessException(hotelDao.getHotelByNo(room.getHotel().getHotelNo()).getHotelName()+"-"+room.getRoomNo()+"房已经存在!");
			}
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}		
		
	}

	@Override
	public List<Room> loadRooms(RoomQueryHelper helper) {
		
		String sql=this.generateSQL(helper);
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		List<Room> roomList=null;
		
		try {
			
		  pstmt=conn.prepareStatement(sql);
		  rset=pstmt.executeQuery();
		  roomList=new ArrayList<Room>();
		  
		  while(rset.next()){
			 Room room=new Room();
			 room.setRoomId(rset.getInt("room_id"));
			 room.setRoomNo(rset.getString("room_no"));
			 room.setRoomType(rset.getString("room_type"));
			 room.setRoomStatus(rset.getString("room_status"));
			 room.setRoomMemo(rset.getString("room_memo"));
			 room.setRoomEquip(rset.getString("room_equip").split("\\|"));
			 HotelDao hotelDao=new HotelDaoJDBCImpl();
			 room.setHotel(hotelDao.getHotelByNo(rset.getInt("hotel_no")));
			 
			 roomList.add(room);
		  }
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		
		return roomList;		
	
	}
	
	private String generateSQL(RoomQueryHelper helper){
		
		String baseSQL="select * from room where 1=1 ";
		
		if(helper!=null){
			if(helper.getHotelNo()!=null)
				baseSQL+=" and hotel_no="+helper.getHotelNo();
			
			if(StringUtils.isNotEmpty(helper.getRoomStatus()))
				baseSQL+=" and room_status='"+helper.getRoomStatus()+"'";
			
			if(StringUtils.isNotEmpty(helper.getRoomType()))
				baseSQL+=" and room_type='"+helper.getRoomType()+"'";
		}
		
		baseSQL+=" order by room_id desc";
		
		System.out.println("SQL BY HELPER:"+baseSQL);
		
		return baseSQL;
		
	}

	@Override
	public int cntRooms(RoomQueryHelper helper) {
		
		String sql=this.generateSQL(helper);
		
		sql=sql.replace("*", "count(*) room_cnt");
		sql=sql.substring(0,sql.indexOf("order")).trim();
		
		System.out.println("cntRooms:"+sql);
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		int roomCnt=0;
		
		try {
			
		  pstmt=conn.prepareStatement(sql);
		  rset=pstmt.executeQuery();
		  
		  if(rset.next()){
			 roomCnt=rset.getInt("room_cnt");
		  }
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}		
		
		
		return roomCnt;
	}

	@Override
	public List<Room> getScopedRooms(RoomQueryHelper helper, int begin, int end) {

		String sql=this.generateSQL(helper);
		sql="select * from (select rownum rn, a.* from ("+sql+") a where rownum<=? ) where rn>=?";
		
		System.out.println("get scoped rooms:"+sql+","+end+","+begin);
		
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		List<Room> roomList=null;
		
		try {
			
		  pstmt=conn.prepareStatement(sql);
		  pstmt.setInt(1, end);
		  pstmt.setInt(2, begin);
		  rset=pstmt.executeQuery();
		  roomList=new ArrayList<Room>();
		  
		  while(rset.next()){
			 Room room=new Room();
			 room.setRoomId(rset.getInt("room_id"));
			 room.setRoomNo(rset.getString("room_no"));
			 room.setRoomType(rset.getString("room_type"));
			 room.setRoomStatus(rset.getString("room_status"));
			 room.setRoomMemo(rset.getString("room_memo"));
			 room.setRoomEquip(rset.getString("room_equip").split("\\|"));
			 HotelDao hotelDao=new HotelDaoJDBCImpl();
			 room.setHotel(hotelDao.getHotelByNo(rset.getInt("hotel_no")));
			 
			 roomList.add(room);
		  }
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}		
		return roomList;
	}

}
