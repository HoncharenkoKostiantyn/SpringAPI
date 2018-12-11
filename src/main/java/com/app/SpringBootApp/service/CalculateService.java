package com.app.SpringBootApp.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateService {

    private static final BigDecimal LONG_MAX_VALUE = new BigDecimal(Long.MAX_VALUE);

    public long sum(long firstNumber, long secondNumber) {
        BigDecimal sum = new BigDecimal(firstNumber)
                .add(new BigDecimal(secondNumber));

        if (0 < sum.compareTo(LONG_MAX_VALUE))
            throw new IllegalArgumentException("Sum is to big for long type");

        return firstNumber + secondNumber;
    }

}
