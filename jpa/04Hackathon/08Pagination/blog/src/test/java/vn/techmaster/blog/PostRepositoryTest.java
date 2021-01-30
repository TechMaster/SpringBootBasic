package vn.techmaster.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;
import vn.techmaster.blog.testbase.PostgresqlTestBase;


public class PostRepositoryTest extends PostgresqlTestBase{
  @Autowired  private UserRepository userRepo;
  @Autowired  private PostRepository postRepo;
  
  @Test
  @DisplayName("Tạo một post mới")
  void addNewPost() {
    Optional<User> optionalUser = userRepo.findByEmail("bob@gmail.com");
    assertThat(optionalUser).isPresent();
    User user = optionalUser.get();
    Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
    user.addPost(post);

    assertThat(post.getId()).isNull(); //Chưa được persist vào Persistence Context
    userRepo.flush();  //Cách này đúng: đẩy thay đổi xuống database
    //userRepo.save(user); //Cách này không đúng user đã tồn tại rồi
    //postRepo.save(post);
    
    
     assertThat(post.getUser()).isEqualTo(user);

    User bob = userRepo.findById(1L).get(); //Lấy user Bob qua id
    assertThat(postRepo.existsById(post.getId())).isTrue(); //Kiểm tra post với id = 1L đã có trong CSDL chưa?
    assertThat(bob.getPosts().get(0)).isEqualTo(post); //Kiểm tra xem thực sự user Bob ở CSDL đã thực sự có post chưa
  }

  @Test
  @DisplayName("Tạo một post mới dùng PostRepository để lưu")
  void persistNewPost() {
    User bob = userRepo.findByEmail("bob@gmail.com").get();
    
    Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
    bob.addPost(post);

    assertThat(post.getId()).isNull(); //Chưa được persist vào Persistence Context
    postRepo.save(post);  //Dùng postRepo để lưu đối tượng post mới
    postRepo.flush();    
    
    assertThat(post.getUser()).isEqualTo(bob);
    assertThat(bob.getPosts().get(0)).isEqualTo(post);
  }

  @Test
  @DisplayName("User removes post thì post sẽ phải bị xoá hoàn toàn")
  void addAndRemovePost() {
    User bob = userRepo.findByEmail("bob@gmail.com").get();
    
    Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
    bob.addPost(post);

    postRepo.save(post);  //Dùng postRepo để lưu đối tượng post mới
    postRepo.flush();
    long postId = post.getId();
    bob.removePost(post);
    assertThat(post.getUser()).isNull();  //Đầu tiên thuộc tính Author set null
    userRepo.flush();

    Optional<Post> optionalPost = postRepo.findById(postId);
    assertThat(optionalPost).isNotPresent(); //Post cũng không còn tồn tại trong CSDL
    assertThat(bob.getPosts().size()).isZero();

  }

  @Test
  @DisplayName("Xoá user thì các post user tạo ra cũng bị xoá")
  void removeUserCascadeRemovePostsBelongToUser() {
    User bob = userRepo.findByEmail("bob@gmail.com").get();
    
    Post post = new Post("I love Spring Boot", "I love Spring Boot so much");
    bob.addPost(post);

    postRepo.save(post);  //Dùng postRepo để lưu đối tượng post mới
    postRepo.flush();
    long postId = post.getId();

    userRepo.delete(bob); //Xoá bob thì tất cả các post do bob viết cũng sẽ bị xoá
    
    userRepo.flush();
    Optional<Post> optionalPost = postRepo.findById(postId);
    assertThat(post).isNotNull(); //đối tượng này chỉ tồn tại trong hàm đang chạy chứ trong CSDL post đã bị xoá
    assertThat(optionalPost).isNotPresent(); //Post cũng không còn tồn tại trong CSDL
  }

 /* @Test
  void findPostByUserId() {

  }*/



}
