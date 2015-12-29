<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="list"  type="java.util.TreeSet" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty list}">
    <ul>
    <c:forEach var="itemTree" items="${list}">
    			
				
				<c:url var="ItemTreeSelect" value="/ItemTreeSelect?id=${itemTree.idItemTree}" />
				
        <li> 
        	<c:choose> 
        	
			  <c:when test="${!empty itemTree.childrenList}">
			   <input type="checkbox" id="${itemTree.idItemTree}" /><label for="${itemTree.idItemTree}"><a onclick="ItemTreeSelect(${itemTree.idItemTree})" id="input_str" href="#">${itemTree.value}</a> </label>
			  </c:when>
			  
			  <c:otherwise>
			  <a onclick="ItemTreeSelect(${itemTree.idItemTree})" id="input_str" href="#">${itemTree.value}</a>
			  </c:otherwise>
			  
			</c:choose>
        

       		 <myTags:ItemTrees list="${itemTree.childrenList}"/>
        </li>
        
        
    </c:forEach>
    </ul>
</c:if>

<%--  <a href="${ItemTreeAdd}">add  </a> <a href="${ItemTreeEdit}">edit   </a><a href="${ItemTreeDelete}">delete  </a> --%>