# Mã nguồn khoá học SringBoot căn bản tại Techmaster
Khoá học trong 18 buổi học x 3 tiếng.

- Tác giả Trịnh Minh Cường, cuong@techmaster.vn
- Giảng viên hướng dẫn thực hành: Trịnh Minh Cường
- Giảng viên thỉnh giảng: Nam Loda.me, Trần Thịnh, Trịnh Minh Thuý, Nguyễn Tuấn Anh

## Nội dung học
1. Basic: căn bản, cấu trúc ứng dụng SpringBoot
2. Component - Bean, Dependency Injection
3. REST: các phương thức GET, POST, PUT, DELETE
4. Thymeleaf: render HTML views, form, validate form, upload binary
5. JPA: kết nối cơ sở dữ liệu H2 hoặc Postgresql, MySQL
6. Spring Security: bảo mật, phân quyền user
7. JWT: Json Web Token bảo mật REST API
8. WebFlux: tuỳ chọn vì đây là kỹ thuật nâng cao, chỉ dạy khi còn thời gian
9. Đồ án tốt nghiệp

## Sử dụng gitrepo này như thế nào?
Do các giảng viên liên tục cập nhật code, fix bug do đó các bạn nên clone repo này về máy tính cá nhân như sau:

1. Lần đầu tiên lấy code

```bash
git clone https://github.com/TechMaster/SpringBootBasic.git
```

2. Lâu lâu lại git pull để lấy về những cập nhật mới
```bash
git pull
```

Nếu bạn sửa trực tiếp vào ví dụ thì khi git pull có thể xảy ra xung đột, do đó nếu muốn sửa, hãy copy ra một thư mục riêng.

## Hướng dẫn cách biên dịch mã nguồn
Do thư mục git repo này phân cầp thành nhiều thư mục, cuối cùng mới đến các thư mục dự án Spring Boot ví dụ do đó các bạn cần vào sâu đến thư mục chứa file pom.xml mở ra bằng VSCode hoặc Intellij rồi hãy code hay biên dịch.

Nếu phát hiện bug hãy email cho cuong@techmaster.vn
Tôi sẽ sửa và vá lỗi.

## Quy định điều kiện tốt nghiệp lớp này
1. Hoàn thành đồ án là một web site viết bằng SpringBoot, kết nối CSDL MySQL hoặc Postgresql, có giao diện web hoặc trả về API thì phải sử dụng End 2 End Testing để kiểm thử tự động hoặc sử dụng WebFlux
2. Đồ án phải đóng gói được trong Docker file hoặc Docker Compose nếu kèm CSDL
3. Có slide thuyết trình thiết kế bằng PowerPoint hoặc PDF mô tả chức năng chính, cấu trúc chương trình
4. Đồ án bảo vệ trong hội đồng gồm 1 giảng viên Techmaster và 2 lập trình viên SpringBoot độc lập ở công ty ngoài

Sinh viên được cộng điểm khi dịch 5 bài tutorials SpringBoot tuỳ chọn, không dùng lại bài viết tiếng Việt có sẵn. Bài viết 


## Sách tham khảo
- [Spring in Action 5th: step by step](https://www.amazon.com/Spring-Action-Craig-Walls/dp/1617294942)
- [Pro Spring 5: tổng quan nhiều công nghệ](https://www.amazon.com/Pro-Spring-Depth-Guide-Framework/dp/1484228073)
- [SpringBoot 2 Recipes: có vài ví dụ ngắn thú vị](https://www.amazon.com/Spring-Boot-Recipes-Problem-Solution-Approach-ebook/dp/B07FY5XR9N)
## Khoá học tham khảo
- [Lập trình web với Spring Boot online](https://techmaster.vn/khoa-hoc/2l6/lap-trinh-web-voi-spring-boot-online)
- [Udemy Lập trình Resful web với JPA và MySQL](https://www.udemy.com/course/restful-web-service-with-spring-boot-jpa-and-mysql/)
- [Udemy Bảo mật OAuth2 trong SpringBoot](https://www.udemy.com/course/oauth2-in-spring-boot-applications)
- [Lập trình Reactive WebFlux](https://www.udemy.com/course/build-reactive-restful-apis-using-spring-boot-webflux)

## Web site tham khảo
- [Baeldung.com có rất nhiều bài viết hay](https://www.baeldung.com/)
- [Nhiều tuts ngắn](http://zetcode.com/all/#springboot)
- [19 bài tutorials của thầy Nam Loda.me](https://loda.me/spring-boot-0-series-lam-chu-spring-boot-zero-to-hero-loda1558963914472/)
- [Giải thích các Annotations trong SpringBoot](https://springframework.guru/spring-framework-annotations/)