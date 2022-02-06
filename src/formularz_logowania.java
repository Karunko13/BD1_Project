import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;

public class formularz_logowania {
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
    public JLabel loginLabel;
    public JLabel hasloLabel;

    public formularz_logowania(db_connection mainWindow, Connection c) {
        logConnection = c;
        this.mainWindow = mainWindow;
        login_id = -2;
        tmp = mainWindow;
        panel2 = new ImagePanel(new ImageIcon("images/recdar.jpg").getImage());
        label = new JLabel("", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 25));
        label.setForeground(Color.red);

        zaloguj = new JButton("Logowanie");
        zarejestruj = new JButton("Rejestracja");
        powrot = new JButton("Powrót");

        zaloguj.setFont(new Font("Verdana", Font.BOLD, 25));
        zaloguj.setForeground(Color.white);
        zaloguj.setBounds(75, 100, 100, 150);

        zaloguj.setFocusPainted(false);
        zaloguj.setContentAreaFilled(false);

        powrot.setFont(new Font("Verdana", Font.BOLD, 25));
        powrot.setForeground(Color.white);
        powrot.setBounds(75, 100, 100, 150);

        powrot.setFocusPainted(false);
        powrot.setContentAreaFilled(false);

        panel2.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        panel2.setLayout(new GridLayout(0, 1));
        panel2.add(label);
        loginLabel = new JLabel("Podaj login:");
        loginLabel.setForeground(Color.white);
        hasloLabel = new JLabel("Podaj haslo:");
        hasloLabel.setForeground(Color.white);

        panel2.add(zaloguj);

        panel2.add(powrot);
        log = new JTextField(32);
        pass = new JTextField(32);

        panel2.add(loginLabel);
        panel2.add(log);
        panel2.add(hasloLabel);
        panel2.add(pass);
        zaloguj.setText("Wprowadz dane i kliknij tutaj aby się zalogować!");
        zaloguj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                l = log.getText();
                ps = pass.getText();
                login_id = mainWindow.checkPasswd(l, ps);
                if (login_id > -1) {
                    menu f = new menu(mainWindow, login_id, logConnection, tmp);
                    mainWindow.frame.getContentPane().removeAll();
                    mainWindow.frame.getContentPane().add(f.menuPanel);
                    mainWindow.frame.setTitle("BD PROJEKT - Menu");
                    mainWindow.frame.validate();

                } else {
                    label.setVisible(true);
                    label.setText("Bledne dane logowania!");
                }

                flag = 1;

            }
        });

        powrot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login_rejestr m = new login_rejestr(mainWindow, logConnection);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.panel2, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Zaloguj");
                mainWindow.frame.validate();
            }
        });

    }
}