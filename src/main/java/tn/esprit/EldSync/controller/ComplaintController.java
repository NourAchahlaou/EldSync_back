package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.CommentCom;
import tn.esprit.EldSync.model.Complaint;
import tn.esprit.EldSync.service.IComplaintService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private IComplaintService commentService; // Optional, depending on functionality

    @Autowired
    private IComplaintService complaintCategoryService; // Optional, depending on functionality

    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    @GetMapping("/{id}")
    public Optional<Complaint> getComplaintById(@PathVariable int ComplaintId) {
        return complaintService.getComplaintById(ComplaintId);
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable int ComplaintId, @RequestBody Complaint complaint) {
        complaint.setComplaintId(ComplaintId); // Avoid detached entity exception
        return complaintService.updateComplaint(complaint);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable int ComplaintId) {
        complaintService.deleteComplaint(ComplaintId);
    }

    @PostMapping("/{id}/comments")
    public CommentCom addCommentToComplaint(@PathVariable int ComplaintId, @RequestBody CommentCom comment) {
        // Ensure complaint with given ID exists and add comment
        return commentService.addCommentToComplaint(ComplaintId,  comment); // Assuming CommentService exists
    }}