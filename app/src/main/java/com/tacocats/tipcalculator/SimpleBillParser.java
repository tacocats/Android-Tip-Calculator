package com.tacocats.tipcalculator;

import android.text.Editable;
import android.widget.EditText;

import java.util.regex.Pattern;

/*
    Simple parser/lexer that checks for proper rules for bill EditText
 */
public class SimpleBillParser {
    private Editable theText = null;

    public SimpleBillParser (Editable text) {
        theText = text;
    }

    /*
        Check if they deleted $ sign (Not allowed)
     */
    public boolean parse() {
        // Check if $ is at beginning
        if (!theText.toString().startsWith("$")) {
            return false;
        }
        else {
            return true;
        }
    }
}
