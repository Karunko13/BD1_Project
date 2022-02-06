
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.*;

import java.time.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JList;

public class admin_panel {

    public ImagePanel adminPanel;
    public JButton zatwierdzButton;
    public JButton menuButton;
    public JButton wyswietl;
    public JLabel pokojLabel1;
    public JLabel pokojLabel2;

    public JComboBox<hotel_wrapper> lista_hoteli;

    public db_connection mainWindow;
    public int login_id;
    public Connection a;
    public String hotele[] = { "Zakopane", "Warszawa", "Gdynia" };

    public String wybrany_hotel;

    public int liczba_doroslych;
    public int liczba_dzieci;

    public int liczba_osob_basen;
    public int liczba_osob_silownia;
    public int liczba_osob_sauna;

    public int aktualna_cena;
    public long daysBetween;

    public ArrayList<pracownik_wrapper> lista_prac;
    public DefaultListModel<pracownik_wrapper> lista_wrapper;
    public JList<pracownik_wrapper> my_pracownicy;

    public String imie_p;
    public int prac_id;

    public admin_panel(Connection c, db_connection mainWindow, int id, String wyp) {

        this.mainWindow = mainWindow;
        a = c;
        login_id = id;

        adminPanel = new ImagePanel(new ImageIcon("images/dodrez.jpg").getImage());
        // adminPanel.add(textInput, BorderLayout.CENTER);
        adminPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 100));
        adminPanel.setLayout(null);

        if (wyp.equals("Zakopane")) {
            wypelnijPrac("Zakopane");

        } else if (wyp.equals("Warszawa")) {
            wypelnijPrac("Warszawa");
        } else if (wyp.equals("Gdynia")) {
            wypelnijPrac("Gdynia");
        } else {
            System.out.println("domyslnie puste");
        }

        wybrany_hotel = wyp;
        lista_hoteli = new JComboBox<>();
        lista_hoteli.setBounds(50, 50, 50, 100);
        lista_hoteli.setSize(300, 50);
        lista_hoteli.setLocation(100, 150);
        lista_hoteli.addItem(new hotel_wrapper("Wybierz hotel", -1));
        for (int i = 0; i < 3; i++) {
            lista_hoteli.addItem(new hotel_wrapper(hotele[i], i));
        }
        lista_hoteli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object item2 = lista_hoteli.getSelectedItem();
                wybrany_hotel = ((hotel_wrapper) item2).getName();

            }
        });
        adminPanel.add(lista_hoteli);

        wyswietl = new JButton("Wyświetl pracowników");
        wyswietl.setBounds(50, 50, 50, 100);
        wyswietl.setSize(300, 50);
        wyswietl.setLocation(100, 250);
        wyswietl.setFocusPainted(false);
        wyswietl.setContentAreaFilled(false);
        wyswietl.setFont(new Font("Verdana", Font.BOLD, 15));
        wyswietl.setForeground(Color.white);

        adminPanel.add(wyswietl);
        wyswietl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                admin_panel m = new admin_panel(a, mainWindow, login_id, wybrany_hotel);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.adminPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Moje rezerwacje");
                mainWindow.frame.validate();
            }
        });

        JButton zwolnij = new JButton();
        zwolnij = new JButton("Zwolnij wybranego pracownika");
        zwolnij.setBounds(50, 50, 50, 100);
        zwolnij.setSize(300, 50);
        zwolnij.setLocation(100, 350);
        zwolnij.setFocusPainted(false);
        zwolnij.setContentAreaFilled(false);
        zwolnij.setFont(new Font("Verdana", Font.BOLD, 15));
        zwolnij.setForeground(Color.white);

        adminPanel.add(zwolnij);
        zwolnij.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String msg = mainWindow.zwolnijPracownika(prac_id);

                admin_panel m = new admin_panel(a, mainWindow, login_id, wybrany_hotel);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.adminPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Moje rezerwacje");
                mainWindow.frame.validate();

            }
        });

        JButton powrotmenu = new JButton();
        powrotmenu = new JButton("Powrót do menu");
        powrotmenu.setBounds(50, 50, 50, 100);
        powrotmenu.setSize(300, 50);
        powrotmenu.setLocation(100, 450);
        powrotmenu.setFocusPainted(false);
        powrotmenu.setContentAreaFilled(false);
        powrotmenu.setFont(new Font("Verdana", Font.BOLD, 15));
        powrotmenu.setForeground(Color.white);

        adminPanel.add(powrotmenu);
        powrotmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                menu powrot = new menu(mainWindow, login_id, a, mainWindow);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.getContentPane().add(powrot.menuPanel);
                mainWindow.frame.setTitle("BD PROJEKT - Menu");
                mainWindow.frame.validate();
            }
        });

    }

    public void wypelnijPrac(String val) {

        lista_prac = mainWindow.listaPracownikow(val);
        lista_wrapper = new DefaultListModel<pracownik_wrapper>();

        for (int i = 0; i < lista_prac.size(); i++) {
            lista_wrapper.addElement(lista_prac.get(i));

        }

        my_pracownicy = new JList(lista_wrapper);
        adminPanel.add(my_pracownicy);
        my_pracownicy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        my_pracownicy.setSelectedIndex(1);
        my_pracownicy.setSize(1300, 200);
        my_pracownicy.setLocation(500, 50);

        my_pracownicy.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Object itemC = my_pracownicy.getSelectedValue();
                imie_p = ((pracownik_wrapper) itemC).getImie();
                prac_id = ((pracownik_wrapper) itemC).getIdPrac();

            }
        });
    }

}