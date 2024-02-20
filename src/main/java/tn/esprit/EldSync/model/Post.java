package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    private String title;
    private String content;
    private Date creationDate;
    private Date lastUpdatedDate;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentPost> comments = new ArrayList<>();

}
