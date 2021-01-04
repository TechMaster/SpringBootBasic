# Ánh xạ từ Java class vào bảng trong CSDL

Để ánh xạ trước đây người ta sử dụng file XML điều khiển code Java. Cách này không trực quan dễ đọc, khó lập trình. Nay JPA đã cung cấp Annotation để đánh dấu, định hướng chức năng cho
1. Class
2. Property
3. Method

## @Entity, @Table
Áp dụng cho Class.

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

Hãy tham khảo [Audit.java](src/main/java/vn/techmaster/demojpa/model/blog/Audit.java) và [Post.java](src/main/java/vn/techmaster/demojpa/model/blog/Post.java)
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
Áp dụng cho Class, Field hoặc Property




## Đọc thêm
1. [How to generate primary keys with JPA and Hibernate](https://thorben-janssen.com/jpa-generate-primary-keys/)
2. [Access Strategies in JPA and Hibernate – Which is better, field or property access?](https://thorben-janssen.com/access-strategies-in-jpa-and-hibernate/)