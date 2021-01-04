# File I/O

Phần này dành cho thầy Nguyễn Trường Thịnh
Nội dung cần giảng dạy trong phần này:

1. Upload file từ HTML Form
   - Hạn chế kích thước file upload
   - Hạn chế file upload
   - Cấu hình thư mục lưu file upload lên
2. Upload file từ JSON
3. Phục vụ file từ một thư mục trên server
4. Quét cấu trúc thư mục và hiện thị ra giao diện web kiểu như dưới đây
   - Ấn vào file ảnh thì hiển thị ra ảnh
   - Ấn vào file text thì hiển thị ra text

```
.
├── 01emptyproject
│   ├── bookstore
├── 02returntexthtml
│   ├── bookstore
├── 03returnjsonxml
│   ├── bookstore
│   │   ├── images
│   │   │   ├── json.jpg
│   │   │   └── xml.jpg
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── vn
│   │   │   │   │       └── techmaster
│   │   │   │   │           └── bookstore
│   │   │   │   │               ├── controller
│   │   │   │   │               │   └── HomeController.java
│   │   │   │   │               ├── model
│   │   │   │   │               │   ├── Car.java
│   │   │   │   │               │   └── Person.java
│   │   │   │   │               └── BookstoreApplication.java
│   │   │   │   └── resources
│   │   │   │       ├── static
│   │   │   │       ├── templates
│   │   │   │       └── application.properties
```

# Quy cách làm giáo trình

1. Luôn bắt đầu bằng một ví dụ đơn giản, ít code nhất code thể. Đánh số bằng thư mục 01xxxx
2. Mã nguồn luôn tuân thủ các quy tắc Clean Code tối thiểu, đặt tên class, method, variable bằng tiếng Anh, dễ hiểu.
3. Bổ xung thêm chức năng, giải thích các bước, đặt link để các file. Đánh số 02xxx, 03xxx
4. Thư mục nào cũng có một file ReadMe.md để giải thích
5. Nếu cần vẽ cấu trúc thư mục: Sử dụng VSCode với extenstion [ascii-tree-generator](https://marketplace.visualstudio.com/items?itemName=aprilandjan.ascii-tree-generator)
6. Luôn có phần Link tham khảo cho sinh viên tìm hiểu thêm. Ví dụ:
    - [Java IO Tutorials](https://www.baeldung.com/java-io)
    - [Spring Boot - File Handling](https://www.tutorialspoint.com/spring_boot/spring_boot_file_handling.htm)
    - [Java – How to list all files in a directory?](https://mkyong.com/java/java-how-to-list-all-files-in-a-directory/)