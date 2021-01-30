# Pagination và ApplicationRunner

Trong bài này tôi sẽ hướng các bạn phân trang một danh sách và cách chạy code ngay sau khi Spring Boot khởi động. Tình hình huống như sau:

Tôi muốn tạo ra 2000 bản ghi Post tự động để tạo tình huống phải phân trang. Thời điểm tốt nhất để tự động tạo ra 2000 bản ghi Post đó là ngay sau khi Spring Boot application khởi động xong.

**Tham khảo bài viết này**
1. [Spring Boot: ApplicationRunner and CommandLineRunner](https://dzone.com/articles/spring-boot-applicationrunner-and-commandlinerunne)
2. [When and why do we need ApplicationRunner and Runner interface?](https://stackoverflow.com/questions/59328583/when-and-why-do-we-need-applicationrunner-and-runner-interface)


## ApplicationRunner hay là CommandLineRunner
Có 2 interface ```ApplicationRunner``` và ```CommandLineRunner``` để bổ xung logic ngay khi Spring Boot khởi động hoàn tất. Khác biệt giữa hai interface này là:
- ```ApplicationRunner``` run() will get execute, just after applicationcontext is created and before spring boot application startup. ApplicationRunner takes ApplicationArgument which has convenient methods like ```getOptionNames()```, ```getOptionValues()``` and ```getSourceArgs()```.

- ```CommandLineRunner``` run() will get execute, just after applicationcontext is created and before spring boot application starts up.

It accepts the argument, which are passed at time of server startup.

Ví dụ dùng interface ```CommandLineRunner```
```java
@Component
public class MyRunner implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

  @Autowired  private UserRepository userRepo;
  @Autowired  private PostRepository postRepo;

  @Override
  public void run(String... args) throws Exception {  //Chú ý tham số String... args
    User bob = userRepo.findByEmail("bob@gmail.com").get();
    User alice = userRepo.findByEmail("alice@gmail.com").get();

    Post post = new Post("Hà nội có tuyết rơi", "Hôm nay trời quá lạnh. Tuyết rời đầy");
    bob.addPost(post);

    Comment comment = new Comment("Tôi cực thích lạnh");
    comment.setUser(alice);
    post.addComment(comment);
    userRepo.flush();
  }
}
```
Ví dụ dùng interface ```ApplicationRunner```
```java
public class SpringBootApplicationRunner implements ApplicationRunner {
  private static Logger LOG = LoggerFactory.getLogger(SpringBootApplicationRunner.class);
  @Override
  public void run(ApplicationArguments args) {
    LOG.info("EXECUTING : Run method of Application Runner");
    final List nonOptionArgs = args.getNonOptionArgs();
    final String[] sourceArgs = args.getSourceArgs();
    final Set  optionNames = args.getOptionNames();

    nonOptionArgs.forEach(nonOption -> LOG.info("## Non Option Args : "+nonOption));
    optionNames.forEach(option -> LOG.info("## Option Names    : "+option));
    Arrays.stream(sourceArgs).forEach(srcArgs -> LOG.info("## Source Args     : "+srcArgs));
    LOG.info("## Option Value of --optionalArg1 : " + args.getOptionValues("optionalArg1"));
    LOG.info("## Option Value of --optionalArg2 : " + args.getOptionValues("optionalArg2"));
  }
}
```
## Tự sinh 2000 bản ghi Post

Chúng ta cần bổ xung thêm một depedency vào [pom.xml](pom.xml)
```xml
<dependency>
	<groupId>com.thedeanda</groupId>
	<artifactId>lorem</artifactId>
	<version>2.1</version>
</dependency>
```




