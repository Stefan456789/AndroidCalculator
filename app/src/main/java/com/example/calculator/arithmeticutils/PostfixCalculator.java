package com.example.calculator.arithmeticutils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostfixCalculator {
    private List<String> exp = new ArrayList<>();
    private Deque<Double> stack = new ArrayDeque<>();


    public PostfixCalculator(List<String> exp) {
        this.exp = exp;
    }

    public BigDecimal getResult(){
        return null;
    }
}
