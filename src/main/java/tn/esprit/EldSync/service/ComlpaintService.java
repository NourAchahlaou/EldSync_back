package tn.esprit.EldSync.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.CommentCom;
import tn.esprit.EldSync.model.Complaint;
import tn.esprit.EldSync.repositoy.CommentComRepository;
import tn.esprit.EldSync.repositoy.ComplaintCategoryRepository;
import tn.esprit.EldSync.repositoy.ComplaintRepository;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ComlpaintService implements IComplaintService{

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private CommentComRepository commentRepository;

    @Autowired
    private ComplaintCategoryRepository complaintCategoryRepository;



    @Override
    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }


    @Override
    public Optional<Complaint> getComplaintById(int ComplaintId) {
        return complaintRepository.findById(Math.toIntExact(ComplaintId));
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        Complaint existingComplaint = complaintRepository.findById(complaint.getComplaintId()).orElseThrow();

        return complaintRepository.save(existingComplaint);
    }

    @Override
    public void deleteComplaint(int ComplaintId) {
        complaintRepository.deleteById(Math.toIntExact(ComplaintId));

    }

    @Override
    public CommentCom addCommentToComplaint(int ComplaintId, CommentCom comment) {
        Complaint complaint = complaintRepository.findById(ComplaintId).get();



        comment.setComplaint(complaint);


        CommentCom savedComment = commentRepository.save(comment);



        return savedComment;
    }









}
