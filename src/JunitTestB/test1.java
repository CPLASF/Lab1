package JunitTestB;
import static org.junit.Assert.*;
import Source.*;
import org.junit.Test;

public class test1 {
	public static Calculator test = new Calculator();
	@Test
	public void test() {
		///≤‚ ‘”√¿˝1
				String inputString ="test^  9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3+ test	^9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3"; 
				
				String resultString = "10*test^8*x*y+40*test^9"
						+ "-2*test*ui*x^3-8512*ui";
				try {
					assertEquals(resultString,test.expression(inputString).printStringP());
				} catch (ExpressionException e) {
					e.printStackTrace();
				}
	}

}
