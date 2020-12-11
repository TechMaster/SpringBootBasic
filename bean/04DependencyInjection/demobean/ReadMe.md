# Dependency Injection

Dependency: Thành phần phụ thuộc
Injection: Tiêm
Dependency Injection = Tiêm thành phần phụ thuộc vào đối tượng ~ Cấu hình thành phần

Đọc nghe khoai ! Rồi trong bài hôm nay chúng ta sẽ bàn đến
- Khái niệm cấu hình thành phần
- Cấu hình Bean
- phương thức ```Application.getBean(String name)```
- ```@Qualifier```
- ```@Value```
- ```@Configuration```

Chuyện là thế này, Vinfast là một công ty sản xuất ô tô. Ban đầu họ chỉ sản xuất ô tô chạy xăng như Fadil, Lux Turbo. Tuy nhiên do cạnh tranh, Vinfast bổ xung thêm các model car mới chạy bằng điện và có lựa chọn điều khiển xe tự động (auto steering) hay thủ công (manual steering). Hãy viết một ứng dụng mô phỏng một kiểu Car gồm 2 thành phần Engine và Steering.

Engine có 3 loại:
1. gas
2. electric
3. hybrid

Steering có 2 loại:
1. auto steering
2. manual steering

Với 3 kiểu động cơ và 2 kiểu lái, chúng ta có 6 biến thể xe tất cả. Làm sao trong ứng dụng có thể cấu hình động