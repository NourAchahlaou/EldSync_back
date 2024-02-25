package tn.esprit.EldSync.service;

import tn.esprit.EldSync.model.CommentCom;
import tn.esprit.EldSync.model.Complaint;

import java.util.List;
import java.util.Optional;

public interface IComplaintService {
    public Complaint createComplaint(Complaint complaint);

    public Optional<Complaint> getComplaintById(int ComplaintId);

    public List<Complaint> getAllComplaints();

    public Complaint updateComplaint(Complaint complaint);

    public void deleteComplaint(int ComplaintId);

    public CommentCom addCommentToComplaint(int ComplaintId, CommentCom comment);
}
