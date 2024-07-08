package name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz extends JFrame {

    String questions[][] = new String[10][5];
    String ans[][] = new String[10][2];
    String user_ans[][] = new String[10][1];
    JLabel qno, ques;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, previous, submit;

    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;

    String name;

    Quiz(String name) {
        this.name = name;
        setBounds(50, 0, 800, 500);
        getContentPane().setBackground(new Color(245, 222, 179));
        setLayout(null);

        qno = new JLabel();
        qno.setBounds(5, 10, 50, 30);
        qno.setForeground(Color.BLACK);
        qno.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        add(qno);

        ques = new JLabel();
        ques.setBounds(50, 10, 730, 30);
        ques.setForeground(Color.BLACK);
        ques.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        add(ques);

        questions[0][0] = "What is the capital city of Australia?";
        questions[0][1] = "Sydney";
        questions[0][2] = "canberra";
        questions[0][3] = "Melbourne";
        questions[0][4] = "Brisane";

        questions[1][0] = "What gas do plants absorb during photosynthesis?";
        questions[1][1] = "Oxygen";
        questions[1][2] = "Carbon Monoxide";
        questions[1][3] = "Carbon Dioxide";
        questions[1][4] = "Nitrogen";

        questions[2][0] = "In which film did Johnny Depp play the character Jack Sparrow?";
        questions[2][1] = "Pirates of the Caribbean";
        questions[2][2] = "Edward Scissorhands";
        questions[2][3] = "Alice in Wonderland";
        questions[2][4] = "Sweeney Todd";

        questions[3][0] = "What does the acronym \"HTML\" stand for?";
        questions[3][1] = "Hyper Transfer Markup Language";
        questions[3][2] = "High Tech Modern Language";
        questions[3][3] = "Human Technology Management Logic";
        questions[3][4] = "Hypertext Markup Language";

        questions[4][0] = "Who was the first President of the United States?";
        questions[4][1] = "Thomas Jefferson";
        questions[4][2] = "John Adams";
        questions[4][3] = "George Washington";
        questions[4][4] = "James Madison";

        questions[5][0] = "Name two countries that are located on the equator.";
        questions[5][1] = "Peru and Chile";
        questions[5][2] = "Kenya and Uganda";
        questions[5][3] = "Brazil and Uganda";
        questions[5][4] = "France and Germany";

        questions[6][0] = "Who wrote the play \"Romeo and Juliet\"?";
        questions[6][1] = "Charles Dickens";
        questions[6][2] = "Jane Austen";
        questions[6][3] = "Mark Twain";
        questions[6][4] = "William Shakespeare";

        questions[7][0] = "In which sport would you perform a slam dunk?";
        questions[7][1] = "Soccer";
        questions[7][2] = "Basketball";
        questions[7][3] = "Tennis";
        questions[7][4] = "Golf";

        questions[8][0] = "What is the name of the lead singer of the rock band Queen?";
        questions[8][1] = "Freddie Mercury";
        questions[8][2] = "Goger Taylor";
        questions[8][3] = "Brian May";
        questions[8][4] = "John Deacon";

        questions[9][0] = "Who is the protagonist of the \"Harry Potter\" book series?";
        questions[9][1] = "Gandalf";
        questions[9][2] = "Dumbldore";
        questions[9][3] = "Harry Potter";
        questions[9][4] = "Merlin";

        ans[0][1] = "canberra";
        ans[1][1] = "Carbon Dioxide";
        ans[2][1] = "Pirates of the Caribbean";
        ans[3][1] = "Hypertext Markup Language";
        ans[4][1] = "George Washington";
        ans[5][1] = "Kenya and Uganda";
        ans[6][1] = "William Shakespeare";
        ans[7][1] = "Basketball";
        ans[8][1] = "Freddie Mercury";
        ans[9][1] = "Harry Potter";

        for (String[] question : questions) {
            List<String> answersList = Arrays.asList(Arrays.copyOfRange(question, 1, question.length));
            Collections.shuffle(answersList);
            System.arraycopy(answersList.toArray(), 0, question, 1, answersList.size());
        }

        opt1 = new JRadioButton();
        opt1.setBounds(20, 60, 700, 30);
        opt1.setBackground(new Color(245, 222, 179));
        opt1.setForeground(Color.BLACK);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);
        
        opt2 = new JRadioButton();
        opt2.setBounds(20, 110, 700, 30);
        opt2.setBackground(new Color(245, 222, 179));
        opt2.setForeground(Color.BLACK);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);
        
        opt3 = new JRadioButton();
        opt3.setBounds(20, 150, 700, 30);
        opt3.setBackground(new Color(245, 222, 179));
        opt3.setForeground(Color.BLACK);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);
        
        opt4 = new JRadioButton();
        opt4.setBounds(20, 190, 700, 30);
        opt4.setBackground(new Color(245, 222, 179));
        opt4.setForeground(Color.BLACK);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);
        
        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        previous = new JButton("Previous");
        previous.setBounds(30, 250, 200, 40);
        previous.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        previous.setBackground(new Color(144, 173, 147));
        previous.setForeground(Color.BLACK);
        previous.setEnabled(false);
        add(previous);

        next = new JButton("Next");
        next.setBounds(280, 250, 200, 40);
        next.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        next.setBackground(new Color(173, 216, 230));
        next.setForeground(Color.BLACK);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(550, 250, 200, 40);
        submit.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        submit.setBackground(new Color(204, 85, 51));
        submit.setForeground(Color.BLACK);
        submit.setEnabled(false);
        add(submit);

        start(count);

        setVisible(true);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == next) {
                    opt1.setEnabled(true);
                    opt2.setEnabled(true);
                    opt3.setEnabled(true);
                    opt4.setEnabled(true);

                    ans_given = 1;
                    if (groupoptions.getSelection() == null) {
                        user_ans[count][0] = "";
                    } else {
                        user_ans[count][0] = groupoptions.getSelection().getActionCommand();
                    }

                    if (count == 8) {
                        next.setEnabled(false);
                        submit.setEnabled(true);
                    }

                    count++;
                    start(count);
                }
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == previous) {
                    if (count > 0) {
                        count--;
                        start(count);
                        next.setEnabled(true);

                        if (count == 0) {
                            previous.setEnabled(false);
                        }
                    }
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == submit) {
                    ans_given = 1;
                    if (groupoptions.getSelection() == null) {
                        user_ans[count][0] = "";
                    } else {
                        user_ans[count][0] = groupoptions.getSelection().getActionCommand();
                    }

                    for (int i = 0; i < user_ans.length; i++) {
                        if (user_ans[i][0].equals(ans[i][1])) {
                            score += 10;
                        } else {
                            score += 0;
                        }
                    }
                    setVisible(false);
                    new Score(name, score, Quiz.this);
                }
            }
        });
    }

    public void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        ques.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);
        
        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        
        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        
        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);

        groupoptions.clearSelection();

        if (count == 0) {
            previous.setEnabled(false);
        } else {
            previous.setEnabled(true);
        }
    }

    public void resetQuiz() {
        ans_given = 0;
        count = 0;
        score = 0;
        start(count);
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
