<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="material_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>物 料 名：</label>
				<input name="materialName" type="text" size="25" value=""/>
			</p>
			<p>
				<label>物 料 代号：</label>
				<input name="materialCode" type="text" size="25" value=""/>
			</p>
			<p>
				<label>规格 / 型号：</label>
				<input name="materialType" type="text" size="25" value=""/>
			</p>
			<p>
				<label>单位：</label>
				<input name="materialUnit" type="text" size="25" value=""/>
			</p>
			<p>
				<label>最 少 量：</label>
				<input name="materialMinNum" type="text" size="25" value=""/>
			</p>
			<p>
				<label>批 量 数：</label>
				<input name="materialBatchNum" type="text" size="25" value=""/>
			</p>
			<p>
				<label>批 量 价：</label>
				<input name="materialBatchPrice" type="text" size="25" value=""/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
