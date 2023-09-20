package com.example.service;

import java.util.List;

import com.example.entity.Message;

public interface MessageService {
    List<Message> getAllMessages();

    Message getMessageById(int messageId);

    Message createMessage(Message message);

    int deleteMessage(int messageId);

    int updateMessage(int messageId, String messageText);

    List<Message> getAllMessagesByAccountId(int accountId);
}
