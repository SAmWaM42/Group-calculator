import java.util.ArrayList;
import java.util.Stack;

public class operations {
  boolean error=false;
  String operators[] = { "+", "-", "x", "/", "(", ")","^" };
  String trig[] = { "sin", "cos", "tan","sqrt","!","log","1/","asin","acos","atan","pi"};


  ArrayList<String> arithmetic = new ArrayList<String>();
  Double answer;
  ArrayList<String> integer = new ArrayList<String>();
  Stack<String> tempStack = new Stack<String>();

  private boolean check(String array[], String a) {
    boolean ans = false;
    for (int i = 0; i < array.length; i++) {
      if (a == array[i]) {
        ans = true;
      }

    }
    return ans;

  }

  int evaluate(String s) {
    if (s == "+") {
      return 2;
    } else if (s == "-") {
      return 1;
    } else if (s == "x") {
      return 3;
    } else if (s == "/") {
      return 4;
    } else if (s == ")" || s == "(") {
      return -1;
    } else if (s == "sin" || s == "cos" || s == "tan" ||s== "sqrt"||s=="^" ||s=="log"||s=="1/"||s=="!"
    ||s=="asin" || s=="acos" || s =="atan" ) {
      return 5;
    } else {
      return 0;
    }

  }

  private double equal_to(String a, String c, String b) {
    Double ans = 0.0;
  try
 {   switch (a) {
      case "+":
        ans = Double.valueOf(c) + Double.valueOf(b);
        break;
      case "-":

        ans = Double.valueOf(c) - Double.valueOf(b);
        break;
      case "x":

        ans = Double.valueOf(c) * Double.valueOf(b);
        break;
      case "/":
        ans = Double.valueOf(c) / Double.valueOf(b);
        break;
      case "^":
      ans=Math.pow(Double.valueOf(c),Double.valueOf(b));
      break;

     
      
    }

  }catch(Exception operation_Exception)
  {
    error=true;
  }

    return ans;
  }

  private double trig(String a, String b) {
    double ans = 0;
    try
{    switch (a) {
      case "sin":
        ans = Math.sin(Math.toRadians(Double.valueOf(b)));
        break;
      case "cos":
        ans = Math.cos(Math.toRadians(Double.valueOf(b)));
        break;
      case "tan":
        ans = Math.tan(Math.toRadians(Double.valueOf(b)));
        break;
        case "sqrt":
        ans=Math.sqrt(Double.valueOf(b));
        break;
        case "!":
          for(int i=Integer.valueOf(b);i>0;i--)
          {
            ans+=i;
          }
        break;
        case "log":
        ans=Math.log(Double.valueOf(b));
        break;
        case "1/":
        ans=1/Double.valueOf(b);
        break;
        case "asin":
        ans = Math.asin(Math.toRadians(Double.valueOf(b)));
        break;
        case "acos":
        ans = Math.acos(Math.toRadians((Double.valueOf(b))));
        break;
        case "atan":
        ans=Math.atan(Math.toRadians((Double.valueOf(b))));
          break;





    }
  }
  catch(Exception e)
  {
    error=true;
  }
    return ans;
  }

  public operations(ArrayList<String> array) {

    arithmetic = array;
    

    for (int i = 0; i < arithmetic.size(); i++) {
   //conversion post fix 
      
      if (!check(operators, arithmetic.get(i)) && !check(trig, arithmetic.get(i)) ) {
        integer.add(arithmetic.get(i));
        
      } else if (arithmetic.get(i) == "(") {
        tempStack.push(arithmetic.get(i));
      } else if (arithmetic.get(i) == ")") {
        while (!tempStack.isEmpty() && tempStack.peek() != "(") {
          integer.add(tempStack.pop());
        }
        tempStack.pop();
      } else {
        if (tempStack.empty() ) {
          tempStack.push(arithmetic.get(i));
        } else {
          while (!tempStack.isEmpty() && evaluate(tempStack.getLast()) > evaluate(arithmetic.get(i))) {
            String order = tempStack.pop();
            integer.add(order);
          }
          tempStack.push(arithmetic.get(i));

        }

      }

    }
//evaluation
    while (!tempStack.isEmpty()) {
      integer.add(tempStack.pop());
    }
    for (int i = 0; i < integer.size(); i++) {

      if (!check(operators, integer.get(i)) && !check(trig, integer.get(i))) {
        tempStack.push(integer.get(i));
      } else {
        try
      {  if (check(trig, integer.get(i))) {
         
          String num1 = tempStack.pop();
          tempStack.push(Double.toString(trig(integer.get(i), num1)));
          tempStack.peek();
         
        } else {
          String num1 = tempStack.pop();
          String num2 = tempStack.pop();
          tempStack.push(Double.toString(equal_to(integer.get(i), num2, num1)));
        }
      }
      catch(Exception e)
      {
        error=true;
      }
      

      }

    }

    if (tempStack.size() == 1) {
      answer = Double.valueOf(tempStack.pop());
    }
  }
 public static void main(String[] args) {
   /*   ArrayList<String> test = new ArrayList<String>();
 
    test.add("45");
    test.add("+");
    test.add("3");
    test.add("x");
    test.add("3");
    test.add("+");
    test.add("45");

    operations operations = new operations(test);
    System.out.println(operations.answer);
*/
 }

}
