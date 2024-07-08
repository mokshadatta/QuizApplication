package name;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Score extends JFrame {
	private Quiz quizInstance;
	
	Score(String name, int score, Quiz quizInstance){
		
		this.quizInstance = quizInstance;
		
		setBounds(400, 150, 750, 550);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("score.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 100, 300, 250);
        add(image);
        
        JLabel heading = new JLabel("Thankyou " + name + " for playing 123 Quiz");
        heading.setBounds(170, 30, 700, 30);
        heading.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        add(heading);
        
        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        add(lblscore);
        
        JButton again = new JButton("Play Again");
        again.setBounds(380, 270, 120, 30);
        again.setBackground(new Color(30, 144, 255));
        again.setForeground(Color.WHITE);
        add(again);
        
        setVisible(true);
        
        again.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		dispose();
        		quizInstance.resetQuiz();
                new Login();
        	}
        });
        
        saveScoreToDatabase(name, score);
	}
	
	private void saveScoreToDatabase(String name, int score) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nitish", "root", "nitishpapp")) {
	        String sql = "INSERT INTO quiz_scores (name, score, date_time) VALUES (?, ?, ?)";
	        System.out.println("Inserting Records of " + name);

	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, name);
	            preparedStatement.setInt(2, score);

	            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
	            preparedStatement.setTimestamp(3, currentTimestamp);

	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		new Score("User", 0, new Quiz("User"));
	}

}
