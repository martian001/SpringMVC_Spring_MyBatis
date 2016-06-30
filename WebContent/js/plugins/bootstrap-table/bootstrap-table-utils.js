/**
 * BootstrapTable工具类
 * 
 * @author liangyanjun
 * @date 2016-06-30 11:54:30
 */

// 刷新BootstrapTable列表数据
function loadBootstrapTable(tableId) {
	$(tableId).bootstrapTable('refresh');
}
// BootstrapTable查询参数处理
function queryParams(params) { // 配置参数
	var searchFrom = $.serializeObject($("#searchFrom"));
	// searchFrom.sort=params.sort; //排序列名
	// searchFrom.order=params.order;//排位命令（desc，asc）
	searchFrom.limit = params.limit;
	searchFrom.offset = params.offset;
	return searchFrom;
}