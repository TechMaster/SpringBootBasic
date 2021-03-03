# User Detail Service

## Lý thuyết
Trong bài này, chúng ta sẽ bàn đến UserDetails và UserDetailsManager:

1. UserDetails: dùng để mô tả tài khoản đăng nhập gắn với mỗi người dùng
2. GrantedAuthority: mô tả những hành động nào tài khoản được phép thực hiện
3. UserDetailsManager mở rộng từ UserDetailsService bổ xung các phương thức: tạo user, sửa đổi, và xoá user
4. Authority khác với Role như thế nào?

Phần chức năng tôi bê nguyên ví dụ [crud/08Search/bookstore/](https://github.com/TechMaster/SpringBootBasic/tree/main/crud/08Search/bookstore) đã học trước đây.

## Thực hành

### 1. Cấu trúc thư mục dự án
```
.
├── java
│   └── vn
│       ├── techmaster
│       │   └── simpleauthen
│       │       ├── config
│       │       │   └── RepoConfig.java
│       │       ├── controller
│       │       │   ├── BookController.java
│       │       │   └── HomeController.java
│       │       ├── model
│       │       │   ├── Book.java
│       │       │   └── User.java  <-- Đối tượng user login`
│       │       ├── repository
│       │       │   ├── BookDao.java  <-- Book Data Access Object
│       │       │   └── Dao.java <-- Đây là interface
│       │       ├── request
│       │       │   └── SearchRequest.java <-- Search Request POJO
│       │       ├── security
│       │       │   ├── Authority.java  <-- Enum chứa các hành động được phép làm
│       │       │   ├── CustomAuthenProvider.java
│       │       │   ├── Role.java <-- Chứa các role ở bài này không dùng đến
│       │       │   └── SecurityConfig.java  <-- Cấu hình Authentication, Authorization
│       │       └── SimpleauthenApplication.java
├── resources
│   ├── static
│   │   └── book.csv <-- Danh sách Book ban đầu sẽ nạp vào. Học từ hồi chưa dùng JPA
│   ├── templates
│   │   ├── error <-- Các file báo lỗi customize
│   │   │   ├── 401.html
│   │   │   ├── 403.html
│   │   │   ├── 404.html
│   │   │   ├── 500.html
│   │   │   └── error.html
│   │   ├── allbooks.html <-- Liệt kế các sách
│   │   ├── book.html
│   │   ├── form.html
│   │   ├── index.html <-- Trang chủ
│   │   └── search.html <-- Tìm kiếm
│   └── application.properties
```

### 2. Định nghĩa danh sách các hành động được phép làm (Authority) 
Xem [Authority.java](src/main/java/vn/techmaster/simpleauthen/security/Authority.java)

Tôi dùng ```public static final``` để định nghĩa các constant string
```java
public class Authority {
  public static final String READ = "READ";
  public static final String CREATE = "CREATE";
  public static final String DELETE = "DELETE";
  public static final String EDIT = "EDIT";
  public static final String SEARCH = "SEARCH";
  private Authority() {}
}
```


## Đọc thêm
- [Granted Authority Versus Role in Spring Security](https://www.baeldung.com/spring-security-granted-authority-vs-role)