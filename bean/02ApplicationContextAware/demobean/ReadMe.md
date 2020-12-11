# Component trong SpringBoot

## Khái niệm ApplicationContext trong Springboot

**Application Context là gì?**
[Application Context](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationContext.html) là một giao diện tập trung quản lý và cấu hình các @Component và @Bean. Khi ứng dụng đã khởi động xong và
chạy Application Context chuyển sang Read Only - chỉ đọc. Application Context có thể nạp động @Component, @Bean lúc run time, xem bài viết này [Creating Spring Bean dynamically in the Runtime](https://lofidewanto.medium.com/creating-spring-bean-dynamically-in-the-runtime-d9e32c41d286)

Chức năng chính của Appplication Context qua các interface mà nó thực hiện:

1. [ListableBeanFactory](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/ListableBeanFactory.html): liệt kê các @Component, @Bean
2. [Resource Loader](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/ResourceLoader.html): nạp , đọc file từ ổ cứng vào vùng nhớ
3. [ApplicationEventPublisher](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationEventPublisher.html) thông báo sự kiện đến các đối tượng quan tâm (subsribed listeners)
4. [MessageSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/MessageSource.html) quản lý, đa ngôn ngữ các chuỗi

## Component

@Component là một annotation đánh dấu một kiểu Class sẽ được khởi tạo một đối tượng, đối tượng này sẽ được quản lý trong [Application Context](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationContext.html). Sau này còn có một loại đối tượng nữa là @Bean khá giống với @Component nhưng cách khởi tạo thì từ phương thức.

@Component hay @Bean bản chất là các class do thư viện dependency cung cấp hoặc do lập trình viên viết
có chức năng cụ thể, được nạp vào Application Context sau đó thực hiện nhiệm vụ.

Khái niệm này sẽ khác với những gì các bạn học ở Java căn bản, từ hàm static main chúng ta gọi các phương thức khác, hoặc khởi tạo đối tượng từ các public class, sau đó gọi public method.

Trong lúc khởi động @SpringBootApplication sẽ quét (component scan) tất cả các @Component và @Bean, nạp chúng, và sẵn sàng dùng để xử lý request gọi đến.

## Thí nghiệm 1: Khai báo @Component đăng ký vào Application Context

1. Tạo file [Zip.java](src/main/java/vn/techmaster/demobean/component/Zip.java)

```java
@Component
public class Zip {
  private String startDate;
  public Zip() {// Lưu lại thời điểm đối tượng Zip được khởi tạo
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    startDate = formatter.format(date);
  }

  @Override
  public String toString() {
    return "startDate=" + startDate;
  }
}
```

2. Trong [HomeController.java](src/main/java/vn/techmaster/demobean/controller/HomeController.java) chúng ta sẽ tạo và dùng đối tượng Zip bằng nhiều cách khác nhau:
   - Cách 1: dùng `@Autowired` để tự động nạp đối tượng kiểu Zip vào ApplicationContext
   - Cách 2: Khởi tạo đối tượng zip bằng cách thông thường `zip1 = new Zip();`

```java
@Controller
public class HomeController {

  private Zip zip1; //Không sử dụng annotaton @Autowired

  @Autowired //Tự động load đối tượng kiểu Zip vào ApplicationContext
  private Zip zip2;

  @Autowired //biến zip3 thực ra vẫn giống biến zip2 vì nó đều được là 1 đối tượng duy nhất trong ApplicationContext
  private Zip zip3;
```

3. Biên dịch, chạy và mở trình duyệt tại địa chỉ http://localhost:8080/zip
   Ấn F5 để refresh trình duyệt, bạn sẽ thấy thời gian khởi tạo của đối tượng zip1 liên tục thay đổi,
   còn zip2, zip3 không thay đổi thời gian khởi tạo.

   ```
   zip1 liên tục đổi startDate=11-12-2020 11:07:39
   zip2 không đổi    startDate=11-12-2020 11:07:31
   zip3 không đổi    startDate=11-12-2020 11:07:31
   ```

    Ấn F5 phát nữa

   ```
   zip1 liên tục đổi startDate=11-12-2020 11:08:10
   zip2 không đổi    startDate=11-12-2020 11:07:31
   zip3 không đổi    startDate=11-12-2020 11:07:31
   ```

Vậy một Class được đánh dấu bởi @Component, khi ứng dụng Spring Boot khởi động, **một đối tượng duy nhất** kiểu Class đó được khởi tạo. Cho dùng có khai báo bao nhiêu biến sử dụng ```@Autowired``` ở bất kỳ chỗ nào thì các biến này vẫn trỏ đến một đối tượng duy nhất. Có thể nói @Component này là singleton (duy nhất trong vòng đời ứng dụng).

Ngược lại biến zip1 được khởi tạo trong phương thức ```getZip()``` sẽ được tạo lại khi có request mới đến
```java
zip1 = new Zip();
```

Tại sao **Singleton object** đối tượng duy nhất lại có ý nghĩa trong framework Spring Boot?
Trả lời: khi kiến trúc ứng dụng phức tạp, có nhiều đối tượng thành phần hơn, việc quy kết trách nhiệm trở nên cần thiết, ý tưởng một đầu mối, [Single Responsibility](https://nhungdongcodevui.com/2017/03/18/solid-la-gi-nguyen-tac-1-don-nhiem-single-responsibility-principle/) sẽ cần đến Singleton.

 Thử nghĩ xem, nếu mỗi lần nhận request, Spring Boot lại phải khởi tạo một đối tượng quản lý dữ liệu mới, mở kết nối đến CSDL thì thời gian khởi tạo tốn kém, giải phóng tài nguyên cũng tốn kém, chưa kể phải copy nhiều trạng thái chung sang từng đối tượng hoặc các đối tượng này chia sẻ nhau một vùng nhớ chung. Mọi thứ trở nên phức tạp hơn nhiều so với việc chỉ khởi tạo duy nhất một đối tượng Singleton rồi nạp vào ApplicationContext, các đối tượng khác khi cần chỉ cần dùng cú pháp ```@Autowired``` là truy cập được. Cần liệt kê danh sách, hay tìm kiếm @Component hay @Bean thì dùng interface
[ListableBeanFactory](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/ListableBeanFactory.html) là được.

Spring Boot vẫn cho cho developer cơ hội tuỳ chỉnh trong trường hợp đặc biệt vẫn có thể tạo mới @Component hay @Bean tại lúc cần, lúc này phải sử dụng annotation ```@Lazy``` sẽ nói đến ở bài thí nghiệm khác.



