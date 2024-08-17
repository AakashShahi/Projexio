package com.aakash.Projexio.controller;

import com.aakash.Projexio.dto.IssueDTO;
import com.aakash.Projexio.model.Issue;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.request.IssueRequest;
import com.aakash.Projexio.response.AuthResponse;
import com.aakash.Projexio.response.MessageResponse;
import com.aakash.Projexio.service.IssueService;
import com.aakash.Projexio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

     @Autowired
    private IssueService issueService;

     @Autowired
    private UserService userService;

     @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
         return ResponseEntity.ok(issueService.getIssueById(issueId));
     }

     @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
         return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
     }

     @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,
                                                @RequestHeader("Authorization")String token)
             throws Exception {
         User tokenUser=userService.findUserProfileByJwt(token);
         User user=userService.findUserById(tokenUser.getId());

             Issue createdIssue=issueService.createIssue(issue,tokenUser);
             IssueDTO issueDTO=new IssueDTO();
             issueDTO.setDescription(createdIssue.getDescription());
             issueDTO.setDueDate(createdIssue.getDueDate());
             issueDTO.setId(createdIssue.getId());
             issueDTO.setPriority(createdIssue.getPriority());
             issueDTO.setProject(createdIssue.getProject());
             issueDTO.setProjectId(createdIssue.getProjectId());
             issueDTO.setStatus(createdIssue.getStatus());
             issueDTO.setTitle(createdIssue.getTitle());
             issueDTO.setTags(createdIssue.getTags());
             issueDTO.setAssignee(createdIssue.getAssignee());

             return ResponseEntity.ok(issueDTO);
     }

     @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId
     ,@RequestHeader("Authorization")String token) throws Exception {
         User user=userService.findUserProfileByJwt(token);
         issueService.deleteIssue(issueId, user.getId());

         MessageResponse res=new MessageResponse();
         res.setMessage("Successfully deleted issue");

         return ResponseEntity.ok(res);
     }

     @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId
     ,@PathVariable Long userId)throws Exception{
         Issue issue=issueService.addUserToIssue(issueId,userId);
         return ResponseEntity.ok(issue);
     }

     @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue>updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId
     )throws Exception{
         Issue issue=issueService.updateStatus(issueId,status);
         return ResponseEntity.ok(issue);
    }



}
