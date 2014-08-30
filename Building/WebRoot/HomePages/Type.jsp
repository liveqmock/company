<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>所有行业</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link type="text/css"
	href="${pageContext.request.contextPath}/css/center.css"
	rel="stylesheet" />
</head>
<body>
	<div id="all3">
		<div class="va">
			<div class="cc">
				<div class="pt">所有行业</div>
			</div>
			<div class="ee">
				<form action="../Building/System/selType.action"
					method="post">
					<table>
						<tr align="center">
							<td>行业名称：<input type="text" name="name"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="搜索" id="sou" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="ce">行业信息</div>
		<div class="va1">
			<div>
				<a href="HomePages/AddType.jsp">添加行业</a>
			</div>
			<div class="kk">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
							<td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div
									align="center">
									<span class="STYLE10">编号</span>
								</div>
							</td>
							<td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div
									align="center">
									<span class="STYLE10">行业名称</span>
								</div>
							</td>
							<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div
									align="center">
									<span class="STYLE10">基本操作</span>
								</div>
							</td>
						</tr>
						<s:iterator value="type_list" var="l">
							 <tr>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div
										align="center" id="divID">${l.id}</div>
								</td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19">
									<div align="center">${l.name }</div>
								</td>
								<td height="20" bgcolor="#FFFFFF">
									<div align="center" class="STYLE21">
										<a href="System/delType.action?id=${l.id}">删除</a>
									</div>
									</td>
							</tr> 
						</s:iterator>
					</table>
			</div>
			<div class="aa" align="center">
				<s:if test="%{pagenum==1}">上一页</s:if>
				<s:elseif test="%{pagenum!=1}">
					<a
						href="../Building/System/selType.action?pagenum=${pagenum-1}&name=${areaName}">上一页</a>
				</s:elseif>
				<s:form name="form5"
					action="../Building/System/selType.action" namespace="/Web"
					method="post" theme="simple" style="display:inline-block;">
								第<s:textfield type="text" name="pagenum" id="textfield"
						cssClass="pagetext" size="2"></s:textfield>
					<input type="hidden" name="cityArea" value="${pageCount}" />
					<input type="hidden" name="name" value="${areaName}" />
								页 
								</s:form>
				<input onClick="tijiao();" value="提交" type="button"
					class="search-btn"> <span class="c_fl">共 <s:property
						value="pageCount"></s:property>页</span>
				<s:if test="%{pageCount>pagenum}">
					<a
						href="../Building/System/selType.action?pagenum=${pagenum+1}&name=${areaName}">
						下一页 </a>
				</s:if>
				<s:elseif test="%{pagenum==pageCount}">
									下一页
								</s:elseif>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function tijiao() {
			var ppt = document.getElementById("textfield").value;
			var znengweishuz = /^[0-9]*$/;
			if (!znengweishuz.test(ppt)) {
				alert("页数只能为数字！");
				return false;
			}
			document.form5.submit();
		}
	</script>
</body>
</html>
