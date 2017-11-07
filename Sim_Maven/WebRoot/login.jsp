<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta charset=utf-8"/> 
<head>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
  
  <body>
     <div class="container">
	 <div class="col-md-offset-4 col-md-4">
      <form class="form-signin" action="<%=request.getContextPath()%>/servlet/LoginServlet" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" class="form-control" placeholder="Username" name="username" autocomplete="off" autofocus value="admin"/>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" autocomplete="off" >
       <br/>
       <!--   <div class="checkbox">
          <label>
            <input type="checkbox" name="remember" value="remember-me"> Remember me
          </label>
        </div>-->
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
    </div> 
    </div>
  </body>

