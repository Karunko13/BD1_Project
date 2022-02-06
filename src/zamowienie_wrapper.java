import javax.swing.*;

public class zamowienie_wrapper {
    public int zamowienie_id;
    public int uzytkownik_id;
    public int pokoj_id;
    public String od_kiedy;
    public String do_kiedy;

    public int liczba_dzieci;
    public int liczba_doroslych;
    public int uslugi_id;
    public int numer_pokoju;
    public int pietro;
    public int liczba_miejsc;
    public int kategoria;

    public int pokoj_cena;
    public int liczba_basen;
    public int liczba_silownia;
    public int liczba_sauna;
    public int oplata_id;
    public double kwota;
    public Boolean flag;
    public int stan_zam;
    public String stan_text;

    public zamowienie_wrapper(int zam_, int kl_id_, int p_id, String start, String stop, int dzieci,
            int dorosli, int o_id, double cena_calosc, int p_nr,
            int p_p, int p_lm, int kat, int p_cen, int lb, int ls, int ss, int stan) {
        zamowienie_id = zam_;
        uzytkownik_id = kl_id_;
        pokoj_id = p_id;
        od_kiedy = start;
        do_kiedy = stop;
        liczba_basen = lb;
        liczba_silownia = ls;
        liczba_sauna = ss;
        liczba_dzieci = dzieci;
        liczba_doroslych = dorosli;

        oplata_id = o_id;
        stan_zam = stan;
        kwota = cena_calosc;
        numer_pokoju = p_nr;
        pietro = p_p;
        liczba_miejsc = p_lm;
        kategoria = kat;
        pokoj_cena = p_cen;
        flag = true;
        stanZamowienia();
    }

    public void stanZamowienia() {
        if (stan_zam == 0) {
            stan_text = "Nieopłacone";
        } else {
            stan_text = "Opłacone";
        }
    }

    public String getStan() {
        return stan_text;
    }

    public void setStan(String x) {
        stan_text = x;
    }

    public double getCENA() {
        return kwota;
    }

    public zamowienie_wrapper(Boolean f) {
        flag = f;
        zamowienie_id = 0;
        uzytkownik_id = 0;
        pokoj_id = 0;
        od_kiedy = "";
        do_kiedy = "";
        liczba_dzieci = 0;
        liczba_doroslych = 0;
        uslugi_id = 0;
        oplata_id = -1;
        kwota = 0;
        numer_pokoju = 0;
        pietro = 0;
        liczba_miejsc = 0;
        kategoria = 0;
        pokoj_cena = 0;
    }

    public zamowienie_wrapper(String s) {
        this(false);
        // status = s;
        oplata_id = -1;
    }

    public int getID() {
        return zamowienie_id;
    }

    public String toString() {
        if (flag) {
            return "Data zakwaterowania:   " + od_kiedy + "   Data wykwaterowania:   " + do_kiedy + "   Kwota:   "
                    + kwota + " Stan opłacenia: " + getStan();
        } else {
            return "błąd";
        }
    }

    public void info(JPanel panel) {
        panel.add(new JLabel("Informacje o rezerwacji:", SwingConstants.CENTER));
        panel.add(new JLabel("  Data zakwaterowania:   " + od_kiedy
                + "   Data wykwaterowania:   " + do_kiedy + "   Liczba doroslych:   " + String.valueOf(liczba_doroslych)
                + "   Liczba dzieci:   " + String.valueOf(liczba_dzieci)));

        panel.add(new JLabel("Informacje o wybranym pokoju:", SwingConstants.CENTER));
        panel.add(new JLabel("Nazwa kategorii:   " + kategoria + "   Pietro:   " + String.valueOf(pietro)
                + "   Numer pokoju:   " + String.valueOf(numer_pokoju) + "   Maksymalna liczba miejsc:   "
                + String.valueOf(liczba_miejsc) + "   Cena od osoby:   " + String.valueOf(pokoj_cena) + " zl"));


    }
}