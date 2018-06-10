package com.tacocats.tipcalculator.math;

import com.tacocats.tipcalculator.PriceStructure;
import com.tacocats.tipcalculator.math.Calculator;

public class TipCalculator implements Calculator {

    private float bill = -1; // Total cost not including tip
    private int people = -1; // How many people are paying
    private int tip = -1; // Percentage to tip

    @Override
    public PriceStructure calculateTip() {
        // Check bill, people, and tip are set
        if (bill == -1 || people == -1 || tip == -1) {
            return null;
        }
        else {
            // Create an instance of price structure
            PriceStructure p = new PriceStructure();

            // Calculate total amount
            p.setTipAmount(bill*((float) tip/100));
            p.setTotalAmount(bill + p.getTipAmount());
            p.setEachPay(p.getTotalAmount()/people);

            return p;
        }
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
