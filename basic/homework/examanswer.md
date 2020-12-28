# Đáp án đề kiểm tra kiến thức lớp SpringBoot 1A + 1B

Công việc tối thứ 3 29/12/2020, hãy đọc kỹ đáp án này rồi chấm chéo cho bài làm của bạn.

Với mỗi câu trả lời sai, rối nghĩa, khó hiểu, chữ không đọc nổi --> 0 điẻm

Với câu trả lời đúng, khá đúng, chữ đọc được --> 1 điểm

Sau đó cộng tổng số điểm, gửi lại cho cô Thanh Hương để nhập vào trong hệ thống điểm.


## Bài 1: căn bản
**1. Spring Boot khác gì với Spring Framework?**

Hãy tham khảo bài này [A Comparison Between Spring and Spring Boot](https://www.baeldung.com/spring-vs-spring-boot)
> Spring Boot is basically an extension of the Spring framework which eliminated the boilerplate configurations required for setting up a Spring application.

> Spring Boot là mở rộng, cải tiến của Spring framework. Nó loại bỏ bớt các cấu hình mẫu khung (thường là khá dài dòng) khi khởi tạo một ứng dụng Spring.

**Ưu điểm của Spring Boot:**

- Cung cấp sẵn các thư viện dependencies đơn giản việc xây dựng và cấu hình ứng dụng. Ví dụ Thymeleaf có một thư viện [Thymeleaf](https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf) nhưng Spring Boot sẽ dùng một thư viện [Spring Boot Starter Thymeleaf](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf) cấu hình dành riêng cho ứng dụng SpringBoot.
- Nhúng sẵn web server bên trong ứng dụng Spring Boot để đơn giản hoá khi đóng gói. Mặc định là TomCat nhưng có thể thay bằng Netty.
- Hỗ trợ lấy thông số, kiểm tra sức khoẻ ứng dụng [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)
- Có nhiều cách cấu hình ứng dụng sử dụng ```@Configuration``` hoặc ```application.properties``` hoặc ```application.yml```
- Có sẵn các cấu hình mặc định ví dụ bố trí các thức mục con... để giảm tối đa cấu hình lại.
----
**2. Những cải tiến Spring Boot so với Servlet JSP trước đây?**

Cải tiến của Spring Boot so với Servlet JSP:
- Spring Boot cấu hình sẵn gần như mọi thứ. Còn Servlet sử dụng XML để cấu hình
- Spring Boot đóng gói TomCat bên trong. Servlet dùng TomCat ở ngoài
- Servlet gồm các API ở mức thấp xử lý request - response. Trong khi Spring Boot bổ xung thêm pattern Model - View - Controller
----
**3. JAR khác WAR ở những điểm nào?**

   - JAR đóng gói tất các file class, tài nguyên vào một file Zip để có thể tái sử dụng như là một thư viện, hay nột ứng dụng độc lập.
   - WAR phù hợp để đóng gói ứng dụng web theo cấu trúc để triển khai lên web server.

----
**4. Maven khác Gradle ở những điểm nào?**
   - Maven tạo ra file pom.xml (dạng XML) để cấu hình build ứng dụng gồm tên ứng dụng, group id, artifactid, thông số Java JDK, các thư viện phụ thuộc dependecies, các plugins. Maven ra đời sớm, phổ biến hơn,nhưng tốc độ build chậm hơn. Maven có hệ thống quản lý các dependencies là [https://mvnrepository.com/](https://mvnrepository.com/)
   - Gradle sử dụng script ngắn gọn hơn, dạng thực thi dòng lệnh hơn là cấu hình. Tốc độ build của Gradle cho dự án lớn tốt hơn Maven.

---- 

**5. Annotation ```@ResponseBody``` có ý nghĩa gì?**
Đánh dấu một phương thức trong Controller trả về luôn cho trình duyệt dữ liệu mà không qua xử lý thêm.

----
**6. annotation ```@GetMapping``` có tác dụng gì đối với phương thức trong Controller?**
Đánh dấu một phương thức trong Controller sẽ hứng / xử lý GET request gửi lên trình duyệt.

----
**7. annotation ```@Controller``` thuộc package nào?**

Thuộc package ```org.springframework.stereotype.Controller```

----

**8. annotation ```@SpringBootApplication``` bao gồm những annotation nào bên trong?**
Gồm 3 annotation sau đây: 
- @EnableAutoConfiguration: bật cơ chế cấu hình tự động
- @ComponentScan: bật cơ chế quét các @Bean, @Component ở mọi cấp thư mục
- @Configuration: cho phép đăng ký phương thức trả về Bean

----
**9.  Khi biên dịch một ứng dụng SpringBoot tải trên mạng, IDE báo lỗi không tìm thấy JDK version phù hợp vậy phải vào file pom.xml để sửa thuộc tính nào?**

Sửa thẻ ```java.version``` trong file pom.xml
```xml
<properties>
	<java.version>14</java.version>
</properties>
```
----

**10.  Khi muốn trả về dữ liệu kiểu JSON cần phải thêm tham số nào vào ```@GetMapping```?**

```@GetMapping(value="/json", produces=MediaType.APPLICATION_JSON_VALUE)```

----
**11.  Nếu có hai controller khác nhau nhưng có 2 phương thức mà ```@GetMapping``` cùng trỏ vào một đường dẫn khi chạy ứng dụng, điều gì sẽ xảy ra?**

Không biên dịch được. Báo lỗi có 2 Controller cùng mapping đến một địa chỉ.

----
**12.  Trong file pom.xml có nhiều element <dependency>, trong đó lại có element <scope>, thường có 2 giá trị trong scope là ```runtime``` và ```test```**
    
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
	<optional>true</optional>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```


Khác biệt của scope  ```<scope>runtime</scope>``` với ```<scope>test</scope>``` là gì?

Trả lời: scope runtime chỉ rằng những thư viện này được biên dịch cùng ứng dụng phục vụ lúc chạy. Còn scope test dùng để chạy kiểm thử unit test

---

**13.  Khi ta tạo một class Book như dưới đây**
	
```java
public class Book {
	private int id;
	private String title;
	private String author;
}
```

Hãy liệt kê các cách trong Visual Studio Code hay IntelliJ để tự động sinh ra các phương thức getter, setters, equalsTo, hash, toString

Trả lời: Trong Visual Studio Code, chuột phải, menu rồi chọn Source Action

---

**14. Cách để thay đổi port mặc định của ứng dụng Spring Boot sang port 9000 trong file application.yml như thế nào?**
```yml
server:
	port: 9000
```

---

**15. Có thể định nghĩa một key/value mới trong application.properties được không?**

Hoàn toàn được.

---

**16. Trong một dự án SpringBoot có thể có 2 file cấu hình application.yml và application.properties cùng song song tồn tại được không?**

Hoàn toàn được. Giá trị định nghĩ trong application.yml được load sau, do đó sẽ đè lên giá trị trong application.properties nếu bị trùng

---

**17. Tại sao các thuộc tính của một class trong Model thường có access modifier là private? Tại không để mặc định hoặc là public cho dễ truy xuất?**

```java
public class Book {
	private int id;  //thường là private
}
```

Trả lời: ưu tiên dùng ```private``` để kiểm soát việc đọc (getter) và ghi (setter). Có thể viết logic để biến đổi dữ liệu khi đọc ra, hoặc validate dự liệu khi ghi

---

**18. Trong thư mục dự án Spring Boot, chúng ta thường thấy các thư mực controller, model, service, repository. Hỏi liệu có thể để các file class XXXController.java ở thư mục cùng cấp với Application.java?
Việc bố trí thư mục này có ý nghĩa gì?**

Trả lời:
Hoàn toàn có thể để class XXXController.java ở thư mục cùng cấp với Application.java nhưng việc này không khuyến khích. Hãy để class XXXController.java vào trong thư mục controller

Việc bố trí thư mục giúp phân loại chức năng của các file java tốt hơn, dễ tìm và quản lý hơn.

---

**19. Trong tiến trình khởi động ứng dụng Spring Boot, web server TomCat khởi động trước hay là đối tượng Application khởi động trước?**

Đối tượng Application khởi động trước, đọc các cấu hình vào đặc biệt là cấu hình web server rồi mới khởi động TomCat. Mở log lúc khởi động ra sẽ thấy

---

**20. Tính năng NIO trong web server TomCat là gì?**

Tính năng NIO trong web server TomCat là Non-blocking I/O. Tính năng này đối lập với blocking I/O.

Non Blocking I/O dùng chung 1 thread để xử lý vào ra nhiều kết nối. Còn Blocking I/O với mỗi kết nối phải tạo ra một thread riêng biệt

[Xem giải thích chi tiết ở đây](http://tutorials.jenkov.com/java-nio/nio-vs-io.html)

---

**21.  Ta có một mảng các đối tượng kiểu Book cần trả về kiểu application/json vậy phải dùng thư viện gì để convert mảng này sang JSON string?**

[jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

---

**22.  Sau khi bạn tạo xong Spring Boot project, bạn phát hiện ra bạn quên không chọn dependency Thymeleaf, vậy bạn phải làm gì để bổ xung dependency này?**

Sửa file pom.xml bổ xung
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    <version>2.4.1</version>
</dependency>
```
---

**23.  Hãy mô tả cú pháp Thymeleaf để gán giá trị động vào 2 thuộc tính ```src``` và ```alt``` của thẻ ```<img>```.**

```html
<img th:attr="src=@{'/images/' + ${book.id} + '.jpg'}, alt=${book.title}"/>
```

---
**24.  Ta có một mảng 20 phần tử nhưng khách hàng chỉ muốn hiển thị ra tất cả các phần tử ở vị trí lẻ: 1, 3, 5, 7, 9,.. vậy phải làm thế nào?** Gợi ý xử lý trong Controller hoặc sử dụng cú pháp Thymeleaf

Cách đơn giản nhất là tạo một ArrayList mới, duyệt qua mảng ban đầu chọn ra phần tử ở các vị trí lẻ 1, 3, 5.

Còn đây là cách sử dụng biến status trong ```th:each```. Kiểm tra nếu ```status.even``` bằng true thì thêm ```class = "hide"``` để ẩn li vị trí chẵn
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
        .hide {
            display: none;
        }
    </style>
</head>
<body>
    <h1 th:text="${appName}"></h1>
    <ul>
        <li th:each="book, status: ${books}" th:class="${status.even}?'hide'">
            <span th:text="${book.title + ' : ' + book.author}"></span>
        </li>
    </ul>
    </div>
</body>
</html>
```

----
**25.  Khái niệm POJO trong java là gì?**

```POJO``` viết tắt của Plain Old Java Object, có nghĩa là đối tượng Java thuần tuý.

----

## Bài 2: Bean, Dependency Injection

----
**26. Hãy giải thích tóm tắt cơ chế Dependency Injection cho người không giỏi CNTT cũng có thể hiểu được.**

Dependency Inject là cách để lắp ghép linh hoạt các thành phần tạo nên ứng dụng theo quy ước mặc định hoặc cấu hình có chủ ý của lập trình viên.

---

**27.   Hãy liệt ít nhất 2 cách để lấy Application Context.**

Cách 1: sử dụng
```java
@Autowired
private ApplicationContext appContext;
```

Cách 2: Kế thừa ```interface ApplicationContextAware```
rồi viết đè phương thức ```void setApplicationContext(ApplicationContext applicationContext)```

---

**28.  Cơ chế nào giúp ApplicationContext có thể có được danh sách tất cả các component and bean?**

Cơ chế Component Scan. Cần phải enable ```@AutoScan``` lên hoặc dùng annotation ```@SpringBootApplication```.

----

**29.  ```@Component``` khác với ```@Bean``` ở những điểm nào?**
```@Component``` annotate ở mức class. Còn ```@Bean``` annotate phương thức sẽ trả đối tượng sẽ trở thành bean. ```@Bean``` phải sử dụng bên trong class được đánh dấu bởi ```@Configuration```

----

**30. Annotation ```@Configuration``` có tác dụng gì?**
Tạo ra class cấu hình có các phương thức trả về Bean

---

**31.  Khi có 2 bean trả về cùng kiểu, dùng annotation nào để ưu tiên dùng mặc định một bean?**

Sử dụng ```@Primary```

---

**32.  Tên của bean mặc định sẽ là tên phương thức được annotated bởi @Bean, tuy nhiên chúng ta có thể đổi lại tên bean khác với tên phương thức. Cú pháp sẽ là gì?**

Sử dụng ```@Bean(name = "newNameOfBean")```

---

**33.  Khi có 2 bean khác tên, nhưng trả về cùng 1 kiểu, cùng với annotation ```@Autowired```, chúng ta cần thêm annotation nào khác để chọn bean theo tên trước khi gán vào biến?**

Sử dụng ```@Qualifier("nameOfBean")```

**34.  Trong một ứng dụng, có thể có nhiều class được đánh dấu ```@Configuration``` hay chỉ được phép duy nhất một?**

Hoàn toàn có nhiều class được đánh dấu bằng ```@Configuration```

---

**35.  Mặc định mỗi bean được tạo ra dưới dạng một Singleton, cơ chế nào cho phép bean được đăng ký ApplicationContext nhưng chỉ khởi tạo khi cần?**

Sử dụng khai báo

```java
@Bean
@Scope("prototype")
```
và ```@Lazy``` cùng với ```@Autowired``` khi annotate biến.

---

## Bài 3: Đọc CSV, Excel
36. Hãy kể tên thư viện dependency đọc CSV vào POJO
```xml
<dependency>
  <groupId>com.fasterxml.jackson.dataformat</groupId>
  <artifactId>jackson-dataformat-csv</artifactId>
</dependency>
```

hoặc

```xml
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
</dependency>
```

---

**37. Hãy kể tên 2 thư viện depedency đọc Excel vào POJO**

```xml
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
</dependency>
```

và 

```xml
<dependency>
    <groupId>com.github.ozlerhakan</groupId>
    <artifactId>poiji</artifactId>
</dependency>
```

---

 **38. Tại sao cách đọc dữ liệu vào POJO lại được ưa chuộng hơn cách đọc từng dòng rồi cắt chuỗi**

Vì nó giúp lập trình không phải sửa lại code tách chuỗi, xác định cột khi cấu trúc CSV thay đổi. Khi đọc dữ liệu vào POJO, lập trình viên chỉ cần định nghĩa class có thuộc tính ứng với cột trong csv. Mặc định là trùng tên, nhưng bổ xung tham số có thể ánh xạ khác tên. Có thể bỏ qua một số thuộc tính có trong class mà không có trong csv.