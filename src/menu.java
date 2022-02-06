import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.border.Border;

public class menu {

    public int login_id;
    public db_connection mainWindow;
    public Connection a;
    public ImagePanel menuPanel;
    public JLabel menuLabel;
    public JLabel helloLabel;
    public JLabel infoLabel;
    public JButton rezerwacja;
    public JButton moje_rezerwacje;
    public JButton adminMenu;
    public JButton wyloguj;
    public db_connection loginDatabase;

    public menu(db_connection mainWindow, int id, Connection c, db_connection tmp) {
        a = c;
        login_id = id;
        loginDatabase = tmp;
        this.mainWindow = mainWindow;

        menuPanel = new ImagePanel(new ImageIcon("images/blured.jpg").getImage());
        menuPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        menuPanel.setLayout(new GridLayout(0, 1));
        if (login_id == 1) {
            helloLabel = new JLabel(
                    "<html>Zalogowano jako: " + mainWindow.getUserName(login_id)
                            + "<br/><br/> Typ konta: Administrator",
                    SwingConstants.CENTER);

            helloLabel.setFont(new Font("Verdana", Font.BOLD, 25));
            helloLabel.setForeground(Color.white);
            helloLabel.setBounds(75, 100, 100, 150);
        } else {
            helloLabel = new JLabel(
                    "<html>Zalogowano jako: " + mainWindow.getUserName(login_id)
                            + "<br/><br/> Typ konta: Klient hotelu",
                    SwingConstants.CENTER);

            helloLabel.setFont(new Font("Verdana", Font.BOLD, 25));
            helloLabel.setForeground(Color.white);
            helloLabel.setBounds(75, 100, 100, 150);
        }

        menuPanel.add(helloLabel);

        menuLabel = new JLabel("Dostępne opcje: ", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        menuLabel.setForeground(Color.white);
        menuLabel.setBounds(75, 100, 100, 150);

        menuPanel.add(menuLabel);

        rezerwacja = new JButton("Nowa rezerwacja"); 
                                                     
        rezerwacja.setFont(new Font("Verdana", Font.BOLD, 25));
        rezerwacja.setForeground(Color.white);
        rezerwacja.setBounds(75, 100, 100, 150);

        rezerwacja.setFocusPainted(false);
        rezerwacja.setContentAreaFilled(false);

        rezerwacja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nowa_rezerwacja p = new nowa_rezerwacja(a, mainWindow, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(p.rezerwacjaPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Dokonaj rezerwacji");
                mainWindow.frame.validate();
            }
        });
        menuPanel.add(rezerwacja);

        moje_rezerwacje = new JButton("Moje rezerwacje");
        moje_rezerwacje.setFont(new Font("Verdana", Font.BOLD, 25));
        moje_rezerwacje.setForeground(Color.white);
        moje_rezerwacje.setBounds(75, 100, 100, 150);

        moje_rezerwacje.setFocusPainted(false);
        moje_rezerwacje.setContentAreaFilled(false);
        moje_rezerwacje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moje_zamowienia m = new moje_zamowienia(a, mainWindow, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.moje_rezerwacje_Uppanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Moje rezerwacje");
                mainWindow.frame.validate();
            }
        });
        menuPanel.add(moje_rezerwacje);
        if (login_id == 1) {
            adminMenu = new JButton("Menu administratora");
            menuPanel.add(adminMenu);
            adminMenu.setFont(new Font("Verdana", Font.BOLD, 25));
            adminMenu.setForeground(Color.white);
            adminMenu.setBounds(75, 100, 100, 150);
            //adminMenu.setVisible(false);
            adminMenu.setFocusPainted(false);
            adminMenu.setContentAreaFilled(false);

            adminMenu.setVisible(true);

            adminMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    admin_panel m = new admin_panel(a, mainWindow, login_id, "");
                    mainWindow.frame.getContentPane().removeAll();
                    mainWindow.frame.add(m.adminPanel, BorderLayout.CENTER);
                    mainWindow.frame.setTitle("BD PROJEKT - Moje rezerwacje");
                    mainWindow.frame.validate();
                }
            });
            
        }

    }
}