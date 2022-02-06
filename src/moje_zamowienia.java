
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class moje_zamowienia {
    public Connection a;
    public db_connection mainWindow;
    public int login_id;

    public JPanel moje_rezerwacje_Uppanel;
    public JLabel title;
    public JButton menuButton;
    public ArrayList<zamowienie_wrapper> moje_rezerwacje;
    public int val;
    public DefaultListModel<zamowienie_wrapper> rezerwacjeComboBox;
    public JList<zamowienie_wrapper> mylist;
    private int chosen_oplata_id;
    public JLabel info;
    public ArrayList<String> infStr;
    public ArrayList<Integer> infInt;
    public int zam_val;
    public double cena;
    public String sta;
    public int platnosc;

    public moje_zamowienia(Connection p, db_connection mainWindow, int id) {
        a = p;
        this.mainWindow = mainWindow;
        login_id = id;

        moje_rezerwacje_Uppanel = new ImagePanel(new ImageIcon("images/dodrez.jpg").getImage());
        moje_rezerwacje_Uppanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 100, 100));
        moje_rezerwacje_Uppanel.setLayout(null);

        title = new JLabel("Prosze wybrac rezerwacje:", SwingConstants.CENTER);
        moje_rezerwacje_Uppanel.add(title);

        wypelnij();

        JButton inf = new JButton();
        inf = new JButton("Szczegóły rezerwacji");
        inf.setFont(new Font("Verdana", Font.BOLD, 15));
        inf.setForeground(Color.white);
        inf.setBounds(75, 100, 100, 150);

        inf.setFocusPainted(false);
        inf.setContentAreaFilled(false);

        inf.setBounds(50, 50, 50, 100);
        inf.setSize(300, 50);
        inf.setLocation(100, 250);
        moje_rezerwacje_Uppanel.add(inf);
        inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                infStr = new ArrayList<>();
                infInt = new ArrayList<>();
                nowaLista(infStr, infInt);

            }
        });

        JButton oblicz = new JButton();
        oblicz = new JButton("Dokonaj płatności");
        oblicz.setFont(new Font("Verdana", Font.BOLD, 15));
        oblicz.setForeground(Color.white);
        oblicz.setBounds(75, 100, 100, 150);

        oblicz.setFocusPainted(false);
        oblicz.setContentAreaFilled(false);
        oblicz.setBounds(50, 50, 50, 100);
        oblicz.setSize(300, 50);
        oblicz.setLocation(100, 350);
        moje_rezerwacje_Uppanel.add(oblicz);
        oblicz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String msg = mainWindow.dokonajPlatnosci(val);

                moje_zamowienia x = new moje_zamowienia(a, mainWindow, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(x.moje_rezerwacje_Uppanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Moje rezerwacje");
                mainWindow.frame.validate();
            }
        });

        JButton powrotmenu = new JButton();
        powrotmenu = new JButton("Powrót do menu");
        powrotmenu.setFont(new Font("Verdana", Font.BOLD, 15));
        powrotmenu.setForeground(Color.white);
        powrotmenu.setBounds(75, 100, 100, 150);

        powrotmenu.setFocusPainted(false);
        powrotmenu.setContentAreaFilled(false);

        powrotmenu.setBounds(50, 50, 50, 100);
        powrotmenu.setSize(300, 50);
        powrotmenu.setLocation(100, 550);
        moje_rezerwacje_Uppanel.add(powrotmenu);
        powrotmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                menu powrot = new menu(mainWindow, login_id, a, mainWindow);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.getContentPane().add(powrot.menuPanel);
                mainWindow.frame.setTitle("BD PROJEKT - Menu");
                mainWindow.frame.validate();
            }
        });

        JButton uslugiDod = new JButton();
        uslugiDod = new JButton("Usuń zamówienie");
        uslugiDod.setFont(new Font("Verdana", Font.BOLD, 15));
        uslugiDod.setForeground(Color.white);
        uslugiDod.setBounds(75, 100, 100, 150);

        uslugiDod.setFocusPainted(false);
        uslugiDod.setContentAreaFilled(false);

        uslugiDod.setBounds(50, 50, 50, 100);
        uslugiDod.setSize(300, 50);
        uslugiDod.setLocation(100, 450);
        moje_rezerwacje_Uppanel.add(uslugiDod);
        uslugiDod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String msg = mainWindow.usunZamowienie(val);

                moje_zamowienia x = new moje_zamowienia(a, mainWindow, login_id);
                mainWindow.frame.getContentPane().removeAll();
                mainWindow.frame.add(x.moje_rezerwacje_Uppanel, BorderLayout.CENTER);
                mainWindow.frame.setTitle("BD PROJEKT - Moje rezerwacje");
                mainWindow.frame.validate();

            }
        });

    }

    public void wypelnij() {

        moje_rezerwacje = mainWindow.listaZamowien(login_id);
        rezerwacjeComboBox = new DefaultListModel<zamowienie_wrapper>();
        for (int i = 0; i < moje_rezerwacje.size(); i++) {
            rezerwacjeComboBox.addElement(moje_rezerwacje.get(i));

        }

        mylist = new JList(rezerwacjeComboBox);
        mylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mylist.setSelectedIndex(1);
        // mylist.setCellRenderer(listRender

        mylist.setSize(1300, 200);
        mylist.setLocation(500, 50);
        // .addListSelectionListener(this);
        moje_rezerwacje_Uppanel.add(mylist);

        mylist.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Object itemC = mylist.getSelectedValue();
                val = ((zamowienie_wrapper) itemC).getID();
                cena = ((zamowienie_wrapper) itemC).getCENA();
                sta = ((zamowienie_wrapper) itemC).getStan();

            }
        });

    }

    public void nowaLista(ArrayList<String> lista, ArrayList<Integer> listb) {

        mainWindow.szczegolyZam(lista, listb, val);

        String data1 = lista.get(0);
        String data2 = lista.get(1);
        String hnazwa = lista.get(2);
        String nazwa = lista.get(3);
        String kraj = lista.get(4);

        int z1 = listb.get(0);
        int z2 = listb.get(1);
        int z3 = listb.get(2);
        int z4 = listb.get(3);
        int z5 = listb.get(4);
        int z6 = listb.get(5);
        int z7 = listb.get(6);
        int z8 = listb.get(7);
        int z9 = listb.get(8);
        int z10 = listb.get(9);
        int z11 = listb.get(10);
        int z12 = listb.get(11);
        int z13 = listb.get(12);
        int z14 = listb.get(13);

        // info.setVisible(true);
        info = new JLabel("<html>Informacje o hotelu<br/>Miasto: " + hnazwa
                + "<br/>Ulica: " + nazwa
                + "<br/>Kraj: " + kraj
                + "<br/>Numer pokoju: " + z6
                + "<br/>Piętro: " + z7
                + "<br/>Kategoria pokoju: " + z9
                + "<br/>Cena za osobę: " + z10 + " zł"
                + "<br/>Ocena hotelu (gwiazdki): " + z11
                + "<br/><br/>Informacje o zamówieniu numer: " + z1
                + "<br/>ID klienta: " + z2
                + "<br/>Data zakwaterowania: " + data1
                + "<br/>Data wykwaterowania: " + data2
                + "<br/>Liczba dorosłych: " + z5
                + "<br/>Liczba dzieci: " + z4
                + "<br/>Miejsca na basenie: " + z12
                + "<br/>Miejsca na siłowni: " + z13
                + "<br/>Miejsca w saunie: " + z14
                + "<br/>Koszt zamówienia: " + cena
                + "<br/>Stan zamówienia: " + sta
                + "</html>", SwingConstants.CENTER);
        moje_rezerwacje_Uppanel.add(info);

        info.setFont(new Font("Serif", Font.BOLD, 20));
        info.setForeground(Color.white);
        info.setBounds(75, 100, 100, 150);

        info.setBounds(50, 50, 50, 100);
        info.setSize(600, 650);
        info.setLocation(500, 250);

    }

}