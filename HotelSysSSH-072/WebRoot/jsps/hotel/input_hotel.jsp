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
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/form.css"/>">
    <script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
  </head>
  
  <body> 
    <div id="wrapper">
	    <div id="f_title">开设分店</div>
	    <s:form action="create_hotel" namespace="/hotel" method="post" enctype="multipart/form-data">
            <div class="f_row"> <!-- 普通表单域 -->
	          <span>分店名称:</span>
	          <s:textfield name="hotel.hotelName" size="20"></s:textfield>
	        </div>
            <div class="f_row">
	          <span>分店地址:</span>
	          <s:textfield name="hotel.hotelAddr" size="50"></s:textfield>       
	        </div>
            <div class="f_row">
	          <span style="margin-left:55px;"><image width="240" height="150" id="hotelview" src="<c:url value="/pics/no-pic.jpg"/>"/></span><br/>
	          <span>分店图片:</span> <!-- 数据表单域 -->
	          <s:file name="hotelPhoto" size="80" onchange="$('hotelview').src=this.value;"></s:file>    
	        </div>
	        <div class="f_row">
	          <span>联络电话:</span>
              <s:textfield name="hotel.hotelPhone" size="20"></s:textfield>	               
	        </div>
	        <div class="f_row">
	           <s:submit value="保  存"></s:submit>     
	        </div>	        	        	        	       
	    </s:form>
    </div>
  </body>
</html>
