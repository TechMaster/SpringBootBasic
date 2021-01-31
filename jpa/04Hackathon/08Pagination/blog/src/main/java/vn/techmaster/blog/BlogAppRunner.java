package vn.techmaster.blog;

import java.util.List;
import java.util.Random;


import javax.transaction.Transactional;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.Tag;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.TagRepository;
import vn.techmaster.blog.repository.UserRepository;

@Component
public class BlogAppRunner implements CommandLineRunner {
  @Autowired  private UserRepository userRepo;
  @Autowired  private PostRepository postRepo;
  @Autowired  private TagRepository tagRepo;


  @Override
  @Transactional
  public void run(String... args) throws Exception {
    List<User> users = userRepo.findAllUserWithPosts();
    List<Tag> tags = tagRepo.findAll();
    int numberOfTags = tags.size();
    int maxTagsPerPost = numberOfTags / 3;

    Lorem lorem = LoremIpsum.getInstance();
    
    Random random = new Random();
    int numberOfUsers = users.size();
   for (int k = 0; k < 20; k++) {
      User user = users.get(random.nextInt(numberOfUsers));
      Post post = new Post(lorem.getTitle(2, 5), lorem.getParagraphs(2, 4));
      
      int numberTagsForPost = Math.max(1, random.nextInt(maxTagsPerPost));
      for (int i = 0; i < numberTagsForPost; i++) {
        post.addTag(tags.get(random.nextInt(numberOfTags)));
      }
     
      user.addPost(post);
      postRepo.save(post);
    }
    userRepo.flush();
  }  
}
