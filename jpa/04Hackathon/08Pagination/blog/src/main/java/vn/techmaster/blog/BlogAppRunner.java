package vn.techmaster.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.blog.service.IPostService;

@Component
public class BlogAppRunner implements CommandLineRunner {
  @Autowired
  IPostService postService;

  @Override
  public void run(String... args) throws Exception {
    postService.generateSampleData();
  }
}
