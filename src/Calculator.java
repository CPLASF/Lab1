
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



public class Calculator {
  /**.
   * 
   */
  public static void main(String[] args) throws ExpressionException {
    //Logger log = Logger.getLogger(Calculator.class);
    Polynomial exp = new Polynomial();
    Scanner ssc = new Scanner(System.in);
    while (true) {

      //      Scanner sc = new Scanner(System.in);
      String userInput = ssc.nextLine();
      long startTime = System.currentTimeMillis();// 获取当前时间
      /// String input = "x*x*x*y*ui*9+3*x*y*z*x";
      String p1Der = "\\s*\\!d\\/d\\s*";
      String p1Sim = "\\s*\\!simplify";
      String pass = "[^a-zA-Z0-9\\+\\-\\*\\^\\s]";
      /// 判断输入类型
      /// 表达式
      Pattern pp1 = Pattern.compile(pass);
      Matcher mm1 = pp1.matcher(userInput);
      /// 命令---求导
      Pattern pp2 = Pattern.compile(p1Der);
      Matcher mm2 = pp2.matcher(userInput);
      /// 命令---求值
      Pattern pp3 = Pattern.compile(p1Sim);
      Matcher mm3 = pp3.matcher(userInput);
      if (!mm1.find()) {
        try {
          // CreatExpression ce = new CreatExpression();
          try {
            userInput = CreatExpression.create(userInput);
          } catch (ExpressionException e1) {
            
            e1.printStackTrace();
          }
          exp = new Polynomial(userInput);
          System.out.println(exp.printStringP());
          //log.info(exp.printStringP());
          /// System.out.println(exp.simplify("x",
          /// 2).printStringP());
        } catch (ExpressionException e1) {
          
          e1.printStackTrace();
        }
      } else if (mm2.find()) {
        String prefix = mm2.group();
        int mm = prefix.length();
        String vv = userInput.substring(mm);
        String jjz = exp.derivativeP(vv).printStringP();
        /// 判断求导的变量是否是表达式中出现过的
        if (jjz.equals("0")) {
          System.out.println("Error, no variable");
          //log.info("Error, no variable");
        } else {
          System.out.println(exp.derivativeP(vv).printStringP());
          //log.info(exp.derivativeP(vv).printStringP());
        }
      } else if (mm3.find()) {
        final String ppVar = "[a-zA-Z]+\\=\\-?\\d+";
        //String ppSimplify = "\\s*!simplify" + "((" + "\\s" + ppVar + ")" + "+)";
        Pattern ppS = Pattern.compile(ppVar);
        Matcher mmS = ppS.matcher(userInput);
        int judge = 0; /// 判断simplify之后是否有var及其取指的标志
        int count = 0; /// 判断求值次数，确定循环
        int judgepn = 1;/// 判断变量取指正负
        Polynomial sss = new Polynomial();
        while (mmS.find()) { // !simplify x=1
          judge++;
          String ssExp = mmS.group();
          char[] sexpArray = ssExp.toCharArray();
          int flag = 0; /// 标记等号的位置
          while (true) {
            if (String.valueOf(sexpArray[flag]).equals("=")) {
              break;
            } else {
              flag++;
            }
          }
          String var = ssExp.substring(0, flag);
          if (!exp.judgeVariableExist(var)) {
            System.out.println("Format error");
            //log.info("Format error");
            throw new ExpressionException("Format error");
          }
          String str = ssExp.substring(flag + 1, flag + 2);
          if (str.equals("-")) {
            judgepn = -1;
            flag++;
          }
          String valueString = ssExp.substring(flag + 1);
          int value = Integer.valueOf(valueString);
          if (count == 0) {
            sss = exp.simplify(var, value, judgepn);
          } else {
            sss = sss.simplify(var, value, judgepn);
          }
          count++;
        }
        if (judge == 0) {
          System.out.println(exp.printStringP());
          //log.info(exp.printStringP());
        } else {
          System.out.println(sss.printStringP());
          //log.info(sss.printStringP());
        }
      } else {
        System.out.println("Format error");
        //log.info("Format error");
        throw new ExpressionException("Format error");
      }

      long endTime = System.currentTimeMillis();
      System.out.println("运行时间：" + (endTime - startTime) + "ms");
      //log.info("运行时间：" + (endTime - startTime) + "ms");
      //sc.close();
    }
  }
}
