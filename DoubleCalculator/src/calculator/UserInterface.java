
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Baseline: Lynn Robert Carter, Additions: Srikanth Kavuri
 * 
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("Double Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");		// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");		// The divide symbol: \u00F7
	
	// Add-on code
	private Button button_Sqrt = new Button("\u221A");		// The square root symbol: \u221A
	
	// The variables below are Used to introduce new paradigm of error messages.
	private TextFlow err1MeasuredValue;						// The complete error message
    private Text err1MVPart1 = new Text();					// Part one of the error message
    private Text err1MVPart2 = new Text();					// Part two of the error message
    private TextFlow err2MeasuredValue;
    private Text err2MVPart1 = new Text();
    private Text err2MVPart2 = new Text();
    
    private TextFlow err1ErrorTerm;
    private Text err1ETPart1 = new Text();
    private Text err1ETPart2 = new Text();
    private TextFlow err2ErrorTerm;
    private Text err2ETPart1 = new Text();
    private Text err2ETPart2 = new Text();
    
    private int error_Index = 0;
	
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	
	private Label label_errTerm1 = new Label("Error");
	private Label label_errTerm2 = new Label("Error");
	private Label label_errErrTerm1 = new Label("");
	private Label label_errErrTerm2 = new Label("");
	private Label label_resultErrTerm = new Label("Error");
	private TextField text_errTerm1 = new TextField();
	private TextField text_errTerm2 = new TextField();
	private TextField text_resultErrTerm = new TextField();
	
	private Label label_plusMinus_One = new Label("\u00B1"),
					label_plusMinus_Two = new Label("\u00B1"),
					label_plusMinus_Three = new Label("\u00B1");
	
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/2 + 15, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_errTerm1.requestFocus(); });
		
		// First plus minus label
		label_plusMinus_One.setLayoutX(Calculator.WINDOW_WIDTH/2 + 39);
		label_plusMinus_One.setLayoutY(70);
		label_plusMinus_One.setFont(Font.font("Arial", 28));
		
		// Set up the Label for the error term of the first operand.
		setupLabelUI(label_errTerm1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 65, 40);
		
		// Now set the text field up.
		setupTextUI(text_errTerm1, "Arial", 18, Calculator.WINDOW_WIDTH/2 - 75, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 65, 70, true);
		text_errTerm1.textProperty().addListener((observable, oldValue, newValue) -> { setErrorTerm1(); });
		text_errTerm1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		setupLabelUI(label_errErrTerm1, "Arial", 14, Calculator.WINDOW_WIDTH-40, Pos.BASELINE_RIGHT, 30, 130);
		label_errErrTerm1.setTextFill(Color.RED);
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 14, Calculator.WINDOW_WIDTH-100, Pos.BASELINE_LEFT, 10, 125);
		label_errOperand1.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 155);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/2 + 15, Pos.BASELINE_LEFT, 10, 180, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_errTerm2.requestFocus(); });
		
		// Second plus minus label
		label_plusMinus_Two.setLayoutX(Calculator.WINDOW_WIDTH/2 + 39);
		label_plusMinus_Two.setLayoutY(180);
		label_plusMinus_Two.setFont(Font.font("Arial", 28));
		
		// Set up the Label for the error term of the second operand.
		setupLabelUI(label_errTerm2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 65, 155);
				
		// Now set the text field up.
		setupTextUI(text_errTerm2, "Arial", 18, Calculator.WINDOW_WIDTH/2 - 75, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 65, 180, true);
		text_errTerm2.textProperty().addListener((observable, oldValue, newValue) -> { setErrorTerm2(); });
		setupLabelUI(label_errErrTerm2, "Arial", 14, Calculator.WINDOW_WIDTH-40, Pos.BASELINE_RIGHT, 30, 240);
		label_errErrTerm2.setTextFill(Color.RED);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 235);
		label_errOperand2.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 260);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH/2 + 15, Pos.BASELINE_LEFT, 10, 285, false);
		
		// Third and final plus minus label
		label_plusMinus_Three.setLayoutX(Calculator.WINDOW_WIDTH/2 + 39);
		label_plusMinus_Three.setLayoutY(285);
		label_plusMinus_Three.setFont(Font.font("Arial", 28));
		
		// Set up the Label for the error term of the second operand.
		setupLabelUI(label_resultErrTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 65, 260);
				
		// Now set the text field up.
		setupTextUI(text_resultErrTerm, "Arial", 18, Calculator.WINDOW_WIDTH/2 - 75, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 65, 285, false);
		
		// Establish an error message for the second operand just above it with, right aligned
		setupLabelUI(label_errResult, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_RIGHT, 0, 260);
		label_errResult.setTextFill(Color.RED);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 380);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 380);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 380);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 380);
		button_Div.setOnAction((event) -> { divOperands(); });
	
		// Establish the SQRT button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 380);
		button_Sqrt.setOnAction((event) -> { sqrtOperands(); });
		
		// Define text fields for errors
		err1MVPart1.setFill(Color.BLACK);
	    err1MVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    err1MVPart2.setFill(Color.RED);
	    err1MVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    err1MeasuredValue = new TextFlow(err1MVPart1, err1MVPart2);
		err1MeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-40); 
		err1MeasuredValue.setLayoutX(22);  
		err1MeasuredValue.setLayoutY(100);
		
		err2MVPart1.setFill(Color.BLACK);
	    err2MVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    err2MVPart2.setFill(Color.RED);
	    err2MVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    err2MeasuredValue = new TextFlow(err2MVPart1, err2MVPart2);
		err2MeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		err2MeasuredValue.setLayoutX(22);  
		err2MeasuredValue.setLayoutY(210);
		
		err1ETPart1.setFill(Color.BLACK);
	    err1ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    err1ETPart2.setFill(Color.RED);
	    err1ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    err1ErrorTerm = new TextFlow(err1ETPart1, err1ETPart2);
		err1ErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-40); 
		err1ErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH/2 + 50 + 12);  
		err1ErrorTerm.setLayoutY(100);
		
		err2ETPart1.setFill(Color.BLACK);
	    err2ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    err2ETPart2.setFill(Color.RED);
	    err2ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    err2ErrorTerm = new TextFlow(err2ETPart1, err2ETPart2);
	    err2ErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
	    err2ErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH/2 + 50 + 12);  
	    err2ErrorTerm.setLayoutY(210);
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1, 
				label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, 
				button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt, err1MeasuredValue, err2MeasuredValue,
				text_errTerm1, label_errTerm1, text_errTerm2, label_errTerm2, label_resultErrTerm, text_resultErrTerm,
				label_errErrTerm1, label_errErrTerm2, err1ErrorTerm, err2ErrorTerm, label_plusMinus_One, label_plusMinus_Two,
				label_plusMinus_Three);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */

	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		text_resultErrTerm.setText("");
		
		err1MVPart1.setText("");
		err1MVPart2.setText("");
		
		// Clear out the error term value errors.
		label_errErrTerm1.setText("");
		err1ETPart1.setText("");
		err1ETPart2.setText("");
		
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else {												// If there's a problem with the operand, display
			label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
			/* Add-on code */
			error_Index = perform.getOperand1ErrorIndex();
			if(error_Index <= -1)
				return;
			String input = perform.getInputString1();
			err1MVPart1.setText(input.substring(0, error_Index));
			err1MVPart2.setText("\u21EB");
		}
	}
		
	
	/**********
	 * Private local method to set the value of the error term of the first operand given a text value.
	 * The logic is exactly the same as used for the first operand, above.
	 */
	private void setErrorTerm1() {
		text_Result.setText(""); 								// Changing the error changes the value, changing the
		label_Result.setText("Result");							// result. So we clear the result.
		label_errResult.setText("");
		err1ETPart1.setText("");
		err1ETPart2.setText("");
		label_errErrTerm1.setText("");
		
		// Also, clear out the measured value errors. This helps the UI stay readable.
		label_errOperand1.setText("");
		err1MVPart1.setText("");
		err1MVPart2.setText("");
		
		// Since the old error messages don't exist anymore, we can skip that if case.
		if(!perform.setErrorTerm1(text_errTerm1.getText())) {
			label_errErrTerm1.setText(perform.getErrorTerm1ErrorMessage());
			// Error handling.
			error_Index = perform.getErrorTerm1ErrorIndex();
			if(error_Index <= -1)
				return;
			String input = perform.getErrorString1();
			err1ETPart1.setText(input.substring(0, error_Index));
			err1ETPart2.setText("\u21EB");
		}
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		text_resultErrTerm.setText("");
		
		err2MVPart1.setText("");
		err2MVPart2.setText("");
		
		// Clear out the error term value errors.
		label_errErrTerm2.setText("");
		err2ETPart1.setText("");
		err2ETPart2.setText("");
		
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else {
			label_errOperand2.setText(perform.getOperand2ErrorMessage());
			/* Add-on code */
			error_Index = perform.getOperand2ErrorIndex();
			if(error_Index <= -1)
				return;
			String input = perform.getInputString2();
			err2MVPart1.setText(input.substring(0, error_Index));
			err2MVPart2.setText("\u21EB");
		}
	}
	
	/**********
	 * Private local method to set the value of the error term of the first operand given a text value.
	 * The logic is exactly the same as used for the first operand, above.
	 */
	private void setErrorTerm2() {
		text_Result.setText(""); 								// Changing the error changes the value, changing the
		label_Result.setText("Result");							// result. So we clear the result.
		label_errResult.setText("");
		err2ETPart1.setText("");
		err2ETPart2.setText("");
		
		label_errErrTerm2.setText("");
		
		// Also, clear out the measured value errors. This helps the UI stay readable.
		label_errOperand2.setText("");
		err2MVPart1.setText("");
		err2MVPart2.setText("");
		
		// Since the old error messages don't exist anymore, we can skip that if case.
		if(!perform.setErrorTerm2(text_errTerm2.getText())) {
			label_errErrTerm2.setText(perform.getErrorTerm2ErrorMessage());
			// Error handling.
			error_Index = perform.getErrorTerm2ErrorIndex();
			if(error_Index <= -1)
				return;
			String input = perform.getErrorString2();
			err2ETPart1.setText(input.substring(0, error_Index));
			err2ETPart2.setText("\u21EB");
		}
	}
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	/*
	 * Added by Kavuri Srikanth.
	 * 
	 * The validation in binaryOperandIssues() doesn't apply here, since we only need one operand
	 * to apply the square root operation on.
	 * This method will check the operands. Since any one operand must exist, that's what this
	 * function will check for.
	 * 
	 * NOTE: This function will NOT check for positive values. That will be done in BusinessLogic.
	 * The reason is that I'd rather not have to write specific validation functions for each
	 * subsequent operation that I introduce. So, I'll try to make this function as generic as
	 * I can.
	 */
	private boolean validateForSquareRoot() {
		String errorMsg1 = perform.getOperand1ErrorMessage(),
				errorMsg2 = perform.getOperand2ErrorMessage();
		
		/*
		if(errorMsg1.length() > 0 && errorMsg2.length() > 0) {
			// Both operands have errors. Nothing to be done.
			return false;
		} */
		
		if(errorMsg1.length() > 0) {
			// There is an error message for the first operand. So, set the error label.
			label_errOperand1.setText(errorMsg1);
			
			//But all is not lost. If the second operand has no error message, then we are good to go.
			if(errorMsg2.length() > 0) {
				// Both operands have errors. Nothing to be done.
				return false;
			}
		} else if(errorMsg2.length() > 0) {
			// There is an error message for the second operand. So, set the error label.
			label_errOperand2.setText(errorMsg2);
		}
		
		// We've reached here, so at least one of the operands has no error message. Now we check
		// if they are defined.
		if(!perform.getOperand1Defined() && !perform.getOperand2Defined()) {
			// If neither is defined, nothing to be done.
			return false;
		}
		
		return true;
	}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			//text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Sum");								// change the title of the field to "Sum"
			String[] numbers = theAnswer.split("\\s");
			text_Result.setText(numbers[0]);
			text_resultErrTerm.setText(numbers[2]);
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		/*
		 * Came with this code.
		label_Result.setText("Subtraction not yet implemented!");		// Replace this line with the code
		text_Result.setText("");										// required to do subtraction.
		*/
		
		// Start here
		// First, validate operands.
		if(binaryOperandIssues())
			return;
		
		// Operands are valid. So, call the subtract function.
		// BusinessLogic performs the subtraction operation and returns
		// the result.
		// If something goes wrong, we get an empty string.
		String answer = perform.subtraction();
		label_errResult.setText("");
		if(answer.length() > 0) {
			// We have a valid answer
			//text_Result.setText(answer);
			String[] numbers = answer.split("\\s");
			text_Result.setText(numbers[0]);
			text_resultErrTerm.setText(numbers[2]);
			label_Result.setText("Difference");
		} else {
			// Something went wrong
			text_Result.setText("");
			label_Result.setText("Result");
			label_errResult.setText(perform.getResultErrorMessage());
		}
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		/*
		 * Pre-existing codition.
		label_Result.setText("Multiplication not yet implemented!");	// Replace this line with the code
		text_Result.setText("");										// required to do multiplication.
		*/
		
		// Begin
		// Validate operands
		if(binaryOperandIssues())
			return;
		
		// Operands are valid. So proceed with multiplication.
		// BusinessLogic performs the multiplication and returns
		// the result.
		// If something goes wrong, and empty string is returned.
		String answer = perform.multiplication();
		label_errResult.setText("");
		if(answer.length() > 0) {
			// All is well with the world (hopefully).
			//text_Result.setText(answer);
			String[] numbers = answer.split("\\s");
			text_Result.setText(numbers[0]);
			text_resultErrTerm.setText(numbers[2]);
			label_Result.setText("Product");
		} else {
			// Something went wrong
			text_Result.setText("");
			label_Result.setText("Result");
			label_errResult.setText(perform.getResultErrorMessage());
		}
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		/*
		 * This was here when I walked in.
		label_Result.setText("Division not yet implemented!");		// Replace this line with the code
		text_Result.setText("");										// required to do division.
		*/
		
		// Now, let us begin!
		// Validate operands
		if(binaryOperandIssues())
			return;
		
		// Validity validated. Now pass the operands
		// to BusinessLogic to be divided.
		// Any errors give us an empty result.
		String answer = perform.division();
		label_errResult.setText("");
		if(answer.length() > 0) {
			// Everything was OK
			//text_Result.setText(answer);
			String[] numbers = answer.split("\\s");
			text_Result.setText(numbers[0]);
			text_resultErrTerm.setText(numbers[2]);
			label_Result.setText("Quotient");
		} else {
			// Something went wrong
			text_Result.setText("");
			label_Result.setText("Result");
			label_errResult.setText(perform.getResultErrorMessage());
		}
	}
	
	/**********
	 * Added by Kavuri Srikanth.
	 * This is the square root routine. Only non-negative numbers are valid operands.
	 * 
	 * We're calculating the square root. So, we need only one operand. This requires some special
	 * validation.
	 */
	private void sqrtOperands() {
		
		// Validate operands.
		if(!validateForSquareRoot())
			return;
		
		// For now, we'll just compute the square root of the first operand.
		// An error will give an empty result string.
		String answer = perform.squareRoot();
		label_errResult.setText("");
		if(answer.length() > 0) {
			// Everything was OK
			//text_Result.setText(answer);
			String[] numbers = answer.split("\\s");
			text_Result.setText(numbers[0]);
			text_resultErrTerm.setText(numbers[2]);
			String extra = perform.getExtraInfo(),
					res_Label = "Square root (" + extra + ")";
			label_Result.setText(res_Label);
		} else {
			// Something went wrong
			text_Result.setText("");
			label_Result.setText("Result");
			label_errResult.setText(perform.getResultErrorMessage());
		}
	}
}
