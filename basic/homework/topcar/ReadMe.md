
## Đề bài
[Xem chi tiết đề bài ở đây](../ReadMe.md)
## Hướng giải quyết chung
1. Google tìm ra [danh sách top 10 xe bán chạy nhất Việt nam](https://giaxeoto.vn/top-15-xe-o-to-ban-chay-nhat-2020-tai-viet-nam-355)

2. Google tiếp cách đọc CSV trong Java, keyword là "java parse csv", sẽ ra được một số link chủ yếu hướng dẫn OpenCSV
   - [mkyong - How to read and parse CSV file in Java](https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/) site này nhiều hướng dẫn Java tốt, không quảng cáo
   - [baeldung - Reading a CSV File into an Array](https://www.baeldung.com/java-csv-file-array)

3. Tóm lại vài cách:
   - Lập trình mở file, đọc từng dòng lệnh một vào String, cắt String theo dấu phân cách.Cách này code nhiều, thủ công, bạn xem đầy đủ từng dòng. Cũng hay cho người mới học Java.
   - Sử dụng thư viện dependency để đọc file CSV và ánh xạ từng dòng CSV vào đối tượng POJO (Plain Old Java Object). Các này lập trình thực dụng sẽ chọn vì nó tiện dụng.

4. Hãy đừng cố gắng viết chương trình với quá nhiều chức năng vội mà hãy đi từng bước nhỏ, dễ trước rồi nâng lên dần. Ví dụ đọc file thô cần làm trước rồi mới đến phân tích nội dung từng dòng trong file CSV. Sau khi chạy được, thì mới nâng lên đọc từng dòng file CSV vào đối tượng POJO


## Thí nghiệm 1: Đọc file CSV trong thư mục resource của SpringBoot

1. Hãy xem file cần đọc [topcar.csv](target/classes/static/topcar.csv)
2. Đọc bài hướng dẫn này [Read file from resources folder](https://howtodoinjava.com/java/io/read-file-from-resources-folder/)
3. Tạo file [CarController.java](src/main/java/vn/techmaster/topcar/controller/CarController.java)
  ```java
   @ResponseBody
   @GetMapping(value = "/raw", produces = MediaType.TEXT_HTML_VALUE)
   public String readRawCSV() {
      try {
         File file = ResourceUtils.getFile("classpath:static/topcar.csv");
         // Read File Content
         return new String(Files.readAllBytes(file.toPath()));
      } catch (FileNotFoundException e) {
         return "File Not Found";
      } catch (IOException e) {
         return "IO Exception Error";
      }
   }
  ```
4. Chạy vào xem ở địa chỉ http://localhost:8080/raw bạn sẽ thấy
```
model,manufacter,price,sales,photo Vios,Toyota,450,3635,vios.jpg Fadil,Vinfast,420,2816,fadil.png Grand-i10,Hyndai,350,2793,grand-i10.png
```
Dữ liệu đã được đọc ra nhưng không có gì ấn tượng cả. Chúng ta cần bóc tách từng trường, rồi gán vào đối tượng [Car.java](src/main/java/vn/techmaster/topcar/model/Car.java)


## Thí nghiệm 2: Đọc dữ liệu CSV vào class trong Java
Cơ chế thủ công đọc từng dòng, cắt chuỗi theo dấu phân cách (delimiter, rồi gán vào từng trường dễ hiểu, nhưng tốn nhiều công sức, chưa kể code rất khó bảo trì. Do đó tôi sẽ sử dụng thư viện rồi bổ xung dependency vào pom.xml

Nổi bật nhất có 2 thư viện
  - [OpenCSV](http://opencsv.sourceforge.net/) văn bản hướng dẫn nhiều, code lưu trên SourceForge hơi cũ, check [maven repository](https://mvnrepository.com/artifact/com.opencsv/opencsv) thấy bản mới nhất là tháng 10/2020
  - [jackson-dataformat-csv](https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv), check [maven repository](https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-csv) bản mới nhất tháng 11/2020

1. Tôi chọn [jackson-dataformat-csv](https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv) vì nó của team viết thư viện ánh xạ JSON <--> Object rất tốt. Ngoài ra jackson-dataformat-csv sẽ dùng chung cơ chế đánh dấu trường (field annotation) cho POJO tương tự như JSON.
Bổ xung đoạn này vào file [pom.xml](pom.xml)
   ```xml
   <dependency>
      <groupId>com.fasterxml.jackson.dataformat</groupId>
      <artifactId>jackson-dataformat-csv</artifactId>
   </dependency>
   ```

2. Google sẽ ra được bài viết này [How to read a CSV file with Header in Java using Jackson library - Example Tutorial](https://www.java67.com/2019/05/how-to-read-csv-file-in-java-using-jackson-library.html)

Đoạn code hay nhất của Tutorial trên là đây
   ```java
   CsvMapper csvMapper = new CsvMapper();
   CsvSchema schema = CsvSchema.emptySchema().withHeader();
   ObjectReader oReader = csvMapper.reader(OnlineCourse.class).with(schema);
   List<OnlineCourse> courses = new ArrayList<>();
   try (Reader reader = new FileReader("file.txt")) { 
      MappingIterator<OnlineCourse> mi = oReader.readValues(reader);
      while (mi.hasNext()) { 
         OnlineCourse current = mi.next();
         courses.add(current);System.out.println(current); 
      }
   }
   ```

3. Kết hợp đọc thêm code snippet ở github của [jackson-dataformat-csv](https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv)

4. File [TopCar.csv](target/classes/static/images/grand-i10.png) có hàng đầu tiên để mô tả cột:
   - model
   - manufacter
   - price
   - sales
   - photo

```csv
model,manufacter,price,sales,photo
Vios,Toyota,450,3635,vios.jpg
Fadil,Vinfast,420,2816,fadil.png
Grand-i10,Hyndai,350,2793,grand-i10.png
```

5. Tạo POJO Class [Car.java](src/main/java/vn/techmaster/topcar/model/Car.java)
   ```java
   public class Car {
   public String model;
   public String manufacter;
   public int price;
   public int sales;
   public String photo;
   ```
   Có các trường trùng tên với cột trong [topcar.csv](src/main/resources/static/topcar.csv)

6. Tiếp đến viết phương thức trong [CarController.java](src/main/java/vn/techmaster/topcar/controller/CarController.java)

   ```java
   @ResponseBody
   @GetMapping(value = "/parse", produces = MediaType.TEXT_HTML_VALUE)
   public String parseCSV() {
      try {
      File file = ResourceUtils.getFile("classpath:static/topcar.csv");
      
      CsvMapper mapper = new CsvMapper(); //Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
      ObjectReader oReader = mapper.readerFor(Car.class).with(schema); //Cấu hình bộ đọc CSV phù hợp với kiểu Car.class

      StringBuilder sb = new StringBuilder(""); //sb dùng để cộng các chuỗi toString của đối tượng Car

      Reader reader = new FileReader(file);
      MappingIterator<Car> mi = oReader.readValues(reader); //Iterator đọc từng dòng trong file
      while (mi.hasNext()) {
         Car current = mi.next();
         sb.append(current.toString() + "<br>");
      }
      return sb.toString(); //Trả về trình duyệt
      } catch (FileNotFoundException e) {
      return "File Not Found";
      } catch (IOException e) {
      return "IO Exception Error";
      }
   }
   ```
7. Biên dịch và vào địa chỉ http://localhost:8080/parse sẽ thấy
   ```
   Car [manufacter=Toyota, model=Vios, photo=vios.jpg, price=450, sales=3635]
   Car [manufacter=Vinfast, model=Fadil, photo=fadil.png, price=420, sales=2816]
   Car [manufacter=Hyndai, model=Grand-i10, photo=grand-i10.png, price=350, sales=2793]
   ```
   Kết quả đẹp như mơ

## Thí nghiệm 3: Refactor lại code trong Controller
Viết rất nhiều logic vào phương thức trong Controller không phải là cách hay để bố trí code trong dự án Spring Boot.

Việc truy xuất đến CSDL, đọc dữ liệu từ file hay gọi đến REST API khác, chúng ta nên đóng gói vào Component được annotate ```@Service```

Hãy xem bài tập ở phần Bean để chuyển bớt logic trong Controller vào một Bean có kiểu là ```@Service```