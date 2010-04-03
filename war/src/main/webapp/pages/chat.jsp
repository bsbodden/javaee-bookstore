<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
<head>
<title>JSF Bookstore</title>
</head>
<body>
<f:view>
	<h1>Simple Chat</h1>
	<a4j:form>
		<h:panelGrid columns="3">
			<h:outputText value="Send a message:" />
			<h:inputText value="#{chat.message}" />
			<a4j:commandLink reRender="out" action="#{chat.sendMessage}">
				<h:outputText value="Go!" />
			</a4j:commandLink>
		</h:panelGrid>
	</a4j:form>
	<rich:spacer height="7" />
	<br />
	<h2>Responses:</h2>
	<h:panelGroup id="out">
	    <h:outputText value="#{chat.response}" />
	</h:panelGroup>
	<br />
</f:view>
</body>
</html>


