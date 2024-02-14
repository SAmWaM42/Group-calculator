import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

class evaluate {
    TextField values;
    int numbers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
    String name[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

    public evaluate(TextField value) {
        this.values = value;
        String content = value.getText();
        char content2[] = content.toCharArray();
        for (int i = 0; i < value.getColumns(); i++) {
            if (content2[i] == '+') {

            }
        }

    }

}

public class calc_test implements ActionListener {
    JFrame frame;
    JPanel pane_text;
    JPanel pane_numb;
    JTextField writing_pane;
    String number_text[] = { "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "x", "0", "=", ".", "/" };
    int numbers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
    String int_check[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
    ArrayList<String> token = new ArrayList<String>();
    JButton number_button[] = new JButton[16];
    JButton special_button[];
    int memory[];
    int multiplier_x = 0;
    int multiplier_y = 0;
    int padding = 5;

    public calc_test() {

        frame = new JFrame("Calculator");
        frame.setVisible(true);
        pane_text = new JPanel(new GridLayout(1, 1));
        pane_numb = new JPanel(new GridLayout(4, 4, 6, 6));
        pane_text.setSize(frame.getWidth(), 30);
        pane_numb.setSize(frame.getWidth(), frame.getHeight() - pane_text.getHeight());
        int x = frame.getWidth();
        int y = frame.getHeight();
        writing_pane = new JTextField();
        writing_pane.setColumns(1000);
        writing_pane.setVisible(true);
        writing_pane.setBounds(0, 0, y, x / 5);
        Font font = new Font("Gotham", writing_pane.getFont().getStyle(), 32);
        writing_pane.setFont(font);
        pane_text.add(writing_pane);

        int m = 0;

        for (int i = 0; i < 16; i++) {

            number_button[i] = new JButton(number_text[m]);
            number_button[i].setSize(40, 40);
            number_button[i].addActionListener(this);

            if (i == 11) {
                number_button[i].setBackground(Color.BLUE);
            } else {
                number_button[i].setBackground(Color.BLACK);
            }
            m++;

            pane_numb.add(number_button[i]);

        }

        frame.add(pane_text);
        frame.add(pane_numb);
        frame.setLayout(new GridLayout(2, 1));

        frame.setSize(450, 260);

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
        boolean ans=false;
        for (int i = 0; i < array.length; i++) {
            if (a==array[i])
             {
                ans = true;
            } else 
            {
                ans = false;
            }

        }
        return ans;

    }

    // loop through every charachter in the text area message and relate it to the
    // number_text;
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < 16; i++) {
            
            writing_pane.setText(writing_pane.getText().concat(number_text[i]));

            if (source == number_button[i] && number_text[i] != "=") {
             
                if (token.size() > 0 && check(int_check,number_text[i])) {

                    if (check_int(token.get(token.size() - 1))) {
                        token.add(token.get(token.size() - 1).concat(number_text[i]));

                        token.remove(token.size() - 2);

                    } else if (!check_int(token.get(token.size() - 1))) {
                        token.add(number_text[i]);
                    }
                }
                if (token.size() == 0) {
                    token.add(number_text[i]);
                }

                System.out.println(token);

            }

        }
    }

    public static void main(String[] args)

    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new calc_test();

    }

}