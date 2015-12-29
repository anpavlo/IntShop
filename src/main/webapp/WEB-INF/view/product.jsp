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

</head>
<body>

	<div id="container">
		<div id="header">
			<div class="logo">
				<strong>Hello</strong><br> <span>This is test internet
					store</span>
			</div>
		</div>
		<div id="menu">
			<ul>
				<li><a href="main.html" >Main</a></li>
				<c:url var="productsListUrl"
						value="/productslist?id=${itemTree.idItemTree}" />
				<li>
					<ul class="dropdown">
						<li>	<a onclick="ItemTreeSelect(${itemTree.idItemTree})"
								id="input_str" href="#">${itemTree.value} </a>
						 <myTags:pr_tree list="${itemTree.childrenList}" /></li>
					</ul>
				</li>
				<li><a href="partners.html">Partners</a></li>
				<li><a href="about.html">About us</a></li>
				<li><a href="contact.html">Contact</a></li>
			</ul>
		</div>
		<div id="slider"></div>
		<div id="content">
			<div class="col_w560">

				<h2>Product</h2>
						<div class="image_wrapper image_fl">
							<img width="100" height="100" src="images/image_01.jpg"
								alt="TemplatemoImage01" />	
						</div> 
						
						<c:out value="Name: ${article.articleName}" /> <br>
						<c:out value="Description: ${article.articleDescription}" /><br>
						<c:out	value="Price: ${article.articlePrice}" /><br>
						

					
				<div class="hr_divider"></div>
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

			Copyright © 2048 <a href="#"></a> | </a>

		</div>
	</div>

</body>
</html>