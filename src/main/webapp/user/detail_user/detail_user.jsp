<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/2/2022
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/bootstrap/css/bootstrap.min.css">
  <script src=${pageContext.request.contextPath}/asset/bootstrap/js/bootstrap.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/user/detail_user/detail_user">
</head>
<body>
<div id="header">
  <div class="container">
    <div class="row" style="padding: 10px;height: 75px">
      <div class="col-md-1 vertical">
        <a href=""><img src="../../sourcePicture/logo_keeng.png" style="margin-top: 6px"/></a>
      </div>
      <div class="col-md-2 vertical" id="menu">
        <ul id="nav">
          <li style="margin-bottom: -18px"><a style="color:#7aaf16; font-weight: 700;width: 90px;" href="#">NHẠC</a>
            <ul class="subnav">
              <li><a href="">Bài hát</a></li>
              <li><a href="">MV</a></li>
              <li><a href="">Album</a></li>
              <li><a href="">EDM</a></li>
            </ul></li>

        </ul>
      </div>
      <div class="col-md-6 avatar vertical">
        <div class="input-group">
          <input class="form-control border-end-0 border" type="search" placeholder="Tìm kiếm" id="example-search-input">
          <span class="input-group-append">
                    <button class="btn btn-outline-secondary bg-white border-start-0 border-bottom-0 border ms-n5" type="button">
                        <i class="bi bi-search"></i>
                    </button>
                </span>
        </div>
      </div>
      <div class="col-md-3 vertical signIn">
        <a id="avatar" href=""><c:out value="${user.getName()}"/></a>
        <img src="../../sourcePicture/keeng_ver5_02.png"/>
      </div>
    </div>
  </div>
</div>
<div id="content">
  <div id="slider">
    <div class="container" >

      <div class="col col-md-4" style="float: left;">
<%--      sidebar--%>
      </div>


      <div class="col col-md-2" style="float: left;margin-top: 50px">
        <img style=" width: 150px;border-radius:50%;border-style: groove;" src="../../sourcePicture/avatardeatil.jpg" alt="avata loadding...." >
      </div>
      <div class="col col-md-6" style="float: left;margin-top: 30px">
        <table class="col col-md-12" id="table_detail">
          <tr>
            <th class="col col-md-1"></th>
            <th class="col col-md-2">Name</th>
            <td class="col col-md-7"><c:out value="${user.getName()}"/></td>
          </tr>
          <tr>
            <th class="col col-md-1"></th>
            <th class="col col-md-2">Account</th>
            <td class="col col-md-7"><c:out value="${user.getAccount()}"/></td>
          </tr>
          <tr>
            <th class="col col-md-1"></th>
            <th class="col col-md-2">Phone</th>
            <td class="col col-md-7"><c:out value="${user.getPhone()}"/></td>
          </tr>
          <tr>
            <th class="col col-md-1"></th>
            <th class="col col-md-2">Email</th>
            <td class="col col-md-7"><c:out value="${user.getEmail()}"/></td>
          </tr>
          <tr>
            <th class="col col-md-1"></th>
            <th class="col col-md-2">Wallet</th>
            <td class="col col-md-7"><c:out value="${user.getWallet()}"/></td>
          </tr>
          <tr>
            <th class="col col-md-1"></th>
            <th class="col col-md-2" colspan="2">
              <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Create Play List
              </button>
            </th>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>
<%--modal createPlayList--%>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="${pageContext.request.contextPath}/UserServlet?action=createPlayList" method="post">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Play List</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <table class="col-sm-8">
            <tr>
              <th class="col-sm-2"><label for="name">Name</label></th>
              <th class="col-sm-6"><input type="text" id="name" name="name" style="font-size: 20px"></th>
            </tr>
            <tr>
              <th class="col-sm-2"> <label for="avatarSRC">Avatar</label></th>
              <th class="col-sm-6"><input type="text" id="avatarSRC" name="avatarSRC" style="font-size: 20px"></th>
            </tr>
          </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-light" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-light">Create</button>
        </div>
      </form>
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
            <img src="../../sourcePicture/logo_mocha.png" alt="">
            <img src="../../sourcePicture/logo_onbox.png" alt="">
          </div>
          <div class="logo">
            <img src="../../sourcePicture/logo_onvideo.png" alt="">
            <img src="../../sourcePicture/logo_songkhoe.png" alt="">
          </div>
          <div class="logo">
            <img src="../../sourcePicture/logo_tiin.png" alt="">
            <img src="../../sourcePicture/logo_viettelstudy.png" alt="">
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

