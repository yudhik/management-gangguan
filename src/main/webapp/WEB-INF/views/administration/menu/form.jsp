<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<!-- TODO : change "/management-gangguan" with basePath filter -->
	<form:form role="form" action="/management-gangguan/view/administration/menu/form" method="post" modelAttribute="availableMenu">
		<div class="form-group">
			<label for="menuLabel">Menu Label</label>
			<form:input path="label" type="text" class="form-control" id="menuLabel" placeholder="masukan label menu" />
		</div>
		<div class="form-group">
			<label for="relativeUrl">URL</label>
			<form:input path="relativeUrl" type="text" class="form-control" id="relativeUrl" placeholder="masukan url" />
		</div>
		<div class="form-group">
			<label for="parent">Parent</label>
			<form:select path="parent">
				<option value="">Pilih parent menu</option>
				<c:forEach items="${parents}" var="parent">
					<option value="${parent.id}">${parent.label}</option>
				</c:forEach>
			</form:select>
		</div>
		
		<button type="submit" class="btn btn-default">Submit</button>
	</form:form>
</div>