package com.girlcoder.technicaltest.controller;

import com.girlcoder.technicaltest.model.Account;
import com.girlcoder.technicaltest.model.Transaction;
import com.girlcoder.technicaltest.model.User;
import com.girlcoder.technicaltest.model.dto.CreateUserDto;
import com.girlcoder.technicaltest.model.dto.ResponseDto;
import com.girlcoder.technicaltest.model.dto.TransactionDto;
import com.girlcoder.technicaltest.service.AccountService;
import com.girlcoder.technicaltest.service.TransactionService;
import com.girlcoder.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.security.Principal;

@RestController
@RequestMapping("/test")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/user")
    @PermitAll()
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto userDto){
        var response = new ResponseDto<User>();
        try{
            var user = userService.createUser(userDto);
            if (user == null){
                response.setResponseCode(400);
                response.setResponseMessage("Bad Request! Username already Exists");
                response.setResponseData(null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.setResponseCode(200);
            response.setResponseMessage("User Created Successfully");
            response.setResponseData(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception ex){
            response.setResponseCode(400);
            response.setResponseMessage("Bad Request " + ex.getMessage());
            response.setResponseData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransactionDto transactionDto){
        var response = new ResponseDto<Transaction>();
        try {
            var transaction = transactionService.createTransaction(transactionDto);
            response.setResponseCode(200);
            response.setResponseMessage("Transaction Initiated Successfully");
            response.setResponseData(transaction);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.setResponseCode(400);
            response.setResponseMessage("Bad Request " + e.getMessage());
            response.setResponseData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/fundAccount")
    public ResponseEntity<?> fundAccount(@RequestParam(name = "amount") double amount, Principal principal){
        var response = new ResponseDto<Account>();
        try {
            var user = userService.findByUsername(principal.getName());
            accountService.fundAccount(user.getUserAccount(),amount);
            response.setResponseCode(200);
            response.setResponseMessage("Account Funded Successfully");
            response.setResponseData(user.getUserAccount());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.setResponseCode(400);
            response.setResponseMessage("Bad Request " + e.getMessage());
            response.setResponseData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
