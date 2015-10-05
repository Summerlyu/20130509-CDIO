<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>HOTELSYS 系统登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/form.css"></c:url>">
    
    <style>
      .error{
        border:1px solid yellow;
        color:red;
        width:180px;
        padding:8px;
        margin:5px;
      }  
    </style>
    
    <script type="text/javascript">
      
       function validateFrm(){
          
          with(document.forms[0]){
          
            if(userno.value==''){
               alert("用户编号必须填写!");
               userno.focus();
               return false;
            }
            
            if(userpwd.value==''){
               alert("用户密码必须填写!");
               userpwd.focus();
               return false;
            }
          }
          
          return true;
          
       }
    
    </script>
    
  </head>
  
  <body>
    <div id="wrapper">
	    <div id="f_title">用户登录</div>
        <s:form namespace="/security" action="login" method="post">
	        <div class="f_row">
		          <span>用户编号:</span>
		          <s:textfield name="user.userNo"></s:textfield> <!-- ognl expression  -->
		    </div>
	        <div class="f_row">
		          <span>登录密码:</span>
		          <s:password  name="user.userPwd"></s:password>     
		    </div>
		    <s:actionerror cssClass="error"/>
		    <div class="f_row">
		          <s:submit value=" 登 录 "></s:submit>
		    </div>	    	    
        </s:form>
    </div>
    <%@ include file="/jsps/common/footer.jsp"%>
  </body>
</html>
