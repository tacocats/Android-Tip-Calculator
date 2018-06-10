package com.tacocats.tipcalculator;

import android.util.Log;

import com.tacocats.tipcalculator.math.TipCalculator;

public class testCalculator {

    static String tag = "TESTS";

   public static void testIt() {
        TipCalculator calculator = new TipCalculator();
        calculator.setBill(100.00f); // $100 bill
        calculator.setPeople(1); // 1 person
        calculator.setTip(50); // 50% tip

        PriceStructure p = calculator.calculateTip();
        float tipAmount = p.getTipAmount();
        float total = p.getTotalAmount();
        float eachPay = p.getEachPay();

       Log.e(tag, String.valueOf(tipAmount));
       Log.e(tag, String.valueOf(total));
       Log.e(tag, String.valueOf(eachPay));
   }
}
