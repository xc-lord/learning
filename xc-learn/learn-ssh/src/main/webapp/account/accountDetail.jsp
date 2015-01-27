<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<c:choose>
		<c:when test="${not empty pager.page }">
			<c:set var="varTitle" value="${accountUser.name }的账单明细,第${pager.page}页-时间的灰烬"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varTitle" value="${accountUser.name }的账单明细查看-时间的灰烬"></c:set>
		</c:otherwise>
	</c:choose>

	<jsp:include page="/comm/include/pageHeader.jsp">
		<jsp:param name="ptitle" value="${varTitle }" />
		<jsp:param name="pdescription" value="时间的灰烬--作者：肖诚，邮箱：miraclelord@163.com"/>
		<jsp:param name="pkeywords" value="时间的灰烬,java,google开源项目"/>
	</jsp:include>
	
    <c:set var="currUrl" value="account/account_accountDetail"></c:set>
    <div class="container">
		<div class="row xccontent">
			<div class="span12">
				<h2 class="text-center">
					${accountUser.name }的帐户金额：
					<c:if test="${accountUser.balance >= 0 }">
					<span class="text-success">
						<strong>
							<fmt:formatNumber value="${accountUser.balance }" pattern="0.00"/>
						</strong>
					</span>
					</c:if>
					<c:if test="${accountUser.balance < 0 }">
					<span class="text-error">
						<strong>
							<fmt:formatNumber value="${accountUser.balance }" pattern="0.00"/>
						</strong>
					</span>
					</c:if>
					，交易明细如下表
				</h2>
				<div class="pagination pagination-right">
					<ul>
						<c:choose>
							<c:when test="${expenseType == 1 }">
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }">全部</a></li>
								<li class="disabled"><span>消费出资</span></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-1">参与消费</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=2">结账收款</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-2">结账还款</a></li>
							</c:when>
							<c:when test="${expenseType == -1 }">
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }">全部</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=1">消费出资</a></li>
								<li class="disabled"><span>参与消费</span></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=2">结账收款</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-2">结账还款</a></li>
							</c:when>
							<c:when test="${expenseType == 2 }">
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }">全部</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=1">消费出资</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-1">参与消费</a></li>
								<li class="disabled"><span>结账收款</span></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-2">结账还款</a></li>
							</c:when>
							<c:when test="${expenseType == -2 }">
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }">全部</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=1">消费出资</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-1">参与消费</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=2">结账收款</a></li>
								<li class="disabled"><span>结账还款</span></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><span>全部</span></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=1">消费出资</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-1">参与消费</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=2">结账收款</a></li>
								<li><a href="${dynSite }/${currUrl }?accountId=${accountUser.id }&expenseType=-2">结账还款</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			
				<table class="table table-striped">
					<thead>
						<tr>
						<th><strong>姓名</strong></th>
						<th><strong>交易总金额</strong></th>
						<th><strong>交易类型</strong></th>
						<th><strong>交易实际金额</strong></th>
						<th><strong>交易的参与人</strong></th>
						<th><strong>交易时间</strong></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${records}" var="record">
						<tr>
							<td>${accountUser.name }</td>
							<td>
								<c:if test="${record.totalAmount >= 0 }">
								<span class="text-success">
									<strong>
										<fmt:formatNumber value="${record.totalAmount }" pattern="0.00"/>
									</strong>
								</span>
								</c:if>
								<c:if test="${record.totalAmount < 0 }">
								<span class="text-error">
									<strong>
										<fmt:formatNumber value="${record.totalAmount }" pattern="0.00"/>
									</strong>
								</span>
								</c:if>
							</td>
							<td>
								<c:choose>
									<c:when test="${record.expenseType == 1 }">
										<span class="text-success">消费出资</span>
									</c:when>
									<c:when test="${record.expenseType == -1 }">
										<span class="text-error">参与消费</span>
									</c:when>
									<c:when test="${record.expenseType == -2 }">
										<span class="text-error"><strong>结账还款</strong></span>
									</c:when>
									<c:when test="${record.expenseType == 2 }">
										<span class="text-success"><strong>结账收款</strong></span>
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:if test="${record.amount >= 0 }">
								<span class="text-success">
									<strong>
										<fmt:formatNumber value="${record.amount }" pattern="0.00"/>
									</strong>
								</span>
								</c:if>
								<c:if test="${record.amount < 0 }">
								<span class="text-error">
									<strong>
										<fmt:formatNumber value="${record.amount }" pattern="0.00"/>
									</strong>
								</span>
								</c:if>
							</td>
							<td>${record.joinNames }</td>
							<td>
								<fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd HH:mm:ss" var="createTime"/>
								${createTime }								
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
					
				</table>
				
			    <div class="pagination pagination-centered">
				    ${pager.pageHtml }
			    </div>
			</div>
			
		</div>	
      
    </div> <!-- /container -->
    
    <jsp:include page="/comm/include/pageFooter.jsp"/>   
    