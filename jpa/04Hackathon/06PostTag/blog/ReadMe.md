# Post - Tag

Trong phần này chúng ta sẽ bổ xung chức năng phân loại bài viết: tagging a post. Một Post được đánh dấu bởi nhiều Tag. Một Tag dùng để đánh dấu nhiều Post
Bảng TAG
![](images/tag.jpg)

Bảng POST
![](images/post.jpg)

Bảng trung gian POST_TAG
![](images/post_tag.jpg)
## Định nghĩa quan hệ nhiều - nhiều ~ Many to Many

Hãy xem [Post.java](src/main/java/vn/techmaster/blog/model/Post.java)
```java
@Entity(name = "post")
@Table(name = "post")
public class Post { 
    // Bỏ phần trên cho ngắn gọn
    @ManyToMany
    @JoinTable(
        name = "post_tag",  //Tên bảng trung gian
        joinColumns = @JoinColumn(name = "post_id"), //Tên cột trong bảng trung gian, mà bảng Post góp foreign key ''
        inverseJoinColumns = @JoinColumn(name = "tag_id") //Tên cột đầu bên kia, bảng Tag
    )
    private Set<Tag> tags = new HashSet<>(); //Cấu trúc dữ liệu Set trong Post Entity để lưu trữ Tag
    
    public void addTag(Tag tag) {  //Thêm Tag vào Post
        tags.add(tag);
        tag.getPosts().add(this);        
    }
 
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }
}
```

Hãy xem [Tag.java](src/main/java/vn/techmaster/blog/model/Tag.java)
```java
@Entity(name = "tag")
@Table(name = "tag")
public class Tag {
  //Lược bỏ phần đầu
  @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
  List<Post> posts = new ArrayList<>();  //Danh sách các Post được tag này đánh dấu
}
```

Chúng ta bổ xung thêm file [tag.sql](src/main/resources/tag.sql) để nạp sẵn dữ liệu cho bảng Tag.

Ở đây chúng ta thấy trong ```class Post``` có 2 phương thức ```addTag(Tag tag)``` và ```removeTag(Tag tag)``` mà tại sao không có chiều ngược lại ở ```class Tag``` kiểu như ```addPost(Post post)``` và ```removePost(Post post)``?
Trả lời: 
- Có thể bổ xung 2 method tương ứng ở ```class Tag``` nhưng việc đó khiến cho code rối.
- Chủ thể quan trọng ở đây là Post. Tag là thông tin bổ trợ, do đó chúng ta tạo 2 phương thức tác động lên Post

## PostRequest.java
Xem [PostRequest.java](src/main/java/vn/techmaster/blog/controller/request/PostRequest.java)

Chúng ta đã có định nghĩa Entity [Post.java](src/main/java/vn/techmaster/blog/model/Post.java) rồi, tại sao lại cần [PostRequest.java](src/main/java/vn/techmaster/blog/controller/request/PostRequest.java)?

Trả lời:
- ```Post``` làm nhiệm vụ lưu thông tin xuống CSDL qua JPA. Nó có cả các phương thức thêm comment, xoá comment, thêm tag, bỏ tag
- ```PostRequest``` có thể hiểu là một Plain Old Java Object dùng để tạo khuôn mẫu cho Form trong method GET và để bóc tách dữ liệu các trường trong Form gửi lên trong method POST.
- Trong ```PostRequest``` các thuộc tính còn cả định nghĩa Validation (```@NotNull```, ```@Size```). Kiểm tra dữ liệu gửi lên (validation) sẽ nói ở tutorial sau. Nó cũng chỉ chứa những cấu trúc dữ liệu nào cần thiết: tags thì có, nhưng comment thì không !

```java
public class PostRequest {
  private Long id;
  @NotNull
	@Size(min=10, max=300, message="title must be with 10 and 300")
  private String title;

  @NotNull
	@Size(min=20, max=5000, message="content must be with 20 and 5000")
  private String content;

  private Long user_id;
  private Set<Tag> tags;
}
```

## PostController.java
Xem [PostController.java](src/main/java/vn/techmaster/blog/controller/PostController.java)

#### @GetMapping("/posts")  public String getAllPosts(Model model, HttpServletRequest request)

Liệt kê tất cả các Post của một User
![](images/all_posts.jpg)
```java
@GetMapping("/posts")  //Liệt kê các post của một blogger cụ thể
public String getAllPosts(Model model, HttpServletRequest request) { 
  UserInfo user = authenService.getLoginedUser(request);
  if (user != null) {  //Kiểm tra xem người dùng có đăng nhập không?
    model.addAttribute("user", user); //thêm thông tin người dùng để hiển thị tên
    List<Post> posts = postService.getAllPostsByUserID(user.getId()); //Lấy tất cả các Post của user này
    model.addAttribute("posts", posts); //trả về danh sách Post cho Thymeleaf
    return Route.ALLPOSTS;
  } else { //không đăng nhập, quay về trang chủ
    return Route.REDIRECT_HOME;
  } 
}
```
#### @GetMapping("/post") public String createEditPostForm(Model model, HttpServletRequest request)
![](images/new_post.jpg)
```java
@GetMapping("/post")  //Show form để tạo mới Post
public String createEditPostForm(Model model, HttpServletRequest request) {
  UserInfo user = authenService.getLoginedUser(request);
  if (user != null) {
    PostRequest postReqest = new PostRequest();
    postReqest.setUser_id(user.getId());
    model.addAttribute("post", postReqest);
    model.addAttribute("user", user);
    List<Tag> tags = postService.getAllTags();  //Lấy tất cả tag để tạo checkbox
    model.addAttribute("tags", tags);
    return Route.POST;
  } else {
    return Route.REDIRECT_HOME;
  }
}
```



