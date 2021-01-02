package vn.techmaster.demojpa.model.blog;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.collect.Sets;

import javax.persistence.JoinColumn;


import lombok.Data;

@Entity(name = "Post")
@Table(name = "post")
@Data
public class Post { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String title;
     
    @Embedded
    private Audit audit = new Audit();
 
    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = Sets.newHashSet(); //Dùng Guava Set

    public Post(String title) {
        this.title = title;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getPosts().add(this);
        
    }
 
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }
 

}
