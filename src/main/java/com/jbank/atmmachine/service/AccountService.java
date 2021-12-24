package com.jbank.atmmachine.service;

import com.jbank.atmmachine.model.Account;
import com.jbank.atmmachine.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account getAccountDetails(Account acct){
        return repository.findByAccountNumber(acct.getAccountNumber());
    }

    /**
     * Update account balance after withdrawal.
     * @param acct
     * @param withdrawnAmount
     */
    public void updateAccountBalance(Account acct, int withdrawnAmount){
        int currentBalance = acct.getBalance();

        if(withdrawnAmount > currentBalance ){
            acct.setBalance(0);
            int overDraft = acct.getOverDraft() - (withdrawnAmount - currentBalance);
            acct.setOverDraft(overDraft);
        } 
        else 
            acct.setBalance(currentBalance - withdrawnAmount);
        repository.save(acct);
    }

    public boolean isValidPin(Account account) {
        Account acctDetails = getAccountDetails(account);
        return acctDetails.getPin() == account.getPin();
    }
    
}
