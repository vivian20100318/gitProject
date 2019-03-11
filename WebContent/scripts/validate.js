
	//手机电话
	jQuery.validator.addMethod("isPhone", function(value,element) {   
			//var dou = /^[a-zA-Z_\u4E00-\u9FA5]+$/g;//汉字
			var mobile =  /^0?(13[0-9]|15[0-9]|18[0-9]|14[57])[0-9]{8}$/;
			//var tel = /^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$/;
			var tel = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/ ;
			return this.optional(element) || (mobile.test($.trim(value))) || (tel.test($.trim(value)));  
	}, "");
	//身份证
	jQuery.validator.addMethod("isIdcard", function(value,element) {   
			//var dou = /^[a-zA-Z_\u4E00-\u9FA5]+$/g;//汉字
			return this.optional(element) || validateIdCard(value) ;  
	}, "");
	//组织机构代码
	jQuery.validator.addMethod("isOrgcode", function(value,element) {   
			
			return this.optional(element) || validateOrgcode(value) ;  
	}, "");
	//姓名
	jQuery.validator.addMethod("isName", function(value,element) {   
			var dou = /^[a-zA-Z_\u4E00-\u9FA5]+$/g;//汉字
			return this.optional(element) || (dou.test($.trim(value))) ;  
	}, "");
	//订阅号名称
	jQuery.validator.addMethod("isChannelName", function(value,element) {   
			var dou = /^[a-zA-Z0-9_\u4E00-\u9FA5]+$/g;//汉字
			return this.optional(element) || (dou.test($.trim(value))) ;  
	}, "");
	//QQ
	jQuery.validator.addMethod("isQQ", function(value,element) {   
			var qq = /^\d{5,10}$/;
			return this.optional(element) || (qq.test($.trim(value))) ;  
	}, "");
	//传真
	jQuery.validator.addMethod("isFax", function(value,element) {   
			//var fax = /^[+]{0,1}(d){1,3}[ ]?([-]?((d)|[ ]){1,12})+$/;
			var fax = /^(\d{3,4}-)?\d{7,8}$/;
			return this.optional(element) || (fax.test($.trim(value))) ;  
	}, "");
	//密码
	jQuery.validator.addMethod("isPassword", function(value,element) {   
			//var password = /^[a-zA-Z0-9]+$/g;//汉字
			var password = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
			return this.optional(element) || (password.test($.trim(value))) ;  
	}, "");
	jQuery.validator.addMethod("haveLogo", function(value,element) {   
			if($("[name=logo]")){
				return true;
			}else{
				return false;
			}  
	}, "");
	jQuery.validator.addMethod("haveProv", function(value,element) {   
			 if($("[name=prov_img]")){
			 	return true;
			 }else{
			 	return false;
			 }
	}, "");

	
function validateIdCard(idCard){
	 //15位和18位身份证号码的正则表达式
	 var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[X])$)$/;

	 //如果通过该验证，说明身份证格式正确，但准确性还需计算
	 if(regIdCard.test(idCard)){
		  if(idCard.length==18){
			   var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
			   var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
			   var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
			   for(var i=0;i<17;i++){
			    idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
			   }
		
			   var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
			   var idCardLast=idCard.substring(17);//得到最后一位身份证号码
			
			   //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
			   if(idCardMod==2){
				    if(idCardLast=="X"||idCardLast=="x"){
				    	return true;
				    }else{
				    	return false;
				    }
			   }else{
				    //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
				    if(idCardLast==idCardY[idCardMod]){
				   	 return true;
				    }else{
				     return false;
				    }
   				}
  		}else{
  			 return false;
  		} 
	 }else{
	   return false;
	 }
}
//组织机构代码
 function validateOrgcode(value){
   //return ""==orgCode || orgCode.length==10;
   //return true;
   var ret=false;
   var codeVal = ["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
   var intVal =    [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35];
   var crcs =[3,7,9,10,5,8,4,2];
   if(!(""==value) && value.length==10){
      var sum=0;
      for(var i=0;i<8;i++){
         var codeI=value.substring(i,i+1);
         var valI=-1;
         for(var j=0;j<codeVal.length;j++){
             if(codeI==codeVal[j]){
                valI=intVal[j];
                break;
             }
         }
         sum+=valI*crcs[i];
      }
      var crc=11- (sum%11);
               
      switch (crc){
           case 10:{
               crc="X";
               break;
           }default:{
               break;
           }
       }
      //最后位验证码正确
      if(crc==value.substring(9)){
         ret=true;
      }else{
         ret=false;
       }
   }else if(""==value){
       ret=false;
   }else{
       ret=false;
   }
   return ret;
 }

