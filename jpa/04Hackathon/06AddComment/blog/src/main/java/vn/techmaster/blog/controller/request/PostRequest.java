package vn.techmaster.blog.controller.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
  private Long id;
  private String title;
  private String content;
  private Long user_id;
}
