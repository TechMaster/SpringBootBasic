# Ví dụ về tạo một Custom Annotation cho class

Annotation là cách / cú pháp để bổ xung metadata vào một class, interface, method, variable hay vào chính một annotation đang có.
Trong ví dụ này chúng ta tạo ra một annotation có tên là @Entity giống với annotation nổi tiếng trong thư viện [JPA](https://www.baeldung.com/jpa-entities). Kiểu như code dưới đây:

```java
@Entity(name="person")
public class Person {
  private String name;
  private String email;
}
```

1. Tạo annotation ở file [Entity.java](src/main/java/vn/techmaster/demoannotation/annotation/Entity.java)

```java
package vn.techmaster.demoannotation.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)  //Sử dụng lúc Run time
@Target(ElementType.TYPE) // Áp dụng ở mức class
public @interface Entity {
  String name(); //Có một tham số là name
}
```
2. Trong [HomeController.java](src/main/java/vn/techmaster/demoannotation/controller/HomeController.java) chúng ta sẽ lấy ra annotation và tham số name
```java
  @ResponseBody
  @GetMapping(value="/", produces=MediaType.TEXT_HTML_VALUE)
  public String getHome() {
    String msg;
    try {
      Entity anno = Person.class.getAnnotation(Entity.class);
      msg = "Table name attribute: " + anno.name(); 
    } catch (Exception e) {
      msg = e.toString();
    }
    return msg;    
  }
```
Kết quả xem tại đường dẫn http://localhost:8080 như sau
```
Table name attribute: person
```

Rồi bây giờ bạn không còn hoang mang không hiểu về Annotation nữa nhé, vì bạn còn tạo ra bất kỳ Annotation nào bạn muốn.