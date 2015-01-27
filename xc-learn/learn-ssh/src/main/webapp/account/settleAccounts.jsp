<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="/comm/include/pageHeader.jsp">
		<jsp:param name="ptitle" value="当前账单查看与结账-时间的灰烬" />
		<jsp:param name="pdescription" value="时间的灰烬--作者：肖诚，邮箱：miraclelord@163.com"/>
		<jsp:param name="pkeywords" value="时间的灰烬,java,google开源项目"/>
	</jsp:include>
	
    
    <div class="container">
		<div class="row xccontent">
			<div class="span8">
				<%@include file="/account/currentAccounts.jsp"%>
			</div>
			<div class="span4">
				<h2 class="text-center">${accountUser.name }正在结账：</h2>
				<br/>
				<form class="form-horizontal" action="${dynSite }/account/account_settleAccounts" method="post">
					<s:token></s:token>          
					<div class="control-group">
						<label class="control-label">当前时间：</label>
						<div class="controls">
							<span class="input uneditable-input">
							<fmt:formatDate value="${nowTime}" pattern="yyyy-MM-dd HH:mm:ss" var="vnowTime"/>
							${vnowTime}
							</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">帐户金额：</label>
						<div class="controls">
							<span class="input uneditable-input">
								<fmt:formatNumber value="${accountUser.balance }" pattern="0.00"/>
							</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">结账还款金额：</label>
						<div class="controls">
							<input name="amount" type="number" class="inputMoney" placeholder="0.0" required>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">结账收款用户：</label>
						<div class="controls">
							<select name="traderId" id="traderIdSelect">
								<option value="noChoose">- 请选择 -</option>
								<c:forEach items="${accountInfos}" var="info">
									<option value="${info.accountUser.id }">${info.accountUser.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>					
					<div class="control-group">
						<div class="controls">
							<button type="button" class="btn btn-primary" id="settleAccountsSubmit">提交</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>	
      
    </div> <!-- /container -->
    
    <jsp:include page="/comm/include/pageFooter.jsp"/>
    
    <script type="text/javascript">
		$(document).ready(function(){
			$("input[class='inputMoney']").keyup(function(){  //keyup事件处理 
				$(this).val($(this).val().replace(/\D|^0/g,''));
			}).bind("paste",function(){  //CTR+V事件处理 
				$(this).val($(this).val().replace(/\D|^0/g,''));  
			}).css("ime-mode", "disabled");  //CSS设置输入法不可用
			
			$("#settleAccountsSubmit").click(function(){
				var selectVal = $("#traderIdSelect").val();
				if(selectVal == "noChoose") {
					alert("请选择收款用户！");
				} else {
					alert("提交成功" + selectVal);
				}
			});
			
		});
	</script>
    
    <c:if test="${not empty succeed }">
    	<c:choose>
    		<c:when test="${succeed }">
    			<script type="text/javascript">
				<!--
					alert("提交成功！");
				//-->
				</script>
    		</c:when>
    		<c:otherwise>
    			<script type="text/javascript">
				<!--
					alert("提交失败！");
				//-->
				</script>
    		</c:otherwise>
    	</c:choose>
	    
    </c:if>