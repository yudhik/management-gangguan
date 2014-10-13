<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<table class="table table-bordered">
		<thead>
			<tr><th>ID</th><th>Label</th><th>Relative URL</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${availableMenus}" var="availableMenu">
				<tr>
					<td><a href="detail/${availableMenu.id}">${availableMenu.id}</a></td>
					<td>${availableMenu.label}</td>
					<td>${availableMenu.relativeUrl}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>