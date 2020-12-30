package vn.techmaster.pathquery.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.techmaster.pathquery.model.Person;

public interface PersonRepositoryInterface {
  List<Person> getAll(); //Liệt kê danh sách tất cả

  List<Person> sortPeopleByFullName(); //Liệt kê danh sách sắp xếp theo tên full name từ A-Z
  
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

  HashMap<String, String> findTopJobInCity(); //Ở mỗi thành phố, tìm nghề nào có nhiều người làm nhất

}
