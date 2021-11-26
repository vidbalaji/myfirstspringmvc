<%@ include file="../common/header.jspf"%>
<body>
	<p>
		<font color="red">${errorMessage}</font>
	</p>

	<div class="container">
		<H1>Welcome</H1>


		<table class="table table-striped">
			<caption>Your Processes are</caption>
			<thead>
				<th>Description</th>
				<th>Pid</th>
				<th>CPU</th>
				<th>Memory</th>
				<c:if test="${isFromUser == 1}">
					<th>Actions</th>
				</c:if>
			</thead>
			<tbody>
				<c:forEach items="${processlist}" var="process">
					<tr>
						<td>${process.name}</td>
						<td>${process.pid}</td>
						<td>${process.cpuUsage}</td>
						<td>${process.virtualMemory}</td>
						<c:if test="${isFromUser == 1}">
							<td>&nbsp;&nbsp;<a class="btn btn-danger"
								href="../getprocess?kill_pid=${process.pid}">Kill</a>
							</td>
							<td>&nbsp;&nbsp;<a class="btn btn-danger"
								href="../getprocess?schedule=${process.name}">Schedule to
									kill process</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<a class="btn btn-link" href="../getprocess?prev"> --Prev </a>
		&nbsp;&nbsp; <a class="btn btn-link" href="../getprocess?next">Next
			>> </a>

	</div>

	<div>
		<a class="btn btn-link" href="../getprocess?refresh"> Refresh
			Schedule List</a>
	</div>
	<%@ include file="../common/footer.jspf"%>