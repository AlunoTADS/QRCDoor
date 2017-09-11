<!DOCTYPE html>
<html>
<head>
<title>Sign in</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">QRCDoor</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-xs-offset-3 col-xs-6">
				<form class="form-horizontal" method="post">
					<h2 class="form-signin-heading">Please sign in</h2>
					<div class="form-group">
						<label for="username" class="col-xs-2 control-label">Email</label>
						<div class="col-xs-10">
							<input type="text" class="input-block-level"
								placeholder="User name" id="username" name="username" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-xs-2 control-label">Password</label>
						<div class="col-xs-10">
							<input type="password" class="input-block-level"
								placeholder="Password" id="password" name="password" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button class="btn btn-primary" type="submit">Sign in</button>
							<small>* Use user1/user1 | user2/user2 | user3/user3</small>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
