package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.arithmeticutils.PostfixCalculator;
import com.example.calculator.arithmeticutils.PostfixConverter;

public class MainActivity extends AppCompatActivity {

    private StringBuilder term = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onEnterNumber(View view){
        Button b = (Button) view;
        String input = b.getText().toString();

        switch (input){
            case "CLEAR":
                term = new StringBuilder();
                break;
            case "=":
                PostfixConverter conv = new PostfixConverter(term.toString());
                //PostfixCalculator calc = new PostfixCalculator();
                //term = new StringBuilder(conv.getPostfixAsList().toArray());

                break;
            case ".":
                term.append(input);
                break;
            default:
                if (!(term.length() == 0) && !(term.charAt(term.length()-1) == '.'))
                    term.append(" ");
                term.append(input);
        }
        ((TextView)findViewById(R.id.textView)).setText(term);
    }
}