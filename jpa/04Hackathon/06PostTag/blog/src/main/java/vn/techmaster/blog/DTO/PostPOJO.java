package vn.techmaster.blog.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPOJO {
  private Long id;
  private String title;
  private String content;
  private Long user_id;
  private String userFullName;
  private LocalDateTime lastUpdate;
}
