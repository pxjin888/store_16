<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#total_material_num").keyup(function(){
		var total_material_num = $("#total_material_num").val();
		var material_id = $("#material_id").val();
		$.ajax({
			url:"material_children.action?" + "materialId=" + material_id,
			success: function(data){
				var obj = jQuery.parseJSON(data);
				$.each(obj, function(index, value){
					alert(value.id+value.name+value.materialNum+value.materialFormulaNum);
					$("#material_child").text($("#material_child").html() + value.name+": 现存量"+value.materialNum+", 出库需要"+value.materialFormulaNum+";\n");
					$('<input type="hidden" value='+value.id+' name="materialChildId" />'+'<input type="hidden" value='+value.materialFormulaNum+' name="materialFormulaNum" />').insertAfter( "#material_child" );
				});
			}
		});
	});
});
</script>

<div class="pageContent">
	<form method="post" action="materialLog_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>分类：</label>
				<select class="combox" name="logDo" id="operation" onchange="changeLogDo();" >
					<option value="mix">物料拼合</option>
				</select>
				<input type="hidden" id="logDo" value="mix" rel="logDo" />
			</p>
			<p>
				<label>综合物料 ：</label>
				<input name="materialId" id="material_id" bringBackName="material.materialId" type="hidden" size="25" value="" />
				<input name="materialName" bringBackName="material.materialName" type="text" />
				<a class="btnLook" href="/material_list_mix.action" lookupGroup="material" targetType="dialog">物料带回</a>
			</p>
			<p>
				<label>数量 ：</label>
				<input name="materialNum" id="total_material_num" type="text" />
			</p>
			<p style="height:80px;">
				<label>物料组成：</label>
				<textarea rows="5" cols="20" id="material_child" readonly="readonly"></textarea>
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
