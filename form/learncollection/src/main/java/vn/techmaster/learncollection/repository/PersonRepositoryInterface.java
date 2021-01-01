package vn.techmaster.learncollection.repository;

import java.util.HashMap;
import java.util.List;

import vn.techmaster.learncollection.model.Person;

public interface PersonRepositoryInterface {
  
  List<Person> getAll(); //Liệt kê danh sách tất cả

  List<Person> sortPeopleByFullNameReversed(); //Liệt kê danh sách sắp xếp theo tên full name từ Z-A

  List<String> getSortedJobs(); //Lấy danh sách tất cả nghề nghiệp đã được sắp xếp từ A-Z

  List<String> getSortedCities(); //Lấy danh sách tất cả thành phố đã được sắp xếp từ A-Z

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

  HashMap<String, Float> averageJobSalary(); //Ứng với mỗi nghề nghiệp (job - String), tính mức lương trung bình (float)

  HashMap<String, Float> top5HighestSalaryCities(); //Tìm 5 thành phố có mức lương trung bình cao nhất, sắp xếp từ cao xuống thấp

  HashMap<String, Float> averageJobAge(); //Ứng với mỗi loại job hãy tính độ tuổi trung bình

  HashMap<String, Float> averageCityAge(); //Ứng với mỗi thành phố hãy tính độ tuổi trung bình

  List<String> find5CitiesHaveMostSpecificJob(String job); //Với một nghề cụ thể, hãy tìm ra 5 thành phố có nhiều làm nghề đó nhất

}
