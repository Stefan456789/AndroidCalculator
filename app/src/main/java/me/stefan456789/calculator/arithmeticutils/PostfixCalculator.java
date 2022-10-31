package me.stefan456789.calculator.arithmeticutils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostfixCalculator {
    private List<String> exp = new ArrayList<>();
    private Deque<Double> stack = new ArrayDeque<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PostfixCalculator(List<String> exp) {
        this.exp = exp;

        exp.forEach(x -> {
            char firstChar = x.charAt(0);
            if (Character.isDigit(firstChar)){
                stack.push(Double.valueOf(x));
            } else switch (firstChar){
                case '+':
                    stack.push(stack.pop()+stack.pop());
                    break;
                case '-':
                    stack.push(stack.pop()-stack.pop());
                    break;
                case '/':
                    stack.push(stack.pop()/stack.pop());
                    break;
                case '*':
                    stack.push(stack.pop()*stack.pop());
                    break;
            }
        });
    }

    public BigDecimal getResult(){
        return stack.isEmpty() ? null : BigDecimal.valueOf(stack.peek());
    }
}
