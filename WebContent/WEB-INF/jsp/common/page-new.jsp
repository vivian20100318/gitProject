 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<c:if test="${!empty paginator.records}">
	<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; margin-left:20px">
						共有
						<span class="ui_txt_bold04">${paginator.rowsAmount}</span>
						条记录，当前第
						<span class="ui_txt_bold04">${paginator.currentPage}
						/
						${paginator.totalPages}</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						<c:if test="${paginator.totalPages>1 && paginator.currentPage>1 }">
							<input type="button" value="首页"  onclick="javascript:doQuery(1);"/>
							<input type="button" value="上一页"  onclick="javascript:doQuery(${paginator.currentPage-1});"/>
						</c:if>
						<c:if test="${paginator.totalPages>1 && paginator.currentPage<paginator.totalPages }">
							<input type="button" value="下一页" 
								onclick="javascript:doQuery(${paginator.currentPage+1});" />
							<input type="button" value="尾页" 
								onclick="javascript:doQuery(${paginator.totalPages});" />
							</c:if>												
						<!--     如果是最后一页，则只显示首页、上一页 -->
						
						转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" /> 页
							 <input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage('${paginator.totalPages}');" />
							 
					</div>
				</div>
</c:if>