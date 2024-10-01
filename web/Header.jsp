<%-- 
    Document   : Header
    Created on : Sep 18, 2024, 7:41:09 AM
    Author     : sontu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Home</title>
        <link rel="icon" href="img2/FIcon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class="header_area">
            <div class="main_menu">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="container">
                        <a class="navbar-brand logo_h" href="home"><img src="img/logo.png" alt=""></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                            <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
                                <li class="nav-item active"><a class="nav-link" href="home">Home</a></li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Shop</a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a class="nav-link" href="#">Products</a></li>
                                        <li class="nav-item"><a class="nav-link" href="#">Product Checkout</a></li>
                                        <li class="nav-item"><a class="nav-link" href="#">Confirmation</a></li>
                                        <li class="nav-item"><a class="nav-link" href="#">Shopping Cart</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="blogs">Blog</a></li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Pages</a>
                                    <ul class="dropdown-menu">
                                        <% 
                                           boolean isUse = (session != null && session.getAttribute("account") != null);                                           
                                        

                                        <% if (isUse) { %>
                                        <li class="nav-item"><a class="nav-link" href="home">Welcome, ${sessionScope.account.fullName}</a></li>
                                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                                            <% } else { %>
                                        <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                                        <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
                                            <% } %
                                        <li class="nav-item"><a class="nav-link" href="tracking-order.html">Tracking</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
                            </ul>

                            <ul class="nav-shop">
                                <li class="nav-item"><button><a class="ti-shopping-cart" href="#"></a><span class="nav-shop__circle">3</span></button> </li>
                                <li class="nav-item"><a class="ti-user" href="#"></a></li>
                                <li class="nav-item"><a class="button button-header" href="#">Buy Now</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
    </body>
</html>
