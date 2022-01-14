import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DatabaseConnection {
    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JLabel autor;
    public JButton start;
    public Connection c = null;

    public DatabaseConnection() {

        try {
            String dbaseURL = "jdbc:postgresql://localhost/u9nizio";
            String username = "u9nizio";
            String password = "9nizio";
            c = DriverManager.getConnection(dbaseURL, username, password);

        } catch (SQLException se) {
            System.out.println("Brak polaczenia z baza danych, wydruk logu sledzenia i koniec.");
            se.printStackTrace();
            System.exit(1);
        }

        if (c != null) {
            System.out.println("Polaczenie z baza danych OK ! ");
        } else {
            System.out.println("Brak polaczenia z baza, dalsza czesc aplikacji nie jest wykonywana.");
        }

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel("Projekt BD1 Mateusz Nizio", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        autor = new JLabel("Symulacja bazy danych Hotelu", SwingConstants.CENTER);
        start = new JButton("Zaloguj lub zarejestruj");
        panel.setBorder(BorderFactory.createEmptyBorder(75, 100, 100, 150));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(label);
        panel.add(autor);
        panel.setBackground(Color.gray);
        panel.add(start);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BD PROJEKT - Start");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }
}
