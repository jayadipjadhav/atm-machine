package com.jbank.atmmachine;

import com.jbank.atmmachine.model.Account;
import com.jbank.atmmachine.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmMachineApplication implements CommandLineRunner{

	@Autowired
	AccountRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(AtmMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Account(123456789, 1234, 800, 200));
		repository.save(new Account(987654321, 4321, 1230, 150));

			for (Account account : repository.findAll()) {
				System.out.println(account);
			}
		
	}
}
