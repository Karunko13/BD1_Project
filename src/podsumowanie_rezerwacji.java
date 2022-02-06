import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class podsumowanie_rezerwacji {

    public JPanel rezerwacjaPanel;
    public JButton menuButton;
    public JLabel statement;
    public db_connection mainWindow;
    public int login_id;
    public Connection a;

    public podsumowanie_rezerwacji(Connection c, db_connection mainWindow, int id, String st) {
        login_id = id;
        this.mainWindow = mainWindow;
        a = c;
        rezerwacjaPanel = new ImagePanel(new ImageIcon("images/dodrez.jpg").getImage());
        rezerwacjaPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        rezerwacjaPanel.setLayout(new GridLayout(0, 1));

        statement = new JLabel(st, SwingConstants.CENTER);
        statement.setFont(new Font("Verdana", Font.BOLD, 25));
        statement.setForeground(Color.white);
        statement.setBounds(75, 100, 100, 150);

        rezerwacjaPanel.add(statement);

        menuButton = new JButton("Powrot do menu");
        menuButton.setFont(new Font("Verdana", Font.BOLD, 25));
        menuButton.setForeground(Color.white);
        menuButton.setBounds(75, 100, 100, 150);

        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                menu mymenu = new menu(mainWindow, login_id, a, mainWindow);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.getContentPane().add(mymenu.menuPanel);
                mainWindow.frame.setTitle("BD PROJEKT - Menu");
                mainWindow.frame.validate();

            }
        });
        rezerwacjaPanel.add(menuButton);
    }
}
