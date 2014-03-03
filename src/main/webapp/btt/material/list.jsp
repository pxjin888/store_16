<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="user_pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader"></div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
			<s:iterator value="#request.operations" var="operation">
			<li><a class='<s:property value="#operation.operationIconClass"/>' href='<s:property value="#operation.operationUrl"/>' target='<s:property value="#operation.operationTarget"/>' rel='<s:property value="#operation.operationOpenRel"/>'><span><s:property value="#operation.operationName"/></span></a></li>
			<li class="line">line</li>
			</s:iterator>
			<!-- 
			<li><a class="add" href="/btt/material/materialFactory_addDisplay.jsp" target="dialog" rel="material_addDisplay"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="/btt/user/group_edit.action?uid={sid_user}" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			 -->
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<s:iterator value="#request.list[0]">
				<th width="80"><s:property value="key"/></th>
				</s:iterator>
			</tr>
			
		</tdead>
		<tbody>
			<s:iterator value="#request.list" var="content">
			<tr target="sid_user" rel="10">
				<s:iterator value="#content">
				<td><s:property value="value"/></td>
				</s:iterator>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>

<script type="text/javascript">
  $(document).ready(function(){
	  var url = '<s:property value="#request.searchBar.searchUrl"/>';
	  $(".pageHeader").load("btt/material/"+url);
  });
</script>