<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="/account/listAccount.jsp"/>
	
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