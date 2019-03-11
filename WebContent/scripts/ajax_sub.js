	function commonparamsub(paramval,action,successCallBack,failCallBack){
		showBusyDialog("正在提交", '');
		dialogMask();
		debugger;
		setTimeout(function(){
		$.ajax({
			type:"POST",
			url:action,
			data:paramval,
			dataType : "json",
			success:function(data){
				destoryBusyDialog();
				dlgunmask();
				if(data.s=="0"){
					successCallBack(data.r);
				}
				if(data.s=="1"){
					//var respText = data.r;
					failCallBack(data.r);
				}
				if(data.s=="2"){
					//alert("aaa");
					window.top.document.getElementById("rightMain").src=js_web_root+"/jsp/logout.jsp";
				}
			}
		});
		},200);
	}
	
	function formparamsub(formname,action,successCallBack,failCallBack){
		
		showBusyDialog("正在提交", '');
		dialogMask();
		formname = "#"+formname;
		setTimeout(function(){
			$(formname).ajaxSubmit({  
		        type:"post",  //提交方式  
		        dataType:"json", //数据类型  
		        url:action, //请求url
		        async : false,
		        success:function(data){ //提交成功的回调函数  
//		        	debugger;
		        	destoryBusyDialog();
		     		dlgunmask();
		     		destoryBusyDialog();
					dlgunmask();
					if(data.s=="0"){
						successCallBack(data.r);
					}
					if(data.s=="1"){
						//var respText = data.r;
						failCallBack(data.r);
					} 
					if(data.s=="2"){
						//alert("aaa");
						window.top.document.getElementById("rightMain").src=js_web_root+"/jsp/logout.jsp";
					}
		        }  
		    }); 
		},200);
		
		
		
	}

	function formRedirect(url){
		var form = $("<form>");
		form.attr("action",url);
		form.attr('method','post');
		form.appendTo("body")
	    form.css('display','none')
	    form.submit();
	}
	function formRedirectWithData(url,obj){
	    // 创建一个 form  
	    var form1 = document.createElement("form");  
	    form1.id = "form1";  
	    form1.name = "form1";  
	  
	    // 添加到 body 中  
	    document.body.appendChild(form1);  
	  
	  /*
	    // 创建一个输入  
	    var input = document.createElement("input");  
	    // 设置相应参数  
	    input.type = "text";  
	    input.name = "value1";  
	    input.value = "1234567";  
	  
	    // 将该输入框插入到 form 中  
	    form1.appendChild(input);  
	  */
	  	for ( var p in obj ){
	        if ( typeof ( obj [ p ]) == " function " ){ 
	        	obj [ p ]() ;
	        } else {
	             if(isArray(obj [ p ])){
	                 for(var arr in obj [ p ]){
	                     var input = document.createElement("input");
	                     input.type = "hidden";
	                     input.name = p;
	                     input.value = obj[p][arr];
	                     form1.appendChild(input);  
	                 }
	             }else{
                     var input = document.createElement("input");
                     input.type = "hidden";
                     input.name = p;
                     input.value = obj[p];
                     form1.appendChild(input); 
	            }
	        }
	    }
	    // form 的提交方式  
	    form1.method = "POST";  
	    // form 提交路径  
	    form1.action = url;  
	  	//form1.style = "display:none;";
	    // 对该 form 执行提交  
	    form1.submit();  
	}
	function isArray(o) {  
		return Object.prototype.toString.call(o) === '[object Array]';   
	}    