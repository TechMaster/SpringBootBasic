# Viết kiểm thử các model

Ở bước này chúng ta viết kiểm thử cho các model của chúng ta. Việc viết kiểm thử chưa bao giờ là thừa cả. Lập trình viên tồi dành 80% thời gian làm việc để fix bug thậm chí còn hơn. Giá trị thực sự của anh ta đem lại cho dự án rất thấp.

Trước khi viết kiểm thử hãy hiểu rõ quan hệ giữa các bảng và thứ tự dữ liệu được tạo ra:

1. Tài khoản User được tạo ra
2. Người dùng đăng nhập rồi viết Post: Một User --< Nhiều Post
3. Người dùng khác đăng nhập rồi comment vào một Post: Một Post --< Nhiều Comment

## Cascading khi persist
Trong quan hệ một nhiều, Entity phía một thường sẽ có trước, sau đó Entity phía nhiều tham chiếu đến, hoặc được tạo ra vào gắn vào Entity phía một.

Ta có thể tạo ra User, chưa cần lưu xuống CSDL. Tiếp tục tạo ra Post chưa cần lưu xuống CSDL, rồi tiếp tục tạo ra comment lên post.

Lúc này dùng ```UserRepository``` để save đối tượng User, rồi flush. Nhờ định nghĩa ```cascade = CascadeType.ALL``` trong [User.java](src/main/java/vn/techmaster/blog/model/User.java) mà các đối tượng Post và Comment liên quan cũng được lưu cùng với đối tượng User
```java
@OneToMany(
  cascade = CascadeType.ALL,
  orphanRemoval = true,
  fetch = FetchType.LAZY
)
```

Trong file [PostRepositoryTest.java](src/test/java/vn/techmaster/blog/PostRepositoryTest.java), bản ghi user bob@gmail.com đã có sẵn trong CSDL. Post được tạo mới, rồi sau đó được gắn vào đối tượng ```user``` bằng lệnh ```user.addPost(post);```


```java
@Test
@DisplayName("Tạo một post mới")
void addNewPost() {
  Optional<User> optionalUser = userRepo.findByEmail("bob@gmail.com");
  assertThat(optionalUser).isPresent();
  User user = optionalUser.get();
  Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
  user.addPost(post);

  assertThat(post.getId()).isNull(); //Chưa được persist vào Persistence Context
  userRepo.flush();  //Cách này đúng: đẩy thay đổi xuống database
  //userRepo.save(user); //Cách này không đúng user đã tồn tại rồi
  //postRepo.save(post);
  assertThat(post.getId()).isEqualTo(1L); //Đã được lưu xuống Persistence Context
  
  assertThat(post.getUser()).isEqualTo(user);

  User bob = userRepo.findById(1L).get(); //Lấy user Bob qua id
  assertThat(postRepo.existsById(1L)).isTrue(); //Kiểm tra post với id = 1L đã có trong CSDL chưa?
  assertThat(bob.getPosts().get(0)).isEqualTo(post); //Kiểm tra xem thực sự user Bob ở CSDL đã thực sự có post chưa
}
```
Bạn sẽ phân vân giữa 3 câu lệnh sau đây

1. ```userRepo.flush();```: Lệnh này ok vì nó lưu thay đổi của user đồng thời post gắn với user xuống CSDL
2. ```userRepo.save(user);```: Lệnh này sai, vì nó có tác dụng lưu đối tượng mới tạo ra
3. ```postRepo.save(post);```: Lệnh này ok vì lưu đối tượng post mới tạo ra xuống CSDL

Lệnh ```userRepo.flush();```


## ```save``` khác gì với ```flush```?

Trong file [PostRepositoryTest.java](src/test/java/vn/techmaster/blog/PostRepositoryTest.java), hãy xem phương thức ```void persistNewPost()```

```java
@Test
@DisplayName("Tạo một post mới dùng PostRepository để lưu")
void persistNewPost() {
  User bob = userRepo.findByEmail("bob@gmail.com").get();
  
  Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
  bob.addPost(post);

  assertThat(post.getId()).isNull(); //Chưa được persist vào Persistence Context
  postRepo.save(post);  //Dùng postRepo để lưu đối tượng post mới
  postRepo.flush();
  assertThat(post.getId()).isEqualTo(1L); //Đã được lưu xuống Persistence Context
  
  assertThat(post.getUser()).isEqualTo(bob);
  assertThat(bob.getPosts().get(0)).isEqualTo(post);
}
```
Lệnh ```postRepo.save(post);``` sinh ra lệnh INSERT bản ghi
```sql
insert 
into
  post
  (id, User_id, content, last_update, title) 
values
  (null, ?, ?, ?, ?)
```
Lệnh ```postRepo.flush();``` sinh ra câu lệnh UPDATE cập nhật Foreign key
```sql
update
  post 
set
  user_id=? 
where
  id=?
```
## Phân biệt 

## Debug câu lệnh SQL JPA-Hibernate sẽ sinh ra
```
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.properties.hibernate.hbm2ddl.import_files=user.sql
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```
https://levelup.gitconnected.com/sql-statement-logging-in-spring-boot-ffac0f9294a5

https://github.com/gavlyukovskiy/spring-boot-data-source-decorator