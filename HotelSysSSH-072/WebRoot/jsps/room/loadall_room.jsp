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
    <script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
    
    <script type="text/javascript">
       
       function removeRoom(roomid,roomno,hotelname){
          if(confirm("您确认要删除["+hotelname+"-"+roomno+"房]的信息资料吗?")){         
             location.href='<s:url namespace="/room" action="remove_room"/>?room.roomId='+roomid;
          }
       }
       
       function updateRoom(roomid){
         location.href='<s:url namespace="/room" action="forUpdate_room"/>?room.roomId='+roomid;
       }
       
              
    </script>
    
  </head>
  
  <body>
    <div id="wrapper">
	     <div id="title">房间信息列表</div>
         <div>
	        <table id="listtable" cellpadding="0" cellspacing="0">
	          <tr>
	            <th>编号</th>
	            <th>房号</th>
	            <th>所属分店</th>
	            <th>房间类型</th>
	            <th>屋内设施</th>
	            <th>当前状态</th>
	            <th>操作</th>                                                            
	          </tr>
	          <s:iterator value="roomList">
	             <tr>
	                <td><s:property value="roomId"/></td>
	                <td><s:property value="roomNo"/></td>
                    <td><s:property value="hotel.hotelName"/></td>
  	                <td>
  	                   <s:if test="roomType==\"a\"">普单人间</s:if>
  	                   <s:elseif test="roomType==\"b\"">普双人间</s:elseif>
  	                   <s:elseif test="roomType==\"c\"">三人间</s:elseif>
  	                   <s:elseif test="roomType==\"d\"">商务套房</s:elseif>
  	                   <s:elseif test="roomType==\"e\"">贵宾套房</s:elseif>
  	                   <s:else>无效类型</s:else>
  	                </td>
	                <td>
	                   <s:iterator value="roomEquip" id="equip">
	                     <s:if test="#equip==\"a\"">平面液晶电视</s:if>
	                     <s:elseif test="#equip==\"b\"">冰箱</s:elseif>
  	                     <s:elseif test="#equip==\"c\"">空调</s:elseif>
  	                     <s:elseif test="#equip==\"d\"">卫星电视</s:elseif>
  	                     <s:elseif test="#equip==\"e\"">互联网接入</s:elseif>
  	                     <s:elseif test="#equip==\"f\"">冲浪浴缸</s:elseif>
  	                     <s:elseif test="#equip==\"g\"">观海景</s:elseif>
  	                     <s:else>---</s:else>  
	                   </s:iterator>
	                </td>
                    <td>
  	                   <s:if test="roomStatus==\"a\"">未入住</s:if>
  	                   <s:elseif test="roomStatus==\"b\"">有住客</s:elseif>
  	                   <s:elseif test="roomStatus==\"c\"">已预订</s:elseif>
  	                   <s:elseif test="roomStatus==\"d\"">保洁中</s:elseif>
  	                   <s:elseif test="roomStatus==\"e\"">已退房未保洁</s:elseif>
  	                   <s:elseif test="roomStatus==\"f\"">维护中</s:elseif>
  	                   <s:else>---</s:else>                                          
                    </td>
                    <td>
		               <button onclick="updateRoom(<s:property value="roomId"/>);"> 修 改 </button>
		               <button title="点击删除[<s:property value="hotel.hotelName"/>-<s:property value="roomNo"/>房]信息资料"
		                       onclick="removeRoom(<s:property value="roomId"/>,'<s:property value="roomNo"/>','<s:property value="hotel.hotelName"/>');"> 删 除 </button>                    
                    </td>                                      	                
	             </tr>
	          </s:iterator>
	        </table>
         </div>	     
     </div>
  </body>
</html>
