# Kiểm tra kiến thức lập trình kết nối CSDL - JPA

## Quy định và hướng dẫn
1. Bài kiểm tra viết trực tiếp trên giấy. Không được gõ bằng máy tính.
2. Sinh viên tự chuẩn bị giấy và bút để làm
3. Nộp trước 9:30 và chấm chéo khi có đáp án chính thức
4. Ghi rõ họ và tên.
5. Viết chữ càng rõ ràng, càng đẹp càng tốt. Chữ xấu khó đọc, khi bạn chấm có quyền trừ điểm !
6. Ai không đi và làm bài kiểm tra nhận 0 điểm, không có cơ hội làm lại.
7. Phần lập trình thì phải chạy ra kết quả demo cho giảng viên buổi chấm chéo. Ai làm xong tại lớp sẽ được cộng 2 điểm.

## Phần 1: Định nghĩa Entity trong JPA (3 điểm)
1. Thuộc tính ```name``` trong annotation ```@Entity``` khác với thuộc tính ```name``` trong ```@Table``` như thế nào? Hãy giải thích rõ cần thì minh hoạ
2. Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ xung lệnh nào vào file application.properties?
3. Khi sử dụng H2, cần bổ xung lệnh nào vào application.properties để có thể xem được bảng dữ liệu, viết câu lệnh truy vấn đến H2 database?
4. Khi viết mô tả một model, những thuộc tính chúng ta không muốn lưu xuống CSDL thì cần đánh dấu bằng annotation nào?
5. Annotation ```@Column``` dùng để bổ xung tính chất cho cột ứng với một thuộc tính. Tham số nào trong ```@Column``` sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính, tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu, tham số nào buộc trường không được null?
6. Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ xung
   - Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)
   - Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
  Vậy 2 annotation này là gì? Gợi ý hãy xem file Audit.java trong ví dụ đã học

7. Tổ hợp các trường thông tin địa chỉ: country, city, county, addressline thường luôn đi cùng nhau và sử dụng lại trong các Entity khác nhau. Nhóm 2 annotation nào dùng để tái sử dụng, nhúng một Entity vào một Entity khác?
8. ```JpaRepository``` là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể ```JpaRepository``` kế thừa từ interface nào?
9. Hãy viết khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là long, tuân thủ interface ```JpaRepository```.
10. Khi đã chọn một cột là Identity dùng ```@Id``` để đánh dấu, thì có cần phải dùng xác định unique dùng annotation ```@Column(unique=true)``` không?
11. Khác biệt giữa ```@Id``` với ```@NaturalId``` là gì?
12. Có những cột không phải primary key (```@Id```) hay ```@NaturalId```, dữ liệu có thể trùng lặp (unique không đảm bảo true), nhưng cần đánh chỉ mục để tìm kiếm nhanh hơn vậy phải dùng annotation gì? *Phần này chưa được dạy, không có ví dụ, nhưng Google sẽ ra ngay.*
13. Annotation ```@Data``` thuộc depencies nào? Ý nghĩa nó dùng để làm gì?
14. Bên cạnh việc bổ xung dependency Lombok vào file ```pom.xml```, chúng ta phải cài bổ xung thêm phần mềm gì cho IDE?
15. ```@NoArgsConstructor``` trong Lombok sẽ sinh ra constructor có đặc điểm gì?
16. ```@AllArgsConstructor``` trong Lombok sẽ sinh ra constructor có đặc điểm gì?
17. Nếu đã đánh dấu class với ```@Data``` của Lombok, chúng ta có được phép tự viết thêm constructor với những tham số tuỳ chọn không?
18. Annotation ```@GeneratedValue``` dùng để chọn cách tự sinh unique id cho primary key phải là trường kiểu int hoặc long. Nếu trường primary key có kiểu là String, chúng ta không thể dùng ```@GeneratedValue``` vậy hãy thử liệt kê các cách đảm bảo sinh ra chuỗi có tính duy nhất? *Google đi*

## Phần 2: Quan hệ One to Many và Many to Many (2 điểm)
Có 3 Entity [Product.java](exam/src/main/java/vn/techmaster/exam/model/Product.java) và [Category.java](exam/src/main/java/vn/techmaster/exam/model/Category.java)
và [Tag.java](exam/src/main/java/vn/techmaster/exam/model/Tag.java)

1. Hãy bổ xung định nghĩa quan hệ One to Many giữa bảng Category (One) -- Product (Many). Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null.
2. Hãy bổ xung định nghĩa quan hệ Many to Many giữa bảng Tag(Many) -- Product(Many).

Có 2 Entity [Student.java](exam/src/main/java/vn/techmaster/exam/model/Student.java) và [Course.java](exam/src/main/java/vn/techmaster/exam/model/Course.java)

3. Hãy mô tả quan hệ Many to Many. Một Student có thể học nhiều Course. Một Course có nhiều Student tham gia. Bảng trung gian giữa Student và Course cần có một trường là điểm score kiểu int giá trị gán vào từ 0 đến 10. Cần validate ở phương thức setter.
Hãy xem kỹ bài viết này [Many-To-Many Relationship in JPA](https://www.baeldung.com/jpa-many-to-many) để bổ xung bảng course_registration.

**Dữ liệu mẫu để kiểm thử:**
student.sql
```sql
INSERT INTO student (id, name) VALUES (1, 'bob');
INSERT INTO student (id, name) VALUES (2, 'alice');
INSERT INTO student (id, name) VALUES (3, 'tom');
INSERT INTO student (id, name) VALUES (4, 'jane');
INSERT INTO student (id, name) VALUES (5, 'van');
INSERT INTO student (id, name) VALUES (6, 'long');
```
course.sql
```sql
INSERT INTO course (id, name) VALUES (1, 'math');
INSERT INTO course (id, name) VALUES (2, 'music');
INSERT INTO course (id, name) VALUES (3, 'history');
```
**Nội dung môn học và điểm**
```
bob học {math: 7, music: 5, history: 8}
alice học {math: 8, music: 2, history: 9}
tom học {math: 4, history: 10}
jane học {music: 9, history: 8}
van học {math: 9, music: 7, history: 6}
long học {math: 10, music: 3}
```

## Phần 3: Truy vấn (2 điểm)
Hãy lập trình JPARepository và viết JUnit5 để tính
1. Trả về liệt kê sinh viên tham gia từng môn học ```Map<String, List<Student>>```: key là tên môn học, value là danh sách sinh viên đăng ký
2. Viết Native Query để tính điểm trung bình một môn bất kỳ
3. Liệt kê danh sách sinh viên học math nhưng không học music

Nếu nộp tất cả ngay tại lớp được thưởng 2 điểm. MAX là 12 điểm