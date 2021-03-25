<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="kr">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 등록</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<script src="${cp }/js/memberreg.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	$(function() {
		 <c:if test="${msg != null}">
			alert("${msg}");
		</c:if>
		
		// 정규식 검사하기
		$('#cancelBtn').on('click', function() {
			document.location.reload(true);
		})

		$('#userid').keyup(function() {
			if (!idcheck()) {
				return false;
			}
		else{
 		$('#idbtn').on('click',function(){
				idvalue = $('#userid').val().trim();
					$("#frm").attr("method", "get");
					$("#frm").attr("action", "${cp }/user/idsearch?userid="+idvalue);
					$("#frm").submit();
 				})
			}
		})

		$('#usernm').keyup(function() {
			if (!namecheck()) {
				return false;
			}
		})
		$('#pass').keyup(function() {
			if (!pwdcheck()) {
				return false;
			}
		})
		
		// 	전송 - 회원가입신청
		$('#registBtn').on('click', function() {
			if (!regcheck()) {
				return false;
			}
			$("#frm").submit();
		})

		//주소검색 버튼이 클릭 되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {

					$("#addr1").val(data.roadAddress); //도로주소
					$("#zipcode").val(data.zonecode); //우편번호

					//사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
					$("#addr2").focus();
				}
			}).open();
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
							<b>회원 등록</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" class="form-horizontal" id="frm"
									action="${cp }/user/registUser" method="post"
									enctype="multipart/form-data">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix"
											style="text-align: center; width: 100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView"
												style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img id="pictureViewImg" style="width: 100%; height: 100%;" />
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="profile" class="form-control" type="file"
														name="profile" accept=".gif, .jpg, .png"
														style="height: 37px;" />
												</div>
											</div>
										</div>
										<br />
									</div>

									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>아이디 <br>
											<span id="idCk" style="color: red;">${notice }</span>
										</label>
										<div class="col-sm-9 input-group-sm">
											<input type="text" class="form-control" id="userid"
												name="userid" placeholder="회원 아이디" value="${param.userid}" />
											<input id="idbtn" class="btn btn-default" type="button"
												value="아이디 중복검사">
										</div>
									</div>


									<div class="form-group row">
										<label for="pass" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드 <span
											id="pwCk"></span>
										</label>
										<div class="col-sm-9 input-group-sm">
											<input type="password" class="form-control" id="pass"
												name="pass" placeholder="비밀번호" value="${param.pass }" />
										</div>
									</div>

									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름 <span
											id="nmCk"></span>
										</label>
										<div class="col-sm-9 input-group-sm">
											<input type="text" class="form-control" id="usernm"
												name="usernm" placeholder="사용자 이름" value="${param.usernm }" />
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="alias" type="text"
												id="alias" placeholder="별명" value="${param.alias }">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">등록일자
<!-- 										<span style="color: red;">&nbsp;&nbsp;${date }&nbsp;&nbsp;</span> -->
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<input type="text" class="form-control" id="reg_dt2"
												name="reg_dt2" placeholder="yyyy-MM-dd형식으로 작성" value="${param.reg_dt }" />
										</div>
									</div>

									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input name="addr1" type="text" class="form-control"
												id="addr1" placeholder="주소" value="${param.addr1 }" readonly>
										</div>
										<div class="col-sm-6">
											<span class="input-group-append-sm">
												<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
											</span>
										</div>
										<div class="col-sm-3 input-group-sm">
											<input name="addr2" type="text" class="form-control"
												id="addr2" placeholder="상세주소" value="${param.addr2 }">
										</div>

										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" class="form-control"
												id="zipcode" placeholder="우편번호" value="${param.zipcode }"
												readonly>
										</div>
									</div>

									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="registBtn" class="btn btn-info">등록</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn"
													class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취
													&nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
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
	<!-- ./wrapper -->
	<script>
		$(document).ready(function() {
			// picture input의 파일 변경시 이벤트 
			$("#profile").change(function() {
				readURL(this);
			});
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#pictureViewImg').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>


</body>
</html>







