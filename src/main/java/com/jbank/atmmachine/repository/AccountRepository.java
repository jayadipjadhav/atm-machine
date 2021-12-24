package com.jbank.atmmachine.repository;

import com.jbank.atmmachine.model.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    Account findByAccountNumber(int accountNumber);

    Account findByAccountNumberAndPin(int accountNumber, int pin);
}

