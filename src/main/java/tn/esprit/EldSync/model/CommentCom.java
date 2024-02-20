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
public class CommentCom  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String text;
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "complaintId")
    private Complaint complaint;



}
