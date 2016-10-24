///kjvggiugoiugohohih
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// 多项式类
public class Polynomial {
  public TreeMap<Monomial, Integer> variableP;/// <单项式，(无用)>
  
  public Polynomial() {

  }
  /**.
   * 
   */
  
  public Polynomial(String userInput) throws ExpressionException {
    variableP = new TreeMap<Monomial, Integer>();
    String ppFactor = "((\\d+\\^\\d+)|([a-zA-Z]+\\^\\d+)|(\\d+)|([a-zA-Z]+))";
    String ppMonomial = "(" + ppFactor + "(\\s*(\\*)?\\s*" + ppFactor + ")*)";

    Pattern pp = Pattern.compile(ppMonomial);
    Matcher mm = pp.matcher(userInput);
    while (mm.find()) {
      String ss = mm.group();
      Monomial cm = new Monomial(ss);

      int start = mm.start();
      if (!(start == 0) && userInput.toCharArray()[start - 1] == '-') {
        cm.coefficient *= -1;
      }
      /// 合并同类项
      if (this.variableP == null || !this.variableP.containsKey(cm)) {
        this.variableP.put(cm, cm.coefficient);
      } else {
        int cof1 = cm.coefficient;
        int cof2 = this.variableP.get(cm);
        this.variableP.remove(cm);
        cm.coefficient = cof1 + cof2;
        this.variableP.put(cm, cm.coefficient);
      }
    }
  }
  /**.
   * 
   */

  public String printStringP() {
    String outExp = "";
    Iterator<Entry<Monomial, Integer>> it = this.variableP.entrySet().iterator();
    while (it.hasNext()) {
      Entry<Monomial, Integer> mm = (Entry<Monomial, Integer>) it.next();
      Monomial pp = mm.getKey();
      if (mm.getValue() >= 0) {
        outExp = outExp + "+" + pp.printStringM();
      } else {
        outExp = outExp + "-" + pp.printStringM();
      }
    }
    if (outExp.substring(0, 1).equals("+")) {
      outExp = outExp.substring(1);
    }
    return outExp;
  }

  /// 多项式求导数
  /**.
   * 
   */
  
  public Polynomial derivativeP(String vv) {
    Polynomial der = new Polynomial();
    der.variableP = new TreeMap<Monomial, Integer>();
    @SuppressWarnings("rawtypes")
    Iterator it = this.variableP.keySet().iterator();
    while (it.hasNext()) {
      Monomial mm = (Monomial) it.next();
      Monomial nn = mm.derivativeM(vv);
      der.variableP.put(nn, nn.coefficient);
    }
    return der;
  }

  /// 多项式带入求值
  /**.
   * 
   */
  
  public Polynomial simplify(String xx, int vv, int judge) {
    Polynomial pp = new Polynomial();
    pp.variableP = new TreeMap<Monomial, Integer>();
    Iterator<Monomial> it = this.variableP.keySet().iterator();
    while (it.hasNext()) {
      Monomial mm = it.next();
      Monomial nn = mm.simplify(xx, vv, judge);
      if (pp.variableP == null || !pp.variableP.containsKey(nn)) {
        pp.variableP.put(nn, nn.coefficient);
      } else {
        int cof1 = nn.coefficient;
        int cof2 = pp.variableP.get(nn);
        pp.variableP.remove(nn);
        nn.coefficient = cof1 + cof2;
        pp.variableP.put(nn, nn.coefficient);
      }
    }
    return pp;
  }

  /// 判断多项式中是否有变量V
  /**.
   * 
   */
  
  public boolean judgeVariableExist(String vv) {
    Iterator<Entry<Monomial, Integer>> it = this.variableP.entrySet().iterator();
    while (it.hasNext()) {
      Entry<Monomial, Integer> mm = (Entry<Monomial, Integer>) it.next();
      Monomial pp = mm.getKey();
      if (pp.judgeVariableExist(vv)) {
        return true;
      }
    }
    return false;
  }
}