package vn.techmaster.demojpa.model.blog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "Tag")
@Table(name = "tag")
@Data
public class Tag {
 
    @Id
    private String name;
 
    @Embedded
    private Audit audit = new Audit();

    @ManyToMany(mappedBy = "tags")
    List<Post> posts = new ArrayList<>();
    //Set<Post> posts = Sets.newHashSet(); Lỗi stack overflow nếu cả Post và Tag dùng HashSet hoặc Guava Set

    public Tag(String name) {
        this.name = name;
    }
}
