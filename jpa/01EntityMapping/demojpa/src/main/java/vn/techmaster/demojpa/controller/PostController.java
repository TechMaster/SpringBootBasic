package vn.techmaster.demojpa.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.demojpa.dto.PostDTO;
import vn.techmaster.demojpa.model.blog.Post;
import vn.techmaster.demojpa.repository.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {
@Autowired
private PostRepository postRepo;
@Autowired
private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        Optional<Post> postOp = postRepo.findById(id);
        if (postOp.isPresent()) {
            PostDTO postDto = modelMapper.map(postOp.get(), PostDTO.class);
            return ResponseEntity.ok(postDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
