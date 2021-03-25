<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 상세보기</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>

<script>
$(function(){
	 <c:if test="${msg != null}">
		alert("${msg}");
	</c:if>
	$("#modifyBtn").on("click", function(){
		$("#frm").attr("method", "get");
		$("#frm").attr("action", "${cp }/user/userModify");
		$("#frm").submit();
	});
	
	$("#deleteBtn").on("click", function(){
		alert("삭제하시겠습니까?");
		$("#frm").attr("method", "post");
		$("#frm").attr("action", "${cp }/user/deleteUser");
		$("#frm").submit();
	});
});
</script>
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<%@ include file="/WEB-INF/views/common/left_nav.jsp"%>

		</nav>
		<!-- /.navbar -->


		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<%@ include file="/WEB-INF/views/common/aside.jsp"%>

		</aside>


		<div id="if_list_div"
			style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height: 100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 상세보기</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form class="form-horizontal" role="form" id="frm">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix"
											style="text-align: center; width: 100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView"
												style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img src="${cp }/user/profile?userid=${user.userid }" />
											</div>
										</div>
										<br />
									</div>

								<input type="hidden" name="userid" value="${user.userid}" />
									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;"></span>아이디 <br>
										</label>
										<div class="col-sm-9 input-group-sm">
											<label class="control-label">${user.userid }</label>
										</div>
									</div>


									<div class="form-group row">
										<label for="pass" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;"></span>패스워드
										</label>
										<div class="col-sm-9 input-group-sm">
											<label class="control-label">*****</label>
										</div>
									</div>

									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;"></span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<label class="control-label">${user.usernm }</label>
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<label class="control-label">${user.alias }</label>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">등록일자
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<label class="control-label"><fmt:formatDate value="${user.reg_dt }"
																	pattern="yyyy-MM-dd" /></label>
										</div>
									</div>

									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<label class="control-label">${user.addr1 }</label>
										</div>
										<div class="col-sm-3 input-group-sm">
											<label class="control-label">${user.addr2 }</label>
										</div>

										<div class="col-sm-2 input-group-sm">
											<label class="control-label">${user.zipcode }</label>
										</div>
									</div>

									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="modifyBtn" class="btn btn-info">수정</button>
												<button type="button" id="deleteBtn" class="btn btn-info">삭제</button>
											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<!-- Main Footer -->
	<footer class="main-footer">
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</footer>

</body>
</html>







