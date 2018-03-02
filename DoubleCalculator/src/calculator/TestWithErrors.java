package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestWithErrors {

	BusinessLogic test = new BusinessLogic();
	
	/***
	 * Check if the application is able to set operands without error terms.
	 * Old functionality mustn't be disturbed because of new code.
	 */
	@Test
	void testSettingWithoutErrors() {
		assertTrue(test.setOperand1("1"));
		assertTrue(test.getOperand1Defined());
		
		// We've set no errors manually. So the error value must be zero.
		CalculatorValue op1 = test.getOperand1();
		assertEquals(op1.measuredValue, 1.0, 0.0001);
		assertEquals(op1.errorTerm, 0.0, 0.0001);
		
		assertTrue(test.setOperand2("2"));
		assertTrue(test.getOperand2Defined());
		
		CalculatorValue op2 = test.getOperand2();
		assertEquals(op2.measuredValue, 2.0, 0.0001);
		assertEquals(op2.errorTerm, 0.0, 0.0001);
	}
	
	/***
	 * Check if the application is able to set operands with error terms.
	 */
	@Test
	void testSettingWithErrors() {
		assertTrue(test.setOperand1("1"));
		assertTrue(test.getOperand1Defined());
		assertTrue(test.setErrorTerm1("0.1"));
		assertTrue(test.getErrorTerm1Defined());
		
		CalculatorValue op1 = test.getOperand1();
		assertEquals(op1.measuredValue, 1.0, 0.0001);
		assertEquals(op1.errorTerm, 0.1, 0.0001);
		
		assertTrue(test.setOperand2("2"));
		assertTrue(test.getOperand2Defined());
		assertTrue(test.setErrorTerm2("0.05"));
		assertTrue(test.getErrorTerm2Defined());
		
		CalculatorValue op2 = test.getOperand2();
		assertEquals(op2.measuredValue, 2.0, 0.0001);
		assertEquals(op2.errorTerm, 0.05, 0.0001);
	}
	
	/***
	 * Check if addition still works when we don't define error terms.
	 */
	@Test
	void testAdditionWithoutErrors() {
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			assertTrue(test.setOperand1(num_One[i]));			
			assertTrue(test.setOperand2(num_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   n_Target = n_One + n_Two,
				   e_Target = 0.0;
			
			String ans = test.addition();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if addition works with error terms.
	 */
	@Test
	void testAdditionWithErrors() {
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setErrorTerm1(err_One[i]));
			
			assertTrue(test.setOperand2(num_Two[i]));
			assertTrue(test.setErrorTerm2(err_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   e_One = Double.parseDouble(err_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   e_Two = Double.parseDouble(err_Two[i]),
				   n_Target = n_One + n_Two,
				   e_Target = ((e_One) + (e_Two));
			
			String ans = test.addition();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if subtraction works.
	 */
	@Test
	void testSubtractionWithoutErrors() {
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			assertTrue(test.setOperand1(num_One[i]));			
			assertTrue(test.setOperand2(num_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   n_Target = n_One - n_Two,
				   e_Target = 0.0;
			
			String ans = test.subtraction();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if subtraction works with error terms.
	 */
	@Test
	void testSubtractionWithErrors() {
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setErrorTerm1(err_One[i]));
			
			assertTrue(test.setOperand2(num_Two[i]));
			assertTrue(test.setErrorTerm2(err_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   e_One = Double.parseDouble(err_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   e_Two = Double.parseDouble(err_Two[i]),
				   n_Target = n_One - n_Two,
				   e_Target = ((e_One) + (e_Two));
			
			String ans = test.subtraction();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if multiplication works without error terms.
	 */
	@Test
	void testMultiplicationWithoutErrors() {
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			assertTrue(test.setOperand1(num_One[i]));			
			assertTrue(test.setOperand2(num_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   n_Target = n_One * n_Two,
				   e_Target = 0.0;
			
			String ans = test.multiplication();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if multiplication works with error terms.
	 */
	@Test
	void testMultiplicationWithErrors() {
		
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			// Small number
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setErrorTerm1(err_One[i]));
			
			assertTrue(test.setOperand2(num_Two[i]));
			assertTrue(test.setErrorTerm2(err_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   e_One = Double.parseDouble(err_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   e_Two = Double.parseDouble(err_Two[i]),
				   n_Target = n_One * n_Two,
				   e_Target = ((e_One/n_One) + (e_Two/n_Two)) * (n_Target);
			
			String ans = test.multiplication();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if division works without error terms.
	 */
	@Test
	void testDivisionWithoutErrors() {
		String[] num_One = {"1", "18337", "3.371E+14", "7.31E+24"},
				 num_Two = {"2", "2183", "1.383E+8", "8.63E+38"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			assertTrue(test.setOperand1(num_One[i]));			
			assertTrue(test.setOperand2(num_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   n_Target = n_One / n_Two,
				   e_Target = 0.0;
			
			String ans = test.division();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if division works with error terms.
	 */
	@Test
	void testDivisionWithErrors() {
		String[] num_One = {"1.0", "18337.0", "3.371E+14", "7.31E+24"},
				 num_Two = {"2.0", "2183.0", "1.383E+8", "8.63E+38"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			// Small number
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setErrorTerm1(err_One[i]));
			
			assertTrue(test.setOperand2(num_Two[i]));
			assertTrue(test.setErrorTerm2(err_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   e_One = Double.parseDouble(err_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   e_Two = Double.parseDouble(err_Two[i]),
				   n_Target = n_One / n_Two,
				   e_Target = ((e_One/n_One) + (e_Two/n_Two)) * (n_Target);
			
			String ans = test.division();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
	
	/***
	 * Check if division by zero throws the proper error.
	 */
	@Test
	void testDivisionByZero() {
		String[] num_One = {"1.0", "18337.0", "3.371E+14", "7.31E+24"},
				 num_Two = {"0", "0", "0", "0"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6"};
		
		int i = 0;
		for(i = 0; i < 4; i++) {
			// Small number
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setErrorTerm1(err_One[i]));
			
			assertTrue(test.setOperand2(num_Two[i]));
			assertTrue(test.setErrorTerm2(err_Two[i]));
			
			Double n_One = Double.parseDouble(num_One[i]),
				   e_One = Double.parseDouble(err_One[i]),
				   n_Two = Double.parseDouble(num_Two[i]),
				   e_Two = Double.parseDouble(err_Two[i]),
				   n_Target = n_One / n_Two,
				   e_Target = ((e_One/n_One) + (e_Two/n_Two)) * (n_Target);
			
			String ans = test.division();
			assertTrue(test.getResultErrorMessage().length() > 0);
			assertEquals(test.getResultErrorMessage(), "Cannot divide by zero!");
		}
	}
	
	/***
	 * Check if square roots work without error terms.
	 */
	@Test
	void testSquareRootWithoutErrors() {
		String[] num_One = {"1.0", "18337.0", "3.371E+14", "7.31E+24", "0"},
				 num_Two = {"2.0", "2183.0", "1.383E+8", "8.63E+38", "0"};
		
		int i = 0, upto = num_One.length;
		Double n_One, n_Two, n_Target;
		
		// Setting just operand 1. The result should be its square root.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			n_One = Double.parseDouble(num_One[i]);
			n_Target = Math.sqrt(n_One);
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
		}
		
		// Setting just operand 2. The result should be its square root.
		test = new BusinessLogic();		// Resetting everything. Should be OK since we're not using a UI here.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand2(num_Two[i]));
			n_Two = Double.parseDouble(num_Two[i]);
			n_Target = Math.sqrt(n_Two);
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
		}
		
		// Setting both operands. The result should be the square root of operand 1.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			n_One = Double.parseDouble(num_One[i]);
			assertTrue(test.setOperand2(num_Two[i]));
			n_Two = Double.parseDouble(num_Two[i]);
			
			n_Target = Math.sqrt(n_One);	// Ignore operand 2. It's just along for the ride.
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
		}
		
		// Set operand 1 as negative. The result should be the square root of operand 2.
		test = new BusinessLogic();		// Reset everything.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1("-" + num_One[i]));
			n_One = Double.parseDouble("-" + num_One[i]);
			assertTrue(test.setOperand2(num_Two[i]));
			n_Two = Double.parseDouble(num_Two[i]);
			
			n_Target = Math.sqrt(n_Two);	// Ignore operand 1. It's negative.
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
		}
		
		// Set operand 2 as negative. The result should be the square root of operand 1.
		test = new BusinessLogic();		// Reset everything.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			n_One = Double.parseDouble(num_One[i]);
			assertTrue(test.setOperand2("-" + num_Two[i]));
			n_Two = Double.parseDouble("-" + num_Two[i]);
			
			n_Target = Math.sqrt(n_One);	// Ignore operand 2. It's negative.
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
		}
	}

	// Negative test cases for square root.
	@Test
	void testNegativeSquareRootWithoutErrors() {
		String[] num_One = {"-1.0", "-18337.0", "-3.371E+14", "-7.31E+24"},
				 num_Two = {"-2.0", "-2183.0", "-1.383E+8", "-8.63E+38"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6"};
		
		int i = 0, upto = num_One.length;
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setOperand2(num_Two[i]));
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() > 0);
			assertEquals(test.getResultErrorMessage(), "At least one operand must be non-negative!");
		}
		
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			assertTrue(test.setOperand2(num_Two[i]));
			assertTrue(test.setErrorTerm1(err_One[i]));
			assertTrue(test.setErrorTerm2(err_Two[i]));
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() > 0);
			assertEquals(test.getResultErrorMessage(), "At least one operand must be non-negative!");
		}
	}
	
	/***
	 * Check if square roots work with error terms.
	 */
	@Test
	void testSquareRootWithErrors() {
		String[] num_One = {"1.0", "18337.0", "3.371E+14", "7.31E+24", "0"},
				 num_Two = {"2.0", "2183.0", "1.383E+8", "8.63E+38", "0"},
				 err_One = {"0.1", "3.15", "323.51", "3.51E+5", "0"},
				 err_Two = {"0.05", "2.05", "218.05", "8.05E+6", "0"};
		
		int i = 0, upto = num_One.length;
		Double n_One, n_Two, n_Target, e_One, e_Two, e_Target;
		
		// Setting just operand 1. The result should be its square root.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			n_One = Double.parseDouble(num_One[i]);
			n_Target = Math.sqrt(n_One);
			
			assertTrue(test.setErrorTerm1(err_One[i]));
			e_One = Double.parseDouble(err_One[i]);
			e_Target = (e_One/n_One) * n_Target / 2;
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
		
		// Setting just operand 2. The result should be its square root.
		test = new BusinessLogic();		// Resetting everything. Should be OK since we're not using a UI here.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand2(num_Two[i]));
			n_Two = Double.parseDouble(num_Two[i]);
			n_Target = Math.sqrt(n_Two);
			
			assertTrue(test.setErrorTerm2(err_Two[i]));
			e_Two = Double.parseDouble(err_Two[i]);
			e_Target = (e_Two/n_Two) * n_Target / 2;
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
		
		// Setting both operands. The result should be the square root of operand 1.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			n_One = Double.parseDouble(num_One[i]);
			assertTrue(test.setOperand2(num_Two[i]));
			n_Two = Double.parseDouble(num_Two[i]);
			
			n_Target = Math.sqrt(n_One);	// Ignore operand 2. It's just along for the ride.
			
			assertTrue(test.setErrorTerm1(err_One[i]));
			e_One = Double.parseDouble(err_One[i]);
			assertTrue(test.setErrorTerm2(err_Two[i]));
			e_Two = Double.parseDouble(err_Two[i]);
			e_Target = (e_One/n_One) * n_Target / 2;
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
		
		// Set operand 1 as negative. The result should be the square root of operand 2.
		test = new BusinessLogic();		// Reset everything.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1("-" + num_One[i]));
			n_One = Double.parseDouble("-" + num_One[i]);
			assertTrue(test.setOperand2(num_Two[i]));
			n_Two = Double.parseDouble(num_Two[i]);
			
			n_Target = Math.sqrt(n_Two);	// Ignore operand 1. It's negative.
			
			assertTrue(test.setErrorTerm1(err_One[i]));
			e_One = Double.parseDouble(err_One[i]);
			assertTrue(test.setErrorTerm2(err_Two[i]));
			e_Two = Double.parseDouble(err_Two[i]);
			e_Target = (e_Two/n_Two) * n_Target / 2;
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
		
		// Set operand 2 as negative. The result should be the square root of operand 1.
		test = new BusinessLogic();		// Reset everything.
		for(i = 0; i < upto; i++) {
			assertTrue(test.setOperand1(num_One[i]));
			n_One = Double.parseDouble(num_One[i]);
			assertTrue(test.setOperand2("-" + num_Two[i]));
			n_Two = Double.parseDouble("-" + num_Two[i]);
			
			n_Target = Math.sqrt(n_One);	// Ignore operand 2. It's negative.
			
			assertTrue(test.setErrorTerm1(err_One[i]));
			e_One = Double.parseDouble(err_One[i]);
			assertTrue(test.setErrorTerm2(err_Two[i]));
			e_Two = Double.parseDouble(err_Two[i]);
			e_Target = (e_One/n_One) * n_Target / 2;
			
			String ans = test.squareRoot();
			assertTrue(test.getResultErrorMessage().length() <= 0);
			String[] nums = ans.split("\\s");
			
			assertEquals(Double.parseDouble(nums[0]), n_Target, 0.0001);
			assertEquals(Double.parseDouble(nums[2]), e_Target, 0.0001);
		}
	}
}
