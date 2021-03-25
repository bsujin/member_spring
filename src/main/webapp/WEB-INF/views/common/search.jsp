<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script type="text/javascript">
	$(function() {
		 <c:if test="${msg != null}">
			alert("${msg}");
		</c:if>
		
		$(".member").on("click", function() {
			var userid = $(this).data("userid");
			//select를 해서 값을 userid 
			$("#userid").val(userid);
			//form태그를 이용하여 전송
			$("#frm").submit();

		});
		
		$("#searchBtn").on("click", function() {
			$("#search").attr("method", "get");
			$("#search").attr("action", "${cp }/user/searchUser");
			$("#search").submit();
		});
		
		$('#pages').val('${pageVo.pageSize }');
		$('#searchT').val('${searchType}');
		$('#key').val('${keyword}');
		
	})	
</script>