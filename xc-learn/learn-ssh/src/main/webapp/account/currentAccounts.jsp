<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h2 class="text-center">当前的帐目</h2>
	<table class="table table-striped">
		<thead>
			<tr>
			<th><strong>姓名</strong></th>
			<th><strong>帐户余额</strong></th>
			<th><strong>最近交易</strong></th>
			<th><strong>相关操作</strong></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accountInfos}" var="info">
			<tr>
				<td>${info.accountUser.name }</td>
				<td>
					<c:if test="${info.accountUser.balance >= 0 }">
					<span class="text-success">
						<strong>
							<fmt:formatNumber value="${info.accountUser.balance }" pattern="0.00"/>
						</strong>
					</span>
					</c:if>
					<c:if test="${info.accountUser.balance < 0 }">
					<span class="text-error">
						<strong>
							<fmt:formatNumber value="${info.accountUser.balance }" pattern="0.00"/>
						</strong>
					</span>
					</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${empty info.record }">
							暂无交易
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${info.record.createTime}" pattern="yyyy-MM-dd HH:mm:ss" var="createTime"/>
							${createTime }&nbsp;&nbsp;${info.type}：
							<c:if test="${info.record.amount >= 0 }">
							<span class="text-success">
								<strong>
									<fmt:formatNumber value="${info.record.amount }" pattern="0.00"/>
								</strong>
							</span>
							</c:if>
							<c:if test="${info.record.amount < 0 }">
							<span class="text-error">
								<strong>
									<fmt:formatNumber value="${info.record.amount }" pattern="0.00"/>
								</strong>
							</span>
							</c:if>									
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="${dynSite }/account/account_accountDetail?accountId=${info.accountUser.id }"><i class="icon-search"></i>&nbsp;&nbsp;明细</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${dynSite }/account/account_settleAccounts?accountId=${info.accountUser.id }"><i class="icon-off"></i>&nbsp;&nbsp;结账</a>
				</td>
			</tr>
			</c:forEach>
			
		</tbody>
					
	</table>