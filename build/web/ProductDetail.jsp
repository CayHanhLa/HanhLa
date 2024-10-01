<%-- 
    Document   : ProductDetail
    Created on : Sep 14, 2024, 2:26:20 PM
    Author     : sontu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Product Details</title>
        <link rel="icon" href="img2/FIcon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .star-grey {
                color: grey !important;
            }
            .star {
                color: grey;/* Màu mặc định là xám cho tất cả các sao */
                cursor: pointer;
            }

            .star.gold {
                color: gold; /* Màu vàng cho các sao được chọn */
            }
        </style>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="blog">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Detail Information</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Detail</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->


        <!--================Single Product Area =================-->
        <div class="product_image_area">
            <div class="container">
                <div class="row s_product_inner">
                    <div class="col-lg-6">
                        <div class="owl-carousel owl-theme s_Product_carousel">
                            <div class="single-prd-item">
                                <img class="img-fluid" src="img/category/s-p1.jpg" alt="a">
                            </div>
                            <div class="single-prd-item">
                                <img class="img-fluid" src="img/category/s-p1.jpg" alt="a">
                            </div>
                            <div class="single-prd-item">
                                <img class="img-fluid" src="img/category/s-p1.jpg" alt="a">
                            </div> 
                        </div>
                    </div>
                    <div class="col-lg-5 offset-lg-1">
                        <div class="s_product_text">
                            <h3>${productDetail.title}</h3>
                            <h2>$${productDetail.price}</h2>
                            <ul class="list">
                                <li><a class="active" href="#"><span>Category</span> : Household</a></li>
                                <li><a href="#"><span>Availibility</span> : In Stock</a></li>
                            </ul>
                            <p>${productDetail.briefInfor}</p>
                            <div class="product_count">
                                <label for="qty">Quantity:</label>
                                <!-- Nút tăng số lượng -->
                                <button onclick="var result = document.getElementById('sst');
                                        var sst = parseInt(result.value, 10); // Chuyển giá trị sang số nguyên
                                        if (!isNaN(sst)) {
                                            result.value = sst + 1; // Tăng số lượng
                                        }
                                        return false;"
                                        class="increase items-count" type="button">
                                    <i class="ti-angle-left"></i>
                                </button>
                                <!-- Ô nhập số lượng -->
                                <input type="text" name="qty" id="sst" size="2" maxlength="12" value="${productDetail.stockQuanlity}" title="Quantity:" class="input-text qty">
                                <!-- Nút giảm số lượng -->
                                <button onclick="var result = document.getElementById('sst');
                                        var sst = parseInt(result.value, 10); // Chuyển giá trị sang số nguyên
                                        if (!isNaN(sst) && sst > 1) { // Chỉ giảm nếu số lượng > 1
                                            result.value = sst - 1; // Giảm số lượng
                                        }
                                        return false;"
                                        class="reduced items-count" type="button">
                                    <i class="ti-angle-right"></i>
                                </button>

                                <!-- Nút Thêm vào giỏ hàng -->
                                <a class="button primary-btn" href="#">Add to Cart</a>               
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--================End Single Product Area =================-->

        <!--================Product Description Area =================-->
        <section class="product_description_area">
            <div class="container">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Description</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
                           aria-selected="false">Specification</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
                           aria-selected="false">Comments</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review"
                           aria-selected="false">Reviews</a>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <p>${productDetail.description}</p>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>
                                            <h5>Catgeory:</h5>
                                        </td>
                                        <td>
                                            <h5>128mm</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>Size avalible:</h5>
                                        </td>
                                        <td>
                                            <h5>508mm</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>Brand</h5>
                                        </td>
                                        <td>
                                            <h5>85mm</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>Quantity Avalible:</h5>
                                        </td>
                                        <td>
                                            <h5>52gm</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>Quality checking</h5>
                                        </td>
                                        <td>
                                            <h5>yes</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>Freshness Duration</h5>
                                        </td>
                                        <td>
                                            <h5>03days</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>When packeting</h5>
                                        </td>
                                        <td>
                                            <h5>Without touch of hand</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5>Each Box contains</h5>
                                        </td>
                                        <td>
                                            <h5>60pcs</h5>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="comment_list">
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-1.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <h5>12th Feb, 2018 at 05:56 pm</h5>
                                                <a class="reply_btn" href="#">Reply</a>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                    <div class="review_item reply">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-2.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <h5>12th Feb, 2018 at 05:56 pm</h5>
                                                <a class="reply_btn" href="#">Reply</a>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-3.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <h5>12th Feb, 2018 at 05:56 pm</h5>
                                                <a class="reply_btn" href="#">Reply</a>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="review_box">
                                    <h4>Post a comment</h4>
                                    <form class="row contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="name" name="name" placeholder="Your Full name">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="email" class="form-control" id="email" name="email" placeholder="Email Address">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="number" name="number" placeholder="Phone Number">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <textarea class="form-control" name="message" id="message" rows="1" placeholder="Message"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-12 text-right">
                                            <button type="submit" value="submit" class="btn primary-btn">Submit Now</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="row total_rate">
                                    <div class="col-6">
                                        <div class="box_total">
                                            <h5>Overall</h5>
                                            <h4>${avgRating}</h4>
                                            <h6>(${numberOfFeedback} Reviews)</h6>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="rating_list">
                                            <h3>Based on ${numberOfFeedback} Reviews</h3>
                                            <ul class="list">
                                                <li><a href="#">5 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star"></i> ${numberFBByRating.get(0)}</a></li>
                                                <li><a href="#">4 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.get(1)}</a></li>
                                                <li><a href="#">3 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.get(2)}</a></li>
                                                <li><a href="#">2 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star star-grey"></i><i
                                                            class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.get(3)}</a></li>
                                                <li><a href="#">1 Star <i class="fa fa-star"></i><i class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i><i
                                                            class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.get(4)}</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="review_list">
                                    <c:forEach items="${feedbacks}" var="f">
                                        <div class="review_item">
                                            <div class="media">
                                                <div class="d-flex">
                                                    <img src="${f.avatar}" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <h4>${f.userName}</h4> <p style="padding-top: 0px"><fmt:formatDate value="${f.feedbackDate}" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Ho_Chi_Minh" /></p>
                                                    <c:forEach var="i" begin="1" end="5">
                                                        <i class="fa fa-star star-grey" <c:if test="${i <= f.rating}">style="color: gold !important"</c:if>></i>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <p style="padding-top: 10px">
                                                <c:if test="${f.imageURL != null}">
                                                    <img src="${f.imageURL}" width="100px" height="100px"/>
                                                </c:if>
                                            </p>
                                            <p>${f.feedbackText}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="review_box">
                                    <h4>Add a Review</h4>
                                    <p style="color: red">*Only people who have purchased this product can review it.</p>
                                    <form action="feedback" class="form-contact form-review mt-3" method="post" enctype="multipart/form-data">
                                        <div class="form-group">      
                                            <p>Your Rating:</p>
                                            <ul class="list start-list">
                                                <li><i class="fa fa-star star" data-value="1" onclick="setRating(1)"></i></li>
                                                <li><i class="fa fa-star star" data-value="2" onclick="setRating(2)"></i></li>
                                                <li><i class="fa fa-star star" data-value="3" onclick="setRating(3)"></i></li>
                                                <li><i class="fa fa-star star" data-value="4" onclick="setRating(4)"></i></li>
                                                <li><i class="fa fa-star star" data-value="5" onclick="setRating(5)"></i></li>
                                            </ul>
                                            <input type="hidden" name="rating" id="ratingValue" value="5">
                                            <p>Outstanding</p>
                                        </div>
                                        <input class="form-control" name="productId" type="hidden" value="1">
                                        <div class="form-group">                                          
                                            Image <input type="file" class="form-control" name="image" 
                                                         onchange="previewImage(this)"/>
                                        </div>
                                        <div class="form-group">
                                            <img src="" alt="Service Image" class="img-preview" id="img-preview-id"
                                                 style="width: 200px; border: 1px solid #ddd; border-radius: 5px;" />
                                        </div>
                                        Message: 
                                        <div class="form-group">
                                            <textarea class="form-control different-control w-100" name="message" id="textarea" cols="30" rows="5" placeholder="Enter Message" required></textarea>
                                        </div>
                                        <div class="form-group text-center text-md-right mt-3">
                                            <c:if test="${canFeedback != null && canFeedback == true}">
                                                <button type="submit" class="button button--active button-review">Submit Now</button>
                                            </c:if>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Product Description Area =================-->

        <!--================ Start related Product area =================-->  
        <section class="related-product-area section-margin--small mt-0">
            <div class="container">
                <div class="section-intro pb-60px">
                    <p>Popular Item in the market</p>
                    <h2>Top <span class="section-intro__style">Product</span></h2>
                </div>
                <div class="row mt-30">
                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">            
                        <div class="single-search-product-wrapper">
                            <h6>Top <span class="section-intro__style">Seller</span></h6>
                            <c:forEach items="${list3ProductsTopSeller}" var="p">
                                <div class="single-search-product d-flex">
                                    <a href="#"><img src="${p.thumbnail}" alt="image"></a>
                                    <div class="desc">
                                        <a href="#" class="title">${p.title}</a>
                                        <div class="price">${p.price}$</div>
                                        <div class="size">${p.size}</div>
                                    </div>
                                </div>
                            </c:forEach>                               
                        </div>
                    </div>
                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <h6>Top <span class="section-intro__style">Price</span></h6>
                            <c:forEach items="${list3ProductsTopSeller}" var="p">
                                <div class="single-search-product d-flex">
                                    <a href="#"><img src="${p.thumbnail}" alt="image"></a>
                                    <div class="desc">
                                        <a href="#" class="title">${p.title}</a>
                                        <div class="price">${p.price}$</div>
                                        <div class="size">${p.size}</div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <h6>Top <span class="section-intro__style">Trending</span></h6>
                            <c:forEach items="${list3ProductTopTrending}" var="p">
                                <div class="single-search-product d-flex">
                                    <a href="#"><img src="${p.thumbnail}" alt="image"></a>
                                    <div class="desc">
                                        <a href="#" class="title">${p.title}</a>
                                        <div class="price">${p.price}$</div>
                                        <div class="size">${p.size}</div>
                                    </div>
                                </div>
                            </c:forEach>                           
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <h6>Top <span class="section-intro__style">Sale</span></h6>
                            <c:forEach items="${list3ProductTopSale}" var="p">
                                <div class="single-search-product d-flex">
                                    <a href="#"><img src="${p.thumbnail}" alt="image"></a>
                                    <div class="desc">
                                        <a href="#" class="title">${p.title}</a>
                                        <div class="price">${p.price}$</div>
                                        <div class="size">${p.size}</div>
                                    </div>
                                </div>
                            </c:forEach> 
                        </div>
                    </div>
                </div>             
            </div>
        </div>
    </section>
    <!--================ end related Product area =================-->  	

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

    <script>
                                                             document.addEventListener('DOMContentLoaded', function () {
                                                                 const preview = document.getElementById('img-preview-id');
                                                                 preview.style.display = "none";
                                                             });
                                                             function previewImage(input) {
                                                                 const preview = document.getElementById('img-preview-id');

                                                                 // Kiểm tra xem có file được chọn hay không
                                                                 if (input.files && input.files[0]) {
                                                                     // Hiển thị preview của hình ảnh
                                                                     preview.src = window.URL.createObjectURL(input.files[0]);
                                                                     preview.style.display = "block"; // Hiển thị ảnh
                                                                 } else {
                                                                     preview.style.display = "none"; // Ẩn ảnh nếu không có file nào
                                                                 }
                                                             }
                                                             // Hàm để set giá trị rating khi người dùng chọn sao
                                                             function setRating(value) {
                                                                 document.getElementById("ratingValue").value = value;
                                                                 const stars = document.querySelectorAll('.start-list .star');
                                                                 stars.forEach((star, index) => {
                                                                     if (index < value) {
                                                                         star.style.color = 'gold';  // Đổi thành màu vàng
                                                                     } else {
                                                                         star.style.color = 'grey';  // Đổi thành màu xám
                                                                     }
                                                                 });
                                                             }

                                                             // Khi trang load lần đầu, set mặc định 5 sao vàng
                                                             window.onload = function () {
                                                                 setRating(5);  // Gán giá trị mặc định là 5 sao vàng
                                                             };
    </script>
</body>
</html>
