<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<html>
	<head>
		<title>JSF Bookstore</title>
	</head>
	<body>
		<f:view>
		    <h1>All Stores</h1>
	        <rich:dataList var="store" value="#{stores.all}" rows="5" type="disc" title="Book Store">
	            <h:outputText value="#{store.nickName}"/>
	        </rich:dataList>
	
			<rich:calendar />
		</f:view>
	</body>	
</html>  
