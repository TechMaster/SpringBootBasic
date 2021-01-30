package vn.techmaster.blog;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;

@Component
public class BlogAppRunner implements CommandLineRunner {
  @Autowired  private UserRepository userRepo;
  @Autowired  private PostRepository postRepo;

  @Override
  public void run(String... args) throws Exception {
    List<User> users = userRepo.findAll();
    Lorem lorem = LoremIpsum.getInstance();
    int count = 0;
    Random random = new Random();
    int numberOfUsers = users.size();
    while (count < 200) {
      int userIndex = random.nextInt(numberOfUsers);
      User user = users.get(userIndex);
      Post post = new Post(lorem.getTitle(2, 5), lorem.getParagraphs(2, 4));      
      user.addPost(post);
      postRepo.save(post);
    }
    userRepo.flush();
  }  
}
