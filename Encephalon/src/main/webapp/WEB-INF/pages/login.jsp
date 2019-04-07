<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 
    Document   : index
    Created on : 6 Oct, 2018, 8:02:42 PM
    Author     : prawal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href='<c:url value="/resources/css/myCss.css" />' />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ecephalon Login Page</title>


        <link rel="stylesheet" href='<c:url value="/resources/css/myCss.css" />' />
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.4/angular.min.js" type="text/javascript"></script>
        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

        <!--         Optional theme 
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        <style>
        </style>
    </head>
    <body class="container" onload='document.loginForm.username.focus();'>


        <div id="login-box" class="login-form vertical-center ">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Login</div>
                            <div class="card-body">
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger ">${error}</div>
                                </c:if>
                                <c:if test="${not empty msg}">
                                    <div class="alert alert-success ">${msg}</div>
                                </c:if>

                                <form name='loginForm'
                                      action="<c:url value='j_spring_security_check' />" method='POST'>
                                    <div class="form-group row">
                                        <label for="username" class="col-md-4 col-form-label text-md-right">UserName</label>    
                                        <div class="col-md-6">
                                            <input type="text" id="username" class="form-control" name="username" required="" autofocus="">

                                        </div>

                                    </div>
                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password" class="form-control" name="password" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-6 offset-md-4">
                                        <input class="btn btn-primary" name="submit" type="submit"
                                               value="Login" />
                                        <a href="#" class="btn btn-link">
                                            Forgot Your Password?
                                        </a>
                                    </div>


                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </body>
    <script src='<c:url value="/resources/js/myScript.js" />' type="text/javascript"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- jQuery Custom Scroller CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

</html>
