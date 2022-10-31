package me.stefan456789.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.stefan456789.calculator.arithmeticutils.PostfixCalculator;
import me.stefan456789.calculator.arithmeticutils.PostfixConverter;


public class MainActivity extends AppCompatActivity {

    private StringBuilder term = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onEnterNumber(View view){
        Button b = (Button) view;
        String input = b.getText().toString();
        TextView output = findViewById(R.id.result);
        switch (input){
            case "Clear":
                term = new StringBuilder();
                break;
            case "=":
                PostfixConverter conv = new PostfixConverter(term.toString());
                PostfixCalculator calc = new PostfixCalculator(conv.getPostfixAsList());
                term = new StringBuilder();
                output.setText(calc.getResult() == null ? "Error" : calc.getResult().toPlainString());
                break;
            default:
                term.append(input);
                break;
        }
        ((TextView)findViewById(R.id.input)).setText(term);
    }
}