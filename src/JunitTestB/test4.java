package JunitTestB;
import static org.junit.Assert.*;
import Source.*;
import org.junit.Test;

public class test4 {
	public static Calculator test = new Calculator();
	@Test
	public void test() {
		///≤‚ ‘”√¿˝1
				String inputString ="test^ 9*4+5xy*test^8-56*76*ui "
						+ "-ui*test*x^3 test	^9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3"; 
				
				String resultString = "-4*test^10*ui*x^3+5*test^8*x*y+"
						+ "36*test^9+5*test^8*xy-test*ui*x^3-8512*ui";
				try {
					assertEquals(resultString,test.expression(inputString).printStringP());
				} catch (ExpressionException e) {
					e.printStackTrace();
				}
	}

}
