package tn.esprit.EldSync.repositoy;

import com.example.nouranmanagment.entities.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPostRepository extends JpaRepository<CommentPost, Integer> {
}