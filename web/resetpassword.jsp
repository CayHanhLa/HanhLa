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
            
        </style>
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
                                        <form action="resetpassword" method="post">
                                            <h4>Enter Your Email To Reset Password</h4>
                                            <p>Please enter the CORRECT email address associated with your account to proceed with the password reset.</p>
                                            <div class="code-container">
                                                <input name="emailreset" placeholder="Enter Your Email" value="${EmailReset}" type="email" class="code" required> <br>                                              
                                            </div>                                            
                                            <div  style="color: #FFFFFF;">                                      
                                                ${requestScope.MessageFromReset
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