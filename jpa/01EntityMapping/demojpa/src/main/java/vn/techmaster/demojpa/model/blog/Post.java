package vn.techmaster.demojpa.model.blog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Post")
@Table(name = "post")
@Data
@NoArgsConstructor
public class Post { 
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String title;
    @Version
    private int version;
     
    @Embedded
    private Audit audit = new Audit();

    //----- Chỗ này chuyên viết các constructor
    public Post(String title) {
        this.title = title;
    }
 
    //Quan hệ một nhiều:
    //Một post có nhiều comment
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "post_id")
    private List<Comment> comments = new ArrayList<>();
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    //------------
    //Quan hệ nhiều nhiều:
    //- Một post được phân loại bởi 1 hay nhiều tag. 
    //- Ngược lại mỗi tag dùng để phân loại nhiều post

    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
    
    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getPosts().add(this);        
    }
 
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }    

}
