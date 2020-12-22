## Giới thiệu
Dự án eBook Store giới thiệu các đầu sách ebook do các tác giả viết. Cộng đồng có thể viết nhận xét và hỗ trợ tài chính để sách được xuất bản ra sách giấy.

Trong khuôn khổ ví dụ cho sinh viên lớp SpringBoot eBook Store có một số chức năng sau đây

1. Danh sách các đầu sách gồm: title - tiêu đề, description - mô tả, cover - ảnh minh hoạ bìa, authors - danh sách tác giả.
2. Danh sách các tác giả: full name - tên đầy đủ, email, mobile, giới thiệu bản thân.
Một tác giả có thể viết nhiều đầu sách. Một đầu sách cũng được viết bởi nhiều tác giả.
3. Phân loại sách: mỗi quyển sách có tối đa 3 phân loại. Lịch sử, khoá học, viễn tưởng, ngôn tình, thơ, truyện tranh, sách ảnh, audio book, trinh thám, self help....

### Danh sách bài thực hành
1. [01GetAllBooks](01GetAllBooks/bookstore): DAO, lấy danh sách book được tạo trong constructor
2. [02LoadCSV](02LoadCSV/bookstore): Đọc dữ liệu từ CSV vào
3. [03ViewDetail](03ViewDetail/bookstore): Xem chi tiết một đầu sách
4. [04AddNew](04AddNew/bookstore): Thêm mới một đầu sách
5. [05EditUpdate](05EditUpdate/bookstore): Sửa đổi thông tin đầu sách
6. [06Delete](06Delete/bookstore): Xoá một đầu sách bằng phương thức GET
7. [07DeleteByPOST](07DeleteByPOST/bookstore): Xoá một đầu sách bằng phương thức POST
8. [08Search](08Search/bookstore): Tìm kiếm theo keyword

## Lý thuyết

### Cơ sở dữ liệu quan hệ
Trong bài số 1 này, chúng ta làm quen với cơ sở dữ liệu quan hệ [H2](https://www.h2database.com/html/main.html). H2 là một cơ sở dữ liệu viết bằng Java được nhúng vào trong Spring Boot Application. Nó lưu dữ liệu trong bộ nhớ động (in memory), hỗ trợ đầy đủ các tính năng căn bản của cơ sở dữ liệu quan hệ. H2 phù hợp để lập trình thử nghiệp, quy mô rất nhỏ chứ không phù hợp dự án triển khai thật (in production). Để có kiến thức cho bài này, các bạn cần tìm hiểu thêm về CSDL quan hệ (Relational Database Management System) và truy vấn SQL (Structure Query Language).

- [SQL Tutorial](https://www.w3schools.com/sql/)
- [SQL cho người mới bắt đầu](https://techmaster.vn/khoa-hoc/l9y/sql-cho-nguoi-moi-bat-dau)

### Ánh xạ Object Relational Mapping (ORM)
Trong Java chúng ta có các class (kiểu). Từ class sẽ tạo ra các đối tượng. Các đối tượng này nếu bỏ qua, không quan tâm đến các phương thức thì chúng ta gọi là POJO (Plain Old Object Model). Chúng ta thấy 1 đối tượng POJO cũng tương đồng với một bản ghi (record).

Làm sao để ánh xạ một đối tượng POJO với một bản ghi record?
[Cơ chế ORM](https://viblo.asia/p/object-relational-mapping-djeZ1PQ3KWz) ra đời.

### DAO - Respository
- DAO (Data Access Object): đọc bài này nhé [Hướng dẫn Java Design Pattern – DAO](https://gpcoder.com/4935-huong-dan-java-design-pattern-dao/)

- Respository: [DAO vs Repository Patterns](https://www.baeldung.com/java-dao-vs-repository)

Chú ý đọc kỹ, phần kiểm tra sẽ có câu hỏi so sánh DAO với Respository đó!
