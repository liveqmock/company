<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>应用汇列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery.js" type=text/javascript></script>
<style type="text/css">
<!--
html,body {height:100%; margin:0px; font-size:12px;}
.mydiv {
background-color: #FFCC66;
border: 1px solid #f00;
text-align: center;
line-height: 40px;
font-size: 12px;
font-weight: bold;
z-index:999;/*优先级*/
width: 300px;
height: 120px;
left:50%;
top:50%;
margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 ，!important的出现就是为了让用户自己设置被执行语句的优先级*/
margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
margin-top:0px;
position:fixed!important;/* FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/

}

.bg,.popIframe {
background-color: #666; display:none;
width: 100%;
height: 100%;
left:0;
top:0;/*FF IE7*/
filter:alpha(opacity=50);/*IE ，透明度*/
opacity:0.5;/*FF*/
z-index:1;/*优先级*/
position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);
}
.popIframe {
filter:alpha(opacity=0);/*IE 设置透明度*/
opacity:0;/*FF*/
}
-->
</style>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {
	color: #000000; font-size: 12;
}
.STYLE10 {
	color: #000000; font-size: 12px;
 }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.page{ text-align:center; margin-bottom:10px;}
.page form{display:inline !important; zoom:1;}
-->
</style>

  </head>
  
  <body>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1">应用列表</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
   <tr>
  	<td><table width="100%" border="0" cellpadding="0" cellspacing="1">
  	<s:form id="login" action="../City/System/selRecommend.action" method="post">
    	<tr>
    	<td width="10%" height="30"><a href="../City/System/addRecommendPage.action">新增</a></td>
    	<td width="90%" height="30">
        <div align="center">
      	 名称:<input name="name" type="text" /><input name="name" type="submit" value="搜索" />
        </div>
        </td></tr>
         </s:form>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编号</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">logo</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否免费</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">简介</span></div></td>
        <!-- <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">分类</span></div></td> -->
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">安卓下载地址</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ios下载地址</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <s:iterator value="listRecommend" var="d">
      <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d.id}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d.name}</div></td>
		  <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><img src="${d.logo}" height="50" width="50"></span></div></td>
		  <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">
		  ${d.ifNo}
		  </span></div></td>
		  <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center">
		  <span class="STYLE19">
		   <c:if test="${fn: length(d.bife)>11}">  
       		${fn: substring(d.bife, 0, 12)}...
	       	</c:if> 
	       	<c:if test="${fn: length(d.bife)<=11}">
	       		${d.bife}
	       	</c:if>
		  </span></div></td>
		  <%-- <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">
		  <c:if test=" ${d.ismend == 1 }">
		  	热门应用
		  </c:if>
		 <c:if test=" ${d.ismend != 1 }">
		 	精品推荐
		 </c:if>
		  </span>
		  </div>
		  </td> --%>
		  <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">
		  <a href="${d.aurl}">
		  	<c:if test="${fn: length(d.aurl)>11}">  
       			${fn: substring(d.aurl, 0, 12)}...
	       	</c:if> 
	       	<c:if test="${fn: length(d.aurl)<=11}">
	       		${d.aurl}
	       	</c:if>
		  </a></span></div></td>
		  <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="${d.iurl}">
		  	<c:if test="${fn: length(d.iurl)>11}">  
       			${fn: substring(d.iurl, 0, 12)}...
	       	</c:if> 
	       	<c:if test="${fn: length(d.iurl)<=11}">
	       		${d.iurl}
	       	</c:if>
		  </a>
		  </span></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="../City/System/delRecommend.action?id=${d.id}">删除<a>|<a href="../City/System/addRecommendPage.action?id=${d.id}">修改<a></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
  
  <tr>
  <td class="page">
  
  <s:if test="%{pagenum==1}">上一页</s:if>
				<s:elseif test="%{pagenum!=1}">
				<a href="../City/System/selRecommend.action?pagenum=${pagenum-1}&name=${idOne}">上一页</a>
				</s:elseif>
				<s:form name="form5" action="../City/System/selRecommend.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
				第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2"></s:textfield>
				<input type="hidden" name="typeOne" value="${cityNameOne}"/>
				<input type="hidden" name="cityArea" value="${pageCount}"/>
				<input type="hidden" name="type" value="${typeOne}"/>页 
				</s:form>
				<input onClick="tijiao();" value="提交" type="button" class="search-btn">
				<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
				<s:if test="%{pageCount>pagenum}">
					<a href="../City/System/selRecommend.action?pagenum=${pagenum+1}&name=${idOne}">
					下一页 </a>
				</s:if>
				<s:elseif test="%{pagenum==pageCount}">
					下一页
				</s:elseif>
  </td>
  </tr>
</table>
<div id="popDiv" class="mydiv" style="display:none;">
			
</div>
		<div id="bg" class="bg" style="display:none;"></div>
		<br><br>
		<iframe id='popIframe' class='popIframe' frameborder='0' ></iframe>
  <script type="text/javascript">
  function tijiao() {
	var ppt = document.getElementById("textfield").value;
		var znengweishuz =/^[0-9]*$/;
		if(!znengweishuz.test(ppt)){ 
				alert("页数只能为数字！");
				return false;
			}
		document.form5.submit();
	}
	function sorteee()
	{
		var sort = document.getElementById("sort").value;
		var znengweishuz =/^[0-9]*$/;
		if(!znengweishuz.test(sort) || sort == "")
		{ 
				alert("排序只能为数字！");
				return false;
		}
	}
	
	</script>
  </body>
</html>
