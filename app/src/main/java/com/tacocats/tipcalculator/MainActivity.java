package com.tacocats.tipcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tacocats.tipcalculator.math.TipCalculator;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    Activity activity;

    public void runCalculations() {
        try {
            // Get the tip, bill, and people
            EditText billText = findViewById(R.id.bill_amount);
            TextView tipText = findViewById(R.id.tip_percentage);
            TextView peopleText = findViewById(R.id.peoplecount);

            // Get set values
            TextView totalCost = findViewById(R.id.totalcost);
            TextView eachPay = findViewById(R.id.costperperson);
            TextView tipAmount = findViewById(R.id.tip_cost);

            // Get their values
            int bill = Integer.parseInt((billText.getText().toString()).replace("$", ""));
            int tip = Integer.parseInt(tipText.getText().toString().replace("%", ""));
            int people = Integer.parseInt(peopleText.getText().toString());

            // Create an instance of calculator
            TipCalculator calc = new TipCalculator();

            calc.setTip(tip);
            calc.setBill(bill);
            calc.setPeople(people);

            PriceStructure p = calc.calculateTip();
            totalCost.setText("$" + ( (double) Math.round(p.getTotalAmount()*100) /100));
            tipAmount.setText("$" + ((double) Math.round(p.getTipAmount()*100)/100));
            eachPay.setText("$" + ((double) Math.round(p.getEachPay()*100)/100));
        }
        catch (Exception e) {
          e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // Monitor text change for bill
         final EditText editText = findViewById(R.id.bill_amount);
         editText.addTextChangedListener(new TextWatcher() {

             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
             }

             @Override
             public void afterTextChanged(Editable s) {
                 if (!s.toString().startsWith("$")) {
                     SimpleBillParser simpleBillParser = new SimpleBillParser(editText.getText());

                     // If it fails parsing test, set it back to old
                     if (simpleBillParser.parse() == false) {
                         editText.setText("$");
                         editText.setSelection(1);
                     }

                 }
             }
         });

         editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
             @Override
             public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                 if (actionId == EditorInfo.IME_ACTION_DONE) {
                     // Run calculations
                     runCalculations();
                     return false;
                 }
                 return false;
             }
         });


        // Add the listeners to the buttons
        addButtonListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    // Add listeners to increase/decrease buttons
    private void addButtonListeners() {
        // Increase people
        findViewById(R.id.button_increase_people).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView people = findViewById(R.id.peoplecount);
                    int increasedCount = Integer.parseInt(people.getText().toString()) + 1;
                    people.setText(String.valueOf(increasedCount));
                    runCalculations();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Decrease people
        findViewById(R.id.button_decrease_people).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView people = findViewById(R.id.peoplecount);

                    // Only decrease people if there is > 1 person
                    int peopleCount = Integer.parseInt(people.getText().toString());
                    if (peopleCount > 1) {
                        peopleCount--;
                        people.setText(String.valueOf(peopleCount));
                    }
                    runCalculations();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Increase tip
        findViewById(R.id.button_increase_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    TextView tipview = findViewById(R.id.tip_percentage);
                    String rawTip = tipview.getText().toString();
                    // Remove the % from tip string
                    rawTip = rawTip.replace("%", "");

                    int tip = Integer.parseInt(rawTip);
                    tip++;

                    tipview.setText(String.valueOf(tip) + "%");
                    runCalculations();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Decrease tip
        findViewById(R.id.button_decrease_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView tipview = findViewById(R.id.tip_percentage);
                    String rawTip = tipview.getText().toString();
                    // Remove the % from tip string
                    rawTip = rawTip.replace("%", "");

                    int tip = Integer.parseInt(rawTip);

                    // Check if tip is > 1
                    if (tip > 1) {
                        tip--;
                        tipview.setText(String.valueOf(tip) + "%");
                    }
                    runCalculations();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
