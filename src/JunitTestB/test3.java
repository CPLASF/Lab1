package JunitTestB;

import static org.junit.Assert.*;
import Source.*;
import org.junit.Test;

public class test3 {
	public static Calculator test = new Calculator();
	@Test
	public void test() {
		///��������3
				String inputString ="te%&st^  9*4+5x*y*test^8-56*76*ui"
						+ "  -ui*test*x^3 test	"
						+ "^9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3"; 
				
				try {
					test.expression(test.expression(inputString).printStringP());
				} catch (ExpressionException e) { 
			        assertTrue(e.getMessage().equals("Format error")); 
				}

	}

}

