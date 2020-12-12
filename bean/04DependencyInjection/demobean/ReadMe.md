# Dependency Injection

## Nếu bạn rất vội
Hãy biên dịch và mở trình duyệt tại địa chỉ http://localhost:8080, bạn sẽ thấy dòng chữ này
```
Car [engine=Tesla Electric Engine, steering=Manual steering]
```
Giờ hãy bình tĩnh đọc để hiểu Dependency Injection giúp ứng dụng xây dựng nên từ các thành phần. Trong quá trình xây dựng và cả lúc chạy, chúng ta có thể hoán đổi thành phần. Khi học hãy thử code lại từ đầu sang một project mới để nhớ các cú pháp.
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

## Car.java
Hãy chú đến hai annotation:
- ```@Autowired```: tự động tìm đến Bean có tên phù hợp để gán vào biến mà nó đánh dấu.
- ```@Qualifier```: nếu có nhiều Bean khác tên nhưng trả về đối tượng cùng kiểu, thì cần định rõ tên.

**Khi đã dùng ```@Autowired``` cho một thuộc tính, thì bạn không khai báo tham số ở contructor để khởi tạo thuộc tính này nữa.**
Có nghĩa là ```@Autowired``` đã giúp tự động inject (tiêm, cấu hình) cho thuộc tính ```steering``` khi đối tượng ```Car``` được khởi tạo (construct).

Nếu bỏ khai báo
```java
@Autowired
@Qualifier("manualsteering")
```

Ứng dụng vẫn biên dịch được, như khi bạn truy cập http://localhost:8080 sẽ thấy lỗi sau
```Cannot invoke "vn.techmaster.demobean.interfaces.Steering.steer()" because "this.steering" is null```

Một điểm chú ý nữa là thuộc tính ```private Steering steering``` lấy kiểu là interface tổng quát [Steering](src/main/java/vn/techmaster/demobean/interfaces/Steering.java). Làm như vậy để sau đó lúc compile time hoặc run time, có thể gán đối tượng có kiểu thực hiện Interface này là được. Trong trường hợp này, thuộc tính steering được gán đối tượng kiểu [ManualSteering](src/main/java/vn/techmaster/demobean/bean/ManualSteering.java)

```java
public class Car {
  private Engine engine;

  @Autowired
  @Qualifier("manualsteering") //chủ động chọn Bean manualsteering
  private Steering steering;

  public Car(Engine engine) { 
    this.engine = engine;
  }

  @Override
  public String toString() {
    return "Car [engine=" + engine + ", steering=" + steering.steer() + "]";
  }
}
```

## Đọc giá trị engineType từ application.properties
Khách hàng muốn rằng phải được tuỳ biến loại động cơ (engine), mà không phải sửa code. Vậy chúng ta sẽ đọc giá trị từ key ```engineType``` trong application.properties nhờ cú pháp sau đây:
```java
@Value("${engineType}")
private String engineType;
```

Trong [CarConfig.java](src/main/java/vn/techmaster/demobean/configuration/CarConfig.java)
hãy xem phương thức tạo ra bean car. Dựa vào biến engineType được gán giá trị từ application.properties, bạn có thể lấy ra loại động cơ phù hợp từ Application Context thông quá lệnh ```context.getBean("nameOfBeanHere");```

Chú ý: ```ApplicationContext.getBean("typeEngineHere")``` sẽ lấy một đối tượng Bean được đăng ký, quản lý trong ApplicationContext. Đối tượng này thường là duy nhất trong vòng đời ứng dụng SpringBoot. Nếu bạn sử dụng phương pháp thông thường
```engine = new Engine("typeEngineHere")``` mỗi lần chạy, sẽ tạo ra một đối tượng động cơ mới.

```java
@Bean
public Car car() {
  Engine engine;
  switch (engineType) {
    case "gas":
      engine = (Engine) context.getBean("gasEngine");
      break;
    case "electric":
      engine = (Engine) context.getBean("electricEngine");
      break;
    case "hybrid":
      engine = (Engine) context.getBean("hybridEngine");
      break;
    default:
      engine = (Engine) context.getBean("gasEngine");
  }
    
  return new Car(engine);
}
```

## HomeController.java
[HomeController.java](src/main/java/vn/techmaster/demobean/configuration/EngineConfig.java)

HomeController.java hứng request đến đường dẫn "/" (đây là đường dẫn tương đối, trước đó sẽ là http://localhost:port hoặc https://yourdomain.com/)

```java
@Controller
public class HomeController {

  @Autowired
  Car car; //Lấy bean có tên là car

  @ResponseBody
  @GetMapping(value = "/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return car.toString();
  }
}
```

## Thí nghiệm 1: khi chỉ có duy nhất một phương thức @Bean trả về kiểu Car
Trong [CarConfig.java](src/main/java/vn/techmaster/demobean/configuration/CarConfig.java) có duy nhất một phương thức @Bean trả về kiểu Car
```java
  @Bean
  public Car car() {
```

Trong [HomeController.java](src/main/java/vn/techmaster/demobean/controller/HomeController.java) khi đặt tên thuộc tính kiểu Car bất kỳ tên gì, Spring Boot vẫn có thể kết nối (```@Autowired```)đúng
```java
@Controller
public class HomeController {

  @Autowired
  Car mycar; //Mặc dù tên Bean là car, nhưng chạy vẫn được !

  @ResponseBody
  @GetMapping(value = "/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    return mycar.toString();
  }
}
```

## Thí nghiệm 2: khi có 2 phương thức @Bean trả về kiểu Car
Trong [CarConfig.java](src/main/java/vn/techmaster/demobean/configuration/CarConfig.java) bổ xung thêm phương thức thứ 2 cũng trả về kiểu Car
```java
@Bean
public Car funcar() {
  return new Car((Engine) context.getBean("hybridEngine"));
}
```

Khi biên dịch lỗi sẽ báo như sau:
```
Field mycar in vn.techmaster.demobean.controller.HomeController required a single bean, but 2 were found:
        - car: defined by method 'car' in class path resource [vn/techmaster/demobean/configuration/CarConfig.class]
        - funcar: defined by method 'funcar' in class path resource [vn/techmaster/demobean/configuration/CarConfig.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
```

Bạn sẽ phải dùng đến ```@Primary``` để ưu tiên một Bean trong trường hợp có nhiều Bean trả về cùng kiểu.
Hoặc dùng ```@Qualifier``` để chủ động chọn ra Bean theo tên khi dùng với ```@Autowired```

## Thí nghiệm 3: Bổ xung @Primary vào public Car funcar()
```java
@Primary
@Bean
public Car funcar() {
    return new Car((Engine) context.getBean("hybridEngine"));
}
```
Giờ thì ứng dụng đã biên dịch được, và kết quả trả về như sau
```
Car [engine=Hybrid Engine, steering=Manual steering]
```