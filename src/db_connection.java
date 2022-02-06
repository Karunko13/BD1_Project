import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.Color;

public class db_connection {
    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JLabel inf;
    public JButton start;
    public Connection c = null;
    public static int counter = 664;

    public db_connection() {

        try {
            String dbaseURL = "jdbc:postgresql://localhost/u9nizio";
            String username = "u9nizio";
            String password = "9nizio";
            c = DriverManager.getConnection(dbaseURL, username, password);

        } catch (SQLException se) {
            System.out.println("Brak polaczenia z baza danych");
            se.printStackTrace();
            System.exit(1);
        }

        if (c != null) {
            System.out.println("Połączono z bazą danych na serwerze pascal");
        } else {
            System.out.println("Brak polaczenia z baza, dalsza czesc aplikacji nie jest wykonywana.");
        }

        ImagePanel panel = new ImagePanel(new ImageIcon("images/image.jpg").getImage());
        frame = new JFrame();

        label = new JLabel("Projekt BD1 Mateusz Nizio", SwingConstants.CENTER);

        label.setFont(new Font("Verdana", Font.ITALIC, 60));
        label.setForeground(Color.white);
        inf = new JLabel("Symulacja bazy danych Hotelu", SwingConstants.CENTER);
        inf.setFont(new Font("Verdana", Font.BOLD, 30));
        inf.setForeground(Color.white);
        start = new JButton("Zaloguj lub zarejestruj");
        start.setFont(new Font("Verdana", Font.BOLD, 30));
        start.setForeground(Color.white);
        start.setBounds(75, 100, 100, 150);

        start.setBorder(new RoundedBorder(10));

        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        panel.setBorder(BorderFactory.createEmptyBorder(75, 100, 100, 150));
        panel.setLayout(new GridLayout(0, 1));
        db_connection copy = this;
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login_rejestr m = new login_rejestr(copy, c);
                frame.getContentPane().removeAll();
                frame.add(m.panel2, BorderLayout.CENTER);
                frame.setTitle("BD PROJEKT - Zaloguj");
                frame.validate();
            }
        });

        panel.add(label);

        panel.add(inf);

        panel.add(start);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Projekt Bazy Danych1");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    int checkPasswd(String login, String password) {
        int a = -1;
        try {
            PreparedStatement pst = c.prepareStatement(
                    "SELECT * FROM projektbd.czyZalogowany(\'" + login + "\',\'" + password + "\')",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                a = Integer.parseInt(rs.getString("czyZalogowany"));
            }
            rs.close();
            pst.close();

            return a;
        } catch (SQLException e) {
            System.out.println("Blad podczas przetwarzania danych:" + e);
            return a;
        }
    }

    public String getUserName(int id) {
        try {
            PreparedStatement pst = c.prepareStatement(
                    "SELECT imie ||\' \'|| nazwisko AS result FROM projektbd.klient WHERE klient_id=" + id,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            String dane = "";
            while (rs.next()) {
                dane = rs.getString("result");
            }
            rs.close();
            pst.close();
            return dane;
        } catch (SQLException e) {
            System.out.println("Blad podczas przetwarzania danych:" + e);
            return "";
        }
    }

    public String usunZamowienie(int id) {
        try {
            int myind = 0;
            PreparedStatement pst5 = c.prepareStatement(
                    "SELECT rachunek_id FROM  projektbd.rachunek WHERE zamowienie_id = " + id,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst5.executeQuery();
            while (rs.next()) {
                myind = Integer.parseInt(rs.getString("rachunek_id"));
            }

            PreparedStatement pst0 = c.prepareStatement("BEGIN");
            PreparedStatement pst = c.prepareStatement(
                    "DELETE FROM projektbd.zamowienie WHERE zamowienie_id = " + id);
            PreparedStatement pst1 = c.prepareStatement("DELETE FROM projektbd.uslugi WHERE zamowienie_id = " + id);
            PreparedStatement pst2 = c.prepareStatement("DELETE FROM projektbd.rachunek WHERE zamowienie_id = " + id);
            PreparedStatement pst6 = c.prepareStatement("DELETE FROM projektbd.platnosc WHERE rachunek_id = " + myind);
            PreparedStatement pst4 = c.prepareStatement("COMMIT");

            pst0.executeUpdate();
            pst1.executeUpdate();
            pst2.executeUpdate();
            pst.executeUpdate();
            pst6.executeUpdate();
            pst4.executeUpdate();

            pst0.close();
            pst1.close();
            pst2.close();
            pst.close();
            pst6.close();
            pst4.close();
            return "Pomyślnie usunięto z bazy";
        } catch (SQLException e) {
            System.out.println("Blad podczas przetwarzania danych:" + e);
            return "Błąd podczas usuwania";
        }
    }

    public String zwolnijPracownika(int id) {
        try {
            PreparedStatement pst0 = c.prepareStatement("BEGIN");
            PreparedStatement pst = c.prepareStatement(
                    "DELETE FROM projektbd.pracownik_hotelu WHERE pracownik_id = " + id);

            PreparedStatement pst4 = c.prepareStatement("COMMIT");

            pst0.executeUpdate();

            pst.executeUpdate();
            pst4.executeUpdate();

            pst0.close();

            pst.close();
            pst4.close();
            return "Pomyślnie usunięto pracownika z bazy";
        } catch (SQLException e) {
            System.out.println("Blad podczas przetwarzania danych:" + e);
            return "Błąd podczas usuwania";
        }
    }

    public String dokonajPlatnosci(int id) {
        try {
            PreparedStatement pst0 = c.prepareStatement("BEGIN");
            PreparedStatement pst = c.prepareStatement(
                    "UPDATE projektbd.rachunek SET stan = 1 WHERE zamowienie_id = " + id);

            PreparedStatement pst4 = c.prepareStatement("COMMIT");

            pst0.executeUpdate();

            pst.executeUpdate();
            pst4.executeUpdate();

            pst0.close();

            pst.close();
            pst4.close();
            return "Pomyślnie dokonano płatności";
        } catch (SQLException e) {
            System.out.println("Blad podczas przetwarzania danych:" + e);
            return "Bład podczas dokonywania płatności";
        }
    }

    public static void Counter() {
        counter = counter + 1;
    }

    public String zarejestruj(String imie, String nazwisko, String email, String telefon, String login, String haslo,
            String typ) {
        try {
            Counter();
            boolean a = false;
            int ind = 0;
            PreparedStatement pst0 = c.prepareStatement(
                    "SELECT * FROM  projektbd.klientIndex()",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst0.executeQuery();
            while (rs.next()) {
                ind = Integer.parseInt(rs.getString("klientIndex"));
            }

            PreparedStatement pst1 = c.prepareStatement("BEGIN");
            PreparedStatement pst2 = c.prepareStatement(
                    "INSERT INTO projektbd.klient(\"klient_id\",\"imie\",\"nazwisko\",\"email\",\"numer\",\"plec\",\"typ\") VALUES(\'"
                            + ind + "\',\'" + imie + "\',\'" + nazwisko + "\',\'" + email + "\',\'" + telefon + "\',\'"
                            + a + "\',\'" + typ + "\' )");
            PreparedStatement pst3 = c
                    .prepareStatement("INSERT INTO projektbd.danelogowania VALUES(projektbd.klientIndex(),\'" + login
                            + "\',\'" + haslo + "\')");
            PreparedStatement pst4 = c.prepareStatement("COMMIT");

            pst1.executeUpdate();
            pst2.executeUpdate();
            pst3.executeUpdate();
            pst4.executeUpdate();

            pst1.close();
            pst2.close();
            pst3.close();
            pst4.close();
            Counter();

            return "Pomyślnie dodano użykownika do bazy";
        } catch (SQLException e) {

            try {
                PreparedStatement pst5 = c.prepareStatement("ROLLBACK");
                pst5.executeUpdate();
                pst5.close();
            } catch (SQLException d) {

                return "Blad przy dodawaniu nowego uzytkownika!";
            }
            String powod = e.getMessage();
            String[] powod_tab = powod.split("[||]");
            return "Blad przy dodawaniu nowego uzytkownika! " + powod_tab[2];
        }
    }

    public ArrayList<Integer> getWolnePokoje(int liczba_osob, String od_kiedy, String do_kiedy, String nazwa) {
        ArrayList<Integer> lista_pokoi = new ArrayList<>();
        try {
            PreparedStatement pst = c
                    .prepareStatement(
                            "SELECT * FROM projektbd.zwroc_pokoje(" + liczba_osob + ",'" + od_kiedy + "','" + do_kiedy
                                    + "','" + nazwa + "')",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista_pokoi.add(Integer.parseInt(rs.getString("id_pokoju")));
            }
            rs.close();
            pst.close();
            return lista_pokoi;
        } catch (SQLException e) {

            return lista_pokoi;
        }
    }

    public pokoj_wrapper getPokojInfo(int id) {
        pokoj_wrapper info = new pokoj_wrapper();
        try {
            PreparedStatement pst = c.prepareStatement("SELECT * FROM projektbd.pokojeView WHERE pokoj_id = " + id,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int pokoj_id = Integer.parseInt(rs.getString("pokoj_id"));
                int numer_pokoju = Integer.parseInt(rs.getString("numer"));
                int pietro = Integer.parseInt(rs.getString("pietro"));
                int miejsca = Integer.parseInt(rs.getString("ilosc_miejsc"));
                String kategoria = "Pierwsza";
                int cena = Integer.parseInt(rs.getString("cena_od_osoby"));
                int kategoria_val = Integer.parseInt(rs.getString("kategoria_id"));
                String nazwa = rs.getString("hotel_nazwa");


                info.setNew(pokoj_id, pietro, numer_pokoju, kategoria, cena, miejsca, nazwa);
            }
            rs.close();
            pst.close();

            return info;
        } catch (SQLException e) {
            System.out.println("Blad podczas przetwarzania danych:" + e);
            return info;
        }
    }

    public String noweZamowienie(int uzyt_id, int pokoj_id, int adult_people, int kiddo_people, String start,
            String stop, int mybasen, int mysilownia, int mysauna) {
        try {
            int ind2 = 0;
            PreparedStatement pst0 = c.prepareStatement(
                    "SELECT * FROM  projektbd.zamowienieIndex()",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst0.executeQuery();
            while (rs.next()) {
                ind2 = Integer.parseInt(rs.getString("zamowienieIndex"));
            }
            ind2 = ind2 + 2;
            // System.out.println(ind2);
            // System.out.println(start);
            // System.out.println(stop);
            // System.out.println(kiddo_people);
            // System.out.println(adult_people);
            // System.out.println(pokoj_id);
            // System.out.println(uzyt_id);
            PreparedStatement pst1 = c.prepareStatement("BEGIN");
            PreparedStatement pst2 = c.prepareStatement(
                    "INSERT INTO projektbd.zamowienie(\"zamowienie_id\",\"data_przyjazdu\",\"data_odjazdu\",\"liczba_dzieci\",\"liczba_doroslych\",\"pokoj_id\",\"klient_id\") VALUES("
                            + ind2 + ",\'" + start + "\',\'" + stop + "\',\'" + kiddo_people + "\',\'" + adult_people
                            + "\'," + pokoj_id + "," + uzyt_id + ")");
            PreparedStatement pst5 = c.prepareStatement(
                                "UPDATE projektbd.pokoj SET dostepny = 'False' WHERE pokoj_id = " + pokoj_id);
            PreparedStatement pst3 = c.prepareStatement(
                    "INSERT INTO projektbd.uslugi(\"zamowienie_id\", \"basen\", \"silownia\", \"sauna\")VALUES(\'"
                            + ind2 + "\',\'" + mybasen + "\',\'" + mysilownia + "\',\'" + mysauna + "\')");
            PreparedStatement pst4 = c.prepareStatement("COMMIT");

            pst1.executeUpdate();
            pst2.executeUpdate();
            pst5.executeUpdate();
            pst3.executeUpdate();
            pst4.executeUpdate();

            pst1.close();
            pst2.close();
            pst5.close();
            pst3.close();
            pst4.close();
            return "Rezerwacja została dodana do bazy danych. Proszę przejśc do panelu: moje rezerwacje.";
        } catch (SQLException e) {

            try {
                PreparedStatement pst5 = c.prepareStatement("ROLLBACK");
                pst5.executeUpdate();
                pst5.close();
            } catch (SQLException d) {

                return "Nastąpił błąd. Przepraszamy.";
            }
            String powod = e.getMessage();
            String[] powod_tab = powod.split("[||]");
            return "Nastąpił błąd. Przepraszamy. " + powod_tab[2];
        }
    }

    public ArrayList<zamowienie_wrapper> listaZamowien(int id) {
        try {
            ArrayList<zamowienie_wrapper> a = new ArrayList<>();
            PreparedStatement pst = c.prepareStatement(
                    "SELECT * FROM projektbd.ZamowienieAndRachunek WHERE klient_id = " + id,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int zamowienie_id = Integer.parseInt(rs.getString("zamowienie_id"));

                int uzytkownik_id = Integer.parseInt(rs.getString("klient_id"));
                int pokoj_id = Integer.parseInt(rs.getString("pokoj_id"));
                String od_kiedy = rs.getString("data_przyjazdu");
                String do_kiedy = rs.getString("data_odjazdu");
                int liczba_dzieci = Integer.parseInt(rs.getString("liczba_dzieci"));
                int liczba_doroslych = Integer.parseInt(rs.getString("liczba_doroslych"));
                int numer_pokoju = Integer.parseInt(rs.getString("numer"));
                int pietro = Integer.parseInt(rs.getString("pietro"));
                int liczba_miejsc = Integer.parseInt(rs.getString("ilosc_miejsc"));
                int kategoria = Integer.parseInt(rs.getString("kategoria_id"));
                int pokoj_cena = Integer.parseInt(rs.getString("cena_od_osoby"));
                int liczba_basen = Integer.parseInt(rs.getString("basen"));
                int liczba_silownia = Integer.parseInt(rs.getString("silownia"));
                int liczba_sauna = Integer.parseInt(rs.getString("sauna"));
                int oplata_id = Integer.parseInt(rs.getString("rachunek_id"));
                double kwota = Double.parseDouble(rs.getString("kwota"));
                int stan_zam = Integer.parseInt(rs.getString("stan"));


                a.add(new zamowienie_wrapper(zamowienie_id, uzytkownik_id, pokoj_id, od_kiedy, do_kiedy,
                        liczba_dzieci, liczba_doroslych, oplata_id, kwota,
                        numer_pokoju, pietro, liczba_miejsc, kategoria, pokoj_cena, liczba_basen, liczba_silownia,
                        liczba_sauna, stan_zam));
            }
            rs.close();
            pst.close();
            return a;
        } catch (SQLException e) {
            // System.out.println("Blad podczas przetwarzania danych:"+e) ;
            return new ArrayList<zamowienie_wrapper>();
        }
    }

    public void szczegolyZam(ArrayList<String> list1, ArrayList<Integer> list2, int id) {
        try {
          
            PreparedStatement pst = c.prepareStatement(
                    "SELECT * FROM projektbd.WszystkieInf WHERE zamowienie_id = " + id,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {



                String od_kiedy = rs.getString("data_przyjazdu");
                String do_kiedy = rs.getString("data_odjazdu");
                String hotel_nazwa = rs.getString("hotel_nazwa");
                String adres = rs.getString("adres");
                String kraj = rs.getString("kraj");


                int zamowienie_id = Integer.parseInt(rs.getString("zamowienie_id"));
                int uzytkownik_id = Integer.parseInt(rs.getString("klient_id"));
                int pokoj_id = Integer.parseInt(rs.getString("pokoj_id"));
                int liczba_dzieci = Integer.parseInt(rs.getString("liczba_dzieci"));
                int liczba_doroslych = Integer.parseInt(rs.getString("liczba_doroslych"));
                int numer_pokoju = Integer.parseInt(rs.getString("numer"));
                int pietro = Integer.parseInt(rs.getString("pietro"));
                int liczba_miejsc = Integer.parseInt(rs.getString("ilosc_miejsc"));
                int kategoria = Integer.parseInt(rs.getString("kategoria_id"));
                int pokoj_cena = Integer.parseInt(rs.getString("cena_od_osoby"));
                int gwiazdki = Integer.parseInt(rs.getString("gwiazdki"));
                int liczba_basen = Integer.parseInt(rs.getString("basen"));
                int liczba_silownia = Integer.parseInt(rs.getString("silownia"));
                int liczba_sauna = Integer.parseInt(rs.getString("sauna"));

                list1.add(od_kiedy);
                list1.add(do_kiedy);
                list1.add(hotel_nazwa);
                list1.add(adres);
                list1.add(kraj);

                list2.add(zamowienie_id);
                list2.add(uzytkownik_id);
                list2.add(pokoj_id);
                list2.add(liczba_dzieci);
                list2.add(liczba_doroslych);
                list2.add(numer_pokoju);
                list2.add(pietro);
                list2.add(liczba_miejsc);
                list2.add(kategoria);
                list2.add(pokoj_cena);
                list2.add(gwiazdki);
                list2.add(liczba_basen);
                list2.add(liczba_silownia);
                list2.add(liczba_sauna);

            }
            rs.close();
            pst.close();

        } catch (SQLException e) {
            // System.out.println("Blad podczas przetwarzania danych:"+e) ;

        }
    }

    public ArrayList<pracownik_wrapper> listaPracownikow(String nazwa_h) {
        try {
      
            ArrayList<pracownik_wrapper> a = new ArrayList<>();
            PreparedStatement pst = c.prepareStatement(
                    "SELECT * FROM projektbd.HotelPracownicyPozycja WHERE nazwa =?",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, nazwa_h);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                int prac_id = Integer.parseInt(rs.getString("pracownik_id"));
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                int pensja = Integer.parseInt(rs.getString("pensja"));
                String email = rs.getString("email");
                int staz = Integer.parseInt(rs.getString("staz"));
                String h_nazwa = rs.getString("nazwa");
                String opis = rs.getString("opis");

                a.add(new pracownik_wrapper(prac_id, pensja, staz, imie, nazwisko, email, h_nazwa, opis));
            }


            rs.close();
            pst.close();
            return a;
        } catch (SQLException e) {
            // System.out.println("Blad podczas przetwarzania danych:"+e) ;
            return new ArrayList<pracownik_wrapper>();
        }
    }

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

}
