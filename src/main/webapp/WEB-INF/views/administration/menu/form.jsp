<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<form:form role="form" action="/view/administration/menu/" method="post" modelAttribute="availableMenu">
		<div class="form-group">
			<form:label for="menuLabel">Menu Label</form:label>
			<form:input path="label" type="text" class="form-control" id="menuLabel" placeholder="masukan label menu" />
		</div>
		<div class="form-group">
			<form:label for="relativeUrl">URL</form:label>
			<form:input path="url" type="text" class="form-control" id="relativeUrl" placeholder="masukan url" />
		</div>
		
		<button type="submit" class="btn btn-default">Submit</button>
	</form:form>
</div>