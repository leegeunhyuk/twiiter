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

<%@ include file="/views/common/header.jspf"%>

<!-- Header ========================================================================================== -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="jumbotron">
                    <h1>나무 소셜과 함께!</h1>
                    <p>오늘은 어땠나요? 나무 소셜과 함께 대화를 시작하세요.</p>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <!-- ★★★ Tab Menu -->
            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li class="active"><a href="#" data-toggle="tab">나의메시지</a></li>
                <li><a href="#" data-toggle="tab">최신메시지</a></li>
            </ul>


            <!-- ★★★ Tab Panel -->
            <div id="communityList" class="tab-content">
                <!-- ★★★ 가입 커뮤니티 -->
                <div class="tab-pane fade active in" id="joined">
                    <div class="page-header">
                        <h3 id="container">오늘의 상태 업데이트</h3>
                        <form class="form-search" action="${pageContext.request.contextPath}/message/regist.do">
                            <div class="input-group">
                        		<input type="text" class="form-control" name="contents" placeholder="내용을 입력하세요">
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-primary">등록</button>
                                </span>
                            </div>
                        </form>
                    </div>

                    <ul class="list-group">
                        <c:forEach var="message" items="${messages}" varStatus="sts">
	                        
	                        <li class="list-group-item">
                            	<span class="badge">${message.registDate }</span>
                            	<h4><a href="${pageContext.request.contextPath }/user">${message.writerId }</a></h4>
                            	<p>${message.contents }</p>
                        	</li>
                        	
                        </c:forEach>
                        
                        
                    </ul>

                    <button type="button" class="btn btn-default btn-lg btn-block">메시지 목록 더보기</button>
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
                    <li><a href="index.jsp">소셜 홈</a>
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