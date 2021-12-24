package com.jbank.atmmachine;

import static org.junit.Assert.assertEquals;

import com.jbank.atmmachine.model.Account;
import com.jbank.atmmachine.repository.AccountRepository;
import com.jbank.atmmachine.service.ATMService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private ATMService atmService;

    @Test
    public void verifyAccountDetails(){
        Account fetchedAccount = repository.findByAccountNumber(123456789);

        assertEquals("Fetched account should match to that of requested account", fetchedAccount.getAccountNumber(), 123456789);
        assertEquals("Fetched account's PIN should match to that of requested account", fetchedAccount.getPin(), 1234);
    }

    /**
     * When withdrawl amount is less than the current account balance
     */
    @Test
    public void accountBalanceAfterWithdrawl() {
        Account account = repository.findByAccountNumber(123456789);
        atmService.withdraw(account, 500);
        int acctBalance = repository.findByAccountNumber(123456789).getBalance();
        assertEquals("Account balance should be updated correctly after withdrawl", 300 ,  acctBalance);
        assertEquals("Account overdraft should be as it is after withdrawl when withdrawl amount < account balance", 300 ,  acctBalance);

    }

    /**
     * When withdrawl amount is more than the current account balance
     */
    @Test
    public void accountBalanceAndOverdraftAfterWithdrawl() {
        Account account = repository.findByAccountNumber(123456789);
        atmService.withdraw(account, 400);
        int acctBalance = repository.findByAccountNumber(123456789).getBalance();
        int overDraft = repository.findByAccountNumber(123456789).getOverDraft();
        assertEquals("Account balance should be updated correctly after withdrawl", 0 ,  acctBalance);
        assertEquals("Account overdraft should be as it is after withdrawl when withdrawl amount < account balance", 100 ,  overDraft);
    }

}