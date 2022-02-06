import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;

public class login_rejestr {
    public db_connection mainWindow;
    public db_connection tmp;
    public JPanel panel;
    public ImagePanel panel2;
    public JLabel label;
    public JTextField log;
    public JTextField pass;
    public JButton zaloguj;
    public JButton zarejestruj;
    public int login_id;
    public Connection logConnection;
    public static Integer flag = 0;
    public String l;
    public String ps;
    public JButton powrot;

    public login_rejestr(db_connection mainWindow, Connection c) {
        logConnection = c;
        this.mainWindow = mainWindow;
        login_id = -2;
        tmp = mainWindow;
        panel2 = new ImagePanel(new ImageIcon("images/recdar.jpg").getImage());
        label = new JLabel("nothing", SwingConstants.CENTER);

        zaloguj = new JButton("Logowanie");
        zarejestruj = new JButton("Rejestracja");
        powrot = new JButton("Powrót");
        zaloguj.setFont(new Font("Verdana", Font.BOLD, 25));
        zaloguj.setForeground(Color.white);
        zaloguj.setBounds(75, 100, 100, 150);

        zaloguj.setFocusPainted(false);
        zaloguj.setContentAreaFilled(false);

        zarejestruj.setFont(new Font("Verdana", Font.BOLD, 25));
        zarejestruj.setForeground(Color.white);
        zarejestruj.setBounds(75, 100, 100, 150);

        zarejestruj.setFocusPainted(false);
        zarejestruj.setContentAreaFilled(false);

        powrot.setFont(new Font("Verdana", Font.BOLD, 25));
        powrot.setForeground(Color.white);
        powrot.setBounds(75, 100, 100, 150);

        powrot.setFocusPainted(false);
        powrot.setContentAreaFilled(false);

        panel2.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        panel2.setLayout(new GridLayout(0, 1));
        panel2.add(label);
        panel2.add(zarejestruj);
        label.setVisible(false);
        powrot.setVisible(false);
        panel2.add(zaloguj);
        panel2.add(powrot);
        log = new JTextField(32);
        pass = new JTextField(32);
        log.setBackground(Color.GRAY);

        zaloguj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                formularz_logowania m = new formularz_logowania(mainWindow, logConnection);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.panel2, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Zaloguj");
                mainWindow.frame.validate();

            }
        });
        panel2.add(zarejestruj);

        zarejestruj.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                formularz_rejestracji f = new formularz_rejestracji(mainWindow, logConnection, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(f.registerPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Zarejestruj");
                mainWindow.frame.validate();
            }
        });
        powrot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularz_rejestracji m = new formularz_rejestracji(mainWindow, logConnection, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.registerPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Zaloguj");
                mainWindow.frame.validate();
            }
        });

    }
}