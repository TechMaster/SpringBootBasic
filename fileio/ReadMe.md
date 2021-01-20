# File I/O

1. Upload file từ HTML Form (demoupload example)
   - Hạn chế kích thước file upload
   - Hạn chế file upload
   - Cấu hình thư mục lưu file upload lên
2. Upload file với RestController
3. Demo tổng hợp - App quản lý file trên server
a. Chức năng của app
   - Show thư mục root (quản lý file upload, chia ra 2 thư mục con cho user thường và admin)
   - Liệt kê file và folder thuộc trực tiếp folder đang làm việc
   - Upload fle vào folder đang làm việc
   - Xóa file/folder
   - Tạo folder con
   - Download file
b. Các bước thực hiện
   - Cấu hình thư mục gốc lưu file upload trên server + hạn chế kích thước file
   - Phân quyền với spring security và sử dụng thư mục riêng chỉ cho admin truy cập
   - Viết HomeController để show home page lên UI (thymeleaf) 
   - Viết FileRestController để cung cấp các api làm việc với file, viết các file service để xử lý business
   - Thiết kế giao diện với thymeleaf, css, js
   - Viết các function trong js để call rest api, sau đó update lại home page
