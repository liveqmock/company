<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	table{
		width:300px;
		height:210px;
		}
	</style>
  </head>
  
  <body>
    <form id="login" action="../City/System/addImages.action"
		method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td>
    			图片：
    		</td>
    		<td>
    			<input type="file" name="picture" id="aa"/>
    			<input type="hidden" name="type" value="${typeOne}">
    			<input type="hidden" name="com" value="${comOne}">
    		</td>
    	</tr>
    	<tr>
    		<td>排序：</td>
    		<td>
    			<input type="text" name="sort" id="sort"/>
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定"/></td></tr>
    </table>
    </form>
  </body>
</html>
