package me.stefan456789.calculator.arithmeticutils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collector;

public class PostfixConverter {
    private String infix;
    private Deque<Character> stack = new ArrayDeque<>();
    private List<String> postfix = new ArrayList<>();

    public PostfixConverter(String exp) {
        this.infix = exp;
        convertExpression();
    }

    private void convertExpression(){

        for (int i = 0; i < infix.length(); ++i){
            char operator = infix.charAt(i);

            // Ist Zahl
            if (Character.isLetterOrDigit(operator)){
                StringBuilder number = new StringBuilder();
                while (infix.length() > i && (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')){
                    number.append(infix.charAt(i));
                    i++;
                }
                i--;
                postfix.add(number.toString());
            }

            // Ist (
            else if (operator == '(')
                stack.push(operator);

            // Ist )
            else if (operator == ')') {

                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.add("" + stack.peek());
                    stack.pop();

                }
                stack.pop();
            }

            // ist Operator
            else
            {
                while (!stack.isEmpty() && getPrecedence(operator) <= getPrecedence(stack.peek())) {
                    postfix.add("" + stack.peek());
                    stack.pop();

                }
                stack.push(operator);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '('){
                postfix.clear();
                return;
            }


            postfix.add("" + stack.peek());
            stack.pop();
        }
    }

    private void InputToStack(char input){
        stack.add(input);
    }

    private int getPrecedence(char up){
        switch (up) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }

        return -1;
    }

    private void clearStack(){
        stack.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getPostfixExpression(){
        return postfix.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> getPostfixAsList(){
        return postfix.stream().collect(Collector.of(ArrayList<String>::new, (x, y) -> x.add("" + y), (a, b) -> {a.addAll(b); return a;}));
    }
}
