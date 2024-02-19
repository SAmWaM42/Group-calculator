
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;




public class calculator implements ActionListener {
    JFrame frame;
    JPanel pane_text;
    JPanel pane_numb;
    JTextField writing_pane;
    String number_text[] = { "1", "2", "3","", "+", "C","^","!","asin",
                             "4", "5", "6","", "-", "sin","(","1/","atan",
                             "7", "8", "9","", "x", "tan",")","log","acos",
                             "0", "=", "rad",".", "/", "cos","sqrt","E","pi" };
    String operators[] = { "+", "-", "x", "/","tan","sin","cos","^","sqrt","(",")","!","log","e","1/","asin","atan,acos","pi","rad"};
    String operator_check[]={ "+", "-", "x", "/"};
    String special_op[]={"tan","sin","cos","asin","atan","acos","pi","E","rad"};
    // add remove button and special buttons
    int numbers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
    String int_check[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
    ArrayList<String> token = new ArrayList<String>();
    JButton number_button[] = new JButton[36];
    double final_ans;
    double memory;
    int multiplier_x = 0;
    int multiplier_y = 0;
    int padding = 5;
    boolean display_clear = false;

    public calculator() {
        //UI creation
        Color norm_buttonColor=new Color(80,58,37,255);
        Color oper_buttonColor=new Color(241,175,96,255);
        Color Back_Color=new Color(27,28,30,255);
        frame = new JFrame("Calculator");
        Container c =frame.getContentPane();
         c.setBackground(Back_Color);
        frame.setVisible(true);
        pane_text = new JPanel(new GridLayout(1, 1));
        pane_numb = new JPanel(new GridLayout(4, 9));
        pane_text.setSize(frame.getWidth(), 30);
        pane_numb.setSize(frame.getWidth(), frame.getHeight() - pane_text.getHeight());
        int x = frame.getWidth();
        int y = frame.getHeight();
        writing_pane = new JTextField();
        writing_pane.setColumns(1000);
        writing_pane.setVisible(true);
        writing_pane.setBounds(0, 0, y, x / 5);
        Font font = new Font("Arial", writing_pane.getFont().getStyle(), 42);
        Font button_font = new Font("Arial", writing_pane.getFont().getStyle(), 18);
        writing_pane.setFont(font);
        pane_text.add(writing_pane);
         writing_pane.setBackground(Back_Color);
         writing_pane.setForeground(Color.WHITE);
  
        int m = 0;

        for (int i = 0; i < 36; i++) {

            number_button[i] = new JButton(number_text[m]);
            number_button[i].setSize(40, 40);
            number_button[i].addActionListener(this);
            number_button[i].setBackground(norm_buttonColor);
            number_button[i].setForeground(Color.WHITE);
           number_button[i].setFont(button_font);

            m++;
           
            if(check(operator_check,number_text[i]))
            {
              number_button[i].setBackground(oper_buttonColor);
              number_button[i].setForeground(new Color(27,28,30,255));

            }
          

            pane_numb.add(number_button[i]);

        }
       //pane_text.setForeground(Color.WHITE);;
        frame.add(pane_text);
        frame.add(pane_numb);
        frame.setLayout(new GridLayout(2, 1));
        frame.setSize(580, 720);
       

    }

    private boolean check_int(String s) {
        try {
            Double number = Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean check(String array[], String a) {
        boolean ans = false;
        for (int i = 0; i < array.length; i++) {
            if (a == array[i]) {
                ans = true;
            }

        }
        return ans;

    }

    // loop through every charachter in the text area message and relate it to the
    // number_text;
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //screen clearing upon entering another sum
        if (display_clear == true) {
            writing_pane.setText(null);
            display_clear = false;
        }
        //memory of last answer
        if (memory != 0 ) {
             for(int i = 0; i < 36; i++)
             {
                if(source==number_button[i] && check(special_op,number_text[i]) && token.size()==0)
         {       token.add(Double.toString(memory));
                 writing_pane.setText(writing_pane.getText().concat("ANS"));
                  memory = 0;
         }
             }
           
        }
        for (int i = 0; i < 36; i++) {
//input handling and storage in array list
            if (source == number_button[i] && number_text[i] != "=" && number_text[i] != "del" && number_text[i]!="C" && number_text[i]!="rad")
            {

                writing_pane.setText(writing_pane.getText().concat(number_text[i]));

                if (token.size() > 0 && check(int_check, number_text[i])) {

                    if (check_int(token.get(token.size() - 1))) {

                        token.add(token.get(token.size() - 1).concat(number_text[i]));
                        token.remove(token.size() - 2);

                    } else if (!check_int(token.get(token.size() - 1))) {
                        token.add(number_text[i]);
                    }
                }
              else {
                    token.add(number_text[i]);
                }
                  //managing of special cases like brackets and constants
                if(token.size()>0 && check_int(token.getLast()))
                {
                    if(token.size()==2)
                    {
                        if(token.get(token.size()-2)=="-")
                        {
                             token.add(token.get(token.size()-2).concat(token.getLast()));
                             token.remove(token.get(token.size()-3));
                             token.remove(token.get(token.size()-2));
                        }

                    }
                    if(token.size()>=4)
                    {
                        if(token.get(token.size()-2)=="-" && check(operators,token.get(token.size()-3)))
                        {
                            token.add(token.get(token.size()-2).concat(token.getLast()));
                            token.remove(token.get(token.size()-3));
                            token.remove(token.get(token.size()-2));

                        }

                    }
                    if(token.size()>1 && token.get(token.size()-2)==")")
                    {
                        String s=token.getLast();
                        token.removeLast();
                        token.add("x");
                        token.add(s);

                    }
                    
               
                }
                if(token.size()>1 && token.getLast()=="(")
                {
                    if(check_int(token.get(token.size()-2)))
                    {
                        String s=token.getLast();
                    token.removeLast();
                    token.add("x");
                    token.add(s);
                    }

                }
                if(token.size()>=2 && check(special_op,token.getLast()) && check_int(token.get(token.size()-2)))
                {
                    String add=token.getLast();
                    token.removeLast();
                    token.add("x");
                    token.add(add);
         
                }
                if(token.getLast()=="pi")
                {
                    token.removeLast();
                    token.add(Double.toString(Math.PI));
                    
                }
                if(token.getLast()=="E")
                {
                    token.removeLast();
                    token.add(Double.toString(Math.E));
    
                }
                
    
                
              System.out.println(token);

            }

            
        
        }
        //clear  button
        if (source == number_button[5]) {
            token.clear();
            writing_pane.setText(" ");
            
        }
         //solution button
        if (source == number_button[28]) {
            operations operations = new operations(token);
      
 
           final_ans = operations.answer;
            memory = final_ans;
            writing_pane.setText(Double.toString(final_ans));
            token.clear();
            display_clear = true;

        }
        if(source==number_button[29])
        {
                   String S=token.getLast();
                    token.removeLast();
                    token.add(Double.toString(Math.toRadians(Double.valueOf(S))));
                    writing_pane.setText(token.getLast());

        }

       
    }
    

    public static void main(String[] args)

    {

        new calculator();

    }

}