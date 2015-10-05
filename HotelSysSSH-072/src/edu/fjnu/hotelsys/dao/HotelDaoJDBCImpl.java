/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.exception.DataAccessException;
import edu.fjnu.hotelsys.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class HotelDaoJDBCImpl implements HotelDao {
	
	private static final String ADD_HOTEL="insert into hotel values(seq_hotel.nextval,?,?,?,?,Empty_BLOB())";//hotel_pic blob字段初始化
	private static final String LOAD_ALL="select * from hotel order by hotel_no desc";
	private static final String LOAD_HOTEL_BYNO="select * from hotel where hotel_no=?";
	private static final String DEL_HOTEL_BYNO="delete from hotel where hotel_no=?";
	private static final String UPDATE_HOTEL="update hotel set hotel_name=?,hotel_addr=?,hotel_phone=?,hotel_room_count=? where hotel_no=?";
	private static final String SQL_GETPIC="select hotel_pic from hotel where hotel_no=?";

	public void add(Hotel hotel) {
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//conn.setAutoCommit(false);//设置数据库为不自动提交，必须的一步	
			
			pstmt=conn.prepareStatement(ADD_HOTEL);
			pstmt.setString(1, hotel.getHotelName());
			pstmt.setString(2, hotel.getHotelAddr());
			pstmt.setString(3, hotel.getHotelPhone());
			pstmt.setInt(4,hotel.getHotelRoomCount());
			
			pstmt.executeUpdate();
			
			   //以行的方式锁定   
			   pstmt=conn.prepareStatement("select hotel_pic from hotel where hotel_no=(select max(hotel_no) from hotel) for update");
			   rs=pstmt.executeQuery();  
			   
			   if (rs.next()) {   
				   //得到流   
				   oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob("hotel_pic");   
				   //从得到的低级流构造一个高级流   
				   PrintStream ps = new PrintStream(blob.getBinaryOutputStream());   
				   ps.write(hotel.getHotelPic());
				   //清空流的缓存   
				   ps.flush();   
				   //关闭流，注意一定要关   
				   ps.close();   
				}
			   
			  // conn.commit();			
			
		}catch(SQLException e)
		{
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.getInstance().ReleaseRes(null, pstmt, rs);
		}
		
		
	}

	public List<Hotel> loadall() {
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		List<Hotel> hotelList=null;
		
		try{
			
		  pstmt=conn.prepareStatement(LOAD_ALL);
		  rset=pstmt.executeQuery();
		  
		  hotelList=new ArrayList<Hotel>();
		  
		  while(rset.next()){
			  
			  Hotel hotel=new Hotel();
			  hotel.setHotelNo(rset.getInt("hotel_no"));
			  hotel.setHotelName(rset.getString("hotel_name"));
			  hotel.setHotelAddr(rset.getString("hotel_addr"));
			  hotel.setHotelPhone(rset.getString("hotel_phone"));
			  hotel.setHotelRoomCount(rset.getInt("hotel_room_count"));
			  
			  hotelList.add(hotel);
			  
		  }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtils.getInstance().ReleaseRes(null, pstmt,rset);
		}
		
		return hotelList;
	}

	public Hotel getHotelByNo(Integer hotelNo) {

		Connection conn=DBUtils.getInstance().getConn();
		//System.out.println("hotelDao:getHotelByNo "+conn);
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Hotel hotel=null;
		
		try{
			
			  pstmt=conn.prepareStatement(LOAD_HOTEL_BYNO);
			  pstmt.setInt(1, hotelNo);
			  rset=pstmt.executeQuery();
			 
			  
			  if(rset.next()){
				  
				  hotel=new Hotel();
				  hotel.setHotelNo(rset.getInt("hotel_no"));
				  hotel.setHotelName(rset.getString("hotel_name"));
				  hotel.setHotelAddr(rset.getString("hotel_addr"));
				  hotel.setHotelPhone(rset.getString("hotel_phone"));
				  hotel.setHotelRoomCount(rset.getInt("hotel_room_count"));
				  
			  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
			}	
			
		  return hotel;
	}

	public void delete(Integer hotelNo) {

		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		
		try {
			
			pstmt=conn.prepareStatement(DEL_HOTEL_BYNO);
			pstmt.setInt(1, hotelNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getMessage().contains("HOTELMGR.FK_ROOM_HOTEL")){
			   HotelDao hotelDao=new HotelDaoJDBCImpl();
			   Hotel hotel=hotelDao.getHotelByNo(hotelNo);
			   throw new DataAccessException(hotel.getHotelName()+"拥有"+hotel.getHotelRoomCount()+"间房间，无法删除拥有房间的分店!");
			}
		} finally{
			DBUtils.getInstance().ReleaseRes(null, pstmt, null);
		}		
		
	}

	public void update(Hotel hotel) {

		Connection conn=DBUtils.getInstance().getConn();
		System.out.println("hotelDao:updateHotel "+conn);
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//conn.setAutoCommit(false);//设置数据库为不自动提交，必须的一步	
			pstmt=conn.prepareStatement(UPDATE_HOTEL);
			pstmt.setString(1, hotel.getHotelName());
			pstmt.setString(2, hotel.getHotelAddr());
			pstmt.setString(3, hotel.getHotelPhone());
			pstmt.setInt(4,hotel.getHotelRoomCount());
			pstmt.setInt(5, hotel.getHotelNo());
			
			pstmt.executeUpdate();
			
			if(hotel.getHotelPic()!=null && hotel.getHotelPic().length>0){
			 //以行的方式锁定   
			   pstmt=conn.prepareStatement("select hotel_pic from hotel where hotel_no=? for update");
			   pstmt.setInt(1, hotel.getHotelNo());
			   rs=pstmt.executeQuery();  
			   
			   if (rs.next()) {   
				   //得到流   
				   oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob("hotel_pic");   
				   //从得到的低级流构造一个高级流   
				   PrintStream ps = new PrintStream(blob.getBinaryOutputStream());   
				   ps.write(hotel.getHotelPic());
				   //清空流的缓存   
				   ps.flush();   
				   //关闭流，注意一定要关   
				   ps.close();   
				}
			}
			   
			  // conn.commit();						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtils.getInstance().ReleaseRes(null, pstmt, rs);
		}		
		
	}

	@Override
	public byte[] getHotelPic(Integer hotelNo) {

		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		conn=DBUtils.getInstance().getConn();
		byte[] hotelPic=null;
		
		try{
		
		   pstmt=conn.prepareStatement(SQL_GETPIC);
		   pstmt.setInt(1, hotelNo);
		   rset=pstmt.executeQuery();
		   
		   
		   if(rset.next())
		   {
			   java.sql.Blob blob = rset.getBlob("hotel_pic");
			   if(blob!=null){
			    InputStream inStream = blob.getBinaryStream();
			    hotelPic = new byte[(int) blob.length()];
			    inStream.read(hotelPic);
			    inStream.close();
			   }
		   }
		   
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		return hotelPic;
	}

}
