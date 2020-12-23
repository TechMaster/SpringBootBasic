
# Lý thuyết

### Cơ sở dữ liệu quan hệ
Trong bài số 1 này, chúng ta làm quen với cơ sở dữ liệu quan hệ [H2](https://www.h2database.com/html/main.html). H2 là một cơ sở dữ liệu viết bằng Java được nhúng vào trong Spring Boot Application. Nó lưu dữ liệu trong bộ nhớ động (in memory), hỗ trợ đầy đủ các tính năng căn bản của cơ sở dữ liệu quan hệ. H2 phù hợp để lập trình thử nghiệp, quy mô rất nhỏ chứ không phù hợp dự án triển khai thật (in production). Để có kiến thức cho bài này, các bạn cần tìm hiểu thêm về CSDL quan hệ (Relational Database Management System) và truy vấn SQL (Structure Query Language).

- [SQL Tutorial](https://www.w3schools.com/sql/)
- [SQL cho người mới bắt đầu](https://techmaster.vn/khoa-hoc/l9y/sql-cho-nguoi-moi-bat-dau)

### Ánh xạ Object Relational Mapping (ORM)
Trong Java chúng ta có các class (kiểu). Từ class sẽ tạo ra các đối tượng. Các đối tượng này nếu bỏ qua, không quan tâm đến các phương thức thì chúng ta gọi là POJO (Plain Old Object Model). Chúng ta thấy 1 đối tượng POJO cũng tương đồng với một bản ghi (record).

Làm sao để ánh xạ một đối tượng POJO với một bản ghi record?
[Cơ chế ORM](https://viblo.asia/p/object-relational-mapping-djeZ1PQ3KWz) ra đời.