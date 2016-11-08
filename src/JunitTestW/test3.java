package JunitTestW;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import Source.Calculator;
import Source.ExpressionException;
import Source.*;
public class test3 {

	public static Calculator test = new Calculator();
	@Test
	public void test() {
		///≤‚ ‘”√¿˝2
				String inputString ="test^  9*x+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3+ test	"
						+ "^9*4+5x*y*test^8-56*76*ui  "
						+ "-ui*test*x^3"; 
				String inputCommand = "!d/d x";
				String resultString = "9*test^9+10*test^8*y-6*test*ui*x^2";
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