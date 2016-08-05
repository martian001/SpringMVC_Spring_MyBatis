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
//提交文件
function submitFileForm(fileForm) {
	if ($("#offlineMeetingFiles").val() != '') {
		$(fileForm).ajaxSubmit({
			success : function(data) {
				var ret = eval("(" + data + ")");
				if (ret && ret.header["success"]) {
					layer.confirm(ret.header["msg"], {
						icon : 6,
						btn : [ '是' ]
					//按钮
					}, function() {
					    $('#fileUploadModal').modal('hide');
					    layer.closeAll('dialog');
					});
				} else {
					layer.alert(ret.header["msg"], {
						icon : 5
					});
				}
			}

		});
	} else {
		layer.alert('请选择文件!', {
			icon : 0
		});
	}
}
//时间格式转换
function convertDate(val,row){
	// 判断是否存在数据  如果不存在，直接退出方法 
	if(null == val || "" == val){
		return "";
	}
	// 去掉时间后面的  .加数字 
	var index = val.indexOf(".");
	// 如果不存在,那index就等于总长度
	if(index == -1){
		index = val.length;
	}
	// 截取时间字符串,并转换
	var str=val.substring(0,index).toString();
	// 把时间里面的  -  转换城 /
	str = str.replace(/-/g,"/");
	// 转换成时间
	var date = new Date(str);
	// 获取时间的年月日
	var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    // 返回指定的时间格式
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function convertDateTime(strDate){
	// 判断是否存在数据  如果不存在，直接退出方法 
	if(null == strDate || "" == strDate){
		return "";
	}
	// 去掉时间后面的  .加数字 
	var index = strDate.indexOf(".");
	// 如果不存在,那index就等于总长度
	if(index == -1){
		index = strDate.length;
	}
	// 截取时间字符串,并转换
	var str=strDate.substring(0,index).toString();
	// 把时间里面的  -  转换城 /
	str = str.replace(/-/g,"/");
	// 转换成时间
	var date = new Date(str);
	// 获取时间的年月日时分秒
	var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    // 拼接年月日
    var strDateTime = year+'-'+(month<10?('0'+month):month)+'-'+(day<10?('0'+day):day);
    // 拼接时分秒
    strDateTime += " "+(hour<10?('0'+hour):hour) + ':' + (minute<10?('0'+minute):minute) + ':' + (second<10?('0'+second):second);
    // 返回 YYYY-MM-dd hh:mm:ss
    return strDateTime;
}

