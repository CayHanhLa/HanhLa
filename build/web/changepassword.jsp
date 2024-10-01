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
        <script>
            function validateForm() {
                var password = document.getElementById("newpassword").value;
                var confirmPassword = document.getElementById("confirmnewpassword").value;

                if (password !== confirmPassword) {
                    document.getElementById("PasswordFailedMessage1").innerHTML = "Confirm passwords do not match!";
                    return false;
                } else {
                    document.getElementById("PasswordFailedMessage1").innerHTML = "";
                    return true;
                }
            }

            function duplicateForm() {
                var oldpassword = document.getElementById("currentpassword").value;
                var newpassword = document.getElementById("newpassword").value;

                if (oldpassword === newpassword) {
                    document.getElementById("PasswordFailedMessage2").innerHTML = "New password cannot be the same as old password!";
                    return false;
                } else {
                    document.getElementById("PasswordFailedMessage2").innerHTML = "";
                    return true;
                }
            }

            function onSubmitForm() {
                var isValid = validateForm();
                var isDuplicate = duplicateForm();
                return isValid && isDuplicate;
            }

    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" 
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="category">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Change Password</h1>
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
                    <div class="col-lg-12">
                        <div class="login_form_inner">
                            <h3>${sessionScope.account.fullName}</h3>
                            <h3>Change Password</h3>
                            <form class="row login_form" action="changepassword" method="Post" onsubmit="return onSubmitForm()" id="contactForm" >
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="currentpassword" name="currentpassword" placeholder="Current Password" onfocus="this.placeholder = ''" value="" onblur="this.placeholder = 'Current Password'">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="New Password" onfocus="this.placeholder = ''" value="" onblur="this.placeholder = 'New Password'">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="confirmnewpassword" name="confirmnewpassword" placeholder="Confirm New Password" onfocus="this.placeholder = ''" value="" onblur="this.placeholder = 'Confirm New Password'">
                                </div>                              
                                <div id="PasswordFailedMessage1" style="color: #4B5CED;">
                                </div>
                                <div id="PasswordFailedMessage2" style="color: #4B5CED;">
                                </div>
                                <div style="color: #4B5CED;">
                                    ${FailChangePassWord}
                                </div>                                
                                <div class="col-md-12 form-group">
                                    <button type="submit" value="submit" class="button button-login w-100">Save Changes</button>
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