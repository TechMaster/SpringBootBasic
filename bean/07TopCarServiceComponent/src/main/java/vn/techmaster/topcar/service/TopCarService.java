package vn.techmaster.topcar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import vn.techmaster.topcar.model.Car;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service //Đánh đấu đấy là Bean ở tầng Service
public class TopCarService {
  public List<Car> getTop10SoldCar() {
    try {
      File file = ResourceUtils.getFile("classpath:static/topcar.csv");

      CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
      ObjectReader oReader = mapper.readerFor(Car.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu Car.class
      ArrayList<Car> cars = new ArrayList<>();

      Reader reader = new FileReader(file);
      MappingIterator<Car> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
      while (mi.hasNext()) {
        Car current = mi.next();
        cars.add(current);
      }
      return cars;
    } catch (IOException e) {
      return Collections.emptyList(); //Trong phần xử lý Exception cần trả về collection rỗng để tuần thủ đúng phương thức
    }
  }
}
