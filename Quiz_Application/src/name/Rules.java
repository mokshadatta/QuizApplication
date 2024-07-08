package name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame {
	
	String name;
	JButton start, back;
	JLabel heading, rules;
	
	Rules(String name){
		this.name = name;
		setLayout(null);
		
		heading = new JLabel("Welcome " + name + " to 123 Quiz");
		heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        
        rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
            "<html>"+ 
                "1. Please answer all the Questions" + "<br><br>" +
                "2. Quiz contains Multiple Choice Questions" + "<br><br>" +
                "3. Only one option is allowed" + "<br><br>" +
                "4. Dont ask your friends for answers, they might be doing Inky Pinky Ponky" + "<br><br>" +
                "5. Score will be Awarded for each correct answer" + "<br><br>" +
            "<html>"
        );
        add(rules);
        
        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        add(back);
        
        start = new JButton("Start");
        start.setBounds(400, 500, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        add(start);
        
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
        
        start.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		if(ae.getSource() == start) {
        			setVisible(false);
        			new Quiz(name);
        		}
        	}
        });
        
        back.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		if(ae.getSource() == back) {
        			setVisible(false);
        			new Login();
        		}
        	}
        });
	}
	
	public static void main(String[] args) {
		new Rules("User");
	}
}
