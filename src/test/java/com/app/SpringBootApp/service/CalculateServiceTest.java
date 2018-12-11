package com.app.SpringBootApp.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class CalculateServiceTest {

    @Test
    public void testSumLongPositiveArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(2, 2);
        //then
        assertEquals(4, result);
    }

    @Test
    public void testSumLongNegativeAndPositiveArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(-23, 23);
        //then
        assertEquals(0, result);
    }

    @Test
    public void testSumLongNegativeArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(-13, -13);
        //then
        assertEquals(-26, result);
    }

    @Test
    public void testSumLongNegativeAndZeroArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(-13, 0);
        //then
        assertEquals(-13, result);
    }

    @Test
    public void testSumLongZeroArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(0, 0);
        //then
        assertEquals(0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumIncorrectLong() {
        //given
        CalculateService calc = new CalculateService();
        //when
        calc.sum(9223372036854775807L, 100);
    }

    @Test
    public void testIncorrectResultSumLongPositiveArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(5, 2);
        //then
        assertNotEquals(5, result);
    }

    @Test
    public void testIncorrectResultSumLongPositiveAndNegativeArgument() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(4, -2);
        //then
        assertNotEquals(3, result);
    }

    @Test
    public void testIncorrectResultSumLongZeroArguments() {
        //given
        CalculateService calc = new CalculateService();
        //when
        long result = calc.sum(0, 0);
        //then
        assertNotEquals(1, result);
    }
}