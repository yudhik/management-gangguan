<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<!-- TODO : change "/management-gangguan" with basePath filter -->
	<form:form role="form" action="/management-gangguan/view/administration/menu/form" method="post" modelAttribute="availableMenu">
		<div class="form-group">
			<label for="menuLabel">Menu Label</label>
			<form:input path="label" type="text" class="form-control" id="menuLabel" placeholder="masukan label menu" />
		</div>
		<div class="form-group">
			<label for="relativeUrl">URL</label>
			<form:input path="url" type="text" class="form-control" id="relativeUrl" placeholder="masukan url" />
		</div>
		
		<button type="submit" class="btn btn-default">Submit</button>
	</form:form>
</div>