package Source;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Control {
	public int judge(String pass, String pDer, String pSim, String userInput, Pattern p1, 
			Matcher m1, Pattern p2, Matcher m2, Pattern p3, Matcher m3) {
		int flag = 10;
		if (!m1.find()) {
			flag = 0;
		} else if (m2.find()) {
			flag = 1;
		} else if (m3.find()) {
			flag = 2;
		} else {
			flag = 3;
		}
		return flag;
	}
}
