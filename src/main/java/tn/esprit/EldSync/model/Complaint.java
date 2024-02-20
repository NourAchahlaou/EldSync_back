package tn.esprit.EldSync.model;

import jakarta.persistence.*;
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
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;



    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ComplaintCategory category;

    private int status; // You might want to use an enum for status
    private String description;
    private Date timestamp;


    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
    private List<CommentCom> comments = new ArrayList<>();}
