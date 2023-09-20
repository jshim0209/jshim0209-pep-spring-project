package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidInputException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.MessageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public void validateMessageInput(String message_text) {
        if (message_text.isBlank() || message_text.length() >= 255) {
            throw new InvalidInputException("Invalid input provided!");
        }
    }

    public void validateOwner(int accountId) {
        if (accountRepository.findById(accountId).isEmpty()) {
            throw new InvalidInputException("User doesn't exist!");
        }
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        Message message;
        if (optionalMessage.isPresent()) {
            message = optionalMessage.get();
        } else {
            return null;
        }
        return message;
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageRepository.findAllByPostedBy(accountId);
    }

    @Override
    public Message createMessage(Message message) {
        validateMessageInput(message.getMessage_text());
        validateOwner(message.getPosted_by());
        Message messageCreated = messageRepository.saveAndFlush(message);
        return messageCreated;
    }

    @Override
    public int deleteMessage(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            messageRepository.delete(optionalMessage.get());
        } else {
            return 0;
        }
        return 1;
    }

    @Override
    public int updateMessage(int messageId, String messageText) {
        validateMessageInput(messageText);
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            throw new InvalidInputException("Message doesn't exist!");
        }
        Message messageToBeUpdated = optionalMessage.get();
        messageToBeUpdated.setMessage_text(messageText);
        messageRepository.saveAndFlush(messageToBeUpdated);
        return 1;
    }
}
