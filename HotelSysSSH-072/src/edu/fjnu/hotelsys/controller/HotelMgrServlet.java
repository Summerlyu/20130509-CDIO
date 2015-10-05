package edu.fjnu.hotelsys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.service.HotelService;
import edu.fjnu.hotelsys.service.HotelServiceImpl;

public class HotelMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HotelMgrServlet() {
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
		
		//�ҵ�����JAVAĬ�ϵ���ʱ�洢�ռ䣬������Ϊ���ݻ�ȡ�����еĻ�����
		File tempFile=new File(System.getProperty("java.io.tmpdir")); //Ŀ¼Ҳ���ļ���Ŀ¼��һ��������ļ�����ʵĿ¼��������һ���ı��ļ������������Ŀ¼���ļ����б���Ϣ��
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		factory.setRepository(tempFile);	
		ServletFileUpload sfu=new ServletFileUpload(factory);
		sfu.setSizeMax(5000000);
		
		List<FileItem> fileItems=null; 
		String act=null;
		
		if(sfu.isMultipartContent(request)) //�ж�request�Ƿ��Ƕ���ύ
		{
			try {
				fileItems=sfu.parseRequest(request);
				for(FileItem item:fileItems)
				{
					if(item.isFormField() && item.getFieldName().equals("act"))
                    {
                    	act=item.getString("utf-8");
                    	break;
                    } 					
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		    act=request.getParameter("act").trim();
		
		//Ϊ�˱���act���ܴ��ڵ�����д�룬���µĿ�ָ���쳣�����鷴����д��
		if("input".equals(act)){
		   request.getRequestDispatcher("jsps/hotel/input_hotel.jsp").forward(request, response);	
		}
		else if("create".equals(act))
		{
			
			Hotel h=new Hotel();
			for(FileItem item:fileItems)
			{
				if(item.isFormField() && item.getFieldName().equals("hotelname"))
					h.setHotelName(item.getString("utf-8"));
				else if(item.isFormField() && item.getFieldName().equals("hoteladdr"))
					h.setHotelAddr(item.getString("utf-8"));
				else if(item.isFormField() && item.getFieldName().equals("hotelphone"))
					h.setHotelPhone(item.getString("utf-8"));
				else if(!item.isFormField() && item.getFieldName().equals("hotelpic")){
                    byte[] hotelPic=new byte[(int)item.getSize()];
                    item.getInputStream().read(hotelPic, 0, (int)item.getSize());  
                    h.setHotelPic(hotelPic);				
				}
			}
			
			HotelService hotelService=new HotelServiceImpl();
			hotelService.createHotel(h);	
			
			response.sendRedirect("hotelMgr?act=loadHotel");
		}
		else if("loadHotel".equals(act)){
			HotelService hotelService=new HotelServiceImpl();
			request.setAttribute("hotelList", hotelService.loadall());
			
			request.getRequestDispatcher("jsps/hotel/list_hotel.jsp").forward(request, response);
		}
		else if("remove".equals(act)){
			int hotelNo=Integer.parseInt(request.getParameter("hotelno"));
			
			HotelService hotelService=new HotelServiceImpl();
			try{
			  hotelService.removeHotel(hotelNo);
			}catch(ApplicationException e){
			  request.setAttribute("err", e.getMessage());
			  request.setAttribute("hotelList", hotelService.loadall());
			  request.getRequestDispatcher("jsps/hotel/list_hotel.jsp").forward(request, response);			  
			  return;
			}
			response.sendRedirect("hotelMgr?act=loadHotel");
		}
		else if("forUpdate".equals(act)){
			
			int hotelNo=Integer.parseInt(request.getParameter("hotelno"));
			HotelService hotelService=new HotelServiceImpl();			
			request.setAttribute("hotel", hotelService.getHotelByNo(hotelNo));
			request.getRequestDispatcher("jsps/hotel/update_hotel.jsp").forward(request, response);					
		}
		else if("update".equals(act)){
			
			Hotel h=new Hotel();
			for(FileItem item:fileItems)
			{
				if(item.isFormField() && item.getFieldName().equals("hotelno"))
					h.setHotelNo(Integer.parseInt(item.getString("utf-8")));		
				if(item.isFormField() && item.getFieldName().equals("hotelroomcount"))
					h.setHotelRoomCount(Integer.parseInt(item.getString("utf-8")));	
				if(item.isFormField() && item.getFieldName().equals("hotelname"))
					h.setHotelName(item.getString("utf-8"));
				else if(item.isFormField() && item.getFieldName().equals("hoteladdr"))
					h.setHotelAddr(item.getString("utf-8"));
				else if(item.isFormField() && item.getFieldName().equals("hotelphone"))
					h.setHotelPhone(item.getString("utf-8"));
				else if(!item.isFormField() && item.getFieldName().equals("hotelpic")){
                    byte[] hotelPic=new byte[(int)item.getSize()];
                    item.getInputStream().read(hotelPic, 0, (int)item.getSize());  
                    h.setHotelPic(hotelPic);				
				}
			}		
			
			HotelService hotelService=new HotelServiceImpl();				
			hotelService.update(h);
			
			response.sendRedirect("hotelMgr?act=loadHotel");
			
		}
		else if("getpic".equals(act)){

			int hotelNo=Integer.parseInt(request.getParameter("hotelno"));
			
			HotelService hotelService=new HotelServiceImpl();
			byte[] hotelPic=hotelService.loadPic(hotelNo);
			
			if(hotelPic==null || hotelPic.length==0){
				
				String realPath=request.getRealPath("/pics/no-pic.jpg"); //��ʵ�������·�� ��ע�����վ·�����֡�
				
				FileInputStream fis=new FileInputStream(realPath);
				
				hotelPic=new byte[fis.available()];
				fis.read(hotelPic);
				fis.close();
			}
			
			response.setContentType("image/jpeg");
			ServletOutputStream sos=response.getOutputStream();
			sos.write(hotelPic);
			sos.flush();
			sos.close();
			
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
