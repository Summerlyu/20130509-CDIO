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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/list.css"></c:url>">
    
    <script type="text/javascript">
    
       function removeHotel(hotelNo,hotelName){
          if(confirm("确认要删除分店["+hotelName+"]的信息吗?")){
            location.href='<s:url action="remove_hotel" namespace="/hotel"/>?hotel.hotelNo='+hotelNo;       
          }
       }
       
       function updateHotel(hotelNo){
          location.href='<s:url action="forUpdate_hotel" namespace="/hotel"/>?hotel.hotelNo='+hotelNo;      
       }
    
    </script>
  </head>
  
  <body>
    <div id="wrapper">
	     <div id="title">分店信息列表</div>
	     <div>
	        <table id="listtable" cellpadding="0" cellspacing="0">
	          <tr>
	            <th>编号</th>
	            <th>分店名称</th>
	            <th>分店地址</th>
	            <th>联络电话</th>
	            <th>房间总数</th>
	            <th>操作</th>                                                            
	          </tr>
	          <s:iterator value="hotelList">
		          <tr>
		            <td>
		              <s:property value="hotelNo"/>
		              <img width="250" height="140" style="margin:3px" src="<s:url action="getpic_hotel" namespace="/hotel"/>?hotel.hotelNo=<s:property value="hotelNo"/>"/>
		            </td>
		            <td><s:property value="hotelName"/></td>
		            <td><s:property value="hotelAddr"/></td>
		            <td><s:property value="hotelPhone"/></td>
		            <td><s:property value="hotelRoomCount"/></td>
		            <td>
		               <button title="点击修改分店[<s:property value="hotelName"/>]" onclick="updateHotel(<s:property value="hotelNo"/>);"> 修 改 </button>
		               <button title="点击删除分店[<s:property value="hotelName"/>]" onclick="removeHotel(<s:property value="hotelNo"/>,'<s:property value="hotelName"/>');"> 删 除 </button>
		            </td>                                                            
		          </tr> 	             
	          </s:iterator>
	        </table>
	     </div>
     </div>
     <c:if test="${not empty err}">
     <script type="text/javascript">
       alert('${err}');
     </script>
     </c:if>
  </body>
</html>
