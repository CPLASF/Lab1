package JunitTestW;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import Source.Calculator;
import Source.ExpressionException;
import Source.*;
public class test2 {

	public static Calculator test = new Calculator();
	@Test
	public void test() {
		///≤‚ ‘”√¿˝2
				String inputString ="test^  9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3+ test	"
						+ "^9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3"; 
				String inputCommand = "!d/d z";
				String resultString = "Error, no variable";
				try {
					String pDer = "\\s*\\!d\\/d\\s*";
					Pattern p2 = Pattern.compile(pDer);
					Matcher m2 = p2.matcher(inputCommand);
					test.poly = test.expression(inputString);
					if(m2.find()){
						assertEquals(resultString,test.derivate(m2.group(),inputCommand));
					}
					else{
						System.out.println("Error");
					}
				} catch (ExpressionException e) {
					e.printStackTrace();
				}
	}
}