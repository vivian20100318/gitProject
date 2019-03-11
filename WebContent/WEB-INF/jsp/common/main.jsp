<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.hjkj.common.MctsSessionKeys"%>
<%@page import="com.hjkj.business.util.WebUtils"%>
<%@page import="com.hjkj.business.util.MctsUtils"%>
<%@ page import="com.hjkj.business.common.WebConstants"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>病案邮寄后台管理系统</title>
    <script src="<%=WebConstants.WEB_ROOT%>/scripts/jquery.form.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/jquery/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/jqueryValidate/js/jquery.validate.min.js"></script>
    <link href="<%=WebConstants.WEB_ROOT%>/style/authority/main_css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=WebConstants.WEB_ROOT%>/style/zTreeStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=WebConstants.WEB_ROOT%>/scripts/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/zTree/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/zTree/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/jquery-ui/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/md5.js"></script>
    <script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/utils.js"></script>
    <script type="text/javascript">
    	
        /**退出系统**/
        function logout() {
            if (confirm("您确定要退出本系统吗？")) {
                //window.location.href = "";
                window.location.href = "<%=WebConstants.WEB_ROOT%>/logout.do?<%=MctsUtils.getURLRandom() %>";
            }
        }
        function logoutPwd() {
            window.location.href = "<%=WebConstants.WEB_ROOT%>/logout.do?<%=MctsUtils.getURLRandom() %>";
        }
        /**获得当前日期**/
        function getDate01() {
            var time = new Date();
            var myYear = time.getFullYear();
            var myMonth = time.getMonth() + 1;
            var myDay = time.getDate();
            if (myMonth < 10) {
                myMonth = "0" + myMonth;
            }
// 			document.getElementById("yue_fen").innerHTML =  myYear + "." + myMonth;
            document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
        }

        /**加入收藏夹**/
        function addfavorite() {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.indexOf("360se") > -1) {
                art.dialog({
                    icon: 'error',
                    title: '友情提示',
                    drag: false,
                    resize: false,
                    content: '由于360浏览器功能限制，加入收藏夹功能失效',
                    ok: true,
                });
            } else if (ua.indexOf("msie 8") > -1) {

            } else if (document.all) {

            } else {
                art.dialog({
                    icon: 'error',
                    title: '友情提示',
                    drag: false,
                    resize: false,
                    content: '添加失败，请用ctrl+D进行添加',
                    ok: true,
                });
            }
        }
 
        /* zTree插件加载目录的处理  */
        var zTree;

        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                expandSpeed: ($.browser.msie && parseInt($.browser.version) <= 6) ? "" : "fast"
            },
            data: {
                key: {
                    name: "resource_name"
                },
                simpleData: {
                    enable: true,
                    idKey: "resource_id",
                    pIdKey: "parent_id",
                    rootPId: ""
                }
            },
            callback: {
                beforeExpand: beforeExpand,
                // 				onExpand: onExpand,
                onClick: zTreeOnClick
            }
        };

        var curExpandNode = null;
        function beforeExpand(treeId, treeNode) {
            var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
            var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
            for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
                if (treeNode !== treeNodeP.children[i]) {
                    zTree.expandNode(treeNodeP.children[i], false);
                }
            }
            while (pNode) {
                if (pNode === treeNode) {
                    break;
                }
                pNode = pNode.getParentNode();
            }
            if (!pNode) {
                singlePath(treeNode);
            }

        }
        function singlePath(newNode) {
            if (newNode === curExpandNode) return;
            if (curExpandNode && curExpandNode.open == true) {
                if (newNode.parentTId === curExpandNode.parentTId) {
                    zTree.expandNode(curExpandNode, false);
                } else {
                    var newParents = [];
                    while (newNode) {
                        newNode = newNode.getParentNode();
                        if (newNode === curExpandNode) {
                            newParents = null;
                            break;
                        } else if (newNode) {
                            newParents.push(newNode);
                        }
                    }
                    if (newParents != null) {
                        var oldNode = curExpandNode;
                        var oldParents = [];
                        while (oldNode) {
                            oldNode = oldNode.getParentNode();
                            if (oldNode) {
                                oldParents.push(oldNode);
                            }
                        }
                        if (newParents.length > 0) {
                            for (var i = Math.min(newParents.length, oldParents.length) - 1; i >= 0; i--) {
                                if (newParents[i] !== oldParents[i]) {
                                    zTree.expandNode(oldParents[i], false);
                                    break;
                                }
                            }
                        } else {
                            zTree.expandNode(oldParents[oldParents.length - 1], false);
                        }
                    }
                }
            }
            curExpandNode = newNode;
        }

        function onExpand(event, treeId, treeNode) {
            curExpandNode = treeNode;
        }

        /** 用于捕获节点被点击的事件回调函数  **/
        function zTreeOnClick(event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
            zTree.expandNode(treeNode, null, null, null, true);
            // 		zTree.expandNode(treeNode);
            // 规定：如果是父类节点，不允许单击操作
            //if (treeNode.isParent) {
                // 			alert("父类节点无法点击哦...");
            //    return false;
            //}
            // 如果节点路径为空或者为"#"，不允许单击操作
            if (treeNode.accessPath == "" || treeNode.accessPath == "#") {
                //alert("节点路径为空或者为'#'哦...");
                return false;
            }
            var accessPath = treeNode.accessPath;
            
            // 跳到该节点下对应的路径, 把当前资源ID(resourceID)传到后台，写进Session
            var urlStr = "<%=WebConstants.WEB_ROOT%>" + accessPath;
//           alert(urlStr);
            rightMain(urlStr);
            var name3=treeNode.resource_name;
            if(treeNode.level==0){
				$('#here_area').html('当前位置：&nbsp;>&nbsp;<span style="color:#0099ff">' +name3 + '</span>');
			} 
            var name2=treeNode.getParentNode().resource_name;
			if(treeNode.level==1){
				 $('#here_area').html('当前位置：'+name2+'&nbsp;>&nbsp;<span style="color:#e75f49">' +name3 + '</span>');
			}else if(treeNode.level==2){
	            var name1 = treeNode.getParentNode().getParentNode().resource_name;	
	            $('#here_area').html('当前位置：'+name1+'&nbsp;>&nbsp;'+name2+'&nbsp;>&nbsp;<span style="color:#e75f49">' +name3 + '</span>');
			}else if(treeNode.level==3){
	            var name1 = treeNode.getParentNode().getParentNode().resource_name;	
	            var name0 = treeNode.getParentNode().getParentNode().getParentNode().resource_name;	
	            $('#here_area').html('当前位置：'+name0+'&nbsp;>&nbsp;'+name1+'&nbsp;>&nbsp;'+name2+'&nbsp;>&nbsp;<span style="color:#e75f49">' +name3 + '</span>');
			}
        }
        ;

        /* 上方菜单 */
        function switchTab(tabpage, tabid) {
            var oItem = document.getElementById(tabpage).getElementsByTagName("li");
            for (var i = 0; i < oItem.length; i++) {
                var x = oItem[i];
                x.className = "";
            }
            if ('left_tab1' == tabid) {
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"病案邮寄模块"下的菜单
                loadMenu('MAILMOD', 'dleft_tab1');
            } else if ('left_tab2' == tabid) {
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"系统权限管理"下的菜单
                loadMenu('SYSTEM', 'dleft_tab1');
            } 

        }


        $(document).ready(function () {
            $(document).ajaxStart(onStart).ajaxSuccess(onStop);
            /** 默认异步加载"业务模块"目录  **/
            loadMenu("", "dleft_tab1");
            // 默认展开所有节点
            if (zTree) {
                // 默认展开所有节点
                //zTree.expandAll(true);
            }
        });

        function loadMenu(resourceType, treeObj) {
            $.ajax({
                type: "POST",
                url: "<%=WebConstants.WEB_ROOT%>/getMenuListByType.rpc?resource_type=" + resourceType,
                dataType: "json",
                success: function (data) {
                    // 如果返回数据不为空，加载"业务模块"目录
                    if (data != null) {
                        // 将返回的数据赋给zTree
                        $.fn.zTree.init($("#" + treeObj), setting, data);
                        //alert(treeObj);
                        zTree = $.fn.zTree.getZTreeObj(treeObj);
                        if (zTree) {
                            // 默认展开所有节点
                            zTree.expandAll(true);
                        }
                    }
                }
            });

        }

        //ajax start function
        function onStart() {
            $("#ajaxDialog").show();
        }

        //ajax stop function
        function onStop() {
            // 		$("#ajaxDialog").dialog("close");
            $("#ajaxDialog").hide();
        }
    </script>
   <style type="text/css"> 

</style> 
</head>
<body onload="getDate01()">
<div id="top" style="border-bottom: 1px solid #d84b34;">


    <table border="0" min-width="800px" width="100%" style="text-align:center;">
        <tr>
            <td width="70%" style="text-align:left;">
                <!-- <div style="min-width:400px">
                    <div id="top_logo">
                        <img alt="logo" src=""
                             style="margin-bottom: 20px;">
                    </div>
                </div> -->
            </td>
            <td width="8%">
                <div style="min-width:180px;">
                    <i class='m_user'></i>
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前用户:
                    <span>${user.user_id}</span>
                    <input type="hidden" value="${user.user_id }" id="userId" name="userId"  />
                </div>
            </td>
           <%--  <td width="8%" id="msgTd">
                <div style="min-width:120px; cursor:pointer;" onclick="showMsg()" id="msgBtn">
                    <i class='m_news' style="top:23px;left:30px"></i>
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;消息
                    <span id="msgCount"></span>
                    <input id="msgCountId" value="0"/>
                </div>
                <a href='<%=WebConstants.WEB_ROOT%>/goToMsg.do' target='right' onclick='location.href=<%=WebConstants.WEB_ROOT%>/goToMsg.do' style="display: none" id="tomsg" class="tomsg" >查看更多</a>
                <div id="msgDiv" style="width: 200px; height:200px; position:fixed; top: 70px;z-index: 10;background-color: #F5F7FA;border: 1px solid #ddd;display:none">
                	<ul id="msgContent" style="z-index: 11">
                	</ul>
               		<a href='<%=WebConstants.WEB_ROOT%>/goToMsg.do' target='right' style="color: black;position: absolute; bottom: 0px; right: 70px" id="goToMsg" >查看更多</a>
               		
                </div>
                
            </td> --%>
             <td width="5%">
                <div style="min-width:136px;">
                    <a href="javascript:void(0);" style="color: #000; text-decoration: none;" onclick="updatepwd();" title="修改密码">
                    <i class='m_pdws'></i>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>修改密码</span>
                    </a>
                </div>
            </td>
            <td width="8%">
                <div style="min-width:200px;">
                <i class='m_date'></i>
                    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今天是
                    <span id="day_day"></span>
                </div>

            </td>
            <td width="6%" style="text-align:left;">
                <div style="min-width:130px;">
                    <a href=" javascript:void(0);" style="color: #000; text-decoration: none;" onclick="logout();" target="_parent">
                        <i class='m_tuichu'></i>
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <!-- <a href="Driving/goToMsg.do" tar/> -->
                        <span> 退出系统 </span>
                    </a>
                </div>
            </td>

        </tr>
    </table>
    <!-- 
    <div id="" style="width: 100%; height: 1px; background: #d84b34; margin-top: 4px;">

    </div>
     -->
</div>
<div id="side">
    <div id="left_menu_cnt">
        <div id="nav_resource">
            <ul id="dleft_tab1" class="ztree"></ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#TabPage2 li').click(function () {
            var index = $(this).index();
            $(this).find('img').attr('src', 'img/homepage/' + (index + 1) + '_hover.png');
            $(this).css({background: '#fff'});
            $('#nav_module').find('img').attr('src', 'img/homepage/module_' + (index + 1) + '.png');
            $('#TabPage2 li').each(function (i, ele) {
                if (i != index) {
                    $(ele).find('img').attr('src', 'img/homepage/' + (i + 1) + '.png');
                    $(ele).css({background: '#e75f49'});
                }
            });
            // 显示侧边栏
            switchSysBar(true);
        });

        // 显示隐藏侧边栏
        $("#show_hide_btn").click(function () {
            switchSysBar();
        });
    });

    function switchSysBar(flag) {
        var side = $('#side');
        var left_menu_cnt = $('#left_menu_cnt');
        if (flag == true) {	// flag==true
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px'});
            $('#main').css({left: '280px'});
        } else {
            if (left_menu_cnt.is(":visible")) {
                left_menu_cnt.hide(10, 'linear');
                side.css({width: '60px'});
                $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
                $('#main').css({left: '60px'});
                $("#show_hide_btn").find('img').attr('src', '<%=WebConstants.WEB_ROOT%>/img/homepage/right.png');
            } else {
                left_menu_cnt.show(500, 'linear');
                side.css({width: '280px'});
                $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
                $('#main').css({left: '280px'});
                $("#show_hide_btn").find('img').attr('src', '<%=WebConstants.WEB_ROOT%>/img/homepage/left.png');
            }
        }
    }
    function updatepwd() {
        $("#pwd_iframediv").dialog({
            title: "修改密码",
            autoOpen: false,
            modal: true,
            resizable: false,
            width: "585",
            height: "500",
            buttons: {
                "修改": function () {
//     	 		        update();
                    window.pwd_iframe.update();
                },
                "取消": function () {
                    $("#pwd_iframediv").dialog("close");
                }
            }
        });
        $("#pwd_iframediv").dialog("open");
    }
    function closePwd() {
        $("#pwd_iframediv").dialog("close");
    }
</script>

<!-- side menu start -->
<div id="top_nav">
    <span id="here_area">当前位置：<span style="color:#e75f49">欢迎页</span></span>
</div>
<div id="pwd_iframediv" style="display:none;background:#ffffff; ">
    <iframe id="pwd_iframe" name="pwd_iframe" frameborder="0" scrolling="no" style="width:540px;height:330px; "
            src=""></iframe>
</div>
<div id="main">
    <iframe name="right" id="rightMain" src="<%=WebConstants.WEB_ROOT%>/jsp/shouye.jsp"  frameborder="no"
            scrolling="auto" width="100%"  height="100%" allowtransparency="true" />

</div>

</body>

</html>
   
 