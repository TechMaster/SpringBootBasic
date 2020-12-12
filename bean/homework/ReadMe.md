# Bài tập cho phần Bean

## Gợi ý ôn tập
Trong phần này các bạn cần hiểu rõ cơ chế ComponentScan, Dependency Injection, @Component, @Bean, @Autowired, @Qualifier, @Value, @Primary, đối tượng ApplicationContext

## Bài tập
Hãy xây dựng một ứng dụng Spring Boot mô phỏng dòng sản phẩm Tivi gồm 2 bộ phận chính:

Tấm hình nền (panel):
- LED
- OLED

Bộ điều khiển (OS):
- Normal (ti vi thường)
- IOS
- Android
- Flutter
  
```java
class Tivi {
  @Autowired
  private Panel panel;

  @Autowired
  private OS os; 
}
```

Hãy cấu hình 2 key trong application.properties để đọc cấu hình cho Tivi. Ví dụ
```
panel=LED
os=IOS
```
Khi chạy, truy cập vào http://localhost:8080/ sẽ thấy được cấu hình tivi theo giá trị trong application.properties
```
Tivi [panel=LED, os=IOS]
```


## Câu hỏi
1. Đối tượng Bean và Component khi đăng ký vào Application Context có đặc điểm gì?
2. Khi cần chọn để tuỳ biến tạo đối tượng, chúng ta chọn annotation nào ```@Component``` hay ```@Bean```?
3. Khái niệm Singleton