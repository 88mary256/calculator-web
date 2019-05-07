package org.fundacionjala.calculator.pages;

import org.fundacionjala.calculator.core.CalculatorUtils;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/** This class contains unit test for CalculatorUtils .**/
public class CalculatorUtilsTest {

    /** Test to verify add method. **/
    @Test
    public void testAdd() {
        assertEquals(CalculatorUtils.add(1,2),3.0);
    }
}
