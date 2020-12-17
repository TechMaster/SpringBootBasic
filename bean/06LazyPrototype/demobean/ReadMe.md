# @Lazy, @Prototype

Ở những bài trước chúng ta đã thấy mặc định Component và Bean được tạo ra là Singleton Object. Tuy nhiên có một số trường hợp cần phải tạo mới đối tượng mỗi lần dùng đến và vẫn đăng ký đối tượng đó vào ApplicationContext. Lúc này chúng ta cần đến 2 annotation đó là ```@Scope("prototype")``` và ```@Lazy```

## Hoàn cảnh
Các thành phần cấu hình lên xe ô tô tương đối là ổn định, trừ khi chúng ta nâng cấp, sửa chữa, tháo thành phần đó ra thay thế bằng thành phần mới. Tuy nhiên có phần bản đồ dẫn đường liên tục phải tạo lại trước mỗi chuyến đi. Trong Java chúng ta có thể dùng lệnh 
```StreetMap streetMap = new StreetMap()```; để tạo mới đối tượng, tuy nhiên đối tượng lại không được đăng ký vào ApplicationContext


## Cấu trúc thư mục

```
├── bean
│   ├── AutoSteering.java
│   ├── Car.java
│   ├── Chassis.java
│   ├── Door.java
│   ├── Engine.java
│   ├── ManualSteering.java
│   └── StreetMap.java  <-- Class này mới thêm
├── configuration
│   ├── CarConfig.java
│   ├── ChassisConfig.java
│   ├── EngineConfig.java
│   ├── MapConfig.java  <-- Class này mới thêm
│   └── SteeringConfig.java
├── controller
│   └── HomeController.java
├── interfaces
│   └── Steering.java
└── DemobeanApplication.java
```

## Thực hành
1. Tạo class [StreetMap](src/main/java/vn/techmaster/demobean/bean/StreetMap.java)
   Constructor của class này, sinh ngẫu nhiên 2 điểm A và B để ra một tuyến đường mới
   ```java
    public StreetMap() {  
      try { //Khởi tạo bộ sinh số ngẫu nhiên
        rand = SecureRandom.getInstanceStrong();
      } catch (NoSuchAlgorithmException e) {      
        e.printStackTrace();
      }
      // Tạo ra tuyến ngẫu nhiên từ điểm A thuộc mảng froms, đến điểm B thuộc mảng tos
      this.route = "From: <b>" + getRamdomFromArrayString(froms) + "</b> -> To: <b>" + getRamdomFromArrayString(tos) + "</b>";
    }
   ```

   Hàm generic này, sẽ lấy ra một phần tử ngẫu nhiên trong mảng truyền vào
   ```java
    //Hàm generic lấy kiểu đầu vào T bất kỳ
    private <T> T getRamdomFromArrayString(T[] array) {
      int index = this.rand.nextInt(array.length);
      return array[index];
    }
   ```

2. Tạo configuration class [MapConfig.java](src/main/java/vn/techmaster/demobean/configuration/MapConfig.java)
   ```java
    @Configuration
    public class MapConfig {
      @Bean
      @Scope("prototype") //Đừng khởi tạo đối tượng theo kiểu Singleton truyền thống nữa!
      //Hãy thử bỏ @Scope("prototype") rồi refresh trình duyệt ở địa chỉ http://localhost:8080/route vài lần
      public StreetMap streetmap() {    
        return new StreetMap();
      }
    }
   ```
  Annotation ```@Scope("prototype")``` định hướng cách tạo đối tượng Bean luôn là bản mẫu, chứ không phải là đối tượng duy nhất. Nó ngược với ```@Scope("singleton")```

3. Bổ xung thuộc tính ```StreetMap streetMap``` vào class [Car.java](src/main/java/vn/techmaster/demobean/bean/StreetMap.java). Chú ý ```@Lazy``` ở đây có nghĩa là thuộc tính này khi nào dùng đến hãy khởi tạo.
   ```java
    public class Car {
      @Autowired
      @Lazy  //Chỉ khi nào dùng mới khởi tạo
      private StreetMap streetMap;
    }
  ```

  và phương thức ```navigate()```
  ```java
    //Trả về điểm bắt đầu và kết thúc trong hành trình
    public String navigate() {
      return streetMap.getRoute();
    }
  ```

  Bộ đôi ```@Scope("prototype")``` và ```@Lazy``` sẽ tạo mới đối tượng Bean mỗi khi đối tượng này được dùng / truy xuất. Cụ thể trong [HomeController.java](src/main/java/vn/techmaster/demobean/DemobeanApplication.java) có phương thức 
  ```java
    @ResponseBody
    @GetMapping(value = "/route", produces=MediaType.TEXT_HTML_VALUE)
    public String getRoute() {
      return mycar.navigate();
    }
  ```

  Mỗi lần người dùng truy cập http://localhost:8080/route thì ```mycar.navigate()``` được gọi, bên trong nó ```streetMap.getRoute()``` được gọi. Do ```StreetMap``` được đánh dấu là ```@Scope("prototype")```, và thuộc tính streetMap được đánh dấu là ```@Lazy``` nên streetMap được tạo mới, mỗi lần ```streetMap.getRoute()``` chạy.

  Kết quả trả về trên trình duyệt sau mỗi lần F5 refresh sẽ như sau:
  ```
  From: 48 Tố Hữu, Nam Từ Liêm -> To: Hồ Đồng Đò
  From: 15 Đoàn Trần Nghiệp, Hai Bà Trưng -> To: Sân Gold Tam Đảo
  From: 79 Mai Hắc Đế, Hai Bà Trưng -> To: Royal City
  From: 2 Hàng Bạc, Hoàn Kiếm -> To: Sân bay Nội Bài
  ```

## Tổng kết
  ```@Scope("prototype")``` + ```@Lazy``` tạo đối tượng Bean mới mỗi khi nó được dùng đến. Cách này hoàn toàn trái ngược với cách truyền thống Bean là đối tượng Singleton.