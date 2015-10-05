<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/form.css"></c:url>">
  </head>
  
  <body>
    <div id="wrapper">
	    <div id="f_title">酒店房间登记</div>
	    <s:form namespace="/room" action="create_room" method="post">
	        <div class="f_row">
	          <span>房间房号:</span>
	          <s:textfield name="room.roomNo" size="20"></s:textfield>
	        </div>
            <div class="f_row">
	          <span>所属分店:</span>
	          <s:select name="room.hotel.hotelNo"
	                    list="hotelList" 
	                    listKey="hotelNo" 
	                    listValue="hotelName" 
	                    headerKey="0" 
	                    headerValue="==请选择=="></s:select>
	        </div>
	        <div class="f_row">
	          <span>房间类型:</span>
	          <s:radio name="room.roomType" 
	                   list="#{'a':'普单人间','b':'普双人间','c':'三人间','d':'商务套房','e':'贵宾套房'}"></s:radio>    
	        </div>
	        <div class="f_row">
	          <span>房间设施:</span>
	          <s:checkboxlist name="room.roomEquip" 
	            list="#{\"a\":'平面液晶电视',\"b\":'冰箱',\"c\":'空调',\"d\":'卫星电视',\"e\":'互联网接入',\"f\":'冲浪浴缸',\"g\":'观海景'}"></s:checkboxlist>         
	        </div>
	        <div class="f_row">
	          <span>房间状态:</span>
	          <s:select name="room.roomStatus"
	                    list="#{'a':'未入住','b':'有住客','c':'已预订','d':'保洁中','e':'已退房未保洁','f':'维护中'}"></s:select>          
	        </div>
	        <div class="f_row">
	          <span>备注说明:</span>
	          <s:textarea name="room.roomMemo" rows="8" cols="60"></s:textarea>
	        </div>		        	        	        	        
	        <div class="f_row">
	          <s:submit value="保存信息"></s:submit>      
	        </div>  	       
	    </s:form>
	    
    </div>
  </body>
</html>
