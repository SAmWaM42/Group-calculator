import java.util.ArrayList;
import java.util.Stack;

public class operations {

  String operators[] = { "+", "-", "x", "/" };

  ArrayList<String> arithmetic = new ArrayList<String>();
  int answer;
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
    } else {
      return 0;
    }

  }

  private int equal_to(String a, String b, String c) {
    int ans = 0;

    switch (a) {
      case "+":
        ans = Integer.valueOf(c) + Integer.valueOf(b);
        break;
      case "-":

        ans = Integer.valueOf(c) - Integer.valueOf(b);
        break;
      case "x":

        ans = Integer.valueOf(c) * Integer.valueOf(b);
        break;
      case "/":
        ans = Integer.valueOf(c) / Integer.valueOf(b);
        break;

    }
    return ans;
  }

  public operations(ArrayList<String> array) {

    arithmetic = array;

    for (int i = 0; i < arithmetic.size(); i++) {
      if (!check(operators, arithmetic.get(i))) {
        integer.add(arithmetic.get(i));

      } else {
        if (tempStack.empty()) {
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

    while (!tempStack.isEmpty()) {
      integer.add(tempStack.pop());
    }
    for (int i = 0; i < arithmetic.size(); i++) {

      if (!check(operators, integer.get(i))) {
        tempStack.push(integer.get(i));
      } else {
        String num1 = tempStack.pop();
        String num2 = tempStack.pop();

        tempStack.push(Integer.toString(equal_to(integer.get(i), num2, num1)));

      }

    }

    if (tempStack.size() == 1) {
      answer = Integer.valueOf(tempStack.pop());
    }
  }

  public static void main(String[] args) {
    ArrayList<String> test = new ArrayList<String>();
    test.add("23");
    test.add("+");
    test.add("40");
    test.add("x");
    test.add("40");

    operations operations = new operations(test);
    System.out.println(operations.answer);
  }

}
