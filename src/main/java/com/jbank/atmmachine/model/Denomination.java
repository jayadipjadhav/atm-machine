package com.jbank.atmmachine.model;

import java.util.Objects;

public class Denomination {
    
    private int note;
    private int quantity;

    public Denomination() {
    }

    public Denomination(int note, int quantity) {
        this.note = note;
        this.quantity = quantity;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int value() {
        return this.note * this.quantity;
    }

    @Override
    public String toString() {
        return "Denomination{" +
                "note=" + note +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Denomination that = (Denomination) o;
        return note == that.note;
    }

    @Override
    public int hashCode() {
        return Objects.hash(note);
    }
}