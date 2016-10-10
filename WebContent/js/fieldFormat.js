//金额格式化
function formatterMoney2(cellvalue, options, rowObject) {
	if(cellvalue){
		return accounting.formatMoney(cellvalue, "", 2, ",", ".");
	}else{
		return "";
	}
}

//状态格式化
function formatterUseStatus(cellvalue, options, rowObject) {
	if (cellvalue == 1) {
		return "有效";
	} else if (cellvalue == 2) {
		return "无效";
	} else {
		return "未知";
	}
}
//状态反格式化
function unformatUseStatus(cellvalue, options, rowObject) {
	if (cellvalue == "有效") {
		return 1;
	} else if (cellvalue == "无效") {
		return 2;
	} else {
		return 1;
	}
}
//权限类型格式化
function formatterPermisType(cellvalue, options, rowObject) {
	if (cellvalue == 1) {
		return "功能权限";
	} else if (cellvalue == 2) {
		return "数据权限";
	} else {
		return "未知";
	}
}
//权限类型反格式化
function unformatPermisType(cellvalue, options, rowObject) {
	if (cellvalue == "功能权限") {
		return 1;
	} else if (cellvalue == "数据权限") {
		return 2;
	} else {
		return 1;
	}
}