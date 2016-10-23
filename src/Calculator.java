
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
  /**.
   * 
   */
  public static void main(String[] args) throws ExpressionException {

    Polynomial exp = new Polynomial();
    Scanner sc = new Scanner(System.in);
    while (true) {

      //      Scanner sc = new Scanner(System.in);
      String userInput = sc.nextLine();
      long startTime = System.currentTimeMillis();// 获取当前时间
      /// String input = "x*x*x*y*ui*9+3*x*y*z*x";
      String p1Der = "\\s*\\!d\\/d\\s*";
      String p1Sim = "\\s*\\!simplify";
      String pass = "[^a-zA-Z0-9\\+\\-\\*\\^\\s]";
      /// 判断输入类型
      /// 表达式
      Pattern p1 = Pattern.compile(pass);
      Matcher m1 = p1.matcher(userInput);
      /// 命令---求导
      Pattern p2 = Pattern.compile(p1Der);
      Matcher m2 = p2.matcher(userInput);
      /// 命令---求值
      Pattern p3 = Pattern.compile(p1Sim);
      Matcher m3 = p3.matcher(userInput);
      if (!m1.find()) {
        try {
          // CreatExpression ce = new CreatExpression();
          try {
            userInput = CreatExpression.create(userInput);
          } catch (ExpressionException e1) {
            
            e1.printStackTrace();
          }
          exp = new Polynomial(userInput);
          System.out.println(exp.printStringP());
          /// System.out.println(exp.simplify("x",
          /// 2).printStringP());
        } catch (ExpressionException e1) {
          
          e1.printStackTrace();
        }
      } else if (m2.find()) {
        String prefix = m2.group();
        int mm = prefix.length();
        String vv = userInput.substring(mm);
        String jz = exp.derivativeP(vv).printStringP();
        /// 判断求导的变量是否是表达式中出现过的
        if (jz.equals("0")) {
          System.out.println("Error, no variable");
        } else {
          System.out.println(exp.derivativeP(vv).printStringP());
        }
      } else if (m3.find()) {
        String ppVar = "[a-zA-Z]+\\=\\-?\\d+";
        //String ppSimplify = "\\s*!simplify" + "((" + "\\s" + ppVar + ")" + "+)";
        Pattern ppS = Pattern.compile(ppVar);
        Matcher mmS = ppS.matcher(userInput);
        int judge = 0; /// 判断simplify之后是否有var及其取指的标志
        int count = 0; /// 判断求值次数，确定循环
        int judgepn = 1;/// 判断变量取指正负
        Polynomial ss = new Polynomial();
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
            ss = exp.simplify(var, value, judgepn);
          } else {
            ss = ss.simplify(var, value, judgepn);
          }
          count++;
        }
        if (judge == 0) {
          System.out.println(exp.printStringP());
        } else {
          System.out.println(ss.printStringP());
        }
      } else {
        System.out.println("Format error");
        throw new ExpressionException("Format error");
      }

      long endTime = System.currentTimeMillis();
      System.out.println("运行时间：" + (endTime - startTime) + "ms");
      //sc.close();
    }
  }
}
