package vn.techmaster.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.controller.request.SearchRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;

@Controller
public class SearchController {
  @Autowired private IAuthenService authenService;
  @Autowired private IPostService postService;

  @GetMapping("/search")
  public String showSearchForm(Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);    
    if (user != null) {  //Người dùng đã login      
      model.addAttribute("user", user);
    }
    model.addAttribute("searchRequest", new SearchRequest());
    return Route.SEARCH;
  }

  @PostMapping("/search")
  public String handleSearch(SearchRequest searchRequest, Model model,  HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);    
    if (user != null) {  //Người dùng đã login      
      model.addAttribute("user", user);
    }
    List<Post> posts = postService.searchPost(searchRequest.getTerm(), 5, 0);
    model.addAttribute("posts", posts);
    return Route.SEARCH_RESULT;
  }

  
  @GetMapping("/search/index")
  public String reindexFullText(Model model, HttpServletRequest request) {
    UserInfo user = authenService.getLoginedUser(request);    
    if (user != null) {  //Người dùng đã login      
      model.addAttribute("user", user);
    }
    postService.reindexFullText();
    return Route.REDIRECT_HOME;
  }
}
