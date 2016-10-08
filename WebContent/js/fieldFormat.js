//金额格式化
function formatterMoney2(cellvalue, options, rowObject) {
	if(cellvalue){
		return accounting.formatMoney(cellvalue, "", 2, ",", ".");
	}else{
		return "";
	}
}

//状态
function formatterUseStatus(cellvalue, options, rowObject) {
	if (cellvalue == 1) {
		return "有效";
	} else if (cellvalue == 2) {
		return "无效";
	} else {
		return "未知";
	}
}