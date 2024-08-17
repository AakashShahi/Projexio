package com.aakash.Projexio.service;

import com.aakash.Projexio.model.Issue;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId,Long userId) throws Exception;

   Issue addUserToIssue(Long issueId,Long userid) throws Exception;

   Issue updateStatus(Long issueId,String status) throws Exception;

}
