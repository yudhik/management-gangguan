<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<form role="form">
		<div class="form-group">
			<div>Id</div>
			<div>${availableMenu.id}</div>
		</div>
		<div class="form-group">
			<div>Menu Label</div>
			<div>${availableMenu.label}</div>
		</div>
		<div class="form-group">
			<div>URL</div>
			<div>${availableMenu.relativeUrl}</div>
		</div>
		<c:if test="${!empty availableMenu.childMenu}">
			<div class="form-group">
				<div>Children</div>
				<c:forEach items="${availableMenu.childMenu}" var="child">
					<div>${child.id}</div>
					<div>${child.label}</div>
					<div>${child.relativeUrl}</div>
				</c:forEach>
			</div>
		</c:if>
		<button class="btn btn-default">Back</button>
	</form>
</div>