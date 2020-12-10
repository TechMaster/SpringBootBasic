# Đọc ra giá trị từ application.properties và application.yml

Trong ứng dụng Spring Boot, chúng ta thường xuyên phải đọc giá trị từ file cấu hình. Spring Boot có 2 file cấu hình mặc định (Có nghĩa bạn có thể thêm các file cấu hình khác nhưng phải ghi rõ tên):

1. [application.properties](src/main/resources/application.properties)
2. [application.yml](src/main/resources/application.yml)

Chú ý hai file có thể song song tồn tại, giá trị của application.yml có thể ghi đè lên giá trị của application.properties.

## Thí nghiệm 1: Đọc spring.application.name ở application.

Nội dung file [application.properties](src/main/resources/application.properties) như sau
```
spring.application.name=Amazon Book Store
```

Trong class [HomeController.java](src/main/java/vn/techmaster/bookstore/controller/HomeController.java), hãy thêm thuộc tính ```private String appName;```
Giờ chúng ta sẽ thấy sức mạnh của Java annotation, hãy bổ xung ngay trước đó
```
@Value("${spring.application.name}")
private String appName;
```

Tác dụng dòng này là đọc giá trị từ spring.application.name vào biến ```appName```. Giờ nội dung chính của HomeController như sau
```java
@Controller
public class HomeController {

  @Value("${spring.application.name}")
  private String appName;

  @ResponseBody
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return "Welcome " + appName;
  }
}
```
Kết quả xem được ở http://localhost:8080/ là Welcom Amazon Book Store

## Thí nghiệm 2: Cấu hình port lắng nghe ở cổng 9000

Mặc định ứng dụng Spring Boot sẽ lắng nghe ở cổng (port) 8080. Tuy nhiên chúng ta có thể cấu hình để nó lắng nghe
ở cổng khác chú ý hãy để giá trị cao hơn 8000 để không bị tranh chấp cổng với các ứng dụng phổ biến khác.

Trong [application.properties](src/main/resources/application.properties) hãy bổ xung
server.port=9000

Khởi động lại và truy cập vào http://localhost:9000

Thêm một chút nữa, là chúng ta hãy tạo biến để đọc giá trị cổng ra

Mã nguồn của HomeController.java giờ sẽ là
```java
@Controller
public class HomeController {

  @Value("${spring.application.name}")
  private String appName;

  @Value("${server.port}")
  private String port;

  @ResponseBody
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return "Welcome " + appName + "<br> Serves at port:  " + port;
  }
}
```
Truy cập vào http://localhost:9000/ sẽ thấy
```
Welcome Amazon Book Store
Serves at port: 9000
```
## Thí nghiệm 3: Chuyển sang sử dụng application.yml thay vì application.properties

Chuyển application.properties sang application.yml
```
spring.application.name=Amazon Book Store
server.port=9000
```
application.yml
```yaml
spring:
  application:
    name: Amazon Book Store
server:
  port: 9000
```

## Thí nghiệm 4: Có thể thêm key/value bất kỳ vào application.properties và application.yml rồi đọc ra
```yaml
magic:
  key: mnw#q5412$09
```