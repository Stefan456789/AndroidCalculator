package me.stefan456789.calculator.arithmeticutils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class PostfixConverter {
    private String infix;
    private Deque<Character> stack = new ArrayDeque<>();
    private List<String> postfix = new ArrayList<>();

    public PostfixConverter(String exp) {
        this.infix = exp;
        convertExpression();
    }

    private void convertExpression(){
        List<String> s = Arrays.asList(infix.split(" "));

        for (int i = 0; i < s.size(); i++){

            //Ist Zahl
            try {
                postfix.add("" + Double.parseDouble(s.get(i)));
                s.remove(i);
            }catch (NumberFormatException ex){}

            //Ist Operator
            if (s.get(i).equals("+") || s.get(i).equals("-") || s.get(i).equals("*") || s.get(i).equals("/")){
                while (!stack.isEmpty() &&
                        (stack.getLast().equals('+') || stack.getLast().equals('-') || stack.getLast().equals('*') || stack.getLast().equals('/')) &&
                        i <= getPrecedence(stack.getLast())){
                    postfix.add(s.get(i));
                    s.remove(i);
                }
                stack.add(s.get(i).charAt(0));
                s.remove(i);
            }
            if (s.get(i).equals(")")){
                while (!stack.getLast().equals('(')){
                    postfix.add("" + stack.pop());
                }
                s.remove(i);
            }


        }
        while (!stack.isEmpty()){
            postfix.add("" + stack.getLast());
        }


    }

    private void InputToStack(char input){
        stack.add(input);
    }

    private int getPrecedence(char up){
        return Arrays.asList(stack.toArray()).indexOf(up);
    }

    private void clearStack(){

    }

    public String getPostfixExpression(){
        return postfix.get(0);
    }

    public List<String> getPostfixAsList(){
        return postfix;
    }
}
