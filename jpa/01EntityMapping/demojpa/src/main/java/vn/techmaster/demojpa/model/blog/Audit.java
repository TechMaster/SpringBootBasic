package vn.techmaster.demojpa.model.blog;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import vn.techmaster.demojpa.component.LoggedUser;

@Embeddable
@Data
public class Audit {
    @Transient //Phải dùng @Transient để không bổ xung loggedUser thành một cột
    @Autowired
    private LoggedUser loggedUser;  
 
    @Column(name = "created_on")
    private LocalDateTime createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
     
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
 
    @Column(name = "updated_by")
    private String updatedBy;
 
    @PrePersist //Trước khi lưu khi khởi tạo record
    public void prePersist() {
        createdOn = LocalDateTime.now();
        //Khi kiểm thử với @DataJpaTest, ApplicationContext không khởi tạo do đó các @Component, @Bean sẽ trả về null khi dùng @Autowired
        createdBy = (loggedUser != null) ? loggedUser.get() : "user";
    }
 
    @PreUpdate //Khi cập nhật record
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = (loggedUser != null) ? loggedUser.get() : "user";
    }
 
    //Getters and setters omitted for brevity
}
