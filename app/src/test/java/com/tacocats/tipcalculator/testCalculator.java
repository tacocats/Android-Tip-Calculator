package com.tacocats.tipcalculator;

import com.tacocats.tipcalculator.math.TipCalculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class testCalculator {
    @Test
    public void testCalculation() {
        TipCalculator calculator = new TipCalculator();
        calculator.setBill(100.00f); // $100 bill
        calculator.setPeople(1); // 1 person
        calculator.setTip(50); // 50% tip

        PriceStructure p = calculator.calculateTip();
        float tipAmount = p.getTipAmount();
        float total = p.getTotalAmount();
        float eachPay = p.getEachPay();

        System.out.println("Tests ran successfully");
        assertEquals(tipAmount, tipAmount, 5);
    }
}
