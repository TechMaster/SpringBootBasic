package vn.techmaster.demojpa;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import com.google.common.collect.Sets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import vn.techmaster.demojpa.model.Post;
import vn.techmaster.demojpa.model.Tag;

@DataJpaTest
public class PostTagTests {
  @Autowired
  private TestEntityManager tem;

  @Test
  public void InsertPostTag() {
    Tag tagTheThao = new Tag("thể thao");
    Tag tagVanHoc = new Tag("văn học");
    Tag tagGiaiTri = new Tag("giải trí");
    Tag tagChinhTri = new Tag("chính trị");
    tem.persist(tagTheThao);
    tem.persist(tagVanHoc);
    tem.persist(tagGiaiTri);
    tem.persist(tagChinhTri);
    
    Post post1 = new Post("Ronaldo đóng phím 45+");
    //Set<Tag> tags1 = Sets.newHashSet(tagTheThao, tagGiaiTri);
    post1.addTag(tagTheThao);
    post1.addTag(tagGiaiTri);
    tem.persist(post1);    
    
    Post post2 = new Post("Trump đi đánh golf, ngâm thơ trong lúc kiểm phiếu");
    post2.addTag(tagTheThao);
    post2.addTag(tagChinhTri);
    post2.addTag(tagVanHoc);
    tem.persist(post2);

    tem.flush();
    //-----
    Post retrieve_post1 = tem.find(Post.class, 1L);
    assertThat(retrieve_post1.getTags())
    .hasSize(2) //Có đúng 2 phần tử
    .extracting("name") //Lấy thuộc tính name
    .contains("thể thao", "giải trí"); //Xem danh sách thuộc tính name có chứa ...


    Tag retrieve_tagTheThao = tem.find(Tag.class, "thể thao");
    assertThat(retrieve_tagTheThao.getPosts())
    .hasSize(2);

    Post retrieve_post2 = tem.find(Post.class, 2L);
    retrieve_post2.removeTag(retrieve_tagTheThao);
    tem.flush();

    assertThat(retrieve_tagTheThao.getPosts())
    .hasSize(1);
  }

}
