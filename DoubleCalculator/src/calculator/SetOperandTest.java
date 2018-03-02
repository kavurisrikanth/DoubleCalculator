package calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Math;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;

class SetOperandTest {

	@Test
	void iCanSetOperands() {
		BusinessLogic test = new BusinessLogic();
		
		assertTrue(test.setOperand1("1.23"));
		assertEquals(test.getOperand1ErrorMessage(), "");
		assertTrue(test.getOperand1Defined());
		
		assertTrue(test.setOperand2("3.21"));
		assertEquals(test.getOperand2ErrorMessage(), "");
		assertTrue(test.getOperand2Defined());
	}

	@Test
	void iCanAdd() {
		BusinessLogic test = new BusinessLogic();
		
		assertTrue(test.setOperand1("1.23"));
		
		// Check adding with a positive number
		assertTrue(test.setOperand2("3.21"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + 3.21, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check adding with zero
		assertTrue(test.setOperand2("0"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + 0, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check adding with negative number smaller than the first
		assertTrue(test.setOperand2("-0.46"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + (-0.46), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// One more...
		assertTrue(test.setOperand2("-0.23"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + (-0.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check adding with negative number larger than the first
		assertTrue(test.setOperand2("-2"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + (-2), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check adding with the number itself
		assertTrue(test.setOperand2("1.23"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + 1.23, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check adding with negative of the number itself
		assertTrue(test.setOperand2("-1.23"));
		assertEquals(Double.parseDouble(test.addition()), 1.23 + (-1.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
	}
	
	@Test
	void iCanSubtract() {
		BusinessLogic test = new BusinessLogic();
		
		assertTrue(test.setOperand1("1.23"));
		
		// Check subtracting with a positive number
		assertTrue(test.setOperand2("3.21"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - 3.21, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check subtracting with zero
		assertTrue(test.setOperand2("0"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - 0, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check subtracting with negative number smaller than the first
		assertTrue(test.setOperand2("-0.46"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - (-0.46), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// One more...
		assertTrue(test.setOperand2("-0.23"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - (-0.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check subtracting with negative number larger than the first
		assertTrue(test.setOperand2("-2"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - (-2), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check subtracting with the number itself
		assertTrue(test.setOperand2("1.23"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - 1.23, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check subtracting with negative of the number itself
		assertTrue(test.setOperand2("-1.23"));
		assertEquals(Double.parseDouble(test.subtraction()), 1.23 - (-1.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
	}
	
	@Test
	void iCanMultiply() {
		BusinessLogic test = new BusinessLogic();
		
		assertTrue(test.setOperand1("1.23"));
		
		// Check multiplying with a positive number
		assertTrue(test.setOperand2("3.21"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * 3.21, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check multiplying with zero
		assertTrue(test.setOperand2("0"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * 0, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check multiplying with negative number smaller than the first
		assertTrue(test.setOperand2("-0.46"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * (-0.46), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// One more...
		assertTrue(test.setOperand2("-0.23"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * (-0.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check multiplying with negative number larger than the first
		assertTrue(test.setOperand2("-2"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * (-2), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check multiplying with the number itself
		assertTrue(test.setOperand2("1.23"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * 1.23, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check multiplying with negative of the number itself
		assertTrue(test.setOperand2("-1.23"));
		assertEquals(Double.parseDouble(test.multiplication()), 1.23 * (-1.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
	}
	
	@Test
	void iCanDivide() {
		BusinessLogic test = new BusinessLogic();
		
		assertTrue(test.setOperand1("1.23"));
		
		// Check dividing with a positive number
		assertTrue(test.setOperand2("3.21"));
		assertEquals(Double.parseDouble(test.division()), 1.23 / 3.21, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check dividing with zero
		assertTrue(test.setOperand2("0"));
		assertEquals(test.division(), "");
		assertEquals(test.getResultErrorMessage(), "Cannot divide by zero!");
		
		// Check dividing with negative number smaller than the first
		assertTrue(test.setOperand2("-0.46"));
		assertEquals(Double.parseDouble(test.division()), 1.23 / (-0.46), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// One more...
		assertTrue(test.setOperand2("-0.23"));
		assertEquals(Double.parseDouble(test.division()), 1.23 / (-0.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check dividing with negative number larger than the first
		assertTrue(test.setOperand2("-2"));
		assertEquals(Double.parseDouble(test.division()), 1.23 / (-2), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check dividing with the number itself
		assertTrue(test.setOperand2("1.23"));
		assertEquals(Double.parseDouble(test.division()), 1.23 / 1.23, 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
		
		// Check dividing with negative of the number itself
		assertTrue(test.setOperand2("-1.23"));
		assertEquals(Double.parseDouble(test.division()), 1.23 / (-1.23), 0.0001);
		assertEquals(test.getResultErrorMessage(), "");
	}
	
	@Test
	void iCanSqrt() {
		BusinessLogic test = new BusinessLogic();
		
		// A positive number as Operand 1. There should be no errors.
		assertTrue(test.setOperand1("1.23"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(1.23), 0.0001);
		
		// A positive number as Operand 2. There should be no errors.
		test = new BusinessLogic();
		assertTrue(test.setOperand2("1.23"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(1.23), 0.0001);

		// A negative number as operand 1 and operand 2. There should be an error.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("-1.23"));
		assertTrue(test.setOperand2("-3.21"));
		test.squareRoot();
		assertEquals(test.getResultErrorMessage(), "At least one operand must be non-negative!");
		
		// A negative number as operand 1 and nothing in operand 2. There should be an error.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("-1.23"));
		test.squareRoot();
		assertEquals(test.getResultErrorMessage(), "At least one operand must be non-negative!");
		
		// A negative number as operand 1 and nothing in operand 2. There should be an error.
		test = new BusinessLogic();
		assertTrue(test.setOperand2("-3.21"));
		test.squareRoot();
		assertEquals(test.getResultErrorMessage(), "At least one operand must be non-negative!");
		
		// Negative number in operand 1, positive number in operand 2. Operand 2's square root must be computed.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("1.23"));
		assertTrue(test.setOperand2("-3.21"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(1.23), 0.0001);
		
		// Negative number in operand 2, positive number in operand 1. Operand 2's square root must be computed.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("-1.23"));
		assertTrue(test.setOperand2("3.21"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(3.21), 0.0001);
		
		// Both operands are zero. There should be no error.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("0"));
		assertTrue(test.setOperand2("0"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(0), 0.0001);
		
		// Operand 1 is 0 and operand 2 is positive. Result should be 0.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("0"));
		assertTrue(test.setOperand2("3.21"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(0), 0.0001);
		
		// Operand 1 is positive and operand 2 is 0. Result should be square root of operand 1 value.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("1.23"));
		assertTrue(test.setOperand2("0"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(1.23), 0.0001);
		
		// Operand 1 is 0 and operand 2 is negative. Result should be 0.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("0"));
		assertTrue(test.setOperand2("-3.21"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(0), 0.0001);
		
		// Operand 1 is negative and operand 2 is 0. Result should be 0.
		test = new BusinessLogic();
		assertTrue(test.setOperand1("-1.23"));
		assertTrue(test.setOperand2("0"));
		assertEquals(Double.parseDouble(test.squareRoot()), Math.sqrt(0), 0.0001);
	}
}
