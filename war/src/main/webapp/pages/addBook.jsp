<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSF Bookstore</title>
</head>
<body>
<f:view>
<h2>Add a Book</h2>
<h:form>
  <h:panelGrid columns="2">
    <h:outputText value="Title"/>
    <h:inputText value="#{books.book.title}" size="15"/>
    <h:outputText value="Actor"/>
    <h:inputText value="#{books.book.isbn}" size="15"/>
    <h:outputText value="Price"/>
    <h:inputText value="#{books.book.publishedOn}" size="15"/>
    <h:outputText value="Price"/>
    <h:inputText value="#{books.book.price}" size="15"/>
  </h:panelGrid>
        
  <h:commandButton action="#{books.addBook}" value="Add" />
</h:form>
</f:view>
</body>
</html>