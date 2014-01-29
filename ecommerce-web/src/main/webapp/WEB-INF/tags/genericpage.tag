<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<head>
	<title>Ecommerce</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link rel="stylesheet" href="resources/css/bootstrap.css" />
	
	<link rel="stylesheet" href="resources/css/styles.css" />

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
</head>
<html>
	<body>
		<header>
			<div class="container">
				<div class="row">
					<div class="col-xs-5"><h1>Ecommerce</h1></div>
					<div class="col-xs-7">
						<menu>
							<ul>
								<li><a class="pull-right" href="/ecommerce">Home</a></li>
								<li><a class="pull-right" href="carrinho">Carrinho</a></li>
								<li><a class="pull-right" href="admin">Admin</a></li>
							</ul>
						</menu>
					</div>
				</div>
			</div>
		</header>
		<section id="subtitle">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<h2>Uma simples aplicação <strong>JavaEE</strong> ;)</h2>
					</div>
				</div>
			</div>
		</section>
		<div id="body">
			<jsp:doBody/>
		</div>
		<div id="pagefooter" class="container">
			<hr/>
			<p id="copyright">
				Aqui não tem esse negócio de <strong><i>copyright</i></strong> não!&nbsp;
				<a href="https://github.com/jnwelzel/ecommerce" target="_blank"><img alt="Github" title="Github" src="resources/img/github.png"></a>
			</p>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="resources/js/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="resources/js/bootstrap.js"></script>
	</body>
</html>