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
<script type="text/JavaScript"
 src="${pageContext.request.contextPath}/resources/js/ArticleUtil.js">
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
				<li><a href="showTree" >Category Tree</a></li>
				
				<c:url var="productsListUrl"	value="/adminProductslist?id=${itemTree.idItemTree}" />
				<li>
					<ul class="dropdown">
						<li>	<a id="input_str" href="${productsListUrl}">${itemTree.value} </a>
						 <myTags:adminPr_tree list="${itemTree.childrenList}" /></li>
					</ul>
				</li>
				<li><a href="#">Users</a></li>
				<li><a href="#">*******</a></li>
				<li><a href="#">*******</a></li>
			</ul>
		</div>
		<div id="slider"></div>
		<div id="content">
			<div class="col_w560">

				<h2>${article.itemTree.value}</h2>
				<h2>${OPENSHIFT_DATA_DIR}</h2>
						<div class="image_wrapper image_fl">
							<img width="400" height="300" src="${pageContext.request.contextPath}/photos/${article.idArticle}.jpg"
								alt="TemplatemoImage01" />	
						</div>
						<br>
						<form  id= "fileform"  name = "fileform" method="POST" action="uploadFile" enctype="multipart/form-data"> 
						<label>Update photo </label><input  onchange = "imageValidation(this.form)"  name="file" type="file"/>
						<input onclick = "imageSave(this.form,${article.idArticle})" name ="update" value = "Update" type = "button">
						</form>
						<br><br>


				<c:forEach items="${article.articleParam}" var="articleParams">
					<div>
						
						<div class="col_w100forlabel">
							<label>${articleParams.param.paramName} </label>
							<%-- <c:out value="${articleParams.param.paramName}" /> --%>
						</div>

						<div class="col_w100forlabel">
							<input type = "text" value = "${articleParams.paramValue.paramValue}" size = 30"/>
							<%-- <c:out value="${articleParams.paramValue.paramValue}" /> --%>
						</div>
					</div>

					<br>
				</c:forEach>
				<br>
				<c:out value="Description:" /> <br>
				<textarea id= "descriptionid" name="desc" style="width:99%; height:200px">${article.articleDescription}</textarea>
				
				<%-- <c:out value="Description: ${article.articleDescription}" /> --%>
				<br>
				<label for = "price">Price </label><input id = "price" type = "text" value = "${article.articlePrice}" size = 15"/>
				<%-- <c:out value="Price: ${article.articlePrice}" /> --%>
				<br><br><br>
				<button style="width: 150px; height: 50px;" onclick = "updateArticle()">Save</button>

					
				<div class="hr_divider"></div>
				<div class="hr_divider"></div>

				

			</div>

			
			<div style="clear: both"></div>
		</div>
		<div id="footer">

			Copyright © 2048 <a href="#"></a> | </a>

		</div>
	</div>

</body>
</html>