package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class CommentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String content;
    private Date creationDate;
    private Date lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

}
