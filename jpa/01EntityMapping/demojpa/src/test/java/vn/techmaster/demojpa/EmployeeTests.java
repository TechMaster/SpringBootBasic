package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.mapping.Employee;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Sql({"/employee.sql"})  //Nạp dữ liệu từ file employee.sql
public class EmployeeTests {
  @Autowired
  private TestEntityManager tem;

  @Test
  public void getEmployeeFullName() {

    Employee emp = new Employee("Trịnh", "Cường");
    tem.persist(emp);
    tem.flush();
    System.out.println(emp.getId());
    assertThat(emp.getId()).isGreaterThan(10); //employee.sql ban đầu có 10 bản ghi, thêm một đối tượng nữa id sẽ phải lớn hơn 10
    assertThat(emp.getFullname()).isEqualTo("Trịnh Cường");

  }
  
}
