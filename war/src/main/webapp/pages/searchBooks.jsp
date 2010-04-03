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
	<h1>Book Search</h1>
	<a4j:form>
		<h:panelGrid columns="3">
			<h:outputText value="Search for Books Like:" />
			<h:inputText value="#{books.query}" />
			<a4j:commandLink reRender="out" action="#{books.search}">
				<h:outputText value="Go!" />
			</a4j:commandLink>
		</h:panelGrid>
	</a4j:form>
	<rich:spacer height="7" />
	<br />
	<h:panelGroup id="out">
		<rich:dataTable value="#{books.searchResults}" var="book" rows="5">
			<rich:column>
				<f:facet name="header">Title</f:facet>
				<h:outputText value="#{book.title}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">ISBN</f:facet>
				<h:outputText value="#{book.isbn}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">Authors</f:facet>
				<h:outputText value="#{book.authors}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">Price</f:facet>
				<h:outputText value="#{book.price}">
					<f:convertNumber pattern="$####.00" />
				</h:outputText>
			</rich:column>
		</rich:dataTable>
	</h:panelGroup>
	<br />
</f:view>
</body>
</html>


