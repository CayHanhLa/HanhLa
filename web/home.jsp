<%-- 
    Document   : home
    Created on : Sep 14, 2024, 1:27:12 AM
    Author     : sontu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
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
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <main class="site-main">

            <!--================ Hero banner start =================-->
            <div id="heroSlider" class="carousel slide" data-ride="carousel">
                <!-- Indicators (tuỳ chọn) -->
                <ol class="carousel-indicators">
                    <c:forEach var="p" items="${list3ProductTrending}" varStatus="status">
                        <li data-target="#heroSlider" data-slide-to="${status.index}" class="${status.first ? 'active' : ''}"></li>
                        </c:forEach>
                </ol>

                <!-- Slide nội dung -->
                <div class="carousel-inner">
                    <c:forEach var="p" items="${list3ProductTrending}" varStatus="status">
                        <div class="carousel-item ${status.first ? 'active' : ''}">
                            <div class="row no-gutters align-items-center pt-60px">
                                <div class="col-5 d-none d-sm-block">
                                    <div class="hero-banner__img">
                                        <img class="img-fluid" src="${p.thumbnail}" alt="image">
                                    </div>
                                </div>
                                <div class="col-sm-7 col-lg-6 offset-lg-1 pl-4 pl-md-5 pl-lg-0">
                                    <div class="hero-banner__content">
                                        <h4>${p.title}</h4>
                                        <h1>${p.briefInfor}</h1>
                                        <p>${p.description}</p>
                                        <a class="button button-hero" href="#">Buy Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Điều hướng -->
                <a class="carousel-control-prev" href="#heroSlider" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#heroSlider" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <!--================ Hero banner start =================-->

            <!--================ Hero Carousel start =================-->
            <section class="section-margin mt-0">
                <div class="owl-carousel owl-theme hero-carousel">
                    <c:forEach items="${list3Products}" var="p">
                        <div class="hero-carousel__slide">
                            <img src="${p.thumbnail}" alt="image" class="img-fluid">
                            <a href="#" class="hero-carousel__slideOverlay">
                                <h3>${p.title}</h3>
                                <p>${p.brandName}</p>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </section>
            <!--================ Hero Carousel end =================-->

            <!-- ================ trending product section start ================= -->  
            <section class="section-margin calc-60px">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Trending <span class="section-intro__style">Product</span></h2>
                    </div>
                    <div class="row">
                        <c:forEach items="${list8ProductsTrending}" var="p">
                            <div class="col-md-6 col-lg-4 col-xl-3">

                                <div class="card text-center card-product">
                                    <div class="card-product__img">
                                        <img class="card-img" src="${p.thumbnail}" alt="image">
                                        <ul class="card-product__imgOverlay">
                                            <li><button><a class="ti-search" href="#"></a></button></li>
                                            <li><button><a class="ti-shopping-cart" href="product_detail?id=${p.id}"></a></button></li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-product__price">${p.price}$</p>                                       
                                        <h4 class="card-product__title"><a href="product_detail?id=${p.id}">${p.title}</a></h4>
                                        <p>${p.briefInfor}</p>
                                    </div>
                                </div>
                            </div>  
                        </c:forEach>                        
                    </div>
                </div>
            </section>
            <!-- ================ trending product section end ================= -->  


            <!-- ================ offer section start ================= --> 
            <section class="offer" id="parallax-1" data-anchor-target="#parallax-1" data-300-top="background-position: 20px 30px" data-top-bottom="background-position: 0 20px">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5">
                            <div class="offer__content text-center">
                                <h3>Big sale on this September</h3>
                                <h4>Winter Sale</h4>
                                <p>Him she'd let them sixth saw light</p>
                                <a class="button button--active mt-3 mt-xl-4" href="#">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ offer section end ================= --> 

            <!-- ================ Best Selling item  carousel ================= --> 
            <section class="section-margin calc-60px">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Best <span class="section-intro__style">Sellers</span></h2>
                    </div>
                    <div class="owl-carousel owl-theme" id="bestSellerCarousel">
                        <c:forEach items="${list4ProductNewest}" var="p">
                            <div class="card text-center card-product">
                                <div class="card-product__img">
                                    <img class="img-fluid" src="${p.thumbnail}" alt="imagez">
                                    <ul class="card-product__imgOverlay">
                                        <li><button><a class="ti-search" href="#"></a></button></li>
                                        <li><button><a class="ti-shopping-cart" href="product_detail?id=${p.id}"></a></button></li>
                                    </ul>
                                </div>
                                <div class="card-body">
                                    <p class="card-product__price">${p.price}$</p>                                       
                                    <h4 class="card-product__title"><a href="product_detail?id=${p.id}">${p.title}</a></h4>
                                    <p>${p.briefInfor}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <!-- ================ Best Selling item  carousel end ================= --> 

            <!-- ================ Blog section start ================= -->  
            <section class="blog">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Latest <span class="section-intro__style">News</span></h2>
                    </div>

                    <div class="row">
                        <c:forEach items="${top3LastestBlog}" var="p">
                            <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                                <div class="card card-blog">
                                    <div class="card-blog__img">
                                        <img class="card-img rounded-0" src="${p.postImg}" alt="">
                                    </div>
                                    <div class="card-body">
                                        <ul class="card-blog__info">
                                            <li><a href="#">By ${p.userName}</a></li>
                                            <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                                        </ul>
                                        <h4 class="card-blog__title"><a href="blog-detail?id=${p.postID}">${p.title}</a></h4>
                                        <p>${p.postBrief}</p>
                                        <a class="card-blog__link" href="blog-detail?id=${p.postID}">Read More <i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <!-- ================ Blog section end ================= -->  

            <!-- ================ Subscribe section start ================= --> 
            <section class="subscribe-position">
                <div class="container">
                    <div class="subscribe text-center">
                        <h3 class="subscribe__title">Get Update From Anywhere</h3>
                        <p>Bearing Void gathering light light his eavening unto dont afraid</p>
                        <div id="mc_embed_signup">
                            <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01" method="get" class="subscribe-form form-inline mt-5 pt-1">
                                <div class="form-group ml-sm-auto">
                                    <input class="form-control mb-1" type="email" name="EMAIL" placeholder="Enter your email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Your Email Address '" >
                                    <div class="info"></div>
                                </div>
                                <button class="button button-subscribe mr-auto mb-1" type="submit">Subscribe Now</button>
                                <div style="position: absolute; left: -5000px;">
                                    <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
                                </div>

                            </form>
                        </div>

                    </div>
                </div>
            </section>
            <!-- ================ Subscribe section end ================= --> 



        </main>


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
