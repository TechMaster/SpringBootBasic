# Lập trình tại lớp 180 phút: ứng dụng Micro Blog

## Yêu cầu

**Bắt buộc**
1. Lập trình ngay tại lớp trong 180 phút, không mang về nhà
2. Sử dụng đúng cấu trúc dự án mẫu
3. Đúng và đủ chức năng Login, CRUD Post, Create - Delete Comment
4. Demo cho giảng viên cuối giờ để được chấm điểm

**Không bắt buộc**
1. Nếu thời gian có hạn, bỏ qua việc viết Unit Test
2. Không cần viết Service component nếu thấy không cần thiết
3. Không cần chức năng Create new account, Change Password...
4. Không cần trang trí CSS. Giảng viên mù tịt boot strap. Ok !

## Mockup giao diện
![](images/login.jpg)

![](images/home.jpg)

![](images/new_post.jpg)

![](images/post_detail.jpg)

## Quan hệ giữa các bảng
- One user writes many posts
- One user writes many comments
- One post can receive many comments

## Dữ liệu mẫu có sẵn
6 users mẫu với password là 'abc' đã được nạp sẵn từ file [user.sql](src/main/resources/user.sql)
![](images/sample_users.jpg)

## Cấu trúc thư mục
```
.
├── java
│   ├── vn
│   │   ├── techmaster
│   │   │   ├── blog
│   │   │   │   ├── controller
│   │   │   │   │   ├── request
│   │   │   │   │   │   └── LoginRequest.java
│   │   │   │   │   ├── response
│   │   │   │   │   ├── HomeController.java
│   │   │   │   │   ├── PostController.java
│   │   │   │   │   └── Route.java
│   │   │   │   ├── DTO  <-- Data Transfer Object
│   │   │   │   │   ├── UserInfo.java
│   │   │   │   │   └── UserMapper.java <-- Dùng để chuyển đối tượng User sang UserInfo
│   │   │   │   ├── model
│   │   │   │   │   ├── Comment.java
│   │   │   │   │   ├── Post.java
│   │   │   │   │   └── User.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── CommentRepository.java
│   │   │   │   │   ├── PostRepository.java
│   │   │   │   │   └── UserRepository.java
│   │   │   │   ├── service
│   │   │   │   │   ├── AuthenException.java
│   │   │   │   │   ├── AuthenService.java
│   │   │   │   │   ├── IAuthenService.java
│   │   │   │   │   ├── iPostService.java
│   │   │   │   │   └── PostService.java
│   │   │   │   └── BlogApplication.java
├── resources
│   ├── static
│   ├── templates
│   │   ├── home.html
│   │   ├── layout.html
│   │   ├── login.html
│   │   └── posts.html
│   ├── application.properties
│   └── user.sql

```

# Thay đổi

## 2021-01-15
1. Loại bỏ CookieManager chuyển logic ```setLoginCookie``` và ```clearLoginCookie``` vào [AuthenService.java](src/main/java/vn/techmaster/blog/service/AuthenService.java)
2. Bổ xung thư mục [DTO](target/classes/vn/techmaster/blog/DTO) gồm 2 files:
  - [UserInfo.java](src/main/java/vn/techmaster/blog/DTO/UserInfo.java) chỉ lưu dữ liệu căn bản của User bỏ qua thông tin nhạy cảm
  - [UserMapper.java](src/main/java/vn/techmaster/blog/DTO/UserMapper.java) dùng để chuyển đổi thuộc tính từ đối tượng [User.java](src/main/java/vn/techmaster/blog/model/User.java) sang [UserInfo.java](src/main/java/vn/techmaster/blog/DTO/UserInfo.java)

Phải bổ xung thêm 2 Mastruct dependencies vào [pom.xml](pom.xml)
```xml
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct</artifactId>
  <version>1.4.1.Final</version>
</dependency>
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct-processor</artifactId>
  <version>1.4.1.Final</version>
</dependency>
```
3. [PostController.java](src/main/java/vn/techmaster/blog/controller/PostController.java) trả về thông tin người dùng để hiển thị
4. Viết kiểm thử tạo Post và Comment ở [PostCommmentRepositoryTest.java](src/test/java/vn/techmaster/blog/PostCommentRepositoryTest.java)
