<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<script type="text/JavaScript"
 src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js">
</script>
<style>
		/*
		 * CSS3 Treeview. No JavaScript
	     * @version 1.0
		 * @author Martin Ivanov
		 * @url developer's website: http://wemakesites.net/
	     * @url developer's twitter: https://twitter.com/#!/wemakesitesnet
		 * @url developer's blog http://acidmartin.wordpress.com/
		 **/
		 
		/*
		 * This solution works with all modern browsers and Internet Explorer 9+. 
		 * If you are interested in purchasing a JavaScript enabler for IE8 
		 * for the CSS3 Treeview, please, check this link:
		 * http://experiments.wemakesites.net/miscellaneous/acidjs-css3-treeview/
		 **/
		 
		.css-treeview ul,
		.css-treeview li
		{
			padding: 0;
			margin: 0;
			list-style: none;
		}

		.css-treeview input
		{
			position: absolute;
			opacity: 0;
		}

		.css-treeview
		{
			/* font: normal 11px "Segoe UI", Arial, Sans-serif; */
			-moz-user-select: none;
			-webkit-user-select: none;
			user-select: none;
		}

		.css-treeview a
		{
			color: #00f;
			text-decoration: none;
		}

		.css-treeview a:hover
		{
			text-decoration: underline;
		}

		.css-treeview input + label + ul
		{
			margin: 0 0 0 22px;
		}

		.css-treeview input ~ ul
		{
			display: none;
		}

		.css-treeview label,
		.css-treeview label::before
		{
			cursor: pointer;
		}

		.css-treeview input:disabled + label
		{
			cursor: default;
			opacity: .6;
		}

		.css-treeview input:checked:not(:disabled) ~ ul
		{
			display: block;
		}

		.css-treeview label,
		.css-treeview label::before
		{
			background: url("${pageContext.request.contextPath}/css/icons.png") no-repeat;
		}

		.css-treeview label,
		.css-treeview a,
		.css-treeview label::before
		{
			display: inline-block;
			height: 16px;
			line-height: 16px;,
			vertical-align: middle;
		}

		.css-treeview label
		{
			background-position: 18px 0;
		}

		.css-treeview label::before
		{
			content: "";
			width: 16px;
			margin: 0 22px 0 0;
			vertical-align: middle;
			background-position: 0 -32px;
		}

		.css-treeview input:checked + label::before
		{
			background-position: 0 -16px;
		}

		/* webkit adjacent element selector bugfix */
		@media screen and (-webkit-min-device-pixel-ratio:0)
		{
			.css-treeview 
			{
				-webkit-animation: webkit-adjacent-element-selector-bugfix infinite 1s;
			}
			
			@-webkit-keyframes webkit-adjacent-element-selector-bugfix 
			{
				from 
				{ 
					padding: 0;
				} 
				to 
				{ 
					padding: 0;
				}
			}
		}
		</style>
</head>
<body>
<script type="text/javascript">

var itemTree;
var idItemTree;
var value;

function ItemTreeSelect(idItemTree) {
	  
	 //var inputText = $("#input_str").val();
	 
	 this.idItemTree=idItemTree;
	 
	 $.ajax({
	  url : "ItemTreeSelect",
	  type: 'GET',
	  dataType: 'json',
	  contentType: 'application/json',
	     mimeType: 'application/json',
	  data : ({
	   id: idItemTree
	  }),
	  success: function (data) {
		 //alert(data.childrenList[0].value);
		
		 itemTree=data;
		 value = new String(data.value);
		 
		 if (itemTree.childrenList==null){
			 
			 var but="<button type=\"button\"  style=\"width: 150px; height: 50px;\" onclick = \"addArticle()\">ADD ARTICLE</button>";
			 
			 $('#addarticle').html(but);
		 }else{
			 $('#addarticle').html("");
		 }
		 
		 var params;
		 if(data.params[0].paramName==null) {
			 params="<h3>empty</h3>";
		 }else {
			 	 
			 params="<table style=\"width:100%;\">";	 
			 params=params+"<tr id=\"edit"+data.params[0].idParam+"\">  <td><h3>"+data.params[0].paramName+"</h3></td>  <td><button type=\"button\" onclick=\"addEditforParam("+data.params[0].idParam+",'"+data.params[0].paramName+"')\">edit</button> </td>       <td> <button type=\"button\" onclick=\"deleteParam("+data.params[0].idParam+")\">delete</button> </td>  </tr>";
				 for (i=1; i<data.params.length;i++){
					 params=params+"<tr id=\"edit"+data.params[i].idParam+"\"><td><h3>"+data.params[i].paramName+"</h3></td><td> <button type=\"button\" onclick=\"addEditforParam("+data.params[i].idParam+",'"+data.params[i].paramName+"')\">edit</button> </td><td>  <button type=\"button\" onclick=\"deleteParam("+data.params[i].idParam+")\">delete</button> </td></tr>";
				 }
			 }
		 params=params+"</table>";
		 $("#params").html(params);
		 
		 
 		 var parentParams;
		 if(data.parentParams[0].paramName==null) {
			 parentParams="<h3>empty</h3>";
		 }else {
			 	 parentParams="<table style=\"width:100%;\">";
			 	 parentParams=parentParams+"<tr><td><h3>"+data.parentParams[0].paramName+"</h3></td></tr>";
				 for (i=1; i<data.parentParams.length;i++){
					 parentParams=parentParams+"<tr><td><h3>"+data.parentParams[i].paramName+"</h3></td></tr>";
				 }
			 }
		 parentParams=parentParams+"</table>";
		 $("#parentParams").html(parentParams);
		 
				   
	   	var categoty = "<h3>"+data.value+"</h3>";
	   	var buttons_htm = "<button type=\"button\" onclick=\"add("+data.idItemTree+")\">add</button> <button type=\"button\" onclick=\"edit("+data.idItemTree+")\">edit</button>  <button type=\"button\" onclick=\"delet("+data.idItemTree+")\">delete</button>";
	  

	   $('#categoty').html(categoty);
	   $('#categoty').prepend(buttons_htm);
	   $('#categoty').prepend("<div id=\"forms\"></div>");
	   $('#addParamButton').html("<button type=\"button\" onclick = \"addInputforParam("+data.idItemTree+")\" style=\"width: 100px; height: 30px;\">add</button>\"");
	   
	  
	   
	  }
	 });
	}
	
	function add(idItemTree){
		var inp ="<form action=\"addItemTree\" method=\"get\"> New category name:<br>  <input type=\"text\" name=\"newItemTreeName\" size=\"35\">  <input type=\"hidden\" name=\"parentIdItemTree\" value=\""+idItemTree+"\"> <input type=\"submit\" value=\"Add\"></form><br>";
		
		$('#forms').html(inp);
	}
	
	function edit(idItemTree){
		var inp ="<form action=\"editItemTree\" method=\"get\"> Edit category name:<br>  <input type=\"text\" name=\"editItemTreeName\" value=\""+value+"\" size=\"35\">  <input type=\"hidden\" name=\"IdItemTree\" value=\""+idItemTree+"\">   <input type=\"submit\" value=\"Edit\"></form><br>";
		
		$('#forms').html(inp);
	}
	
	function delet(idItemTree){
		var inp ="<form action=\"deleteItemTree\" method=\"get\"> Delete category? <br>  <input type=\"text\" name=\"ItemTreeName\" value=\""+value+"\" size=\"35\" readonly  >  <input type=\"hidden\" name=\"deleteIdItemTree\" value=\""+idItemTree+"\">  <input type=\"submit\" value=\"Delete\"></form><br>";
		
		$('#forms').html(inp);
	}
	
	function addInputforParam(idItemTree){
		var input="<input  type=\"text\" name=\"paramName\" size=\"35\"> <button type=\"button\" onclick = \"addParam("+idItemTree+")\" style=\"width: 100px; height: 30px;\">add</button>\"";
		$('#addParamButton').html(input);		
	}
	
	function addEditforParam(idParam, paramName){
		var input="<th><input  type=\"text\" name=\"editParamName\" value=\""+paramName+"\" size=\"12\"></th> <th><button type=\"button\" onclick = \"editParam("+idParam+")\">edit</button></th>";
		$('#edit'+idParam).html(input);	
	}
	
	function addParam(idItemTree){
		var paramName = document.getElementsByName("paramName")[0].value;
		
		$.ajax({
			  url : "addParam",
			  type: 'GET',
			  dataType: 'json',
			  contentType: 'application/json',
			     mimeType: 'application/json',
			  data : ({
			   id: idItemTree,
			   name: paramName
			  }),
			  success: function (data) {
				  ItemTreeSelect(idItemTree); 
			  }
		 });
	}
	
	function editParam(idParam){
		var editparamName = document.getElementsByName("editParamName")[0].value;
	
		$.ajax({
			  url : "editParam",
			  type: 'GET',
			  dataType: 'json',
			  contentType: 'application/json',
			     mimeType: 'application/json',
			  data : ({
			   id: idParam,
			   name: editparamName
			  }),
			  success: function (data) {
				  ItemTreeSelect(idItemTree); 
			  }
		 });
	}
	
	function deleteParam(idParam){
		
		
		$.ajax({
			  url : "deleteParam",
			  type: 'GET',
			  dataType: 'json',
			  contentType: 'application/json',
			     mimeType: 'application/json',
			  data : ({
			   id: idParam
			  }),
			  success: function (data) {
				  ItemTreeSelect(idItemTree); 
			  }
		 });
	}
	
	function addArticle(){
		console.log(itemTree);
		 if (itemTree.childrenList==null){
				var inp ="<form id=\"addarticleform\" action=\"addArticlePage\" method=\"get\"></form>";
				$('#addarticle').html(inp);
				document.getElementById("addarticleform").submit();
			 }else{
				 alert("Select item with no children");
			 } 
	}
	
</script>

	<div id="container">
		<div id="header">
			<div class="logo">
				<strong>Hello</strong><br> <span>This is test internet
					store</span> <br> <span> ADMIN PAGE</span>
			</div>
		</div>
		<div id="menu">
			<ul>
				<li><a href="index.html">Main</a></li>
				<li><a href="products.html">Products</a></li>
				<li><a href="partners.html">Partners</a></li>
				<li><a href="about.html">About us</a></li>
				<li><a href="contact.html">Contact</a></li>
			</ul>
		</div>
		<div id="slider"></div>


		<div id="content">
			<div class="col_w560"><div id="addarticle"></div>
			<h6>To add Article choose category without children and then press the button</h6>
			<br/><br/><br/>

				<h2 >Category Tree</h2>
				


				<%-- <c:url var="ItemTreeSelect"	value="/ItemTreeSelect?id=${itemTree.idItemTree}" /> --%>
		
				<div class="css-treeview">
				<ul>
					<li><input type="checkbox" id="item-0" /><label for="item-0"> <a onclick="ItemTreeSelect(${itemTree.idItemTree})" id="input_str" href="#">${itemTree.value} </a> </label>
					 	<myTags:ItemTrees list="${itemTree.childrenList}" />
					 </li>
				</ul>
				</div>


				<div class="hr_divider"></div>

			</div>
			<div class="col_w300">

			
		
			<div id="categoty"></div>
			
			<div id="addParamButton" class="col_w300">
			<!-- <button type="button" onclick = "addParam()" style="width: 100px; height: 30px;">add</button> -->
			</div>	
			 
				<div class="col_w200" style="overflow: auto">		
				<div id="params"></div>
				</div>
				
				<div class="col_w150" style="overflow: auto" >		
				<div id="parentParams" style="width:127%"></div>
				</div>

			</div>
			
			
			<div style="clear: both"></div>
		</div>
		<div id="footer">

			Copyright © 2048 <a href="#"></a> | </a>

		</div>
	</div>

</body>
</html>