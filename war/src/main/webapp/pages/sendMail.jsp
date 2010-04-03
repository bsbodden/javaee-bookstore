<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSF Bookstore</title>
</head>
<body>
<f:view>
	<h2>Send an email</h2>
	<h:form>
		<h:panelGrid columns="2">
			<h:outputText value="To" />
			<h:inputText value="#{mailer.to}" size="15" />
			<h:outputText value="Subject" />
			<h:inputText value="#{mailer.subject}" size="15" />
			<h:outputText value="Body" />
			<h:inputTextarea value="#{mailer.body}" rows="5" />
		</h:panelGrid>
		<h:commandButton action="#{mailer.deliver}" value="Add" />
	</h:form>
</f:view>
</body>
</html>