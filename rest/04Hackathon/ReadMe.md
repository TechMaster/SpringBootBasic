# Xây dựng REST API cho ứng dụng MicroBlog


Các bạn đã quá quen với ứng dụng microblog trong khoá học này. Ở những bài trước chúng ta sử dụng @Controller hứng request và trả dữ liệu về cho View Template Engine là Thymeleaf xuất ra HTML. Đó là kỹ thuật server side render.

Trong lần hackathon này chúng ta sẽ xuất dữ liệu dạng REST API để cho ứng dụng di động hoặc client side sử dụng.


## Mô tả API

### 1. Trả về danh sách Post được phân trang

```/api/posts?size=10&page=5```s
Trả về danh sách các Post sắp xếp theo ngày tạo mới nhất, trang thứ 5, page có kích thước 10 post

### 2. Trả về một post theo id
```/api/post/10```

```json
  {
  "id": 1,
  "title": "Khoá học Spring Boot ở Techmaster công nhận là khoai",
  "content": "Tôi học ở đây 9 tháng, giờ bắt đầu đi phỏng vấn....",
  "lastupdate": "2021-03-25:10:10:13",
  "user_id": 12,
  "user_name": "Đoàn Văn Tính",
  "tags": [
    {
      "id": 1, 
      "name": "CNTT"
    },
    {
      "id": 3, 
      "name": "Giáo dục"
    }],
  "comments": 3 //số lượng comments
}
```

### 3. Tạo một post mới
```/api/post/```

Chú ý cần có các tags

### 4. Cập nhật nội dung post theo id
```/api/post/12```

Cần cập nhật được trường title, content, và chọn lại tag

### 5. Liệt kê danh sách các comments thuộc một post theo id
```/api/comments/12```


