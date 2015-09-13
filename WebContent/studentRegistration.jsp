<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="img/favicon.png">

<title>Explore and Learn Pte Ltd - Main Page</title>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->

<!-- ----------------------Bootstrap CSS & Theme----------------------------- -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">

<!-- ---------------------External CSS: Fonts and Icons ----------------------- -->
<!-- Fonts and Icons -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- -------------------External CSS: Custom styles----------------------------- -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

</head>

<body>

	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!--custome script for all page-->
	<script src="js/scripts.js"></script>

	<!------- container section start------------ -->
	<section id="container" class="">

		<!--------- including navBar & sideBar------------ -->
		<%@include file="navBar.jsp"%>
		<%@include file="sideBar.jsp"%>

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-bars"></i> Pages
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="adminMain.jsp">Home</a></li>
							<li><i class="icon_document_alt"></i>Student</li>
							<li><i class="fa fa-files-o"></i>New Student Registration</li>
						</ol>
					</div>
				</div>
				<!-- page start-->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> Form validations </header>
							<div class="panel-body">
								<div class="form">
									<form class="form-validate form-horizontal" id="feedback_form"
										method="post" action="">
										<div class="form-group ">
											<label for="cname" class="control-label col-lg-2">Full
												Name <span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="cname" name="fullname"
													minlength="5" type="text" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="cemail" class="control-label col-lg-2">E-Mail
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control " id="cemail" type="email"
													name="email" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="curl" class="control-label col-lg-2">Website</label>
											<div class="col-lg-10">
												<input class="form-control " id="curl" type="url" name="url" />
											</div>
										</div>
										<div class="form-group ">
											<label for="cname" class="control-label col-lg-2">Subject
												<span class="required">*</span>
											</label>
											<div class="col-lg-10">
												<input class="form-control" id="subject" name="subject"
													minlength="5" type="text" required />
											</div>
										</div>
										<div class="form-group ">
											<label for="ccomment" class="control-label col-lg-2">Feedback</label>
											<div class="col-lg-10">
												<textarea class="form-control " id="ccomment" name="comment"
													required></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button class="btn btn-primary" type="submit">Save</button>
												<button class="btn btn-default" type="button">Cancel</button>
											</div>
										</div>
									</form>
								</div>

							</div>
						</section>
					</div>
				</div>
				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
</body>
</html>