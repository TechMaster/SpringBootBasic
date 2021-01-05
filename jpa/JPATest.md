# @DataJpaTest

Trong bài này tôi ít khi dùng đến các tầng Controller, Service hay thậm chí Repository. Vậy làm sao để demo code cho các bạn bây giờ?

Thật may mắn chúng ta có [JUnit5](https://junit.org/junit5/) cho phép viết các hàm test nhỏ xinh kiểm tra ngay những tính năng JPA chúng ta cần quan tâm.

Hãy mở [thư mục test](01EntityMapping/demojpa/src/test/java/vn/techmaster/demojpa), các bạn sẽ thấy rất nhiều file unit test

```
.
├── CarTests.java
├── PersonRepositoryTests.java
├── PostTagTests.java
└── ProductTests.java
```

## Các annotation và đối tượng quan trọng

### @DataJpaTest đánh dấu class chuyên để kiểm thử JPA
Chúng ta có thể viết kiểm thử đối với ```@Controller```, ```@Service``` với annotation ```@SpringBootTest```, thì đối với các tính năng thao tác dữ liệu chúng ta phải sử dụng annotation
```@DataJpaTest```. Hãy làm một thí nghiệm nhỏ sau đây:

1. Mở file [ProductTests.java](01EntityMapping/demojpa/src/test/java/vn/techmaster/demojpa/ProductTests.java) thay ```@DataJpaTest``` bằng ```@SpringBootTest```
2. Chạy Unit Test bạn sẽ gặp lỗi kiểu như thế này
   ```
   javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
   ```

Trong ```@DataJpaTest```, khi cần chúng ta có thể truy xuất đến AppplicationContext

```java
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ProductTests {
  @Autowired
  private ApplicationContext appContext;
  @Test
  public void accessToApplicationContext() {
    String[] beanNames = appContext.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      System.out.println(beanName);
    }
    assertThat(beanNames).contains("testEntityManager", 
    "jpaContext", 
    "jpaMappingContext", 
    "entityManagerFactoryBuilder",
    "entityManagerFactory"
    );
  }
}
```

### TestEntityManager và EntityManager

TestEntityManager là một class thu gọn chức năng so với EntityManager. EntityManager có các phương thức query còn TestEntityManager chỉ có thao tác căn bản:
- persist(object): thêm một đối tượng, lưu
- flush(): lưu mọi thay đổi và đồng bộ thay đổi từ database lên context
- find(object.class, id): tìm đối tượng theo primary key
- remove(object): xoá đối tượng

Xem [PostTagTests.java](01EntityMapping/demojpa/src/test/java/vn/techmaster/demojpa/PostTagTests.java)

### 