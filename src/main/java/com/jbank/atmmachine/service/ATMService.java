package com.jbank.atmmachine.service;

import java.util.List;

import com.jbank.atmmachine.model.ATM;
import com.jbank.atmmachine.model.Account;
import com.jbank.atmmachine.model.Denomination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    @Autowired
    private AccountService accountService;

    private ATM atm = new ATM();

    public List<Denomination> withdraw(Account account, int withdrawnAmount) {

        List<Denomination> denominations = atm.dispense(withdrawnAmount);
            
        if(denominations !=null && denominations.size() >= 1) {
            // found denominations, money dispensed, now update account balance
            accountService.updateAccountBalance(account, withdrawnAmount);
        }
        return denominations;
    }

    public int getATMBalance(){
        return atm.getCurrentBalance();
    }

    public void deposit(Account account, int depositAmount){

    }

    public void changePin(Account account, int pin){

    }

    
}

