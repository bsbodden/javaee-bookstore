<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
	<head>
		<title>JSF Bookstore</title>
	</head>
	<body>
		<f:view>
		    <h1>All Books</h1>
	        <rich:dataList var="store" value="#{books.all}" rows="5" type="disc" title="Book Store">
	            <h:outputText value="#{book.title}"/>
	        </rich:dataList>
		</f:view>
	</body>	
</html>  

