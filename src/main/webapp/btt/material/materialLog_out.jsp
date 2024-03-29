<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
<!--
// 	$("#combox_operation a:nth-child(1)").click(function(){
// 		alert(1);
// 		return false;
// 	});
	function changeLogDo(){
		var value = $("#operation").find("option:selected").attr("value");
		$("#logDo").attr("value", value);
	}
// 	$(document).ready(function(){
// 		$("#operation").change(function(){
// 			alert($(this).find("option:selected").attr("value"));
// 		});
// 	})
//-->
</script>

<div class="pageContent">
	<form method="post" action="materialLog_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>分类：</label>
				<select class="combox" name="logDo" id="operation" onchange="changeLogDo();" >
					<option value="out">物料出库</option>
				</select>
				<input type="hidden" id="logDo" value="out" rel="logDo" />
			</p>
			<p>
				<label>物 料 名：</label>
				<input name="materialId" bringBackName="materialCombine.materialId"  type="hidden" >
				<input name="materialName" bringBackName="materialCombine.materialName" type="text" />
				<a class="btnLook" href="/material_list_combineLookup_factory.action?logDo={logDo}" lookupGroup="materialCombine" targetType="dialog">物料带回</a>
			</p>
			<p>
				<label>供应商：</label>
				<input name="materialProviderId" bringBackName="materialCombine.materialProviderId" type="hidden" >
				<input name="materialProviderName" bringBackName="materialCombine.materialProviderName" type="text" size="25" />
			</p>
			<p>
				<label>各物料总数量：</label>
				<input name="materialPartNum" bringBackName="materialCombine.materialPartNum" type="text" size="25"/>
			</p>
			<p>
				<label>总数量：</label>
				<input name="materialNum" bringBackName="materialCombine.materialNum" type="text" size="25" />
			</p>
			
			<p>
				<label>价格：</label>
				<input name="materialPriceId" bringBackName="materialCombine.materialPriceId" type="hidden"/>
				<input name="materialInputPrice" bringBackName="materialCombine.materialInputPrice" type="text" size="25" />
			</p>
			<p>
				<label>使用：</label>
				<input name="logMaterialUse" type="text" size="25" />
			</p>
			<p>
				<label>项目：</label>
				<input name="logMaterialProject" type="text" size="25" />
			</p>
			<p>
				<label>备注：</label>
				<input name="logRemark" type="text" size="25" />
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
