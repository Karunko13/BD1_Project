
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.*;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.time.temporal.ChronoUnit;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

public class nowa_rezerwacja {

    public ImagePanel rezerwacjaPanel;
    public JButton rezerwacjaButton;

    public double kwota_z_pokoi;
    public double kwota_z_uslug;

    public JComboBox<hotel_wrapper> lista_hoteli;
    public JComboBox<hotel_wrapper> lista_doroslych;
    public JComboBox<hotel_wrapper> lista_dzieci;
    public JComboBox<hotel_wrapper> basen;
    public JComboBox<hotel_wrapper> sauna;
    public JComboBox<hotel_wrapper> silownia;

    public JComboBox<pokoj_wrapper> pokojeDropList;
    public DefaultListModel<pokoj_wrapper> pokoj_select;
    public JList<pokoj_wrapper> my_pracownicy;

    public db_connection mainWindow;
    public int login_id;
    public Connection a;
    public String hotele[] = { "Zakopane", "Warszawa", "Gdynia" };
    public String dorosli[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    public String basenLiczba[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20" };
    public String wybrany_hotel;

    public int liczba_doroslych;
    public int liczba_dzieci;

    public int liczba_osob_basen;
    public int liczba_osob_silownia;
    public int liczba_osob_sauna;

    private int chosen_pokoj_liczba_miejsc;
    private int chosen_pokoj_id;
    private int chosen_pokoj_cena;

    public LocalDate date1;
    public LocalDate date2;
    public String data_pocz;
    public String data_kon;
    public ArrayList<Integer> lista_wolnych_pokoi;

    public int aktualna_cena;
    public long daysBetween;
    public JButton uslugiDod;
    public JLabel inst;

    public nowa_rezerwacja(Connection c, db_connection mainWindow, int id) {

        this.mainWindow = mainWindow;
        a = c;
        login_id = id;

        kwota_z_pokoi = 0;
        kwota_z_uslug = 0;

        rezerwacjaPanel = new ImagePanel(new ImageIcon("images/dodrez.jpg").getImage());
        // rezerwacjaPanel.add(textInput, BorderLayout.CENTER);
        rezerwacjaPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 100));
        rezerwacjaPanel.setLayout(null);

        inst = new JLabel("<html>Instrukcja rezerwacji: "
                + "<br/>1.Wybierz hotel "
                + "<br/>2.Wybierz datę zakwaterowania"
                + "<br/>3.Wybierz datę wymeldowania"
                + "<br/>4.Wybierz liczbę dorosłych"
                + "<br/>5.Wybierz liczbę dzieci "
                + "<br/>6.Sprawdz dostępność "
                + "<br/>7.Z listy wybierz odpowiadający hotel"
                + "<br/>8.Dodaj usługi dodatkowe "
                + "<br/>9.Złóż zamówienie "
                + "</html>", SwingConstants.CENTER);
        rezerwacjaPanel.add(inst);
        inst.setBounds(50, 50, 50, 100);
        inst.setSize(600, 600);
        inst.setLocation(1400, 50);
        inst.setFont(new Font("Verdana", Font.PLAIN, 20));
        inst.setForeground(Color.white);

        // lista hoteli mozliwych do wybrania
        lista_hoteli = new JComboBox<>();
        lista_hoteli.setBounds(50, 50, 50, 100);
        lista_hoteli.setSize(300, 50);
        lista_hoteli.setLocation(100, 50);
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
        rezerwacjaPanel.add(lista_hoteli);

        // kalendarz data rozpoczecia
        JButton b = new JButton("Data zakwaterowania");
        b.setBounds(50, 50, 50, 100);
        b.setSize(300, 50);
        b.setLocation(100, 150);
        final JTextField text = new JTextField(20);
        // text.setBounds(0, 0, 0, 0);
        text.setSize(300, 50);
        text.setLocation(100, 200);
        text.setEditable(false);
        JPanel p = rezerwacjaPanel;
        // p.add(label);
        p.add(text);
        p.add(b);
        final JFrame f = new JFrame();
        f.getContentPane().add(p);
        f.pack();
        f.setVisible(false);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                Calendar myCalendar = new Calendar(f);
                data_pocz = myCalendar.setPickedDate();
                text.setText(myCalendar.setPickedDate());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
                date1 = LocalDate.parse(myCalendar.setPickedDate(), formatter);

            }
        });

        // kalendarz data zakoczenia
        JButton b2 = new JButton("Data wymeldowania");
        b2.setBounds(50, 50, 50, 100);
        b2.setSize(300, 50);
        b2.setLocation(100, 300);
        final JTextField text2 = new JTextField(20);
        text2.setBounds(50, 50, 50, 100);
        text2.setSize(300, 50);
        text2.setLocation(100, 350);
        JPanel p2 = rezerwacjaPanel;
        // p2.add(label2);
        p2.add(text2);
        p2.add(b2);
        final JFrame f2 = new JFrame();
        f2.getContentPane().add(p2);
        f2.pack();
        f2.setVisible(false);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Calendar myCalendar2 = new Calendar(f2);
                data_kon = myCalendar2.setPickedDate();
                text2.setText(myCalendar2.setPickedDate());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
                date2 = LocalDate.parse(myCalendar2.setPickedDate(), formatter);

                calculateDays(date1, date2);

            }
        });

        // lista doroslych
        lista_doroslych = new JComboBox<>();
        // lista_doroslych.setEditable(true);
        lista_doroslych.setBounds(50, 50, 50, 100);
        lista_doroslych.setSize(300, 50);
        lista_doroslych.setLocation(100, 450);
        lista_doroslych.addItem(new hotel_wrapper("Liczba dorosłych", -1));
        for (int i = 0; i < 10; i++) {
            lista_doroslych.addItem(new hotel_wrapper(dorosli[i], i));
        }
        lista_doroslych.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object item3 = lista_doroslych.getSelectedItem();

                liczba_doroslych = 1 + ((hotel_wrapper) item3).getVal();
                lista_doroslych.setSelectedItem("Liczba dorosłych: " + liczba_doroslych);

            }
        });
        rezerwacjaPanel.add(lista_doroslych);

        // lista dzieci
        lista_dzieci = new JComboBox<>();
        lista_dzieci.setBounds(50, 50, 50, 100);
        lista_dzieci.setSize(300, 50);
        lista_dzieci.setLocation(100, 550);
        lista_dzieci.addItem(new hotel_wrapper("Liczba dzieci", -1));
        for (int i = 0; i < 10; i++) {
            lista_dzieci.addItem(new hotel_wrapper(dorosli[i], i));
        }
        lista_dzieci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object item4 = lista_dzieci.getSelectedItem();
                liczba_dzieci = 1 + ((hotel_wrapper) item4).getVal();

            }
        });
        rezerwacjaPanel.add(lista_dzieci);

        // przycisk odpowiedzialny za sprawdzenie dostepnosci pokoi w hotelu
        rezerwacjaButton = new JButton("Sprawdź dostępność");
        rezerwacjaButton.setBounds(50, 50, 50, 100);
        rezerwacjaButton.setSize(300, 50);
        rezerwacjaButton.setLocation(100, 650);
        JPanel pRez = rezerwacjaPanel;
        pRez.add(rezerwacjaButton);
        rezerwacjaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // showButtonUslugi();
                wyczyscPokojeDropList();
                wypelnijPokoje();

            }
        });

        JButton oblicz = new JButton();
        oblicz = new JButton("Reset");
        oblicz.setBounds(50, 50, 50, 100);
        oblicz.setSize(300, 50);
        oblicz.setLocation(100, 750);
        rezerwacjaPanel.add(oblicz);
        oblicz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nowa_rezerwacja p = new nowa_rezerwacja(a, mainWindow, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(p.rezerwacjaPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Dokonaj rezerwacji");
                mainWindow.frame.validate();
            }
        });

        JButton powrotmenu = new JButton();
        powrotmenu = new JButton("Powrót do menu");
        powrotmenu.setBounds(50, 50, 50, 100);
        powrotmenu.setSize(300, 50);
        powrotmenu.setLocation(100, 850);
        rezerwacjaPanel.add(powrotmenu);
        powrotmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                menu powrot = new menu(mainWindow, login_id, a, mainWindow);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.getContentPane().add(powrot.menuPanel);
                mainWindow.frame.setTitle("BD PROJEKT - Menu");
                mainWindow.frame.validate();
            }
        });

        uslugiDod = new JButton();
        uslugiDod = new JButton("Usługi dodatkowe");
        uslugiDod.setBounds(50, 50, 50, 100);
        uslugiDod.setSize(300, 50);
        uslugiDod.setLocation(900, 450);
        uslugiDod.setVisible(false);
        rezerwacjaPanel.add(uslugiDod);
        uslugiDod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                showUslugi();
            }
        });

        JButton podsumowanieButton = new JButton();
        podsumowanieButton = new JButton("Dokonaj rezerwacji");
        podsumowanieButton.setBounds(50, 50, 50, 100);
        podsumowanieButton.setSize(850, 50);
        podsumowanieButton.setLocation(650, 850);
        rezerwacjaPanel.add(podsumowanieButton);
        podsumowanieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String m2 = mainWindow.noweZamowienie(login_id, chosen_pokoj_id, liczba_doroslych, liczba_dzieci,
                        data_pocz, data_kon, liczba_osob_basen, liczba_osob_silownia, liczba_osob_sauna);

                podsumowanie_rezerwacji m = new podsumowanie_rezerwacji(a, mainWindow, login_id, m2);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(m.rezerwacjaPanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Rezerwacja");
                mainWindow.frame.validate();

            }
        });

        // JTextField cena = new JTextField(20);
        // //text.setBounds(0, 0, 0, 0);
        // cena.setSize(300, 50);
        // cena.setLocation(700, 600);
        // cena.setEditable(false);
        // cena.setText("Aktualna cena: " + 10);
        // rezerwacjaPanel.add(cena);

    }

    public void calculateDays(LocalDate d1, LocalDate d2) {
        daysBetween = ChronoUnit.DAYS.between(d1, d2);

    }

    protected void myClear(JComboBox<hotel_wrapper> x) {
        if (x.getItemCount() > 1) {
            for (int i = x.getItemCount() - 1; i > 0; i--) {
                x.removeItemAt(i);
            }
        }
    }

    public void wyczyscPokojeDropList() {
        // for (int i = pokojeDropList.getItemCount() - 1; i > 0; i--) {
        // pokojeDropList.removeItemAt(i);
        // }
    }

    public void wypelnijPokoje() {

        lista_wolnych_pokoi = mainWindow.getWolnePokoje(liczba_doroslych + liczba_dzieci, data_pocz, data_kon,
                wybrany_hotel);

        pokoj_select = new DefaultListModel<pokoj_wrapper>();
        for (int i = 0; i < lista_wolnych_pokoi.size(); i++) {

            pokoj_select.addElement(mainWindow.getPokojInfo(lista_wolnych_pokoi.get(i)));
            // pokoj_select.add(mainWindow.getPokojInfo(6));

        }
        my_pracownicy = new JList(pokoj_select);
        rezerwacjaPanel.add(my_pracownicy);
        uslugiDod.setVisible(true);
        my_pracownicy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        my_pracownicy.setSelectedIndex(1);
        my_pracownicy.setSize(1000, 200);
        my_pracownicy.setLocation(500, 50);

        my_pracownicy.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Object itemC = my_pracownicy.getSelectedValue();
                chosen_pokoj_id = ((pokoj_wrapper) itemC).getId();
                chosen_pokoj_liczba_miejsc = ((pokoj_wrapper) itemC).getMiejsca();
                chosen_pokoj_cena = ((pokoj_wrapper) itemC).getCena();
                obliczCene();

            }
        });

    }

    public void showUslugi() {

        basen = new JComboBox<>();
        rezerwacjaPanel.add(basen);
        // basen.setBounds(50, 50, 50, 100);
        basen.setSize(250, 50);
        basen.setLocation(650, 550);
        basen.addItem(new hotel_wrapper("Basen-liczba osób", -1));
        for (int i = 0; i < 10; i++) {
            basen.addItem(new hotel_wrapper(basenLiczba[i], i));
        }
        basen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object itemB = basen.getSelectedItem();
                liczba_osob_basen = 1 + ((hotel_wrapper) itemB).getVal();

                obliczCene();
            }
        });

        silownia = new JComboBox<>();
        rezerwacjaPanel.add(silownia);
        // silownia.setBounds(50, 50, 50, 100);
        silownia.setSize(250, 50);
        silownia.setLocation(950, 550);
        silownia.addItem(new hotel_wrapper("Silownia-liczba osób", -1));
        for (int i = 0; i < 10; i++) {
            silownia.addItem(new hotel_wrapper(basenLiczba[i], i));
        }
        silownia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object itemC = basen.getSelectedItem();
                liczba_osob_silownia = 1 + ((hotel_wrapper) itemC).getVal();

                obliczCene();
            }
        });

        sauna = new JComboBox<>();
        rezerwacjaPanel.add(sauna);
        sauna.setBounds(50, 50, 50, 100);
        sauna.setSize(250, 50);
        sauna.setLocation(1250, 550);
        sauna.addItem(new hotel_wrapper("Sauna-liczba osób", -1));
        for (int i = 0; i < 10; i++) {
            sauna.addItem(new hotel_wrapper(basenLiczba[i], i));
        }
        sauna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object itemD = sauna.getSelectedItem();
                liczba_osob_sauna = 1 + ((hotel_wrapper) itemD).getVal();

                obliczCene();
            }
        });

    }

    public void showButtonUslugi() {

    }

    public void obliczCene() {

        double aktualna_cena = liczba_doroslych * chosen_pokoj_cena * daysBetween +
                liczba_dzieci * chosen_pokoj_cena * daysBetween * 0.5 +
                liczba_osob_silownia * 25 + liczba_osob_basen * 25 + liczba_osob_sauna * 25;
        JTextField cena = new JTextField(20);
        // text.setBounds(0, 0, 0, 0);
        cena.setSize(850, 30);
        cena.setLocation(650, 800);
        cena.setBorder(null);
        cena.setEditable(false);
        Font font1 = new Font("SansSerif", Font.PLAIN, 30);
        cena.setFont(font1);
        cena.setHorizontalAlignment(JTextField.CENTER);

        cena.setText("Aktualna cena: " + aktualna_cena + "zł");
        rezerwacjaPanel.add(cena);
    }

    public void showBasen() {

    }
}