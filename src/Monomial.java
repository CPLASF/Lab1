import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// 锟斤拷锟斤拷式锟斤拷
public class Monomial implements Comparable<Monomial> {
  public int coefficient = 1;/// 系锟斤拷
  public int totalPower = 0;/// 锟斤拷锟捷达拷
  public int numberOfVariable = 0;/// 锟斤拷锟斤拷锟侥革拷锟斤拷 
  public TreeMap<String, Integer> variableM;/// <锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷>

  public Monomial() {

  }
  /**.
   * 
   */
  
  public Monomial(String expressionM) {
    /// String input = "35111*111*x*Y*sss";
    variableM = new TreeMap<String, Integer>();
    String ppFactor = "((\\d+\\^\\d+)|([a-zA-Z]+\\^\\d+)|(\\d+)|([a-zA-Z]+))";
    Pattern p4 = Pattern.compile(ppFactor);
    Matcher m4 = p4.matcher(expressionM);
    while (m4.find()) {
      /// System.out.println(m4.group());
      String judge = "[a-zA-Z]+";
      String m4String = m4.group();  
      Pattern pp = Pattern.compile(judge);
      Matcher mm = pp.matcher(m4String);
      /// 匹锟斤拷晒锟斤拷锟剿碉拷锟斤拷潜锟斤拷锟�
      if (mm.find()) {
        this.totalPower++;
        /// 锟叫断该憋拷锟斤拷锟角凤拷锟斤拷前锟斤拷谋锟斤拷式锟斤拷锟斤拷止锟�
        if (this.variableM == null || this.variableM.containsKey(m4String) == false) {
          this.numberOfVariable++;
          this.variableM.put(m4String, 1);
        } else {
          int valueM = this.variableM.get(m4String);
          this.variableM.remove(m4String);
          this.variableM.put(m4String, ++valueM);
        }
      } else {
        int num = Integer.parseInt(m4String);
        this.coefficient *= num;
      }
    }
  }
  /**.
   * 
   */
  
  public int compareTo(Monomial aa) {
    int programExit = 0;
    if (this.totalPower > aa.totalPower) {
      programExit = -1;
    } else if (this.totalPower < aa.totalPower) {
      programExit = 1;
    } else {
      if (this.numberOfVariable == aa.numberOfVariable) {
        @SuppressWarnings("rawtypes")
        Iterator itM = this.variableM.keySet().iterator();
        @SuppressWarnings("rawtypes")
        Iterator ita = aa.variableM.keySet().iterator();
        while (itM.hasNext() && ita.hasNext()) {
          String sm = (String) itM.next();
          String sa = (String) ita.next();
          if ((sm.equals(sa) == false)
              || ((sm.equals(sa) == true) 
               &&   (!this.variableM.get(sm).equals(aa.variableM.get(sa))))) {
            if (sm.compareTo(sa) < 0) {
              programExit = -1;
            } else {
              programExit = 1;
            }
            break;
          } else {
            programExit = 0;
          }
        }
      } else {
        // @SuppressWarnings("rawtypes")
        Iterator<String> itM = this.variableM.keySet().iterator();
        // @SuppressWarnings("rawtypes")
        Iterator<String> ita = aa.variableM.keySet().iterator();
        int flag = 1; /// 锟叫讹拷while循锟斤拷锟斤拷锟斤拷
        while (itM.hasNext() && ita.hasNext()) {
          String sm = (String) itM.next();
          String sa = (String) ita.next();
          if (sm.compareTo(sa) < 0) {
            programExit = -1;
            flag = 0;
            break;
          }  else if (sm.compareTo(sa) > 0) {
            programExit = 1;
            flag = 0;
            break;
          }
        }
        if (flag == 1) {
          if (itM.hasNext() == false) {
            programExit = -1;
          } else {
            programExit = 1;
          }
        }
      }
    }
    return programExit;
  }
  /**.
   * 
   */
  
  public String printStringM() {
    String outExp = String.valueOf(Math.abs(this.coefficient));
    if (this.coefficient == 0) {
      outExp = "0";
    } else {
      Iterator<String> it = this.variableM.keySet().iterator();

      while (it.hasNext()) {
        String ss = (String) it.next();
        if (this.variableM.get(ss) > 1) {
          String added = ss + "^" + String.valueOf(this.variableM.get(ss));
          if (outExp.equals("1")) {
            outExp = added;
          } else {
            outExp = outExp + "*" + added;
          }
        } else {
          if (outExp.equals("1") || outExp.equals("-1")) {
            outExp = ss;
          } else {
            outExp = outExp + "*" + ss;
          }
        }
      }
    }
    return outExp;
  }

  /// 锟斤拷锟斤拷式锟斤拷锟斤拷
  
  /**.
   * 
   */
  
  public Monomial derivativeM(String vv) {
    Monomial der = new Monomial();
    der.variableM = new TreeMap<String, Integer>();
    der.coefficient = this.coefficient;
    Iterator<String> it = this.variableM.keySet().iterator();
    int judge = 0;
    while (it.hasNext()) {
      String ss = (String) it.next();
      if (ss.equals(vv)) {
        judge = 1;
        int index = this.variableM.get(ss);
        if (index != 0) {
          der.coefficient *= index;
          if (index != 1) {
            der.variableM.put(ss, --index);
          }
        }
      } else {
        int index = this.variableM.get(ss);
        der.variableM.put(ss, index);
      }
    }
    if (judge == 0) {
      der.variableM.clear();
      der.coefficient = 0;
      der.numberOfVariable = 0;
    }
    return der;
  }

  /// 锟斤拷锟斤拷式锟斤拷锟斤拷锟斤拷值
  /**.
   * 
   */
  
  public Monomial simplify(String xx, int vv, int judge) {
    if (judge < 0) {
      vv = -vv;
    }
    Monomial mm = new Monomial();
    mm.variableM = new TreeMap<String, Integer>();
    mm.coefficient = this.coefficient;
    mm.numberOfVariable = this.numberOfVariable;
    mm.totalPower = this.totalPower;
    Iterator<String> it = this.variableM.keySet().iterator();
    while (it.hasNext()) {
      String ss = it.next();
      if (ss.equals(xx)) {
        int index = this.variableM.get(ss);
        mm.numberOfVariable = mm.numberOfVariable - 1;
        mm.totalPower -= index;
        int cof = (int) Math.pow(vv, index);
        mm.coefficient *= cof;
      } else {
        int index = this.variableM.get(ss);
        mm.variableM.put(ss, index);
      }
    }
    return mm;
  }

  /// 锟叫断碉拷锟斤拷式锟斤拷锟角凤拷锟叫憋拷锟斤拷V
  /**.
   * 
   */
  
  public boolean judgeVariableExist(String vv) {
    Iterator<String> it = this.variableM.keySet().iterator();
    while (it.hasNext()) {
      String ss = (String) it.next();
      if (ss.equals(vv)) {
        return true;
      }
    }
    return false;
  }
}
