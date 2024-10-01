<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Login</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .div-container {
                margin-bottom: 10%;
            }
        </style>
        <script>
            // Kiem tra dinh dang email
            function validateEmail() {
                var email = document.getElementById("eemail").value;
                var emailPattern = /^[a-zA-Z0-9._%+-]+@company\.com$/; //dinh dang email cong ty
                var errorMessage = document.getElementById("error-message");

                if (!emailPattern.test(email)) {
                    errorMessage.textContent = "Invalid email format. Must be a company email.";
                    return false;
                } else {
                    errorMessage.textContent = "";
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="category">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Login / Register</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Login/Register</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->

        <!--================Login Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="login_box_img">
                            <div class="hover">
                                <div class="div-container">
                                    <h4>New to our website?</h4>
                                    <p>Welcome, Thank you for joining our fashion family. We can't wait for you to explore our latest collections and exclusive offers</p>
                                    <a class="button button-account" href="register">Create an Account</a>
                                </div>
                                <div>
                                    <h4>Are you a customer?</h4>
                                    <p>Welcome back! It's great to see you again. Ready to find your next favorite outfit? Check out our latest arrivals!</p>
                                    <a class="button button-account" href="login">Login as Customer</a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="login_form_inner">
                            <h3>Hello Staff</h3>
                            <form class="row login_form" action="employeelogin" method="Post" id="contactForm" onsubmit="return validateEmail()">
                                <div class="col-md-12 form-group">
                                    <input type="email" class="form-control" id="eemail" name="eemail" placeholder="Email" onfocus="this.placeholder = ''" value="${cookie.eemail.value}" onblur="this.placeholder = 'Email'">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="name" name="epassword" placeholder="Password" onfocus="this.placeholder = ''" value="${cookie.epass.value}" onblur="this.placeholder = 'Password'">
                                </div>
                                <div class="col-md-12 form-group">
                                    <div id="error-message" style="color: #4B5CED;">
                                        ${loginFailedMessage}
                                    </div>
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="creat_account">
                                        <input type="checkbox" id="f-option2" ${(cookie.erem!=null?'checked':'')} name="eremember">
                                        <label for="f-option2">Keep me logged in</label>
                                    </div>
                                </div>
                                <div class="col-md-12 form-group">
                                    <button type="submit" value="submit" class="button button-login w-100">Log In</button>
                                    <a href="resetpassword">Forgot Password?</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Login Box Area =================-->



        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->



        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>