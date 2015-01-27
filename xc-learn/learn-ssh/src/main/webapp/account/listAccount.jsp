<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="/comm/include/pageHeader.jsp">
		<jsp:param name="ptitle" value="当前账单查看与记帐-时间的灰烬" />
		<jsp:param name="pdescription" value="时间的灰烬--作者：肖诚，邮箱：miraclelord@163.com"/>
		<jsp:param name="pkeywords" value="时间的灰烬,java,google开源项目"/>
	</jsp:include>
	
    
    <div class="container">
		<div class="row xccontent">
			<div class="span8">
				<%@include file="/account/currentAccounts.jsp"%>
			</div>
			<div class="span4">
				<h2>记账功能</h2>
				<form id="keepAcountForm" class="form-horizontal" action="${dynSite }/account/account_keepAccount" method="post">
					<s:token></s:token>          
					<div class="control-group">
						<label class="control-label" for="inputEmail">当前时间：</label>
						<div class="controls">
							<span class="input uneditable-input">
							<fmt:formatDate value="${nowTime}" pattern="yyyy-MM-dd HH:mm:ss" var="vnowTime"/>
							${vnowTime}
							</span>
						</div>
					</div>
					<div class="control-group" id="inputMoneyGroup">
						<label class="control-label" for="inputEmail">消费金额：</label>
						<div class="controls">
							<input name="amount" type="text" class="inputMoney" placeholder="0.0" required>
							<span id="inputMoneyInfo" class="help-inline" style="display: none;">请输入正确的金额</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">出资人：</label>
						<div class="controls">
							<select name="accountId">
								<c:forEach items="${accountInfos}" var="info">
									<option value="${info.accountUser.id }">${info.accountUser.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">参与人：</label>
						<div class="controls">
							<c:forEach items="${accountInfos}" var="info">
								<label class="checkbox">
									<input name="joinAccountIds" type="checkbox" id="inlineCheckbox1" value="${info.accountUser.id }"> ${info.accountUser.name }
								</label>
							</c:forEach>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">提交</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>	
      
    </div> <!-- /container -->
    
    <jsp:include page="/comm/include/pageFooter.jsp"/>
    
    <script>
		$(document).ready(function(){
			var regx = /^\d+(\.\d+)?$/;
			$("input[class='inputMoney']").blur(function(){  //blur事件处理
				var val = $(this).val();
				if(! regx.test(val)) {
					$(this).val('');
					$('#inputMoneyGroup').addClass("error");
					$('#inputMoneyInfo').show();
				} else {
					$('#inputMoneyGroup').removeClass("error");
					$('#inputMoneyInfo').hide();
				}
			}).bind("paste",function(){  //CTR+V事件处理 
				var val = $(this).val();
				if(! regx.test(val)) {
					$(this).val('');
					$('#inputMoneyGroup').addClass("error");
					$('#inputMoneyInfo').show();
				} else {
					$('#inputMoneyGroup').removeClass("error");
					$('#inputMoneyInfo').hide();
				}
			}).css("ime-mode", "disabled");  //CSS设置输入法不可用
			
			 $("#keepAcountForm").submit(function(e){
				 if($("[name='joinAccountIds']:checkbox:checked").length > 0) {
					 $("#keepAcountForm").submit();
				 } else {
					 alert("请最少选择一个参与人。");
					 return false;
				 }
			 });
		});
	</script>
    