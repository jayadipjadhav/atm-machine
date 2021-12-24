package com.jbank.atmmachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import com.jbank.atmmachine.model.ATM;
import com.jbank.atmmachine.model.Denomination;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ATMTest {

    private ATM atm;

    @Before
    public void setUp() throws Exception {
        atm = new ATM();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * For the sake of simplicity, null is returned when more funds are requested than avaible in ATM.
     * Empty denomination list is returned when correct denomination notes are not available for the amount.
     */
    @Test
    public void doNotDispenseMoreThanBalance(){
        List<Denomination> withdraw = atm.dispense(1600);
        
        assertNull("ATM should not dispense more funds than it holds", withdraw);
        assertEquals( "ATM balance should not be updated when unsucceful withdrawal", atm.getCurrentBalance(), 1500);
    }

    /**
     * For the sake of simplicity, null is returned when more funds are requested than avaible in ATM.
     * Empty denomination list is returned when correct denomination notes are not available for the amount.
     */
    @Test
    public void doNoDispenseIfDenominationsUnavailable(){
        List<Denomination> withdraw = atm.dispense(574);
        assertEquals("ATM should not dispense funds if correct denomination notes are not available ", withdraw.size(), 0);
    }

    @Test
    public void verifyATMBalanceAfterWithdrawal() throws Exception {
        int amountToWithdraw = 500;
        atm.dispense(amountToWithdraw);
        assertEquals( "ATM balance should be updated after succeful withdrawal", atm.getCurrentBalance(), 1500 - amountToWithdraw);
    }

    @Test
    public void dispenseExactAmount(){
        int amountToWithdraw = 590;
        List<Denomination> withdraw = atm.dispense(amountToWithdraw);
        int amt = 0;
        for (Denomination denomination : withdraw) {
            amt+=denomination.value();
        }
        assertEquals( "Exact amount should be dispensed after succeful withdrawal", amt, amountToWithdraw);

    }

    /**
     * Denominations should be 
     *  Denomination{note=50, quantity=10}
     * Instead of 
     *  Denomination{note=20, quantity=20}
     *  Denomination{note=10, quantity=10}
     */
    @Test
    public void dispenseMinimumNotes(){
        int amountToWithdraw = 500;
        List<Denomination> withdraw = atm.dispense(amountToWithdraw);
        assertEquals( "Only €50 notes should be dispensed.", withdraw.get(0).getNote(), 50);
        assertEquals( "10 €50 notes should be dispensed.", withdraw.get(0).getQuantity(), 10);
    }


    /**
     * Denominatins should be
        Denomination{note=50, quantity=10}
        Denomination{note=20, quantity=4}
    Insted of 
        Denomination{note=50, quantity=11}
        Denomination{note=20, quantity=1}
        Denomination{note=10, quantity=1}
     * 
     */
    @Test
    public void dispenseCorrectDenominationsWithMaxDenominationNotes(){
        int amountToWithdraw = 580;
        List<Denomination> withdrawnDenominations = atm.dispense(amountToWithdraw);
        assertEquals("All 10 €50 notes should be dispensed", withdrawnDenominations.get(0).getQuantity(), 10);
        assertEquals("Only 4 €20 notes should be dispensed", withdrawnDenominations.get(1).getQuantity(), 4);
    }


}