package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginDto;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import lombok.RequiredArgsConstructor;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequiredArgsConstructor
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public Message getMessageById(@PathVariable(name = "message_id") int messageId) {
        return messageService.getMessageById(messageId);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // @GetMapping("/accounts/{account_id}/messages")
    // public List<Message> getAllMessagesByAccountId(@PathVariable(name = "account_id") int accountId) {
    //     return messageService.getAllMessagesByAccountId(accountId);
    // }

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PostMapping("/register")
    public Account registerAccount(@RequestBody Account account) {
        return accountService.registerAccount(account);
    }

    @PostMapping("/login")
    public Account loginAccount(@RequestBody LoginDto credentials) {
        return accountService.login(credentials.getUsername(), credentials.getPassword());
    }

    @DeleteMapping("/messages/{message_id}")
    public int deleteMessage(@PathVariable(name = "message_id") int messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PatchMapping("/messages/{message_id}")
    public int updateMessage(@PathVariable(name = "message_id") int messageId, 
                                @RequestBody Message messageToUpdate) {
        return messageService.updateMessage(messageId, messageToUpdate.getMessage_text());
    }



}
