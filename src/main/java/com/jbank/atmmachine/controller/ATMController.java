package com.jbank.atmmachine.controller;

import java.util.List;

import com.jbank.atmmachine.model.Account;
import com.jbank.atmmachine.model.Denomination;
import com.jbank.atmmachine.service.ATMService;
import com.jbank.atmmachine.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ATMController {

    @Autowired
    private ATMService atmService;
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/atm/acct")
    public String accountDetails(){
        return "AccountPage";
    }

    @PostMapping("/atm/acct")
    public String accountDetails(Account account, Model model){
        Account requestedAcct = accountService.getAccountDetails(account);

        if(accountService.isValidPin(account))
            model.addAttribute("account", requestedAcct);
        else 
            model.addAttribute("errorMessage", "Invalid Pin, Please enter correct PIN.");

        return "AccountPage";
    }

    @GetMapping("/atm")
    public String withdrawMoney() {
        return "ATMPage";
    }

    @PostMapping("/atm")
    public String withdrawMoney(@RequestParam int amount, Account account, Model model) {

        Account requestedAcct = accountService.getAccountDetails(account);
        int acctBalance = requestedAcct.getBalance();
        int overDraft = requestedAcct.getOverDraft();
        
        if(requestedAcct.getPin() == account.getPin()){
            
            if(acctBalance+overDraft < amount){
                model.addAttribute("errorMessage", "Not enough balance in your Account.");
                return "AccountPage";
            }

            List<Denomination> withdraw = atmService.withdraw(requestedAcct, amount);
            if(withdraw == null)
                model.addAttribute("errorMessage", "Not enough balance in the ATM");
            else if(withdraw.size() == 0)
                model.addAttribute("errorMessage", "Invalid amount, ATM can dispense amount multiple of - 50, 20, 10 and 5 denomination notes.");
            else{
                model.addAttribute("denominations", withdraw);
            }
        } else
            model.addAttribute("errorMessage", "Invalid Pin, Please enter correct PIN.");
        
        return "ATMPage";
    }
}
