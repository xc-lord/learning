<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${empty result || result eq 'null' }">
		{ message : "结果为空" }
	</c:when>
	<c:otherwise>
		${result }
	</c:otherwise>
</c:choose>