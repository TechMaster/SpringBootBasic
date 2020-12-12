# Truy xuất đến đối tượng ApplicationContext bằng cách nào?

ApplicationContext là một singleton object(đối tượng duy nhất) quan trọng trong SpringBoot.
Ở bất kỳ chỗ nào trong ứng dụng SpringBoot, sẽ có lúc chúng ta cần truy xuất đến ApplicationContext, ví dụ để lấy Bean theo tên gọi:
```ApplicationContext.getBean("PutNameOfBeanHere")```

Trong bài này tôi chỉ ra 2 cách (tất nhiên còn vài cách khác nữa sử dụng interface nhưng ít phổ biến hơn).

1. Sử dụng ```@Autowired``` gắn vào một biến kiểu ```ApplicationContext```
2. Sử dụng phương thức ```void setApplicationContext(ApplicationContext applicationContext)``` của ```interface ApplicationContextAware```

Hãy xem [APC.java](src/main/java/vn/techmaster/demobean/component/APC.java)

```java
@Component
public class APC implements ApplicationContextAware {

  @Autowired
  private ApplicationContext applicationContext1; // Lấy application context
  
  private ApplicationContext applicationContext2;

  @Override //Đè chồng phương thức của interface ApplicationContextAware
  public void setApplicationContext(ApplicationContext applicationContext) {
    applicationContext2 =  applicationContext;
  }
}
```

Đoạn code cuối chỉ để kiểm tra xem hay đối tượng ```applicationContext1``` và ```applicationContext2``` có là một không?
```java
public String doSomething() {
  StringBuilder str = new StringBuilder(applicationContext1.getDisplayName());
  str.append(" ");
  str.append(applicationContext2.getDisplayName());
  return str.toString();
}  
```
Kết quả xem được khi chạy ứng dụng và vào địa chỉ http://localhost:8080/
```
org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@42fa336b
org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@42fa336b
```

Hãy dòng giống hệt nhau vậy kết luận ```applicationContext1``` và ```applicationContext2``` đều trỏ về một đối tượng      ```ApplicationContext``` duy nhất.
