package com.jbank.atmmachine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ATM {

    private final List<Denomination> denominations = Arrays.asList(
        new Denomination(50,10),
        new Denomination(20,30),
        new Denomination(10,30),
        new Denomination(5,20)
);

public List<Denomination> dispense(int amt) {

    if(amt > getCurrentBalance()){
        return null;
    }

    List<Denomination> withdrawnDenominations = new ArrayList<>();
    int remainingAmt = amt, withdrawnAmt = 0;
        for (Denomination denom: denominations) {
            if(denom.getQuantity()>0){
                int requiredQuantity = remainingAmt / denom.getNote();
            int availableQuantity = requiredQuantity <=denom.getQuantity() ? requiredQuantity : denom.getQuantity();

            Denomination withdraw = new Denomination();
            withdraw.setNote(denom.getNote());
            withdraw.setQuantity(availableQuantity);
            withdrawnDenominations.add(withdraw);
            withdrawnAmt += withdraw.value();

            if( withdrawnAmt != amt )
                remainingAmt = amt - withdrawnAmt;
            else {
                updateBalance(withdrawnDenominations);
                return withdrawnDenominations;
            }
            }
            
        }
        if (withdrawnAmt != amt) {
            // Invalid amount, cannot dispense amout with the available denominations.
            withdrawnDenominations = new ArrayList<>();
        }
    return  withdrawnDenominations;
}

private void updateBalance(List<Denomination> withdrawnDenominations) {

    for ( Denomination withdrawn: withdrawnDenominations ) {

        int index = denominations.indexOf(withdrawn);
        Denomination denomination = denominations.get(index);
        denomination.setQuantity(denomination.getQuantity() - withdrawn.getQuantity());
    }
}

public int getCurrentBalance() {
    int balance = 0;
    for (Denomination denom: denominations) {
        balance += denom.value();
    }
    return balance;
}
    
}
