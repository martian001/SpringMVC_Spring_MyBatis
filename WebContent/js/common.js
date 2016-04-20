//重置按钮
function majaxReset() {
	$('#searchFrom').form('reset')
}
/**
 * 将form表单元素的值序列化成对象
 */
$.serializeObject = function(formId) {
	var o = {};
	$.each(formId.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
/**
 * 通用的分页查询提交from表单
 * 
 * @param datagrid
 *            datagrid ID
 * @param formId
 *            Form ID
 */
function ajaxSearchPage(datagrid, formId) {
	// 重新加载数据
	$(datagrid).datagrid('load', $.serializeObject($(formId)));
	// 清空所选择的行数据
	clearSelectRows(datagrid);
}
/**
 * 清除datagrid的选中数据
 * 
 * @param datagrid
 *            datagrid ID
 */
function clearSelectRows(datagrid) {
	// 清除所有选择的行。
	$(datagrid).datagrid("clearSelections");
	// 清除所有勾选的行。
	$(datagrid).datagrid("clearChecked");
}