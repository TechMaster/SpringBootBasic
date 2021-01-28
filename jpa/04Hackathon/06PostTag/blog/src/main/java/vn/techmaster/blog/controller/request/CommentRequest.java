package vn.techmaster.blog.controller.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommentRequest {
 // private long user_id; //Author of comment
  private long post_id; //Post that comment targets to
  private String content;

  public CommentRequest(long post_id) {
    this.post_id = post_id;
  }
}