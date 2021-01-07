# World Population

Ứng dụng này demo đủ 3 layer:
1. Controller
2. Service
3. Repository
   
Ứng dụng cần đến các dependencies:
1. JPA
2. Lombook
3. H2
4. Freemaker (không phải Thymeleaf)

Biên dịch rồi vào địa chỉ http://localhost:8080/
   
## Cấu trúc thư mục
```
.
├── java
│   ├── vn
│   │   ├── techmaster
│   │   │   ├── worldpopulation
│   │   │   │   ├── controller
│   │   │   │   │   └── HomeController.java
│   │   │   │   ├── model
│   │   │   │   │   └── Country.java
│   │   │   │   ├── repository
│   │   │   │   │   └── CountryRepository.java
│   │   │   │   ├── service
│   │   │   │   │   ├── CountryService.java
│   │   │   │   │   └── ICountryService.java <-- Interface của Service
│   │   │   │   └── WorldPopulationApplication.java
├── resources
│   ├── static
│   ├── templates  <-- Sử dụng Freemaker Template
│   │   ├── country.html
│   │   └── home.html
│   ├── application.yml  <-- Cấu hình cho ứng dụng
│   └── data.sql   <-- Dữ liệu ban đầu nạp vào
```

## Service - Repository - Model
Model mô tả một bản ghi. Bản ghi này tương đương một class/struct. Để viết code ít hơn, chúng ta nên dùng Lombok.
```java
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "countries") // Ánh xạ vào countries
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int population;
}
```

Tại sao cần có Service. Service khác gì Repository?
- Repository tập trung tạo thêm sửa xoá, truy vấn lên một bảng.
- Còn Service có thể gọi đến nhiều Repository hoặc chứa các nghiệp vụ, logic trước khi lưu xuống cơ sở dữ liệu, hoặc biến đối dữ liệu trả về từ CSDL rồi trả về cho controller

```java
@Service
public class CountryService implements ICountryService {
  @Autowired
  private CountryRepository repository;  //sử dụng CountryRepository
}
```


## application.yml cấu hình SpringBoot app
Xem thêm ở đây [Using application.yml vs application.properties in Spring Boot](https://www.baeldung.com/spring-boot-yaml-vs-properties)

```yaml
server:
  port: 8080 # phục vụ ở cổng nào
  servlet:
    context-path: /  # đường dẫn mặc định
  
spring:
  main:
    banner-mode: "off"
  jpa:
    database: h2     # 
    hibernate:
      dialect: org.hibernate.dialect.H2Dialect
      ddl-auto: create-drop
  freemarker:   # Sử dụng freemarker
    template-loader-path: classpath:/templates
    suffix: .html
  
logging:
  level:
    org:
      springframework: ERROR
```

## Sử dụng các dependencies
Xem [pom.xml](pom.xml)

### H2 in memory database
Database sẽ dùng H2, cơ sở dữ liệu quan hệ được viết bằng Java, nhúng trực tiếp vào ứng dụng.
```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```
Để nạp dữ liệu vào H2 database hãy tạo file sql có tên là data.sql hoặc import.sql

### Freemaker
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

### JPA
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Lombok
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
```

## Tham khảo
- [Spring Boot @Repository](http://zetcode.com/springboot/repository/)
- [@Component vs @Service vs @Repository](https://viblo.asia/p/spring-boot-4-component-vs-service-vs-repository-maGK7k2AKj2)