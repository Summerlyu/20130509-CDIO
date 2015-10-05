/**
 * 
 */
package edu.fjnu.hotelsys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;

import edu.fjnu.hotelsys.domain.Hotel;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.service.HotelService;
import edu.fjnu.hotelsys.service.HotelServiceImpl;

/**
 * @author Administrator
 *
 */
public class HotelAction extends BaseAction {
	
	private Hotel hotel;
	private List<Hotel> hotelList;
	private File  hotelPhoto;
    private String hotelPhotoFileName;
    private String hotelPhotoContentType;
    
    private HotelService hotelService;
    
	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
		
	
    public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public String input(){
    	return "inputPage";
    }
    
    public String create(){
    	FileInputStream fis=null;
    	try {
			
    		fis=new FileInputStream(hotelPhoto);
			byte[] hotelPic=new byte[fis.available()];
			fis.read(hotelPic);
			this.hotel.setHotelPic(hotelPic);
			hotelService.createHotel(this.hotel);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	return "listhotelAction";
    }
    
    
    public String load(){
	
		this.hotelList=hotelService.loadall();
		
		return "listhotel_page"; 
    }
    
    public String getpic(){
    	
		byte[] hotelPic=hotelService.loadPic(this.hotel.getHotelNo());
		
		try{
		if(hotelPic==null || hotelPic.length==0){
			
			String realPath=ServletActionContext.getRequest().getRealPath("/pics/no-pic.jpg"); //真实物理磁盘路径 ，注意和网站路径区分。
			
			FileInputStream fis=new FileInputStream(realPath);
			
			hotelPic=new byte[fis.available()];
			fis.read(hotelPic);
			fis.close();
		}
		
		ServletActionContext.getResponse().setContentType("image/jpeg");
		ServletOutputStream sos=ServletActionContext.getResponse().getOutputStream();
		sos.write(hotelPic);
		sos.flush();
		sos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
    	return null;
    }
    
    public String remove(){
    	
		try{
		  hotelService.removeHotel(this.hotel.getHotelNo());
		}catch(ApplicationException e){
          e.printStackTrace();	  
		}
		
		return "listhotelAction";	
    }
    
    
    public String forUpdate(){
			
		hotel=hotelService.getHotelByNo(this.hotel.getHotelNo());   		
		
		return "updatehotelPage";
    	
    }
    
	public String update() {
		
		FileInputStream fis = null;
		try {
           if(hotelPhoto!=null){
			fis = new FileInputStream(hotelPhoto);
			byte[] hotelPic = new byte[fis.available()];
			fis.read(hotelPic);
			this.hotel.setHotelPic(hotelPic);
           }

			hotelService.update(this.hotel);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "listhotelAction";

	}
    

	public File getHotelPhoto() {
		return hotelPhoto;
	}

	public void setHotelPhoto(File hotelPhoto) {
		this.hotelPhoto = hotelPhoto;
	}

	public String getHotelPhotoFileName() {
		return hotelPhotoFileName;
	}

	public void setHotelPhotoFileName(String hotelPhotoFileName) {
		this.hotelPhotoFileName = hotelPhotoFileName;
	}

	public String getHotelPhotoContentType() {
		return hotelPhotoContentType;
	}

	public void setHotelPhotoContentType(String hotelPhotoContentType) {
		this.hotelPhotoContentType = hotelPhotoContentType;
	}
    
    

}
