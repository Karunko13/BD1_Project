import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import java.awt.Color;

public class formularz_rejestracji {
    public db_connection mainWindow;
    public JPanel registerPanel;
    public JButton confirm;
    public JButton back;
    public JTextField imieText;
    public JTextField nazwiskoText;
    public JTextField emailText;
    public JTextField telefonText;
    public JTextField loginText;
    public JTextField hasloText;
    public Connection a;
    public JLabel imieLabel;
    public JLabel nazwiskoLabel;
    public JLabel emailLabel;
    public JLabel telefonLabel;
    public JLabel loginLabel;
    public JLabel hasloLabel;
    public JLabel confirmLabel;
    public int login_id;

    public formularz_rejestracji(db_connection mainWindow, Connection c, int id) {
        login_id = id;
        this.mainWindow = mainWindow;
        a = c;
        registerPanel = new ImagePanel(new ImageIcon("images/recdar.jpg").getImage());

        confirm = new JButton("Zatwierdz");
        confirm.setFont(new Font("Verdana", Font.BOLD, 25));
        confirm.setForeground(Color.white);
        confirm.setBounds(75, 100, 100, 150);

        confirm.setFocusPainted(false);
        confirm.setContentAreaFilled(false);

        back = new JButton("Powrot");
        back.setFont(new Font("Verdana", Font.BOLD, 25));
        back.setForeground(Color.white);
        back.setBounds(75, 100, 100, 150);

        back.setFocusPainted(false);
        back.setContentAreaFilled(false);

        imieText = new JTextField(32);
        imieText.setBackground(new Color(222, 228, 231));
        imieText.setOpaque(true);

        nazwiskoText = new JTextField(32);
        emailText = new JTextField(32);
        telefonText = new JTextField(32);
        loginText = new JTextField(32);
        hasloText = new JTextField(32);
        imieLabel = new JLabel("Podaj imie:");
        imieLabel.setForeground(Color.white);
        nazwiskoLabel = new JLabel("Podaj nazwisko:");
        nazwiskoLabel.setForeground(Color.white);
        emailLabel = new JLabel("Podaj e-mail:");
        emailLabel.setForeground(Color.white);
        telefonLabel = new JLabel("Podaj telefon:");
        telefonLabel.setForeground(Color.white);
        loginLabel = new JLabel("Podaj login:");
        loginLabel.setForeground(Color.white);
        hasloLabel = new JLabel("Podaj haslo:");
        hasloLabel.setForeground(Color.white);

        confirmLabel = new JLabel("", SwingConstants.CENTER);
        confirmLabel.setVisible(false);
        confirmLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        confirmLabel.setForeground(Color.white);
        confirmLabel.setBounds(75, 100, 100, 150);

        registerPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        registerPanel.setLayout(new GridLayout(0, 1));
        registerPanel.add(confirmLabel);
        registerPanel.add(imieLabel);
        registerPanel.add(imieText);
        registerPanel.add(nazwiskoLabel);
        registerPanel.add(nazwiskoText);
        registerPanel.add(emailLabel);
        registerPanel.add(emailText);
        registerPanel.add(telefonLabel);
        registerPanel.add(telefonText);
        registerPanel.add(loginLabel);
        registerPanel.add(loginText);
        registerPanel.add(hasloLabel);
        registerPanel.add(hasloText);
        registerPanel.add(confirm);
        registerPanel.add(back);

        confirm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String im = imieText.getText();
                String nw = nazwiskoText.getText();
                String em = emailText.getText();
                String tel = telefonText.getText();
                String log = loginText.getText();
                String pass = hasloText.getText();
                if (im != null && !im.isEmpty() && nw != null && !nw.isEmpty() && em != null && !em.isEmpty()
                        && log != null && !log.isEmpty() && pass != null && !pass.isEmpty())

                {
                    String flag = mainWindow.zarejestruj(im, nw, em, tel, log, pass, "Standardowy");
                    confirmLabel.setText(flag);
                    confirmLabel.setVisible(true);
                } else {
                    confirmLabel.setText("Wypełnij wszystkie pola!");
                    confirmLabel.setVisible(true);
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login_rejestr m = new login_rejestr(mainWindow, a);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.panel2, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Zaloguj");
                mainWindow.frame.validate();
            }
        });

    }
}