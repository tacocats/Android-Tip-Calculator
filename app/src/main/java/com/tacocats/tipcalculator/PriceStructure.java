package com.tacocats.tipcalculator;

// Simple structure for the prices
public class PriceStructure {
    private float tipAmount = -1;
    private float eachPay = -1;
    private float totalAmount = -1;

    public void setTipAmount(float tipAmount) {
        this.tipAmount = tipAmount;
    }

    public void setEachPay(float eachPay) {
        this.eachPay = eachPay;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTipAmount() {
        return tipAmount;
    }

    public float getEachPay() {
        return eachPay;
    }

    public float getTotalAmount() {
        return totalAmount;
    }
}
