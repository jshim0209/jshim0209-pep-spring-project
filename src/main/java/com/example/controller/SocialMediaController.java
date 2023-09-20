package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> returnedMessages = messageService.getAllMessages();
        return new ResponseEntity<List<Message>>(returnedMessages, HttpStatus.OK);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(name = "message_id") int messageId) {
        Message returnedMessage = messageService.getMessageById(messageId);
        if (returnedMessage != null) {
            return new ResponseEntity<Message>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> returnedAccounts = accountService.getAllAccounts();
        return new ResponseEntity<List<Account>>(returnedAccounts, HttpStatus.OK);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable(name = "account_id") int accountId) {
        List<Message> returnedMessages = messageService.getAllMessagesByAccountId(accountId);
        return new ResponseEntity<List<Message>>(returnedMessages, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message returnedMessage = messageService.createMessage(message);
        return new ResponseEntity<Message>(returnedMessage, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account returnedAccount = accountService.registerAccount(account);
        return new ResponseEntity<Account>(returnedAccount, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody LoginDto credentials) {
        Account returnedAccount = accountService.login(credentials.getUsername(), credentials.getPassword());
        return new ResponseEntity<Account>(returnedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable(name = "message_id") int messageId) {
        Integer numOfDeletedRow = messageService.deleteMessage(messageId);
        return new ResponseEntity<>(numOfDeletedRow, HttpStatus.OK);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable(name = "message_id") int messageId, 
                                @RequestBody Message messageToUpdate) {
        Integer numOfUpdatedRow = messageService.updateMessage(messageId, messageToUpdate.getMessage_text());
        return new ResponseEntity<Integer>(numOfUpdatedRow, HttpStatus.OK);
    }
}
