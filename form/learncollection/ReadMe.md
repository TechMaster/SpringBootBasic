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

Gợi ý nên dùng thư viện [Guava của Google](https://github.com/google/guava)

- [Immutable Collection](https://github.com/google/guava/wiki/ImmutableCollectionsExplained)
- [New Collection Types](https://github.com/google/guava/wiki/NewCollectionTypesExplained)