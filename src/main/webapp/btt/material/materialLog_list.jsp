<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="user_pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<!--<ul class="searchContent">
			<li>
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		-->
		<table class="searchContent">
			<tr>
				<td>
					物料名称：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
					</select>
				</td>
				<td>
					建档日期：<input type="text" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/btt/material/materialLog_firstin.jsp" target="dialog" rel="material_addDisplay"><span>一级物料入库</span></a></li>
			<li><a class="add" href="/btt/material/materialLog_out.jsp" target="dialog" rel="material_addDisplay"><span>物料出库</span></a></li>
			<li><a class="add" href="/btt/material/materialLog_mix.jsp" target="dialog" rel="material_addDisplay"><span>成品入库</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">日志id</th>
				<th width="120">用户id</th>
				<th>用户组id</th>
				<th width="100">动作</th>
				<th width="150">物料id</th>
				<th width="80" align="center">操作物料数量</th>
				<th width="80">物料价格</th>
				<th width="80">使用</th>
				<th width="80">摆放位置</th>
				<th width="80">项目</th>
				<th width="80">采购申请单号</th>
				<th width="80">采购合同号</th>
				<th width="80">供应商编号</th>
				<th width="80">备注</th>
				<th width="80">时间</th>
			</tr>
		</tdead>
		<tbody>
			<s:iterator value="#request.logMaterial_list">
			<tr target="sid_user" rel="10">
				<td><s:property value="logId"/></td>
				<td><s:property value="user"/></td>
				<td><s:property value="group"/></td>
				<td><s:property value="logDo"/></td>
				<td><s:property value="material"/></td>
				<td><s:property value="logMaterialNum"/></td>
				<td><s:property value="logMaterialUse"/></td>
				<td><s:property value="logMaterialPosition"/></td>
				<td><s:property value="logBuyRequirecode"/></td>
				<td><s:property value="logBuyAgreementcode"/></td>
				<td><s:property value="logRemark"/></td>
				<td><s:property value="logTime"/></td>
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
