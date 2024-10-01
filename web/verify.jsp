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
                font-size: 60px;
                height: 100px;
                width: 80px;
                border: 1px solid #eee;
                margin: 1rem;
                text-align: center;
                font-weight: 300;
            }
        </style>
        <style>

            input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
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
                                        <form action="verify" method="post">
                                            <h4>Verify Your Account</h4>
                                            <p>We have just sent you a six-digit code to your registered email. <br /> Enter the code below to confirm your email
                                                address.</p>
                                            <div class="code-container">
                                                <input name="number1" type="number" class="code" min="0" max="9" required>
                                                <input name="number2" type="number" class="code" min="0" max="9" required>
                                                <input name="number3" type="number" class="code" min="0" max="9" required>
                                                <input name="number4" type="number" class="code" min="0" max="9" required>
                                                <input name="number5" type="number" class="code" min="0" max="9" required>
                                                <input name="number6" type="number" class="code" min="0" max="9" required>
                                            </div>
                                            <div id="error-message" style="color: #FFFFFF;">                                      
                                                ${FailtoVerify}
                                            </div>
                                            <div style="margin-top: 20px;">
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
        <script>

            const inputs = document.querySelectorAll('.code');


            inputs.forEach((input, index) => {
                input.addEventListener('input', () => {

                    if (input.value.length > 1) {
                        input.value = input.value.slice(0, 1);
                    }


                    if (input.value.length === 1) {

                        if (index < inputs.length - 1) {
                            inputs[index + 1].focus();
                        }
                    }
                });


                input.addEventListener('keydown', (e) => {
                    if (e.key === 'Backspace' && input.value === '') {

                        if (index > 0) {
                            inputs[index - 1].focus();
                        }
                    }
                });
            });
        </script>
    </body>
</html>