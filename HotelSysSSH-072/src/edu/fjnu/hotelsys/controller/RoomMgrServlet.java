package edu.fjnu.hotelsys.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.service.HotelService;
import edu.fjnu.hotelsys.service.HotelServiceImpl;
import edu.fjnu.hotelsys.service.RoomQueryHelper;
import edu.fjnu.hotelsys.service.RoomService;
import edu.fjnu.hotelsys.service.RoomServiceImpl;
import edu.fjnu.hotelsys.utils.Page;

public class RoomMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RoomMgrServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request.setCharacterEncoding("utf-8");
		String act=request.getParameter("act");
		
		if("input".equals(act)){
			
			HotelService hotelService=new HotelServiceImpl();
			request.setAttribute("hotelList", hotelService.loadall());
			
			request.getRequestDispatcher("jsps/room/input_room.jsp").forward(request, response);
		}
		else if("create".equals(act)){
			
			Room room=new Room();
			room.setRoomNo(request.getParameter("room_no"));
			
			Integer hotelId=Integer.parseInt(request.getParameter("hotel_id"));
			Hotel hotel=new Hotel();
			hotel.setHotelNo(hotelId);
			room.setHotel(hotel);
			
			room.setRoomType(request.getParameter("room_type"));
			room.setRoomStatus(request.getParameter("room_status"));
			room.setRoomEquip(request.getParameterValues("room_equip"));
			room.setRoomMemo(request.getParameter("room_memo"));
			
			RoomService roomService=new RoomServiceImpl();
			roomService.createRoom(room);
			
			response.sendRedirect("roomMgr?act=loadAll");
				
		}
		else if("loadAll".equals(act)){
			
			HotelService hotelService=new HotelServiceImpl();
			request.setAttribute("hotelList", hotelService.loadall());
			
			RoomService roomService=new RoomServiceImpl();
			request.setAttribute("roomList", roomService.loadAll());
			
			request.getRequestDispatcher("jsps/room/list_room.jsp").forward(request, response);
		}
		else if("remove".equals(act)){
			
			int roomId=Integer.parseInt(request.getParameter("roomid"));
		    
			RoomService roomService=new RoomServiceImpl();
			roomService.removeRoom(roomId);
			response.sendRedirect("roomMgr?act=loadAll");
			
		}
		else if("forUpdate".equals(act)){
			
			int roomId=Integer.parseInt(request.getParameter("roomid"));
			
			RoomService roomService=new RoomServiceImpl();
			request.setAttribute("room", roomService.getRoomById(roomId));
			
			HotelService hotelService=new HotelServiceImpl();
			request.setAttribute("hotelList", hotelService.loadall());
			
			request.getRequestDispatcher("jsps/room/update_room.jsp").forward(request, response);
			
		}
		else if("update".equals(act)){
			
			Room room=new Room();
			room.setRoomId(Integer.parseInt(request.getParameter("room_id")));
			room.setRoomNo(request.getParameter("room_no"));
			
			Integer hotelId=Integer.parseInt(request.getParameter("hotel_id"));
			Hotel hotel=new Hotel();
			hotel.setHotelNo(hotelId);
			room.setHotel(hotel);
			
			room.setRoomType(request.getParameter("room_type"));
			room.setRoomStatus(request.getParameter("room_status"));
			room.setRoomEquip(request.getParameterValues("room_equip"));
			room.setRoomMemo(request.getParameter("room_memo"));
			
			RoomService roomService=new RoomServiceImpl();
			try{
			  roomService.updateRoom(room);
			}catch(ApplicationException e){
			
			  request.setAttribute("err", e.getMessage());
				
			  request.setAttribute("room", room);
			  HotelService hotelService=new HotelServiceImpl();
			  request.setAttribute("hotelList", hotelService.loadall());			  
			  request.getRequestDispatcher("jsps/room/update_room.jsp").forward(request, response);
			  return;
			}
			response.sendRedirect("roomMgr?act=loadAll");			
		}
		else if("loadRooms".equals(act)){
			
			RoomQueryHelper helper=new RoomQueryHelper();
			
			if(StringUtils.isNotEmpty(request.getParameter("qryhotelno")))
				  helper.setHotelNo(Integer.parseInt(request.getParameter("qryhotelno")));
			
			if(StringUtils.isNotEmpty(request.getParameter("qryroomtype")))
				  helper.setRoomType(request.getParameter("qryroomtype"));
			
			if(StringUtils.isNotEmpty(request.getParameter("qryroomstatus")))
				  helper.setRoomStatus(request.getParameter("qryroomstatus"));
			
			
			HotelService hotelService=new HotelServiceImpl();
			request.setAttribute("hotelList", hotelService.loadall());
			
			RoomService roomService=new RoomServiceImpl();
			request.setAttribute("roomList", roomService.loadRooms(helper));
			
			request.getRequestDispatcher("jsps/room/list_room.jsp").forward(request, response);
			
			
		}
		else if("loadPagedRooms".equals(act)){
			
			RoomQueryHelper helper=new RoomQueryHelper();
			
			if(StringUtils.isNotEmpty(request.getParameter("qryhotelno")))
				  helper.setHotelNo(Integer.parseInt(request.getParameter("qryhotelno")));
			
			if(StringUtils.isNotEmpty(request.getParameter("qryroomtype")))
				  helper.setRoomType(request.getParameter("qryroomtype"));
			
			if(StringUtils.isNotEmpty(request.getParameter("qryroomstatus")))
				  helper.setRoomStatus(request.getParameter("qryroomstatus"));
			
			Page page=new Page();
			if(StringUtils.isNotEmpty(request.getParameter("pageno")))
				 page.setPageNo(Integer.parseInt(request.getParameter("pageno")));
			
			
			HotelService hotelService=new HotelServiceImpl();
			request.setAttribute("hotelList", hotelService.loadall());
			
			RoomService roomService=new RoomServiceImpl();
			request.setAttribute("page", roomService.loadPagedRooms(page, helper));
			
			request.getRequestDispatcher("jsps/room/list_room.jsp").forward(request, response);			
		}
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doGet(request,response);
	}

}
