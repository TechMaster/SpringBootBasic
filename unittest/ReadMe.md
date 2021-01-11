# Kiểm thử JUnit 5

## Ích lợi của viết Unit Test

## JUnit5

### @TestInstance(Lifecycle.PER_CLASS) vs @TestInstance(Lifecycle.PER_METHOD)

Trước tiên hãy tìm hiểu sự khác biệt giữa ```@BeforeEach``` và ```@BeforeAll```
- ```@BeforeEach```: đánh dấu phương thức sẽ luôn chạy trước mỗi lần chạy kiểm thử
- ```@BeforeAll```: đánh dấu phương thức sẽ chạy một lần đầu duy nhất trước khi chạy các kiểm thử tiếp theo.

Nếu chúng ta khởi tạo tài nguyên tốn kém dạng Singleton, hoặc khởi tạo một lần dùng cho nhiều lần thì rõ ràng ```@BeforeAll``` sẽ hợp lý hơn. Ngược lại ```@BeforeEach``` và ```@AfterEach``` lại đảm bảo khởi tạo và dọn dẹp cho mỗi phương thức kiểm thử được chạy.

Khi chạy kiểm thử [AuthenServiceMockTest.testHashedPassword2](../jpa/03HomeWork/authen/src/test/java/vn/techmaster/authen/AuthenServiceMockTest.java) bạn có thể gặp lỗi như sau:
```
void vn.techmaster.authen.AuthenServiceMockTest.initService()' must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS)
```
Có 2 cách xử lý lỗi này

- Cách 1: Annotate testing class với ```@TestInstance(Lifecycle.PER_CLASS)``` để khi chạy một testing method, testing class được khởi tạo và testing method có thể truy xuất đến thuộc tính trong testing class
```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=AppConfig.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AuthenServiceMockTest {
  @Autowired PasswordEncoder encoder;
  private AuthenService authenService;
  @BeforeAll
  void initService() {
    authenService = new AuthenService(encoder);
  }
}
```
- Cách 2: Biến phương thức khởi tạo với ```@BeforeAll``` là static method, các thuộc tính cũng phải biến thành static. **Cách này dở và gây nhiều lỗi khi biên dịch**
```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=AppConfig.class)
public class AuthenServiceMockTest {
  @Autowired static PasswordEncoder encoder;
 
  private static AuthenService authenService;
  @BeforeAll
  static void initService() {
    authenService = new AuthenService(encoder);
  }
```

## AssertJ

## Tối ưu tốc độ kiểm thử

#### Chiêu số 1: Không load quá nhiều file SQL khi ứng dụng khởi động
Xem ví dụ [jpa/01EntityMapping/demojpa](../jpa/01EntityMapping/demojpa)
Để nạp file *.sql vào JPA chúng ta có 2 cách:
1. Nạp ngay từ đầu sử dụng [application.properties](../jpa/01EntityMapping/demojpa/src/main/resources/application.properties)
```
spring.jpa.properties.hibernate.hbm2ddl.import_files=post.sql
```
Cách này dữ liệu luôn được nạp khi JPA khởi tạo. Nếu dữ liệu nhiều mà đôi lúc chúng ta chỉ cần kiểm thử đối với một số bảng cụ thể thì cách này rất chậm chạp.

2. Sử dụng annotation ```@Sql``` đặt vào testing class hoặc testing method

Cách này chỉ nạp đúng phần dữ liệu cần thiết cho testing class hay testing method. Thời gian khởi động test nhanh hơn rất nhiều.
```java
@Test
@Sql({"/post.sql"})
public void LazyOrEager(){
  Comment findComment1 = tem.find(Comment.class, 1L);
  System.out.println("-----");
  Post post = findComment1.getPost();
  System.out.println(post.getTitle());
  assertThat(post.getTitle()).isNotNull();
}
```



#### Chiêu số 2: không sử dụng @SpringBootTest khi không thực sự cần thiết
Xem ví dụ [jpa/03HomeWork/authen](../jpa/03HomeWork/authen) để kiểm thử phương thức ```hasPassword``` trong dịch vụ [AuthenService.java](../jpa/03HomeWork/authen/src/main/java/vn/techmaster/authen/service/AuthenService.java)
```java
@Override
public String hashPassword(String password) {    
  return encoder.encode(password);
}
```
Dịch vụ [AuthenService.java](../jpa/03HomeWork/authen/src/main/java/vn/techmaster/authen/service/AuthenService.java) sử dụng bean encoder được cấu hình trong [AppConfig.java](../jpa/03HomeWork/authen/src/main/java/vn/techmaster/authen/config/AppConfig.java)

```java
@Service
public class AuthenService implements IAuthenService { 
  private final PasswordEncoder encoder;
  public AuthenService(PasswordEncoder encoder){
    this.encoder = encoder;
  }
}
```

```java
@Bean
public PasswordEncoder encoder() {
  return new BCryptPasswordEncoder();
}
```
Có 2 cách để kiểm thử ```AuthenService.hashPassword()```:

**1. Sử dụng ```@SpringBootTest```**
Ưu điểm cách này đó là ApplicationContext khởi động đầy đủ, đăng ký các ```@Component``` và ```@Bean```. Tuy nhiên điểm dở là thời gian nạp quá nhiều Component và Bean sẽ khiến thời gian kiểm thử tăng lên.
Xem file [AuthenServiceTest.java](../jpa/03HomeWork/authen/src/test/java/vn/techmaster/authen/AuthenServiceTest.java)

**2. Chỉ nạp đối tượng cần thiết để kiểm thử**
Không sử dụng ```@SpringBootTest```, chúng sẽ phải sử dụng ```@Mock```, ```@MockBean```, ```@MockInject``` tạo các đối tượng giả hoặc tự tạo ra đối tượng để kiểm thử

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=AppConfig.class)
public class AuthenServiceMockTest {

  @Autowired PasswordEncoder encoder;
 
  private AuthenService authenService;
  //https://reflectoring.io/unit-testing-spring-boot/
  @BeforeEach
  void initService() {
    authenService = new AuthenService(encoder);
  }

  @Test
  public void testHashedPassword2(){
    String hashedPass = authenService.hashPassword("abcde");
    System.out.println(hashedPass);
    assertThat(hashedPass.length()).isGreaterThan(10);
  }
  
}
```

**Hãy chú ý đến mấy điểm:**
- AuthenService phụ thuộc vào Bean ```PasswordEncoder encoder``` mà bean này được cấu hình trong file [AppConfig.java](../jpa/03HomeWork/authen/src/main/java/vn/techmaster/authen/config/AppConfig.java). Vì chúng ta không dùng ```@SpringBootTest``` để tránh việc nạp các bean không cần thiết, nên chúng ta phải nạp thủ công các bean cần thiết cho kiểm thử. Do đó phải bổ xung annotation ```@ContextConfiguration(classes=AppConfig.class)```

- Khi đã có ```@ContextConfiguration(classes=AppConfig.class)``` chúng ta có thể khai báo ```@Autowired PasswordEncoder encoder;```

- Bắt đầu mỗi lần test, cần khởi tạo
