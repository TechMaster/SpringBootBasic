package vn.techmaster.demojpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import vn.techmaster.demojpa.model.blog.Post;
import vn.techmaster.demojpa.model.blog.Tag;

@DataJpaTest

public class PostTagTests {
  @Autowired
  private TestEntityManager tem;

  private Tag tagTheThao, tagVanHoc, tagGiaiTri, tagChinhTri, tagDienAnh;

  @BeforeEach  
  public void initializeTags(){
    /* Code khởi tạo dữ liệu buộc phải để ở trong method @BeforeEach. Không thể để trong @BeforeAll vì lúc đó TransactionManager
    còn chưa được khởi tạo, do đó không thể insert dữ liệu !
     */
    tagTheThao = new Tag("thể thao");
    tagVanHoc = new Tag("văn học");
    tagGiaiTri = new Tag("giải trí");
    tagChinhTri = new Tag("chính trị");
    tagDienAnh = new Tag("điện ảnh");
    
    tem.persist(tagTheThao);
    tem.persist(tagVanHoc);
    tem.persist(tagGiaiTri);
    tem.persist(tagChinhTri);
    tem.persist(tagDienAnh);
    tem.flush();
  }
  

  @AfterEach
  public void afterEachTest() {
    // Sau mỗi bài test thì xoá dữ liệu đi cho sạch, để bài test tiếp theo chạy
    tem.clear();
  }

  @Test
  public void insertPost() {
    String title = "MU thắng Aston Villa 2-1";
    Post post1 = new Post(title);
    long post1_id = tem.persistAndGetId(post1, Long.class);
    tem.flush();
    Post retrievePost1 = tem.find(Post.class, post1_id);    
    assertThat(retrievePost1).isEqualTo(post1);    
  }

  @Test
  public void postShouldHasAssociatedTags() {
    Post post1 = new Post("Ronaldo đóng phím 45+");
    post1.addTag(tagTheThao);
    post1.addTag(tagGiaiTri);
    tem.persist(post1);
    tem.flush();
    
    assertThat(post1.getTags())
    .hasSize(2) //Có đúng 2 phần tử
    .extracting("name") //Lấy thuộc tính name
    .contains("thể thao", "giải trí"); //Xem danh sách thuộc tính name có chứa ...

/*
    
*/
  }

  @Test
  public void tagShouldHaveAssociatedPosts() {
    Post post1 = new Post("Fernando Bruno ghi 15 trên 16 lần đá phạt đền");
    post1.addTag(tagTheThao);

    Post post2 = new Post("Trump đi đánh golf, ngâm thơ trong lúc kiểm phiếu");
    post2.addTag(tagTheThao);
    post2.addTag(tagChinhTri);
    post2.addTag(tagVanHoc);
    
    tem.persist(post1);
    tem.persist(post2);

    Tag retrieve_tagTheThao = tem.find(Tag.class, "thể thao");
    assertThat(retrieve_tagTheThao.getPosts())
    .hasSize(2);


  }

  @Test
  public void postRemovesTag() {
    Post post3 = new Post("HLV Park Hang Seo sẽ đóng Táo Quân 2021");
    post3.addTag(tagTheThao);
    post3.addTag(tagChinhTri);
    post3.addTag(tagVanHoc);

    Post post4 = new Post("Vietnam dành suất tham dự World Cup 2024");
    post4.addTag(tagTheThao);
    
    tem.flush();
    
    assertThat(tagTheThao.getPosts()).hasSize(2);
    
    post3.removeTag(tagTheThao);  //Post bớt đi một tag    

    assertThat(post3.getTags()).hasSize(2);
    
    assertThat(tagTheThao.getPosts()).hasSize(1); //Chỉ cần duy nhất post4 gắn tagTheThao
  }

}
