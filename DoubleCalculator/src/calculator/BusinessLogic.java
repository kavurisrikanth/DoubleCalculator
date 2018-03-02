
package calculator;

/**
 * <p> Title: BusinessLogic Class. </p>
 * 
 * <p> Description: The code responsible for performing the calculator business logic functions. 
 * This method deals with CalculatorValues and performs actions on them.  The class expects data
 * from the User Interface to arrive as Strings and returns Strings to it.  This class calls the
 * CalculatorValue class to do computations and this class knows nothing about the actual 
 * representation of CalculatorValues, that is the responsibility of the CalculatorValue class and
 * the classes it calls.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Baseline: Lynn Robert Carter, Additions: Srikanth Kavuri
 * 
 * @version 4.00	2014-10-18 The JavaFX-based GUI implementation of a long integer calculator 
 * 
 */

public class BusinessLogic {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major calculator values 
	private CalculatorValue operand1 = new CalculatorValue(0);
	private CalculatorValue operand2 = new CalculatorValue(0);
	private CalculatorValue result = new CalculatorValue(0);
	private String operand1ErrorMessage = "";
	private boolean operand1Defined = false;
	private String operand2ErrorMessage = "";
	private boolean operand2Defined = false;
	private String resultErrorMessage = "";
	
	/*
	 * Add-on code.
	 * Added by Srikanth Kavuri.
	 */
	private String extraInfo = "";		// Used to carry some extra information to display in the case of square root.
	private int operand1ErrorIndex = 0; // Used to remember where the error occurred in operand 1. FSM & Calculator integration.
	private int operand2ErrorIndex = 0; // Used to remember where the error occurred in operand 2. FSM & Calculator integration.
	private String inputString1 = "";
	private String inputString2 = "";
	
	private String errorTerm1ErrorMessage = "";
	private String errorTerm2ErrorMessage = "";
	private boolean errorTerm1Defined = true;	// Error terms are defined by default.
	private boolean errorTerm2Defined = true;	// Error term is 0 by default.
	private int errorTerm1ErrorIndex = 0;
	private int errorTerm2ErrorIndex = 0;
	private String errorString1 = "";
	private String errorString2 = "";
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	
	/**********
	 * This method initializes all of the elements of the business logic aspect of the calculator.
	 * There is no special computational initialization required, so the initialization of the
	 * attributes above are all that are needed.
	 */
	public BusinessLogic() {
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into operand1, any associated error message
	 * into operand1ErrorMessage, and sets flags accordingly.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand1(String value) {
		operand1Defined = false;							// Assume the operand will not be defined
		if (value.length() <= 0) {							// See if the input is empty. If so no error
			operand1ErrorMessage = "";						// message, but the operand is not defined.
			return true;									// Return saying there was no error.
		}
		inputString1 = value;
		operand1 = new CalculatorValue(value);				// If there was input text, try to convert it
		operand1ErrorMessage = operand1.getErrorMessage();	// into a CalculatorValue and see if it worked.
		operand1ErrorIndex = operand1.getErrorAt();			// Store the index error for the UI.
		if (operand1ErrorMessage.length() > 0) 				//  If there is a non-empty error 
			return false;									// message, signal there was a problem.
		operand1Defined = true;								// Otherwise, set the defined flag and
		return true;										// signal that the set worked
	}

	/**********
	 * This public method takes an input string and, if the string is not empty, sets the error term
	 * for operand 1.
	 * Any error messages are set according to the same logic as setOperand1().
	 */
	public boolean setErrorTerm1(String value) {
		errorTerm1Defined = false;
		if(value.length() <= 0) {
			errorTerm1ErrorMessage = "";
			return true;
		}
		
		errorString1 = value;
		operand1.setErrorTerm(value);
		errorTerm1ErrorMessage = operand1.getErrorMessage();
		errorTerm1ErrorIndex = operand1.getErrorAt();
		if(errorTerm1ErrorMessage.length() > 0)
			return false;
		errorTerm1Defined = true;
		return true;
	}
	
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into operand2, any associated error message
	 * into operand1ErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand2(String value) {			// The logic of this method is exactly the
		operand2Defined = false;							// same as that for operand1, above.
		if (value.length() <= 0) {
			operand2ErrorMessage = "";
			return true;
		}
		inputString2 = value;
		operand2 = new CalculatorValue(value);
		operand2ErrorMessage = operand2.getErrorMessage();
		operand2ErrorIndex = operand2.getErrorAt();
		if (operand2ErrorMessage.length() > 0)
			return false;
		operand2Defined = true;
		return true;
	}

	/**********
	 * This public method takes an input string and, if the string is not empty, sets the error term
	 * for operand 2.
	 * Any error messages are set according to the same logic as setOperand1().
	 */
	public boolean setErrorTerm2(String value) {
		errorTerm2Defined = false;
		if(value.length() <= 0) {
			errorTerm2ErrorMessage = "";
			return true;
		}
		
		errorString2 = value;
		operand2.setErrorTerm(value);
		errorTerm2ErrorMessage = operand2.getErrorMessage();
		errorTerm2ErrorIndex = operand2.getErrorAt();
		if(errorTerm2ErrorMessage.length() > 0)
			return false;
		errorTerm2Defined = true;
		return true;
	}
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into result, any associated error message
	 * into resuyltErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is similar to that for operand1 above. (There is no defined flag.)
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setResult(String value) {				// The logic of this method is similar to
		if (value.length() <= 0) {						// that for operand1, above.
			operand2ErrorMessage = "";
			return true;
		}
		result = new CalculatorValue(value);
		resultErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		return true;
	}
	
	/**********
	 * This public getter fetches operand 1.
	 */
	public CalculatorValue getOperand1() {
		return operand1;
	}
	
	/**********
	 * This public getter fetches operand 2.
	 */
	public CalculatorValue getOperand2() {
		return operand2;
	}
	
	/**********
	 * This public setter sets the String explaining the current error in operand1.
	 * 
	 * @return
	 */
	public void setOperand1ErrorMessage(String m) {
		operand1ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand1, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getOperand1ErrorMessage() {
		return operand1ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error into operand1.
	 * 
	 * @return
	 */
	public void setOperand2ErrorMessage(String m) {
		operand2ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand2, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getOperand2ErrorMessage() {
		return operand2ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error in the result.
	 * 
	 * @return
	 */
	public void setResultErrorMessage(String m) {
		resultErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in the result, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getResultErrorMessage() {
		return resultErrorMessage;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand1. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand1Defined() {
		return operand1Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand2. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand2Defined() {
		return operand2Defined;
	}

	/**********
	 * This public getter fetches the defined attribute for the error term of operand 1.
	 * 
	 * @return true if the error term is defined and has no error, else false
	 */
	public boolean getErrorTerm1Defined() {
		return errorTerm1Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for the error term of operand 2.
	 * 
	 * @return true if the error term is defined and has no error, else false
	 */
	public boolean getErrorTerm2Defined() {
		return errorTerm2Defined;
	}
	
	/**********
	 * This public getter fetches the extra information. There might not be any; the only operation using
	 * this so far (as of 16-02-2018) is square root.
	 */
	public String getExtraInfo() {
		return extraInfo;
	}
	
	/**********
	 * This public getter fetches the error index for operand 1. Used to get the UI to display helpful
	 * error messages like those in the testbed.
	 */
	public int getOperand1ErrorIndex() {
		return operand1ErrorIndex;
	}
	
	/**********
	 * This public getter fetches the error index for operand 2. Used to get the UI to display helpful
	 * error messages like those in the testbed.
	 */
	public int getOperand2ErrorIndex() {
		return operand2ErrorIndex;
	}
	
	/**********
	 * This public getter fetches the input string for operand 1.
	 */
	public String getInputString1() {
		return inputString1;
	}
	
	/**********
	 * This public getter fetches the input string for operand 2.
	 */
	public String getInputString2() {
		return inputString2;
	}
	
	/**********
	 * This public getter fetches the input string for the error term of operand 1.
	 */
	public String getErrorString1() {
		return errorString1;
	}
	
	/**********
	 * This public getter fetches the input string for the error term of operand 2.
	 */
	public String getErrorString2() {
		return errorString2;
	}
	
	/**********
	 * This public getter fetches the error message for the error term of operand 1.
	 */
	public String getErrorTerm1ErrorMessage() {
		return errorTerm1ErrorMessage;
	}
	
	/**********
	 * This public getter fetches the error message for the error term of operand 2.
	 */
	public String getErrorTerm2ErrorMessage() {
		return errorTerm2ErrorMessage;
	}
	
	/**********
	 * This public getter fetches the error index for the error term of operand 1.
	 */
	public int getErrorTerm1ErrorIndex() {
		return errorTerm1ErrorIndex;
	}
	
	/**********
	 * This public getter fetches the error message for the error term of operand 2.
	 */
	public int getErrorTerm2ErrorIndex() {
		return errorTerm2ErrorIndex;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/**********
	 * This toString method invokes the toString method of the result type (CalculatorValue is this 
	 * case) to convert the value from its hidden internal representation into a String, which can be
	 * manipulated directly by the BusinessLogic and the UserInterface classes.
	 */
	public String toString() {
		return result.toString();
	}
	
	/**********
	 * This public toString method is used to display all the values of the BusinessLogic class in a
	 * textual representation for debugging purposes.
	 * 
	 * @return a String representation of the class
	 */
	public String debugToString() {
		String r = "\n******************\n*\n* Business Logic\n*\n******************\n";
		r += "operand1 = " + operand1.toString() + "\n";
		r += "     operand1ErrorMessage = " + operand1ErrorMessage+ "\n";
		r += "     operand1Defined = " + operand1Defined+ "\n";
		r += "operand2 = " + operand2.toString() + "\n";
		r += "     operand2ErrorMessage = " + operand2ErrorMessage+ "\n";
		r += "     operand2Defined = " + operand2Defined+ "\n";
		r += "result = " + result.toString() + "\n";
		r += "     resultErrorMessage = " + resultErrorMessage+ "\n";
		r += "*******************\n\n";
		return r;
	}

	/**********************************************************************************************

	Business Logic Operations (e.g. addition)
	
	**********************************************************************************************/
	
	/**********
	 * This public method computes the sum of the two operands using the CalculatorValue class method 
	 * for addition. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String addition() {
		result = new CalculatorValue(operand1);
		result.add(operand2);
		resultErrorMessage = result.getErrorMessage();
		/*
		 * Code to control precision. Currently not functioning. To be updated later.
		 * 
		int precision = operand1.numPrecisionDigits > operand2.numPrecisionDigits ? operand1.numPrecisionDigits : operand2.numPrecisionDigits;
		String format = "#";
		for(int i = 0; i < )
		return result.toString().substring(0, precision);
		*/
		return result.toString();
	}
	
	/**********
	 * subtraction: Inputs - None; Returns - String representation of difference
	 * 
	 * No actual subtraction is done in this function.
	 * Rather, the values are passed to CalculatorValue and the subtraction is
	 * done there, much like addition above.
	 */
	public String subtraction() {
		result = new CalculatorValue(operand1);
		result.sub(operand2);
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
	
	/**********
	 * multiplication: Inputs - none; Returns - String representation of product
	 * 
	 * Same design as addition and subtraction.
	 */
	public String multiplication() {
		result = new CalculatorValue(operand1);
		result.mpy(operand2);
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
	
	/**********
	 * division: Inputs - none; Returns - String representation of quotient
	 * 
	 * Same design as addition, subtraction, and multiplication, but with the
	 * added complication of handling division by zero.
	 */
	public String division() {
		result = new CalculatorValue(operand1);
		if(operand2.measuredValue == 0) {
			/*
			 * Checking for division by zero.
			 * NOTE: Returning an empty string is to keep the UserInterface happy.
			 * Once this function returns and control passes to divOperands(), the
			 * length of the result is checked. If it's zero, then something has gone
			 * wrong.
			*/
			result.setErrorMessage("Cannot divide by zero!");
			resultErrorMessage = result.getErrorMessage();
			return "";
		}
		
		result.div(operand2);
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
	
	/**********
	 * squareRoot: Inputs - none; Returns - String representation of product
	 * 
	 * Same design as addition, subtraction, multiplication, and division. This function
	 * checks both operands for values and performs the square root operation on whichever
	 * one is defined and non-negative, defaulting to the first operand if both are.
	 */
	public String squareRoot() {
		
		/* 
		 * We'll take whichever operand is defined.
		 * If both are defined, then we check a bit more. If there's a negative number and
		 * a non-negative number, then we pick the non-negative one.
		 * The case of neither operand being defined has already been checked for.
		 */
		boolean defined1 = getOperand1Defined(),
				defined2 = getOperand2Defined();
		
		if(defined1 && defined2) {
			// Only go for operand2 if operand1 is negative.
			if(operand1.measuredValue < 0)
				result = new CalculatorValue(operand2);
			else
				result = new CalculatorValue(operand1);
		} else
			result = new CalculatorValue(getOperand1Defined() ? operand1 : operand2);
		
		if(result.measuredValue < 0) {
			/*
			 * A negative number cannot have a square root.
			 * Like division-by-zero, we'll validate this here and go to CalculatorValue
			 * for the maths.
			 */
			result.setErrorMessage("At least one operand must be non-negative!");
			resultErrorMessage = result.getErrorMessage();
			return "";
		}
		
		// We'll use this variable to add some more information to the result we display.
		// For instance, if both operands are defined and non-negative, how is the user
		// know which one we just computed the square root of?
		extraInfo = result.measuredValue.toString();
		
		result.sqrt();
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
}
