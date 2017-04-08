<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>나무소셜</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootswatch.min.css" rel="stylesheet">
</head>
<body>

<!-- Main Navigation ========================================================================================== -->

<%@ include file="/views/common/header.jspf"%>

<!-- Header ========================================================================================== -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="jumbotron">
                    <h2>팔로잉으로 인연을 이어가세요</h2>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->
<div class="container">
    <div class="row">

        <!-- ★★★ Contents -->
        <div class="col-lg-12">
            <div class="page-header2">
                <h3>팔로잉 사용자 목록</h3>
            </div>

            <!-- ★★★ Tab Panel -->
            <div id="communityList" class="tab-content">
                <!-- ★★★ 전체회원 -->
                <div class="tab-pane fade active in" id="all">

                    <!-- ★★★ 검색조건 -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="회원명 또는 ID">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-primary">검색</button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>

                    <!-- ★★★ 회원목록 -->
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center">번호</th>
                                <th class="text-center">ID</th>
                                <th class="text-center">회원명</th>
                                <th class="text-center">이메일</th>
                                <th class="text-center">취소</th>
                            </tr>
                            </thead>
                            <tbody>
                        
                    
                    <c:choose>
                    	<c:when test="${followingCount == 0}">
                    		 <tr><td colspan="5" class="text-center">팔로잉 사용자가 없습니다.</td></tr>
                    	</c:when>
                    	<c:otherwise>
                    		<c:forEach var="user" items="${followings}" varStatus="sts">
                    			<tr>
                                    <td class="text-center">${sts.count }</td>
                                    <td class="text-center">${user.id }</td>
                                    <td class="text-center">${user.name }</td>
                                    <td class="text-center">${user.email }</td>
                                    <td class="text-center"><a href="${pageContext.request.contextPath}/user/unfollow.do?toUserId=${user.id}">언팔로우</a></td>
                        		</tr>
                    		</c:forEach>
                    	</c:otherwise>
                    </c:choose>
                            
                            
                            
                               
                               
                                
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>

<!-- Footer ========================================================================================== -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <ul class="list-unstyled">
                    <li class="pull-right"><a href="#top">위로 이동</a></li>
                    <li><a href="#">소셜 홈</a>
                    </li>
                    <li><a href="#">RSS</a></li>
                    <li><a href="#">이용약관</a></li>
                    <li><a href="#">도움말</a></li>
                    <li><a href="#">회원탈퇴</a></li>
                </ul>
                <p>© NamooSori 2016.</p>
            </div>
        </div>
    </footer>
</div>

</body>
</html>