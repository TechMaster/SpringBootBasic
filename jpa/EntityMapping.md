# Ánh xạ từ Java class vào bảng trong CSDL

Để ánh xạ trước đây người ta sử dụng file XML điều khiển code Java. Cách này không trực quan dễ đọc, khó lập trình. Nay JPA đã cung cấp Annotation để đánh dấu, định hướng chức năng cho
1. Class
2. Property
3. Method

## @Entity, @Table
*Áp dụng cho Class.*

Để ánh xạ (mapping) một Java class vào một bảng CSDL chúng ta sẽ dùng annotation ```@Entity```
- ```@Entity```: có thuộc tính name đặt tên cho entity trong câu lệnh truy vấn
- ```@Table```: chỉ rõ ánh xạ xuống bảng nào trong cơ sở dữ liệu. ```@Table``` có 3 thuộc tính
  - name: tên bảng
  - catalog: [Khác biệt giữa catalog và schema](https://stackoverflow.com/questions/7022755/whats-the-difference-between-a-catalog-and-a-schema-in-a-relational-database)
  - schema: tên nhóm các bảng. Một CSDL có thể chia thành nhiều schema để nhóm các bảng cùng chức năng lại ví dụ: Accounting, HR, Engneering, Sales,

Ví dụ
```java
@Entity(name="oto") // Khi truy vấn sẽ là SELECT * FROM oto
@Table(name = "car", schema = "example") //Bảng trong CSDL là car, schema là example
public class Car {
  @Id private long id; 
  private String model;
  private String maker;
  private int year;
}
```

Mỗi thuộc tính (property) sẽ ánh xạ vào một cột trong bảng. Kiểu căn bản sẽ gồm có numeric (Boolean, Byte, Short, Integer, Long, Double, Float), kiểu chuỗi String, kiểu Data, Time, Calendar. Xem chi tiết [JPA - Basic Persistable Types](https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/persistable-basic-types.html)

Các kiểu đặc biệt như enumeration, array, json, key value, multi-dimension array xem tham khảo [Ánh xạ các kiểu dữ liệu đặc biệt](SpecialDataType.md)

## @Id, primary key
```@Id``` dùng để đánh dấu một trường rất đặc biệt: primary key (khoá chính) nó có giá trị duy nhất (unique) trong cả cột dữ liệu. [Primary key](https://www.w3schools.com/sql/sql_primarykey.ASP) có mấy đặc điểm sau:
1. Tính duy nhất (uniqueness)
2. Không được phép rỗng (not null)
3. Cần được đánh chỉ mục (index) để tăng tốc độ tìm kiếm

Primary phải có kiểu là số nguyên hoặc chuỗi ký tự.
### @GeneratedValue
Để đảm bảo tính duy nhất của ```@Id```, primary key, có 2 cách chính:
1. Ứng dụng phải tự sinh giá trị duy nhất rồi gán vào đối tượng --> Entity --> Table
2. Sử dụng ```@GeneratedValue``` để chỉ định CSDL tự sinh giá trị duy nhất.

Có 4 cách:
1. ```GenerationType.AUTO```: để cho CSDL tự chọn cách nào phù hợp tuỳ thuộc vào loại cơ sở dữ liệu và kiểu của primary key.
2. ```GenerationType.IDENTITY```: cột tự động tăng. MySQL, Postgresql, MS-SQL, Oracle 12C hỗ trợ.
3. ```GenerationType.SEQUENCE```: sinh ra các giá trị liên tiếp, có thể cách nhau.
4. ```GenerationType.TABLE```: ít sử dụng vì tốc độ chậm, yêu cầu tạm thời khoá tạo bản ghi mới vào bản trong lúc sinh primary key

Với ```GenerationType.AUTO``` nếu là primary kiểu numeric thì AUTO sẽ chọn IDENTITY hoặc SEQUENCE. Nếu CSDL là Oracle 11C không hỗ trợ IDENTITY thì nó chuyển sang SEQUENCE. Còn nếu là kiểu [UUID](https://en.wikipedia.org/wiki/Universally_unique_identifier) thì nó sẽ trả về chuỗi 32 chữ số Hexadecimal kiểu như thế này '123e4567-e89b-12d3-a456-426614174000'
```java
@Id @GeneratedValue
private UUID courseId;  
```


Tham khảo thêm:
- [How to generate primary keys with JPA and Hibernate](https://thorben-janssen.com/jpa-generate-primary-keys)
- [Difference between sequence and identity in Hibernate](https://www.tutorialspoint.com/difference-between-sequence-and-identity-in-hibernate)
- [GenerationType.IDENTITY vs GenerationType.SEQUENCE vs GenerationType.AUTO](https://ngdeveloper.com/generationtype-identity-vs-generationtype-sequence-vs-generationtype-auto/)

Nếu tạo từng đối tượng rồi lưu xuống CSDL thì dùng ```GenerationType.IDENTITY``` tốc độ sẽ tối ưu nhất. Còn nếu tạo ra nhiều đối tượng rồi đồng loạt lưu xuống CSDL (batch persist) thì dùng ```GenerationType.SEQUENCE```. Tuy nhiên nếu muốn hỗ trợ nhiều loại CSDL khác nhau, hãy dùng ```GenerationType.AUTO```

### @Embeddable - @Embedded kiểu nhúng
```@Embeddable``` cho phép một nhóm các thuộc tính có thể nhúng vào một class khác. Mục đích để tái sử dụng nhóm các thuộc tính lặp đi lặp lại. Còn ```@Embedded``` chỉ ra điểm nhúng

Hãy tham khảo [Audit.java](01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/model/blog/Audit.java)
**Post.java**
```java
@Entity
@Table(name = "post")
public class Post { 
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 
  @Embedded
  private Audit audit = new Audit();
```
**Audit.java**
```java
@Embeddable
@Data
public class Audit {
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @Column(name = "created_by")
  private String createdBy;
    
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

  @Column(name = "updated_by")
  private String updatedBy;
}
```
## @Access và AccessType
*Áp dụng cho Class, Field hoặc Property*

```@Access``` để cấu hình cách JPA truy cập vào dữ liệu bên trong một đối tượng. Có 3 cách truy cập:
1. Đọc / ghi trực tiếp vào Field: ```@Access(AccessType.FIELD)```
2. Đọc qua Getter của property. Ghi qua Setter của Property: ```@Access(AccessType.PROPERTY)```
3. Trộn lẫn giữa 2 cách này.

Xem [Car.java](01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/model/mapping/Car.java)

Áp dụng cấp độ Class
```java
@Entity
@Table(name = "car")
@Access(AccessType.FIELD)
public class Car {
  ...
}
```

Áp dụng cấp độ Field (instant variables)
```java
@Entity
@Table(name = "car")
public class Car {
  @Id private long id;  //@Access(AccessType.FIELD)
  ...
}
```
Áp dụng cấp độ Property (getter / setter)
```java
@Entity
@Table(name = "car")
public class Car {
  @Id    //Đặt @Id là hiểu @Access(AccessType.PROPERTY)
  public long getId() {
    return id;
  }
}
```

Tác giả Thorben Janssen khuyến khích sử dụng ```@Access(AccessType.FIELD)``` vì:
1. Code dễ đọc hơn
2. JPA truy cập trực tiếp không qua getter / setter. Tốc độ sẽ nhanh hơn.
3. Logic đọc - ghi giá trị không bị phụ thuộc getter / setter. Do đó dùng hoặc tuỳ biến getter / setter thoải mái.
4. Không cần áp dụng ```@Transient``` với các utility method. Ý nghĩa ```@Transient``` sẽ giải thích ở phần sau.


Đọc thêm 
- [Access Strategies in JPA and Hibernate – Which is better, field or property access?](https://thorben-janssen.com/access-strategies-in-jpa-and-hibernate/)
- [JPA - Access Type](https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/access-type.html)

## @NaturalId

Ngoài ```@Id``` JPA còn có ```@NaturalId``` như là một khoá uniqe khác hỗ trợ cho việc tìm kiếm.
Ví dụ trong nội bộ ứng dụng, tìm kiếm product bằng id dạng numeric cho tốc độ nhanh nhất. Tuy nhiên khi hiện thị ra giao diện web, mỗi product có một unique slug (một đường dẫn gợi nhớ duy nhất). Yêu cầu làm sao chỉ dùng unique slug mà vẫn tìm ra sản phẩm mà không cần id. Lúc này ta dùng ```@NaturalId``` để làm một unique key bổ trợ cho primary key.

Khác biệt giữa ```@Id``` primary key và ```@NaturalId``` là gì?

- Primary Key và NaturalId luôn là Unique và Not Null
- Primary Key nên giữ nguyên, ổn định, tuyết đối không thay đổi. Nó được dùng để join các bảng khác
- NaturalID có thể thay đổi ví dụ như slug, email, số di động. Nó chỉ dùng để tìm kiếm đối tượng chứ không nên dùng để join các bảng khác.

```java
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @NaturalId //<-- Khoá unique bổ trợ thêm
  @Column(nullable = false, unique = true)
  private String slug;
```

Thử nghiệm tìm kiếm qua ```@NaturalId```, xem file [ProductTests.java](01EntityMapping/demojpa/src/test/java/vn/techmaster/demojpa/ProductTests.java)

```java
@Test
public void insertAndFindProductByNaturalId() {
  Product product = new Product();
  product.setTitle("High-Performance Java persistence");
  String slug = "high-performance-java-persistence";
  product.setSlug(slug);
  em.persist(product);
  
  Session session = em.unwrap(Session.class);
  Product product1 = session.bySimpleNaturalId(Product.class).load(slug);
  assertThat(product1).isEqualTo(product);
}
```

Đọc thêm [The best way to map a @NaturalId business key with JPA and Hibernate](https://vladmihalcea.com/the-best-way-to-map-a-naturalid-business-key-with-jpa-and-hibernate/)

## @Transient

Thuộc tính nhất thời - @Transient chỉ định những thuộc tính sẽ không được lưu trữ xuống CSDL.

Hãy xem file [Employee.java](01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/model/mapping/Employee.java). Thuộc tính ```fullname``` mà một thuộc tính nhất thời, nó không được gán qua setter, nó được trả về qua
getter
```java
public String getFullname() {
  return firstName + " " + lastName;
}
```


```java
@Entity
@Table(name = "employee")
public class Employee {
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Transient private String fullname;
  private String firstName;
  private String lastName;

  public Long getId() {
    return id;
  }

  public String getFullname() {
    return firstName + " " + lastName;
  }
}
```
Hãy chạy thử unit test ở file [EmployeeTests.java](01EntityMapping/demojpa/src/test/java/vn/techmaster/demojpa/EmployeeTests.java).

Tham khảo tiếp file [Audit.java](01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/model/blog/Audit.java). Đối tượng ```private LoggedUser loggedUser``` dùng để lấy id của người dùng đăng nhập. Rõ ràng chúng ta không cần phải lưu đối tượng này xuống database vậy hãy đánh dấu nó là ```@Transient```

```java
@Embeddable
@Data
public class Audit {
    @Transient //Phải dùng @Transient để không bổ xung loggedUser thành một cột
    @Autowired
    private LoggedUser loggedUser;  
```

Xem tiếp file [Person.java](01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/model/mapping/Person.java), thuộc tính ```private int age``` không nên lưu xuống cơ sở dữ liệu mà nó được tính động ở getter ```getAge()``` theo giá trị của ```birthday```

```java
@Transient
private int age;
public int getAge(){
  Date safeDate = new Date(birthday.getTime());
  LocalDate birthDayInLocalDate = safeDate.toInstant()
  .atZone(ZoneId.systemDefault())
  .toLocalDate();
  return Period.between(birthDayInLocalDate, LocalDate.now()).getYears();
}
```

## @Column
```@Column``` là annotation bổ xung tính chất cho cột tương ứng với trường trong class. Nó có tham số như sau:
- ```String name```: đặt lên tên cột khác với tên trường
- ```boolean unique() default false;```: đặt yêu cầu duy nhất nếu là true. Mặc định là false
- ```boolean nullable() default true;```: cho phép cột có giá trị null không. Mặc định là true
- ```boolean updatable() default true;```: cho phép cột có được sửa đổi dữ liệu không. Mặc định là true
- ```String columnDefinition() default "";```: tuỳ biến luôn cả cách tạo ra cột bằng cách viết trực tiếp câu lệnh SQL DDL (Data Defintion Language)
- ```int length() default 255;```: định số ký tự nếu là String


## @Temporal

Dữ liệu nạp vào bảng person kiểu như thế này
```sql
insert into person (id, fullname, job, gender, city, salary, birthday) values (1, 'Riobard Folli', 'Project Manager', 'Male', 'Berlin', 10022, '1970-02-19');
```
Trong lệnh Insert dữ liệu cho cột birthday là String ```'1970-02-19'```. Nếu muốn chuyển sang kiểu Date thực thụ trong CSDL , ta sẽ xử lý như sau
```java
  @Column(name="birthday")
  @Temporal(TemporalType.DATE)
  private Date birthday;
```

TemporalType hỗ trợ 3 kiểu:
1. DATE 
2. TIME: java.sql.Time
3. TIMESTAMP: java.sql.Timestamp


