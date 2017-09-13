<!DOCTYPE html>
<html>
<head>
<title>WebSocket</title>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.js"></script>
</head>
<body>
	<script>
		// var id = "<%=session.getId()%>";
		// var id = "${pageContext.session.id}".split("-").join("");

		var sock = new SockJS("http://localhost:8070/open");
		sock.onopen = function() {
			console.log("open");
			sock.send("test");
		};

		sock.onmessage = function(e) {
			console.log("message", e.data);
			sock.close();
		};

		sock.onclose = function() {
			console.log("close");
		};
	</script>
</body>
</html>