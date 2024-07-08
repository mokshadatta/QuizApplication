package name;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
	
	Login()
	{
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("login.jpeg"));
		JLabel image = new JLabel(i1);
		image.setBounds(0,0,600,500);
		add(image);
		
		JLabel heading = new JLabel("123 Quiz");
		heading.setBounds(800,60,300,45);
		heading.setFont(new Font("Times New Roman", Font.BOLD, 38));
		heading.setForeground(new Color(30,144,254));
		add(heading);
		
		JLabel name = new JLabel("Enter your Roll No.");
		name.setBounds(810,150,300,20);
		name.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name.setForeground(new Color(30,144,254));
		add(name);
		
		JTextField tfname = new JTextField();
		tfname.setBounds(735,200,300,25);
		tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);
        
        JButton rules = new JButton("Rules");
        rules.setBounds(735, 270, 120, 25);
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.WHITE);
        add(rules);
        
        JButton back = new JButton("Back");
        back.setBounds(915, 270, 120, 25);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        add(back);
        
        JLabel warn = new JLabel();
        warn.setBounds(735, 320, 300, 30);
        warn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(warn);
        
        setSize(1200,500);
        setLocation(200,150);
        setVisible(true);
        
        rules.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		if (ae.getSource() == rules) {
                    String name = tfname.getText();
                    if(name.isEmpty() || name.isBlank() || name.contains("-") || name.matches(".*[)(#$@;:+_=%^&/!<>{}].*")) {
                    	warn.setText("Please enter a Valid Id number");
                    }
                    else {
                    	setVisible(false);
                    	new Rules(name);
                    }
                }
        	}
        });
        
        back.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		if(ae.getSource() == back) {
        			setVisible(false);
        		}
        	}
        });
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
