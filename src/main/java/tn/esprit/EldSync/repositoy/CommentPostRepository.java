package tn.esprit.EldSync.repositoy;

import tn.esprit.EldSync.model.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPostRepository extends JpaRepository<CommentPost, Integer> {
}