package vn.techmaster.bookstore.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
 
@Entity(name = "contact")
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Contact implements Serializable {
 
    private static final long serialVersionUID = 4048798961366546485L;
 
    @Schema(description = "Unique ID of Contact.", example = "1", required = true)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Schema(description = "Full name of contact.", example = "Trịnh Minh Cường", required = true)
    @NotBlank
    @Size(max = 100)
    private String name;
    
    @Schema(description = "Mobile contact", example = "0902209011", required = false)
    @Pattern(regexp ="^\\+?[0-9]{10,11}$", message = "Mobile")
    @Size(min=10, max = 11)
    private String phone;
    
    @Schema(description = "Email of contact", example = "cuong@techmaster.vn", required = false)
    @Email(message = "Email Address")
    @Size(max = 100)
    private String email; 
}