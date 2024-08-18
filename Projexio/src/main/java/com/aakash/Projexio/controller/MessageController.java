package com.aakash.Projexio.controller;

import com.aakash.Projexio.model.Chat;
import com.aakash.Projexio.model.Message;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.request.CreatedMessageRequest;
import com.aakash.Projexio.service.MessageService;
import com.aakash.Projexio.service.ProjectService;
import com.aakash.Projexio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreatedMessageRequest request)
    throws Exception {

        User user = userService.findUserById(request.getSenderId());

        Chat chats = projectService.getProjectById(request.getProjectId()).getChat();

        if (chats == null) throw new Exception("Chat not found ");

        Message sendMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId)
        throws Exception {
        List<Message>messages=messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }




}
