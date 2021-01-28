package vn.techmaster.blog.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tag")
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id private long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    List<Post> posts = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
