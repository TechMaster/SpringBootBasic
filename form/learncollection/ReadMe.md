# Bài tập cuối năm 2020

Hạn nộp 8/1/2021. Sẽ chấm bài tại lớp.

1. Hãy import dữ liệu từ file [person.csv](src/main/resources/static/person.csv)

```csv
id,fullname,job,city
1,Roldan Glennon,Soldier,Dubai
2,Sharla Beaman,Banker,Tokyo
3,Aindrea Coare,Taxi Driver,Tokyo
4,Dona Hauxby,Banker,Barcelona
5,Slade Holliar,Police,Da nang
6,Maxine Henryson,Teacher,Tokyo
7,Teriann Maddy,Graphics Designer,Sandiego
8,Briny Bullivent,Police,Barcelona
```

2. Viết nốt logic của [PersonRepositoryCSV.java](src/main/java/vn/techmaster/learncollection/repository/PersonRepositoryCSV.java) tuân thủ theo [PersonRepositoryInterface.java](src/main/java/vn/techmaster/learncollection/repository/PersonRepositoryInterface.java)

```java
List<Person> getAll(); //Liệt kê danh sách tất cả

List<Person> sortPeopleByFullName(); //Liệt kê danh sách sắp xếp theo tên full name từ A-Z

List<String> getSortedJobs(); //Lấy danh sách tất cả nghề nghiệp đã được sắp xếp từ A-Z

List<String> getSortedCities() //Lấy danh sách tất cả thành phố đã được sắp xếp từ A-Z

HashMap<String, List<Person>> groupPeopleByCity(); //Gom tất cả những người trong cùng một thành phố lại
/* - Hanoi 
      - Nguyen Văn X  |
      - Nguyên Văn Y  | -> List<Person>
      - Bui Thi Z     |
    - New York
      - John Lenon
      - Iron Man
      - John Biden
    - Tokyo
      - Ajino Moto
      - Murakami
      - Kawazaki   
*/
HashMap<String, Integer> groupJobByCount();  //Nhóm các nghề nghiệp và đếm số người làm mỗi nghề
/* 
Pharmacist  - 2
Data Coordiator - 3
Sales Representative - 5
*/
HashMap<String, Integer> findTop5Jobs(); //Tìm 5 nghề có số lượng người làm nhiều nhất sắp xếp từ cao xuống thấp

HashMap<String, Integer> findTop5Citis(); //Tìm 5 thành phố có số người thuộc danh sách sinh sống đông nhất từ vị trí thứ 5 đến vị trí thứ 1

HashMap<String, String> findTopJobInCity(); //Ở mỗi thành phố, tìm nghề nào có nhiều người làm nhất
```

3. Tạo một trang chủ gồm các link sau đây
   - Liệt kê danh sách tất cả người -> http://localhost:8080/getAll
   - Liệt kê danh sách sắp xếp theo tên full name từ A-Z  -> http://localhost:8080/sortPeopleByFullName
   - Lấy danh sách tất cả nghề nghiệp đã được sắp xếp từ A-Z  -> http://localhost:8080/getSortedJobs
   - Lấy danh sách tất cả thành phố đã được sắp xếp từ A-Z  -> http://localhost:8080/getSortedCities
   ...
   Xem mẫu hàm trên sẽ tự hiểu.

4. Với mỗi link hãy tạo view Thymeleaf để hiện thị dữ liệu sao cho dễ đọc, dễ hiểu.

Gợi ý nên dùng thêm thư viện [Guava của Google](https://github.com/google/guava)

- [Immutable Collection](https://github.com/google/guava/wiki/ImmutableCollectionsExplained)
- [New Collection Types](https://github.com/google/guava/wiki/NewCollectionTypesExplained)


## Hướng dẫn làm bài tập này

### Tip 1: Hãy thu nhỏ, đơn giản hoá dữ liệu để dễ quan sát
Khi xử lý với lượng dữ liệu quá 20 dòng, chúng ta rất khó trong việc kiểm tra tính đúng đắn của logic.
Do đó hãy tạo một file csv khoảng 20 dòng, [personsmall.csv](src/main/resources/static/personsmall.csv)

### Tip 2: Hãy sử dụng Automation Test.

Trong Java có một thư viện Automation Test rất tốt là JUnit. Phiên bản hiện này là [JUnit5](https://junit.org/junit5/) có rất nhiều cải tiến so với JUnit4.

- [A Guide to JUnit 5](https://www.baeldung.com/junit-5)
- [JUnit 5 Tutorial](https://howtodoinjava.com/junit-5-tutorial/)

Hỏi: JUnit dùng để viết kiểm thử sao lại dùng vào bài tập này?

Trả lời: Kiến trúc của ứng dụng Spring Boot là multi-layers (nhiều tầng): View <-> Controller -> Service -> Repository -> Entity -> Database
![](images/unitTestRepository.jpg)

Bài tập này tập trung vào lập trình phần xử lý dữ liệu ở Repository. Nếu phải lập trình đầy đủ các tầng Controller, View rồi Service thì rất khó kiểm soát lỗi. 

Nguyên tắc số 1 trong quản lý chất lượng sản phẩm đó là kiểm soát, kiểm tra, giám sát thành phần ở tại chỗ nó được tạo ra. Nếu có lỗi, dừng dây truyền sản xuất để tìm lỗi, khắc phục rồi mới chạy tiếp.

Nguyên tắc số 2: đó là tiến hành kiểm tra chất lượng ngay từ đầu, không để sản xuất sau một thời gian mới kiểm tra.

Trong dự án này chúng ta sẽ dùng [JUnit5](https://junit.org/junit5/) và AssertJ(https://assertj.github.io/doc/).
Đây là phần XML các bạn cần thêm vào file [pom.xml](pom.xml)
```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-params</artifactId>
  <version>5.7.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <scope>test</scope>
  <version>3.18.1</version>
</dependency>
```

Hỏi: Viết mã test vào thư mục nào?
Đáp: Trong thư mục src có 2 thư mục main và test. Thư mục test là nới các bạn sẽ đặt các Testing class. Khi kiểm thử, IDE sẽ quét tất các các phương thức trong thư mục này.
```
.
├── src
│   ├── main
│   ├── test
│   │   └── java
│   │       └── vn
│   │           └── techmaster
│   │               └── learncollection <-- Viết các testing class vào đây
│   │                   └── PersonRepositoryTest.java
```








