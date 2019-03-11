/* 页面MASK的对象ID */
var PAGE_MASK_ID = "page-mask-id";

/* 页面MASK对象的样式类名 */
var MASK_CLASS_NAME = "dlg-mask";

/* 页面UNMASK对象的样式类名 */
var UNMASK_CLASS_NAME = "dlg-unmask";

/* 页面对话框对象显示的样式类名 */
var DIALOG_CLASS_SHOW = "dialog-show";

/* 页面对话框对象隐藏的样式类名 */
var DIALOG_CLASS_HIDE = "dialog-hide";


var PAGE_IFRAME_MASK = "parge-iframe-for-mask-dialog";

/* 当窗口大小改变时，如果是mask状态，则调整大小，以遮盖整个页面 */
function onWinResize() {
	var objMask = document.getElementById(PAGE_MASK_ID);
	if (objMask == null) {
		return;
	}
	if (objMask.className != MASK_CLASS_NAME) {
		return;
	}
	var width;
	var height;
	if (navigator.appName == 'Microsoft Internet Explorer') {
		width = document.documentElement.scrollWidth;
		height = document.documentElement.scrollHeight;
	} else {
		width = document.body.scrollWidth;
		height = document.body.scrollHeight;
	}
	objMask.style.width = width + "px";
	objMask.style.height = height + "px";
}/* onWinResize */

function doMask(mask) {
	var width;
	var height;
	if (navigator.appName == 'Microsoft Internet Explorer') {
		width = document.documentElement.scrollWidth;
		height = document.documentElement.scrollHeight;
	} else {
		if (document.documentElement.scrollHeight){
    	width = document.documentElement.scrollWidth;
			height = document.documentElement.scrollHeight;
   }else{
    	width = document.body.scrollWidth;
    	height = document.body.scrollHeight;
   }

		//width = document.body.scrollWidth;// window.innerWidth +
											// window.scrollMaxX;
		//height = document.body.scrollHeight;// window.innerHeight +
											// window.scrollMaxY;
	}

	/* 显示MASK */
	var objMask = document.getElementById(PAGE_MASK_ID);
	if (!objMask) {
		objMask = document.createElement("div");
		objMask.id = PAGE_MASK_ID;
		document.body.appendChild(objMask);
	}
	objMask.className = mask ? MASK_CLASS_NAME : UNMASK_CLASS_BAME;
	objMask.style.width = width + "px";
	objMask.style.height = height + "px";
}/* doMask(mask) */

/* 页面MASK */
function dialogMask() {
	doMask(true);
	window.onresize = onWinResize;
}

/* 页面unmask */
function dlgunmask() {
	var objMask = document.getElementById(PAGE_MASK_ID);
	if (objMask != null) {
		objMask.className = UNMASK_CLASS_NAME;
	}
	window.onresize = null;
}

/**
 * 将控件放在屏幕中心
 */
function centerScreen(oEm) {
	var left = 8;
	var top = 8;
	if (window.innerWidth) {
		left = window.pageXOffset + ((window.innerWidth - oEm.offsetWidth) / 2 - 32) 
			+ "px";
		top = window.pageYOffset  + ((window.innerHeight - oEm.offsetHeight)) / 2 + "px";
	} else {
		var scrolled = 0;
		if(document.documentElement && document.documentElement.scrollTop) {
		    scrolled = document.documentElement.scrollTop;
		} else if(document.body) {
			scrolled = document.body.scrollTop;
		}

		var doc = document.documentElement;
		left = (doc.scrollLeft + (doc.offsetWidth - oEm.offsetWidth) / 2 - 32) + "px";

		top = scrolled + (document.documentElement.offsetHeight - oEm.offsetHeight) / 2 
			+ "px";
	}
	oEm.style.left = left;
	oEm.style.top = top;

	//oEm.style.left = (document.body.offsetWidth - oEm.offsetWidth)/2 + 'px';
	//oEm.style.top = (document.body.offsetHeight - oEm.offsetHeight)/2 + 'px';
}
/**
 * 将控件放在父控件中心
 */
//function center(oEm, oParent) {
//	var result = getAbsolutePoint(oParent);
//	oEm.style.left = result.x + (oParent.offsetWidth - oEm.offsetWidth)/2 + 'px';
//	oEm.style.top = result.y + (oParent.offsetHeight - oEm.offsetHeight)/2 + 'px';	
//}
/**
 * 显示忙对话框
 */
function showBusyDialog(sMsg, sParentId) {
	var oDlg = document.createElement("div");
	var oElm = document.createElement('p');
	var oParent;
	
	oDlg.className = 'busy-dlg';
	oDlg.id = '_busy_dlg_id_';
	
	oElm.innerHTML = sMsg;
	oDlg.appendChild(oElm);
	document.body.appendChild(oDlg);
	
	if (sParentId != '') {
		//oParent = $(sParentId);
		//if (oParent) {
		//	center(oDlg, oParent);
		//} else {
			centerScreen(oDlg);
		//}
	} else {
		centerScreen(oDlg);
	}
}
/**
 * 销毁忙对话框
 */
function destoryBusyDialog() {
	var oDlg = document.getElementById('_busy_dlg_id_');
	if (oDlg) {
		//oDlg.style.display = 'none';
		document.body.removeChild(oDlg);
	}
}

/* 显示指定对象ID的对话框 */
function showDialog(dialog, width) {
	if ((typeof dialog) == "string") {
		dialog = document.getElementById(dialog);
	}
	if (!dialog) {
		return;
	}
	dialogMask();
	/* 改变样式 */
	dialog.className = DIALOG_CLASS_SHOW;

	dialog.style.width = width + "px";
	// dialog.style.height = height + "px";
	
	/* 可拖动 */
	//drag(dialog);
	
	/* 调整位置至居中 */
	center(dialog, width);
	
	if (navigator.appName == 'Microsoft Internet Explorer') {
		var mif = document.getElementById(PAGE_IFRAME_MASK);
		if (!mif) {
			mif = document.createElement('iframe');
			mif.className = 'mask-if';
			mif.id = PAGE_IFRAME_MASK;
			document.body.appendChild(mif);
		}
		
		mif.style.top = dialog.style.top;
		mif.style.left = dialog.style.left;
		mif.style.width = width + "px";
		mif.style.height = parseInt(dialog.offsetHeight, 10) + "px";
		mif.style.display = 'block';
	}
}/* showDialog(dlgId, width, height) */

function cancel(dialog) {
	var mif = document.getElementById(PAGE_IFRAME_MASK);
	if (mif) {
		mif.style.display = 'none';
	}
	if ((typeof dialog) == "string") {
		dialog = document.getElementById(dialog);
	}
	if (dialog != null) {
		dialog.className = DIALOG_CLASS_HIDE;
	}
	dlgunmask();
}/* cancel(dlgId) */

/* 对话框居中，参数：dlgId对话框div的id */
function center(dialog, width){
	if ((typeof dialog) == "string") {
		dialog = document.getElementById(dialog);
	}
	if (dialog == null && dialog.style.display == "none") {
		return;
	}
	var left = 8;
	var top = 8;
	if (window.innerWidth) {
		left = window.pageXOffset + ((window.innerWidth - width) / 2 - 32) 
			+ "px";
		top = window.pageYOffset  + ((window.innerHeight - dialog.offsetHeight)) / 2 + "px";
	} else {
		var scrolled = 0;
		if(document.documentElement && document.documentElement.scrollTop) {
		    scrolled = document.documentElement.scrollTop;
		} else if(document.body) {
			scrolled = document.body.scrollTop;
		}
		var doc = document.documentElement;
		left = (doc.scrollLeft + (doc.offsetWidth - width) / 2 - 32) + "px";
		top = scrolled + (document.documentElement.offsetHeight - dialog.offsetHeight) / 2 
			+ "px";
	}
	dialog.style.left = left;
	dialog.style.top = top;
}/* center(dlgId, width, height) */

function changeWindowByWidth(objwindow,winWidth){

	  var popdiv=document.getElementById(objwindow);
	  if(isopen==1 && tempwindow!="" && tempwindow!=objwindow){
	    isopen=0;
	    document.getElementById(tempwindow).style.display = 'none';
	   //return;
	  }
	  
	  if(isopen==0){
		
	     var dig = document.getElementById(objwindow);
		dig.style.display="";
		//dig.className = "dialog-show";
		showDialog(objwindow,winWidth);
	     isopen=1;
	     tempwindow = objwindow;
	  }else{
	    cancel(objwindow);
	    isopen=0;
	    tempwindow = objwindow;

	  }
	  return;
	}

/**
 * 刷新验证图片
 */
function refreshYzm(sImgId) {
	$(sImgId).src = js_jsp_root + '/utils/yzm.jsp?id=' + Math.random();
}

String.prototype.trim= function(){  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
};

// For ajax
var g_oRpcPostReq;
/**
 * 使用POST方式，向远程发送数据
 */
function rpcPost(url, fCallBack, sContent) {
	if (window.XMLHttpRequest) {
		g_oRpcPostReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		g_oRpcPostReq = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		return;
	}
	if (g_oRpcPostReq) {
		g_oRpcPostReq.onreadystatechange = fCallBack;
		g_oRpcPostReq.open("POST", url, true);
		g_oRpcPostReq.setRequestHeader("Content-Length", sContent.length);
		g_oRpcPostReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		g_oRpcPostReq.send(sContent);
	}
}
var g_oRpcGetReq;
/**
 * 使用GET方式，向远程发送数据
 */
function rpcGet(url, fCallBack, sContent) {
	if (window.XMLHttpRequest) {
		g_oRpcGetReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		g_oRpcGetReq = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		return;
	}
	if (g_oRpcGetReq) {
		g_oRpcGetReq.onreadystatechange = fCallBack;
		g_oRpcGetReq.open("GET", url, true);
		g_oRpcGetReq.send();
	}
}

/*全选和全不选*/
function changeAllCheck(obj,checkName){ 
   if(obj.checked){
      CheckAll(checkName);
   }else{
      CheckNull(checkName);
   }
}

function CheckAll(checkName)
{
   var a = document.getElementsByName(checkName); 
   for (var i=0;i<a.length;i++)
   {
	 if (!a[i].disabled) {
		 a[i].checked = true;
	 }     
   }
}
function CheckNull(checkName)
{
  var a = document.getElementsByName(checkName); 
  for (var i=0;i<a.length;i++)
  {
	  if (!a[i].disabled) {
		  a[i].checked = false;
	  }    
  }
}
function formatFileName(fileName) {
	if (fileName != '') {		
		var dot = fileName.indexOf('\\');
		while(dot >= 0) {
			fileName = fileName.replace('\\','/');
			dot = fileName.indexOf('\\');
		}
		dot = fileName.lastIndexOf('/');
		if (dot >= 0) {
			fileName = fileName.substring(dot + 1);
		}
		return fileName;
	}
	return fileName;
}
function replaceTextarea1(str){
	var reg=new RegExp("\r\n","g"); 
	var reg1=new RegExp(" ","g"); 
	var reg2=new RegExp("\n","g"); 
	var reg3=new RegExp("\"","g"); 

	str = str.replace(reg,"<br>"); 
	str = str.replace(reg2,"<br>"); 
  str = str.replace(reg1,"&nbsp;"); 
	str = str.replace(reg3,"&quot;"); 

	return str; 
}


function replaceTextarea2(str){
	var reg=new RegExp("<br>","g"); 
	var reg1=new RegExp("&nbsp;","g"); 
	var reg2=new RegExp("&quot;","g"); 

	str = str.replace(reg,"\r\n"); 
	str = str.replace(reg1," "); 
	str = str.replace(reg2,"\""); 
	return str; 
} 


function replaceTitle1(str){
	var reg=new RegExp("\"","g"); 
	var reg1=new RegExp("<","g"); 
	var reg2=new RegExp(">","g"); 
	var reg3=new RegExp("'","g"); 
	var reg4=new RegExp("&","g"); 

	str = str.replace(reg4,"&amp;");
	str = str.replace(reg,"&quot;"); 
	str = str.replace(reg1,"&lt;"); 
	str = str.replace(reg2,"&gt;");
	str = str.replace(reg3,"&apos;"); 
	
	
	return str; 
} 

function replaceTitle2(str){
	var reg=new RegExp("&quot;","g"); 
	var reg1=new RegExp("&lt;","g"); 
	var reg2=new RegExp("&gt;","g"); 
	var reg3=new RegExp("&apos;","g"); 
	var reg4=new RegExp("&amp;","g"); 

	str = str.replace(reg,"\""); 
	str = str.replace(reg1,"<"); 
	str = str.replace(reg2,">");
	str = str.replace(reg3,"'");
	str = str.replace(reg4,"&");
	
	return str; 
} 

function getLen(str) {
	var totallength=0;
	for (var i=0;i<str.length;i++)
	{
		var intCode=str.charCodeAt(i);
		if (intCode>=0&&intCode<=128) {
			totallength=totallength+1;//非中文单个字符长度加1
		}
		else {
			totallength=totallength+2;//中文字符长度则加2
		}
	}
	return totallength;
}

function checkStartWithStr(totalStr,str){
	if(str==null||str==""||totalStr.length==0||str.length>totalStr.length){
  	return false;
  }
	if(totalStr.substr(0,str.length)==str){
  	return true;
  }else{
  	return false;
  }
	return true;
}

function checkEndWithStr(totalStr,str){
	var newtotalStr = totalStr.toLowerCase();
	if(str==null||str==""||newtotalStr.length==0||str.length>newtotalStr.length){
  	return false;
  }
	if(newtotalStr.substr(newtotalStr.length-str.length,newtotalStr.length)==str){
  	return true;
  }else{
  	return false;
  }
	return true;
}

function checkNum(str){
	var reg=/^\d+$/; 
		
	return reg.test(str); 
} 

function emailCheck(val) {  
    //var objName = eval("document.all."+obj);  
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
    if (!pattern.test(val)) {   
        return false;  
    }  
    return true;  
}  

function checkIdcard(idcard){
var Errors=new Array(
"1",
"身份证号码位数不对!",
"身份证号码出生日期超出范围或含有非法字符!",
"身份证号码校验错误!",
"身份证地区非法!"
);
var area={"11":"北京","12":"天津","13":"河北","14":"山西","15":"内蒙古","21":"辽宁","22":"吉林","23":"黑龙江","31":"上海","32":"江苏","33":"浙江","34":"安徽","35":"福建","36":"江西","37":"山东","41":"河南","42":"湖北","43":"湖南","44":"广东","45":"广西","46":"海南","50":"重庆","51":"四川","52":"贵州","53":"云南","54":"西藏","61":"陕西","62":"甘肃","63":"青海","64":"宁夏","65":"新疆","71":"台湾","81":"香港","82":"澳门","91":"国外"};
var retflag=false;
var idcard,Y,JYM;
var S,M;
var idcard_array = new Array();
idcard_array = idcard.split("");
//地区检验
if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
//身份号码位数及格式检验
switch(idcard.length){
case 15:
if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 
100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
} else {
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
}
if(ereg.test(idcard)) 
return Errors[0];
else 
{
 return Errors[2];
}
break;
case 18:
//18位身份号码检测
//出生日期的合法性检查 
//闰年月日:
//((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-
//9]|30)|02(0[1-9]|[1-2][0-9]))
//平年月日:
//((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-
//9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && 
parseInt(idcard.substr(6,4))%4 == 0 )){
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
} else {
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
}
if(ereg.test(idcard)){//测试出生日期的合法性
//计算校验位
S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
+ parseInt(idcard_array[7]) * 1 
+ parseInt(idcard_array[8]) * 6
+ parseInt(idcard_array[9]) * 3 ;
Y = S % 11;
M = "F";
JYM = "10X98765432";
M = JYM.substr(Y,1);//判断校验位
if(M == idcard_array[17]) return Errors[0]; //检测ID的校验位
else return Errors[3];
}
else return Errors[2];
break;
default:
return Errors[1];
break;
}
}