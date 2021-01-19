# Kiểm tra kiến thức lập trình kết nối CSDL - JPA

## Quy định và hướng dẫn
1. Bài kiểm tra viết trực tiếp trên giấy. Không được gõ bằng máy tính.
2. Sinh viên tự chuẩn bị giấy và bút để làm
3. Nộp trước 9:30 và chấm chéo khi có đáp án chính thức
4. Ghi rõ họ và tên.
5. Viết chữ càng rõ ràng, càng đẹp càng tốt. Chữ xấu khó đọc, khi bạn chấm có quyền trừ điểm !
6. Ai không đi và làm bài kiểm tra nhận 0 điểm, không có cơ hội làm lại.

## Phần 1: Định nghĩa Entity trong JPA
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
## Phần 2: Quan hệ One to Many và Many to Many
1.

## Phần 3