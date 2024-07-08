package name;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Check extends JFrame {

    private Connection connection;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public Check() {
        JFrame frame = new JFrame("Quiz Scores");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");
        tableModel.addColumn("Quiz Datetime");

        table = new JTable(tableModel);
        JButton showTableButton = new JButton("Show Entire Table");
        JButton searchButton = new JButton("Search Score");
        JButton compareButton = new JButton("Compare Scores");
        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEntireTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchScore();
            }
        });

        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compareScores();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showTableButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(compareButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/nitish";
            String user = "root";
            String password = "nitishpapp";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error connecting to the database!",
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showEntireTable() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM quiz_scores");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");
                String quizDatetime = resultSet.getString("date_time");

                tableModel.addRow(new Object[]{name, score, quizDatetime});
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error fetching data from the database!",
                    "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchScore() {
        String name = JOptionPane.showInputDialog("Enter the ID:");
        try {
            String query = "SELECT * FROM quiz_scores WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            clearTable();

            while (resultSet.next()) {
                int score = resultSet.getInt("score");
                String quizDatetime = resultSet.getString("date_time");

                tableModel.addRow(new Object[]{name, score, quizDatetime});
            }

            preparedStatement.close();

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Name not found in the table!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error executing database query!",
                    "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void compareScores() {
        String name1 = JOptionPane.showInputDialog("Enter the first ID:");
        String name2 = JOptionPane.showInputDialog("Enter the second ID:");

        try {
            String query = "SELECT * FROM quiz_scores WHERE name = ? OR name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name1);
            preparedStatement.setString(2, name2);

            ResultSet resultSet = preparedStatement.executeQuery();

            clearTable();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");
                String quizDatetime = resultSet.getString("date_time");

                tableModel.addRow(new Object[]{name, score, quizDatetime});
            }

            preparedStatement.close();

            if (tableModel.getRowCount() < 2) {
                JOptionPane.showMessageDialog(this,
                        "One or both names not found in the table!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error executing database query!",
                    "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Check();
            }
        });
    }
}
