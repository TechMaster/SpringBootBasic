# Dự án khởi động chưa có code

Hãy để ý đến file pom.xml

bạn đang dùng JDK phiên bản nào thì cần chỉnh sửa thông số ```java.version``` cho phù hợp

```xml
<properties>
  <java.version>15</java.version>
</properties>
```

Dự án này dùng 4 dependecies:

1. spring-boot-starter-web
2. spring-boot-devtools: hỗ trợ Live Reload
3. Lombok dùng để định nghĩa class ngắn gọn hơn cách truyền thống
4. spring-boot-starter-test: mặc định lúc nào cũng có để kiểm thử tự động

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

## Cấu trúc dự án chính

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── vn
│   │   │       └── techmaster
│   │   │           └── bookstore
│   │   │               └── BookstoreApplication.java
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   └── test
│       └── java
│           └── vn
│               └── techmaster
│                   └── bookstore
│                       └── BookstoreApplicationTests.java
```
