# Trả về text/html

Khi chạy Spring Boot sẽ hứng ở cổng 8080, trong ví dụ này cần in ra dòng chữ "This is Amazon Book Store" ở đường dẫn gốc "/"


## Step by Step
1. Tạo một thư mục controller trong src/main/java/vn/techmaster/bookstore
2. Tạo một class HomeController.java
3. Thêm @Controller đánh dấu loại class này để điều hướng các request
4. Tạo phương thức ```public String getHome()```
5. Thêm @ResponseBody để phương thức trả về dữ liệu ngay mà không qua View Template Engine

```java
package vn.techmaster.bookstore.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  @ResponseBody // 
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return "This is Amazon Book Store";
  }
}
```

Cấu trúc dự án giờ đây sẽ như sau
```
.
├── main
│   ├── java
│   │   └── vn
│   │       └── techmaster
│   │           └── bookstore
│   │               ├── controller
│   │               │   └── HomeController.java
│   │               └── BookstoreApplication.java
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
```
## Thí nghiệm 1: thử Live Reload
Hãy trải nghiệm chức năng LiveReload bằng cách thay đổi đoạn text trả về ```"This is Amazon Book Store"``` đổi thành ```"This is my Book Store"```

Không cần biên dịch lại ứng dụng mà chỉ refresh lại trình duyệt

## Thí nghiệm 2: bổ xung about page
Thêm phương thức getAbout()

```java
@ResponseBody
  @GetMapping(value="/about", produces=MediaType.TEXT_HTML_VALUE)
  public String getAbout() {
    return "About page";
  }
```

không cần biên dịch lại hãy truy cập
http://localhost:8080/about