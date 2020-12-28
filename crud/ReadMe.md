# Giới thiệu
Dự án eBook Store giới thiệu các đầu sách ebook do các tác giả viết. Cộng đồng có thể viết nhận xét và hỗ trợ tài chính để sách được xuất bản ra sách giấy.

Trong khuôn khổ ví dụ cho sinh viên lớp SpringBoot eBook Store có một số chức năng sau đây

1. Danh sách các đầu sách gồm: title - tiêu đề, description - mô tả, cover - ảnh minh hoạ bìa, authors - danh sách tác giả.
2. Danh sách các tác giả: full name - tên đầy đủ, email, mobile, giới thiệu bản thân.
Một tác giả có thể viết nhiều đầu sách. Một đầu sách cũng được viết bởi nhiều tác giả.
3. Phân loại sách: mỗi quyển sách có tối đa 3 phân loại. Lịch sử, khoá học, viễn tưởng, ngôn tình, thơ, truyện tranh, sách ảnh, audio book, trinh thám, self help....

## Danh sách bài thực hành
1. [01GetAllBooks](01GetAllBooks/bookstore): DAO, lấy danh sách book được tạo trong constructor
2. [02LoadCSV](02LoadCSV/bookstore): Đọc dữ liệu từ CSV vào
3. [03ViewDetail](03ViewDetail/bookstore): Xem chi tiết một đầu sách
4. [04AddNew](04AddNew/bookstore): Thêm mới một đầu sách
5. [05EditUpdate](05EditUpdate/bookstore): Sửa đổi thông tin đầu sách
6. [06Delete](06Delete/bookstore): Xoá một đầu sách bằng phương thức GET
7. [07DeleteByPOST](07DeleteByPOST/bookstore): Xoá một đầu sách bằng phương thức POST
8. [08Search](08Search/bookstore): Tìm kiếm theo keyword

# Lý thuyết

## 1. CRUD
CRUD == Create . Read . Update . Delete đây là 4 thao tác căn bản lên một đối tượng bản ghi (record).
Trong CSDL chúng ta có khái niệm bản ghi (record) sau khi truy vấn từ 1 dòng trong 1 bảng hay liên kết từ nhiều bảng (joined query). Còn trong Java, chúng ta một đối tượng được lưu trong Collection.

Bên cạnh CRUD còn một số thao tác khác như:
- Liệt Kê (List)
- Sắp xếp (Sort)
- Phân trang (Paginate)
- Tìm theo từ khoá (Search by keyword)
- Lọc theo tiêu chí (Filter by criteria)
- Xoá nhiều bản ghi (Batch delete)

Đọc thêm:
- [CRUD là gì?](https://viblo.asia/p/crud-la-gi-lap-trinh-website-crud-crud-database-Ljy5VyLzlra)

## 2. Request vs Response

Request là yêu cầu gửi đến cho ứng dụng web hoặc REST API.
Response là phản hồi trả về từ ứng dụng web hoặc REST API.

**Request có thể tạo ra bởi:**
- HTML Web: GET và POST
- JavaScript: JavaScript thuần (vanilla) hay dùng các thư viện JQuery, React, Angular, Vue...
- Ứng dụng di động (IOS, Android, Flutter, React Native) gửi yêu cầu lên server
- Ứng dụng crawl dữ liệu
- Ứng dụng server cũng có thể trở thành client để yêu cầu lấy dữ liệu từ ứng dụng server khác

Đọc thêm:
- [HTTP - Requests](https://www.tutorialspoint.com/http/http_requests.htm)
- [Định nghĩa GET tại Mozila](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/GET)

**Response**

## 3. Phương thức GET và POST
Ở bài REST, chúng ta sẽ thực hành với các HTTP Verb phổ biến (GET, POST, PUT, DELETE). Đối với Web, HTML, chỉ có 2 loại HTTP Verb là GET và POST. Trong Web, muốn tạo PUT, DELETE thì phải tạo lời gọi bằng JavaScript.

Đọc thêm:
- [GET vs POST](https://www.w3schools.com/tags/ref_httpmethods.asp)
- [GET vs POST: Key Difference between HTTP Methods](https://www.guru99.com/difference-get-post-http.html)


### 4. HTML Form

Đọc thêm
- [HTML Forms](https://www.w3schools.com/html/html_forms.asp)
- [Định nghĩa Form trong Mozila](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form)
### 5. DAO - Respository
- DAO (Data Access Object): đọc bài này nhé [Hướng dẫn Java Design Pattern – DAO](https://gpcoder.com/4935-huong-dan-java-design-pattern-dao/)

- Respository: [DAO vs Repository Patterns](https://www.baeldung.com/java-dao-vs-repository)

Chú ý đọc kỹ, phần kiểm tra sẽ có câu hỏi so sánh DAO với Respository đó!

# Bài tập về nhà

Thực hành lại đủ 
