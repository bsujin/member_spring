<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>memberList</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<%@ include file="/WEB-INF/views/common/search.jsp"%>

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
			style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
			<%@ include file="/WEB-INF/views/common/header.jsp"%>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<form action="${cp }/user/registUser" method="get">
							<button type="submit" class="btn btn-primary" id="insertBtn">회원등록</button>
							</form>
							<div id="keyword" class="card-tools" style="width: 550px;">
								<form id="search">
									<div class="input-group row">
										<select class="form-control col-md-3" name="pageSize" id="pageSize">
											<option value="">정렬개수</option>
											<option value="3">3개씩</option>
											<option value="5">5개씩</option>
											<option value="7">7개씩</option>
										</select>
										<!-- search bar -->
										<select class="form-control col-md-3" name="searchType" id="searchType">
											<option value="">검색구분</option>
											<option value="i">아이디</option>
											<option value="n">이름</option>
											<option value="a">별명</option>
										</select>
										<input class="form-control" type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요." value="">
										 <span class="input-group-append">
											<button class="btn btn-primary" type="button" id="searchBtn" data-search="search">
												<i class="fa fa-fw fa-search"></i>
											</button>
										</span>
									</div>
								</form>
							</div>
						</div>
						<form id="frm" action="${cp }/user/userDetail">
							<input type="hidden" id="userid" name="userid" value="/">
							<div class="card-body" style="text-align: center;">
								<div class="row">
									<div class="col-sm-12">
										<table class="table table-bordered">
											<tbody>
												<tr>
													<th>아이디</th>
													<th>이름</th>
													<th>별명</th>
													<th>도로주소</th>
													<th>등록날짜</th>
													<!-- yyyy-MM-dd  -->
												</tr>
												<tr>
													<c:forEach items="${userList }" var="user">
														<tr class="member" data-userid="${user.userid }">
															<td>${user.userid }</td>
															<td>${user.usernm }</td>
															<td>${user.alias }</td>
															<td>${user.zipcode }</td>
															<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy-MM-dd" /></td>
													</c:forEach>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- col-sm-12 -->
								</div>
								<!-- row -->
							</div>
						</form>
						<!-- card-body -->
						<div class="card-footer">
						<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link" href="${cp}/user/searchUser?page=1&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-double-left"></i></a></li>
									
									<c:choose>
										<c:when test="${pageVo.page-1<1}">
											<li class="page-item"><a class="page-link" href="${cp}/user/searchUser?page=${pageVo.page}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-left"></i></a></li>
										</c:when>
										<c:otherwise>										
											<li class="page-item"><a class="page-link" href="${cp}/user/searchUser?page=${pageVo.page-1}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-left"></i></a></li>
										</c:otherwise>
									</c:choose>
									<c:forEach begin="1" end="${pagination }" var="i">
										<c:choose>
											<c:when test="${pageVo.page } == ${i}">
												<li class="active"><span>${i }</span></li>
											</c:when>
											<c:otherwise>
												<li class="page-item active"><a class="page-link"
													href="${cp }/user/searchUser?page=${i}&pageSize=${pageVo.pageSize }">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- <li class="page-item active"><a class="page-link" href="#">1</a></li> -->
									<c:choose>
										<c:when test="${pageVo.page+1>pagination}">
											<li class="page-item"><a class="page-link" href="${cp}/user/searchUser?page=${pageVo.page}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-right"></i></a></li>
										</c:when>
										<c:otherwise>										
											<li class="page-item"><a class="page-link" href="${cp}/user/searchUser?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
									<li class="page-item"><a class="page-link" href="${cp}/user/searchUser?page=${pagination}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>
						</div>
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
		</div>
	</div>


	<!-- Main Footer -->
	<footer class="main-footer">
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</footer>
	<!-- ./wrapper -->

</body>
</html>







