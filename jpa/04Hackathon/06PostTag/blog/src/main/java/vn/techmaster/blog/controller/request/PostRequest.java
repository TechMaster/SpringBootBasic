package vn.techmaster.blog.controller.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.blog.model.Tag;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
  private Long id;
  private String title;
  private String content;
  private Long user_id;
  private Set<Tag> tags;
}
