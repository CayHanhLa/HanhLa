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
            .code {
                caret-color: transparent;
                border-radius: 5px;
                font-size: 25px;
                height: 75px;
                width: 600px;
                border: 1px solid #eee;
                margin: 1rem;
                text-align: center;
                font-weight: 100;
            }
        </style>
        <script>
            function validateForm() {
                var password = document.getElementById("newpassword").value;
                var confirmPassword = document.getElementById("confirmpassword").value;

                if (password !== confirmPassword) {
                    document.getElementById("ConfirmPasswordFailed").innerHTML = "Confirm passwords do not match!";
                    return false;
                } else {
                    document.getElementById("ConfirmPasswordFailed").innerHTML = "";
                    return true;
                }
            }
        </script> 
    </head>
    <body>

        <!--================Login Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="login_box_img">
                            <div class="hover">
                                <div class="div-container">
                                    <div class="container">
                                        <form action="recoverypassword" onsubmit="return validateForm()" id="contactForm" method="post">
                                            <h4>Reset Your Password</h4>
                                            <div class="code-container">
                                                <input name="newpassword" id="newpassword" placeholder="New Password" type="password" class="code" required> <br>
                                                <input name="confirmpassword" id="confirmpassword" placeholder="Confirm New Password" type="password" class="code" required>                                               
                                            </div>

                                            <div id="FailtoRecovery" style="color: #FFFFFF;">
                                                ${FailtoRecovery}
                                            </div>   
                                            <div style="margin-top: 20px; font-size: 25px;">

                                                <button type="submit" class="button button-account">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>                  
                </div>
            </div>
        </section>
        <!--================End Login Box Area =================-->
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