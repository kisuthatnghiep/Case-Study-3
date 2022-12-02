<%--
  Created by IntelliJ IDEA.
  User: huyng
  Date: 02/12/2022
  Time: 08:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/bootstrap.js"></script>
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div id="header">
  <div class="container">
    <div class="row" style="padding: 10px;height: 75px">
      <div class="col-md-1 vertical">
        <a href=""><img src="image/logo_keeng.png" style="margin-top: 6px"/></a>
      </div>
    </div>
  </div>
</div>
<div id="center">
  <div class="container">
    <div class="row py-5 mt-4 align-items-center">

      <!-- Left side -->
      <div class="col-md-5 pr-lg-5 mb-5 mb-md-0">
        <img src="image/image-left-side.jpg" alt="image" class="img-fluid mb-5 d-none d-md-block">
        <h1 style="text-align: center">Create an Account</h1>
      </div>

      <!-- Registeration Form -->
      <div class="col-md-7 col-lg-6 ml-auto">
        <form method="post" action="${pageContext.request.contextPath}/register?action=create">
          <div class="row">
            <!-- Account -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-2">
              <span class="input-group-text px-4 bg-white">
                <i class="fa fa-user text-muted"></i>
              </span>
                <input type="text" class="form-control" placeholder="Account" name="account">
              </div>
            </div>

            <!-- Password -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-2">
              <span class="input-group-text px-4 bg-white">
                 <i class="fa fa-lock text-muted"></i>
              </span>
                <input type="password" class="form-control" placeholder="Password" name="password"
                       required="required">
              </div>
            </div>

            <!-- Password Confirm -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-2">
              <span class="input-group-text px-4 bg-white">
                <i class="fa fa-lock text-muted"></i>
              </span>
                <input type="password" class="form-control" placeholder="Password Confirm" name="passwordConfirm"
                       required="required">
              </div>
            </div>

            <!-- Full Name -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-3">
              <span class="input-group-text px-4 bg-white">
                <i class="fa fa-address-card-o text-muted"></i>
              </span>
                <input type="text" class="form-control" placeholder="Full Name" name="name"
                required="required">
              </div>
            </div>

            <!-- Email -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-3">
              <span class="input-group-text px-4 bg-white">
                <i class="fa fa-envelope text-muted"></i>
              </span>
                <input type="email" class="form-control" placeholder="Email Address" name="email"
                required="required">
              </div>
            </div>
            <!-- Role -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-3">
              <span class="input-group-text px-4 bg-white">
                <i class="fa fa-black-tie text-muted"></i>
              </span>
                <select id="job" name="role" class="form-control custom-select bg-white border-left-0 border-md">
                  <option value="">Choose your role</option>
                  <option value="user">User</option>
                  <option value="singer">Singer</option>
                </select>
              </div>
            </div>
            <!-- Telephone -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group mb-3">
              <span class="input-group-text px-4 bg-white">
                <i class="fa fa-phone-square text-muted"></i>
              </span>
                <input type="text" class="form-control" placeholder="Telephone" name="telephone"
                required="required">
              </div>
            </div>
            <!-- Submit Button -->
            <div class="form-group col-lg-4 mx-auto mb-0">
              <button type="submit" class="btn btn-primary btn-block py-2">
                <span class="font-weight-bold">Create your account</span>
              </button>
            </div>
            <!-- Divider Text -->
            <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
              <div class="border-bottom w-100 ml-5"></div>
              <div class="border-bottom w-100 mr-5"></div>
            </div>
            <!-- Already Registered -->
            <div class="text-center w-100">
              <p class="text-muted font-weight-bold">Already Registered? <a href="#" class="text-primary ml-2">Login</a></p>
            </div>

          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div id="footer">
  <div id="footer-top">
    <div class="container">
      <div class="row">
        <div class="col-md-2 center">
          <h3><a href="#">Nhạc</a></h3>
          <ul>
            <li><a href="#">Bài hát</a></li>
            <li><a href="#">Video</a></li>
            <li><a href="#">Album</a></li>
          </ul>
        </div>
        <div class="col-md-2 center" style="margin-top: 40px">
          <ul>
            <li><a href="#">BXH</a></li>
            <li><a href="#">Chủ đề</a></li>
            <li><a href="#">Nghệ sĩ</a></li>
          </ul>
        </div>
        <div class="col-md-1">

        </div>
        <div class="col-md-7" style="padding: 60px">
          <div class="logo">
            <img src="image/logo_mocha.png" alt="">
            <img src="image/logo_onbox.png" alt="">
          </div>
          <div class="logo">
            <img src="image/logo_onvideo.png" alt="">
            <img src="image/logo_songkhoe.png" alt="">
          </div>
          <div class="logo">
            <img src="image/logo_tiin.png" alt="">
            <img src="image/logo_viettelstudy.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
  <hr style="color: white">
  <div id="footer-bottom">
    <div class="container">
      <div class="row" style="padding-top: 30px">
        <div class="col-md-1" style="vertical-align: bottom">
          <img src="https://musicmd1fr.keeng.net/bucket-image-keeng/web_v5//images/logo_keeng_footer.png" width="200%">
          <img src="https://musicmd1fr.keeng.net/bucket-image-keeng/web_v5//images/gov-dathongbao.png" width="230" height="86">
        </div>
        <div class="col-md-11 sizetext">
          <h3>Mạng xã hội Keeng: Âm nhạc</h3>
          Cơ quan chủ quản: Công ty Truyền thông Viettel (Viettel Media) – Chi nhánh Tập đoàn Công nghiệp – Viễn thông Quân đội.
          <br>
          Chịu trách nhiệm nội dung: Ông Võ Thanh Hải
          <br>
          Địa chỉ giao dịch: Tầng 4, Tòa nhà The Light (CT2 Trung Văn), đường Tố Hữu, Nam Từ Liêm, Hà Nội
          <br>
          Chăm sóc khách hàng: 198 (Miễn phí)
          <span>Quảng cáo:hoptac@viettel.com.vn</span>
          <br>
          <span>Hợp tác nội dung: hoptac@keeng.vn</span>
          <br>
          Giấy phép: Số 365/GP-BTTTT do Bộ Thông tin và Truyền thông cấp ngày 28/07/2017
          <br>
          <a href="http://keeng.vn/info?type=gioi-thieu">Giới thiệu</a> |
          <a href="http://keeng.vn/info?type=quy-dinh-su-dung">Quy định sử dụng</a>|
          <a href="/info?type=gop-y-bao-loi">Góp ý - Báo lỗi</a> |
          <a href="/info?type=lien-he-hop-tac">Liên hệ quảng cáo</a>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>