# Dự án Authen

Đây là dự án khung cho phần bài tập lập trình [Xác thực user](../../HomeWork.md)

## Cấu trúc thư mục
```
.
├── main
│   ├── java
│   │   └── vn
│   │       └── techmaster
│   │           └── authen
│   │               ├── config
│   │               │   └── AppConfig.java
│   │               ├── controller
│   │               ├── model
│   │               │   ├── Event.java
│   │               │   ├── Role.java
│   │               │   └── User.java
│   │               ├── repository  <-- Nếu bạn dùng EntityManager thì không cần JPARepository
│   │               ├── service
│   │               │   ├── AuthenException.java  <-- Class dành riêng để throw Exception
│   │               │   ├── AuthenService.java
│   │               │   └── IAuthenService.java <-- Interface của AuthenService
│   │               └── AuthenApplication.java
│   └── resources
│       ├── static
│       ├── templates
│       ├── application.properties
│       └── roles.sql  <-- Nạp danh sách role vào
└── test
    └── java
        └── vn
            └── techmaster
                └── authen
                    ├── AuthenServiceTest.java  <-- Kiểm thử dịch vụ AuthenService
                    └── UserTest.java  <-- Kiểm thử model User
```

## Băm password

Tham khảo bài viết này [Registration with Spring Security – Password Encoding](https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt)

Cấu hình Bean được viết ở file này [AppConfig.java](src/main/java/vn/techmaster/authen/config/AppConfig.java)

## Viết đến đâu kiểm thử đến đó.

1. Đừng viết vội, chúng ta có rất nhiều thời gian
2. Giao diện chỉ cần tối giản thôi, hãy tập trung vào logic back end và kiểm thử JUnit5

## Có cần Session và Cookie không?

Có ! Sau khi user login thành công hãy tạo session và trả về cookie là userid, full name.

Mỗi truy cập vào các đường dẫn cần bảo mật hãy kiểm tra cookie

Phần này đã học ở buổi Session & Cookie của thầy Thịnh rồi.

## Mock Test
https://reflectoring.io/unit-testing-spring-boot/