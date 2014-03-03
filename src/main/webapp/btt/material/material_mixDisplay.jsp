<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="material_mix.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50">
			<dl>
				<dt>物 料 名：</dt>
				<dd><input name="materialName" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>物 料 代号：</dt>
				<dd><input name="materialCode" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>规格 / 型号：</dt>
				<dd><input name="materialType" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>单位：</dt>
				<dd><input name="materialUnit" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>序 列 号：</dt>
				<dd><input name="materialSequence" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>通 道 数：</dt>
				<dd><input name="materialPassNum" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>通 道 号：</dt>
				<dd><input name="materialPassNo" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>版 本：</dt>
				<dd><input name="materialVersion" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>使 用：</dt>
				<dd><input name="materialUse" type="text" size="25" value=""/></dd>
			</dl>
			<dl>
				<dt>摆放位置：</dt>
				<dd><input name="materialPosition" type="text" size="25" value=""/></dd>
			</dl>
			<fieldset id="material_set">
			<legend>组成物料列表</legend>
			<span class="material_child">
			<dl>
				<dt>物料名称：</dt>
				<dd>
					<input name="material_child_id" bringBackName="material.materialId" type="hidden" size="25" value="" />
					<input name="material_child_name" bringBackName="material.materialName" type="text" size="25" value="" />
					<a class="btnLook" href="/material_list_mix.action" lookupGroup="material" targetType="dialog">查找带回</a>
				</dd>
			</dl>
			<dl>
				<dt>物料数量：</dt>
				<dd><input name="material_child_num" type="text" size="25" value=""/></dd>
			</dl>
			</span>
			<span id="material_child_end" style="display:none;">
				end
			</span>
			</fieldset>
			<p><a class="button" id="insert_child"><span>新增子物料</span></a></p>
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
 <script type="text/javascript">
	$(document).ready(function(){
		$("#insert_child").click(function(){
			var html = '<dl><dt>物料名称1：</dt><dd><input name="material_child_id" bringBackName="material'+Date.now()+'.materialId" type="hidden" size="25" value="" /><input name="material_child_name" bringBackName="material'+Date.now()+'.materialName" type="text" size="25" value="" class="textInput"/><a class="btnLook" href="/material_list_mix.action" targetType="dialog" width="820" height="400" minable="true" lookupGroup="material'+Date.now()+'"  >查找带回</a></dd></dl><dl class="material_tag"><dt>物料数量：</dt><dd><input name="material_child_num" type="text" size="25" value="" class="textInput"/></dd></dl>';
			$(html).insertBefore($("#material_child_end"));
			initEnv();
		});
	});
</script>