# Dependency Injection

## Mở đầu

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

Với 3 kiểu động cơ và 2 kiểu lái, chúng ta có 6 biến thể xe tất cả. Làm sao trong ứng dụng có thể tuỳ biến dễ dàng động cơ và kiểu lái để tạo nên một model ô tô Car.

## Cấu trúc thư mục
```
.
├── main
│   ├── java
│   │   ├── vn
│   │   │   ├── techmaster
│   │   │   │   ├── demobean
│   │   │   │   │   ├── bean
│   │   │   │   │   │   ├── AutoSteering.java
│   │   │   │   │   │   ├── Car.java
│   │   │   │   │   │   ├── Engine.java
│   │   │   │   │   │   └── ManualSteering.java
│   │   │   │   │   ├── configuration
│   │   │   │   │   │   ├── CarConfig.java
│   │   │   │   │   │   ├── EngineConfig.java
│   │   │   │   │   │   └── SteeringConfig.java
│   │   │   │   │   ├── controller
│   │   │   │   │   │   └── HomeController.java
│   │   │   │   │   ├── interfaces
│   │   │   │   │   │   └── Steering.java
│   │   │   │   │   └── DemobeanApplication.java
```

## @Configuration đánh dấu class dùng để cấu hình các @Bean
Thực tế ```@Bean``` linh hoạt hơn ```@Component``` ở chỗ: ```@Component``` đánh dấu class, còn ```@Bean``` đánh dấu phương thức trả về đối tượng. Phương thức thì có thể thêm logic tuỳ chỉnh theo ý đồ lập trình viên.

Các phương thức được đánh dấu(annotate) bởi ```@Bean``` bắt buộc phải thuộc một class được đánh dấu bởi ```@Configuration```.
Trong một ứng dụng Spring Boot, chúng ta có thể tạo ra nhiều configuration class (Class được đánh dấu - annotated bởi ```@Configuration```).

Việc sử dụng configuration class giúp lập trình viên bớt vất vả hơn nhiều so với các bậc tiền bối trước đấy phải dùng XML để cấu hình, rồi trong ứng dụng Java thêm công đoạn đọc XML ra.

Trong thư mục configuration, có 3 file cấu hình
```
├── configuration
│   ├── CarConfig.java
│   ├── EngineConfig.java
│   └── SteeringConfig.java
```

## EngineConfig.java
[EngineConfig.java](src/main/java/vn/techmaster/demobean/configuration/EngineConfig.java) có 3 phương thức tạo Bean. Tham số ```name``` trong @Bean dùng để đặt lại tên cho Bean khác với tên mặc định của phương thức. Ví dụ tên phương thức là ```teslaEngine``` nhưng tên bean đặt lại sẽ là ```electricEngine```

```java
@Configuration
public class EngineConfig {
  @Bean
  public Engine gasEngine() {
    return new Engine("Gas Engine");
  }

  @Bean(name = "electricEngine")
  public Engine teslaEngine() {
    return new Engine("Tesla Electric Engine");
  }

  @Bean(name = "hybridEngine")
  public Engine dongcoLai() {
    return new Engine("Hybrid Engine");
  }
}
```

## Steering.java và SteeringConfig.java
[Steering.java](src/main/java/vn/techmaster/demobean/bean/Car.java) là một interface. Từ đó có 2 cơ chế Steering thực hiện interface này là:
- [ManualSteering.java](src/main/java/vn/techmaster/demobean/bean/ManualSteering.java)
- [AutoSteering.java](src/main/java/vn/techmaster/demobean/bean/AutoSteering.java)

Giờ chúng ta xem đến [SteeringConfig.java](src/main/java/vn/techmaster/demobean/configuration/EngineConfig.java). Nó báo gồm 2 phương thức đánh đấu bởi ```@Bean``` trả về đối tượng kiểu ```AutoSteering``` và ```ManualSteering```
```java
@Configuration
public class SteeringConfig {
  @Bean
  public AutoSteering autosteering() {
    return new AutoSteering();
  }

  @Bean
   public ManualSteering manualsteering() {
    return new ManualSteering();
  }
}
```