package com.aakash.Projexio.service;

import com.aakash.Projexio.model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content)throws Exception;

    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
