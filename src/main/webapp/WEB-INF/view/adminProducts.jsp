<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/Dropdown.css"
	rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Products</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js">
	
</script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/resources/js/Dropdown.src.js">
	
</script>

<script type="text/javascript">
function deleteArticle(articleID){
	alert("Delete "+articleID);
}

</script>

</head>


<body>
<script type="text/javascript"></script>

	<div id="container">
		<div id="header">
			<div class="logo">
				<strong>Hello</strong><br>
				<span>This is test internet store</span>
			</div>
			<!-- <div>
				<form action="showTree" method="get">

					<input type="submit" value="Admin Page">
				</form>
			</div> -->
		</div>
		<div id="menu">
			<ul>
				<li><a href="showTree" >Category Tree</a></li>
				
				<c:url var="productsListUrl"	value="/adminProductslist?id=${itemTree.idItemTree}" />
				<li>
					<ul class="dropdown">
						<li>	<a id="input_str" href="${productsListUrl}">${itemTree.value} </a>
						 <myTags:adminPr_tree list="${itemTree.childrenList}" /></li>
					</ul>
				</li>
				<li><a href="#">Users</a></li>
				<li><a href="${pageContext.request.contextPath}/html/database.html">database</a></li>
				<li><a href="#">*******</a></li>
			</ul>
		</div>
		<div id="slider"></div>


		<div id="content">
			<div class="col_w560">

				<h2>Products</h2><br>
					
				<c:forEach items="${listArticle}" var="article">
					<c:url var="artiklePageUrl"
						value="/adminArticlepage?id=${article.idArticle}" />


					
					
					
					<div class="image_wrapper image_fl">
						<img width="100" height="100"
							src="${pageContext.request.contextPath}/photos/${article.idArticle}.jpg" />
					</div>
					<a href="${artiklePageUrl}">
						 <c:forEach
							items="${article.articleParam}" var="articleParams">
							<div>
								<div class="col_w140">
									<c:out value="${articleParams.param.paramName}" />
								</div>

								<div class="col_w100">
									<c:out value="${articleParams.paramValue.paramValue}" />
								</div>
							</div>

							<br>
						</c:forEach> 
					<c:out value="Description: ${article.articleDescription}" /><br>
					<c:out value="Price: ${article.articlePrice}" /><br>
					</a>
					<br><button onclick = "deleteArticle(${article.idArticle})">Delete</button>
					<div class="hr_divider"></div>
					
				</c:forEach>





				<div class="hr_divider"></div>



			</div>

			<div class="col_w280">

				<h2>Advertising</h2>
				<blockquote>
					<p>Additional text</p>

				</blockquote>

				<div class="hr_divider"></div>

				<blockquote>
					<p>Additional text</p>

				</blockquote>

			</div>
			<div style="clear: both"></div>
		</div>
		<div id="footer">

			Copyright © 2048 

		</div>
	</div>

</body>
</html>