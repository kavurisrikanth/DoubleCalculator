package calculator;

import java.lang.Math;
import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Baseline: Lynn Robert Carter, Additions: Srikanth Kavuri
 * 
 * @version 4.00	2017-10-18 Long integer implementation of the CalculatorValue class 
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	/*
	 * long value from the original Integer Calculator.
	 */
	//long measuredValue = 0;
	Double measuredValue = 0.0;
	Double errorTerm = 0.0;
	String errorMessage = "";
	int errorAt = -1;				// Added to integrate the functionalities of Calculator and Testbed.
	//int numPrecisionDigits = -1;	// Added to control the precision of the value.
	//int decimalAt = -1;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	
	/*****
	 * Constructor to cover the case when there is no error term
	 * */
	public CalculatorValue(double v) {
		measuredValue = v;
		errorTerm = 0.0;
	}
	
	/*****
	 * This constructor is for when a value and an error term are both directly available.
	 * @param v
	 * @param e
	 */
	public CalculatorValue(double v, double e) {
		measuredValue = v;
		errorTerm = e;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
		// Inclusion of error term
		errorTerm = v.errorTerm;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 * 
	 * Commented out by Kavuri Srikanth. Uncomment this and comment out the next constructor to get
	 * the original functionality.
	 
	public CalculatorValue(String s) {
		measuredValue = 0;
		if (s.length() == 0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an integer value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
			tempScanner.close();								// return a zero
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = tempScanner.nextDouble();				// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			measuredValue = 0;								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			measuredValue = -measuredValue;					// on the state of the flag that
	} */
	
	/*
	 * Add-on code by Kavuri Srikanth.
	 * Imported MeasuredValueRecognizer from the Testbed.
	 */
	public CalculatorValue(String s) {
		measuredValue = getDoubleFromString(s);
	}
	
	
	/*****
	 * This private function takes the burden of validation away from the constructor.
	 * Introduced so as to not have to write a brand new constructor that takes in two strings.
	 * 
	 * @param s
	 * @return
	 */
	private double getDoubleFromString(String s) {
		
		// Prepare a temporary container to hold the result.
		double tempValue = 0.0;

		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		String noSign = s.substring(start);
		String error = MeasuredValueRecognizer.checkMeasureValue(noSign);
		if(error.length() > 0) {
			errorMessage = error;
			errorAt = MeasuredValueRecognizer.measuredValueIndexofError;
			if(start != 0)									// Tiny bit of a fix to get the UI to behave.
				errorAt++;									// Basically, also counting the sign, if any.
		} else {
			// No errors, so store the value.			
			if(!noSign.isEmpty()) {
				Scanner tempScanner = new Scanner(noSign);
				tempValue = tempScanner.nextDouble();
				tempScanner.close();
			}
			
			/*
			 * Code to control precision. Currently not functioning. To be updated later.
			// Find the index of the decimal point
			decimalAt = noSign.indexOf('.');
			numPrecisionDigits = (decimalAt != -1) ? (noSign.length() - 1) - decimalAt : 0;
			*/
			
			errorMessage = "";
			if (negative)										// Return the proper value based
				tempValue = -tempValue;					// on the state of the flag that we set earlier.
			
		}
		
		// We're done. Return.
		return tempValue;
	}
	
	/*****
	 * This constructor takes in the measured value and the error term strings simultaneously.
	 * Then it calls the function that does FSM-based-validation of the floating-point numbers,
	 * like the constructor just above is doing.
	 * Added to accommodate the error term.
	 * 
	 * @param value_String
	 * @param error_String
	 */
	public CalculatorValue(String value_String, String error_String) {
		measuredValue = getDoubleFromString(value_String);
		errorTerm = getDoubleFromString(error_String);
	}
	
	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/*****
	 * Get the index where the error occurred.
	 */
	public int getErrorAt() {
		return errorAt;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(Double v){
		measuredValue = v;
	}
	
	/*****
	 * Set the current value of the error term.
	 */
	public void setErrorTerm(Double e) {
		errorTerm = e;
	}
	
	/*****
	 * Set the error term, but using a string.
	 */
	public void setErrorTerm(String s) {
		errorTerm = getDoubleFromString(s);
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		return measuredValue.toString() + " \u00B1 " + errorTerm.toString();
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue.toString() + " \u00B1 " + errorTerm.toString() + "\nerrorMessage = " + errorMessage + "\n";
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */
	public void add(CalculatorValue v) {
		measuredValue = Double.sum(measuredValue, v.measuredValue);
		errorTerm = Double.sum(errorTerm, v.errorTerm);
		errorMessage = "";
	}

	public void sub(CalculatorValue v) {
		measuredValue = Double.sum(measuredValue, -v.measuredValue);
		errorTerm = Double.sum(errorTerm, v.errorTerm);
		errorMessage = "";
	}

	public void mpy(CalculatorValue v) {
		double value1ErrorFraction = errorTerm/Math.abs(measuredValue),
				value2ErrorFraction = v.errorTerm/Math.abs(v.measuredValue);
		measuredValue *= v.measuredValue;
		errorTerm = (value1ErrorFraction + value2ErrorFraction) * Math.abs(measuredValue);
		errorMessage = "";
	}

	public void div(CalculatorValue v) {
		// Division by zero is already covered before control
		// reaches here. So divide away!
		double value1ErrorFraction = errorTerm/Math.abs(measuredValue),
				value2ErrorFraction = v.errorTerm/Math.abs(v.measuredValue);
		measuredValue /= v.measuredValue;
		errorTerm = (value1ErrorFraction + value2ErrorFraction) * Math.abs(measuredValue);
		errorMessage = "";
	}
	
	public void sqrt() {
		// Compute the square root of a number. Since we are only talking about one
		// number here, we don't need an argument.
		
		// Taking absolute values is redundant since negative values are filtered
		// out before control reaches here.
		double valueErrorFraction = errorTerm/measuredValue;
		measuredValue = Math.sqrt(measuredValue);
		errorTerm = valueErrorFraction * measuredValue / 2;	// Multiplying by half (see formula)
		errorMessage = "";
	}
}
