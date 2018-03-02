package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class MyTestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(SetOperandTest.class);
		for(Failure fail: result.getFailures()) {
			System.out.println(fail.toString());
		}
	}

}
