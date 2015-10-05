<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>酒店房间管理</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/list.css"/>"/>
    
    <script type="text/javascript">
        
        function logout(){
          if(confirm("尊敬的${loginedUser.userName},您真的要离开系统吗？")){
             location.href='<s:url namespace="/security" action="logout"></s:url>'; 
          }
        }
    
    </script>

  </head>
 
  <body>
     <div id="header">
        <div id="productName">酒店房间管理</div>
        <div style="float:right; margin:10px; font-size:14px;">
                                  操作员:${loginedUser.userName}&nbsp;
             <span class="linkspan"  style="font-size:14px;" onclick="logout();" title="点击离开系统">离开系统</span>
        </div>        
     </div>
     <div>
        <div id="navigator">
            <div class="menuitem">
               <a href="<s:url namespace="/hotel" action="input_hotel"></s:url>" target="contentFrame">开设分店</a>
            </div>
            <div class="menuitem">
               <a href="<s:url namespace="/hotel" action="load_hotel"></s:url>"  target="contentFrame">分店管理</a>
            </div>
            <div class="seperator"></div>
            <div class="menuitem">
               <a href="<s:url namespace="/room" action="input_room"></s:url>"  target="contentFrame">开设房间</a>
            </div>
            <div class="menuitem">
               <a href="<s:url namespace="/room" action="loadAll_room"></s:url>" target="contentFrame">所有房间</a>
            </div>      
            <div class="menuitem">
               <a href="<s:url namespace="/room" action="loadPaged_room"></s:url>" target="contentFrame">分页房间列表</a>
            </div>                        
            <div class="menuitem">
               <a href="<c:url value="/roomMgr?act=loadPagedRooms"/>" target="contentFrame">房间查询</a>
            </div>                        
        </div>
        <div id="content">
          <!-- inner frame 嵌入式窗口 -->
          <iframe id="contentFrame" width="100%" scrolling="no" height="530px" frameborder="0" name="contentFrame" allowtransparency="true" src="<c:url value="/welcome.jsp"/>">              
          </iframe>           
        </div>
     </div>
     <jsp:include page="/jsps/common/footer.jsp"/>
  </body>
</html>
