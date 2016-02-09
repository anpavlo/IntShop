<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="list"  type="java.util.TreeSet" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty list}">
    <ul>
    <c:forEach var="itemTree" items="${list}">
    			
		<c:url var="productsListUrl"
						value="/adminProductslist?id=${itemTree.idItemTree}" />		
        <li> 
			  <a id="input_str" href="${productsListUrl}">${itemTree.value}</a>

       		 <myTags:adminPr_tree list="${itemTree.childrenList}"/>
        </li>
        
        
    </c:forEach>
    </ul>
    
</c:if>

