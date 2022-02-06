
public class pracownik_wrapper {

    public int prac_id;
    public int pensja;
    public int staz;
    public String imie;
    public String nazwisko;
    public String email;
    public String h_nazwa;
    public String opis;

    public pracownik_wrapper(int p1, int p2, int p3, String s1, String s2, String s3, String s4, String s5) {
        prac_id = p1;
        pensja = p2;
        p3 = staz;
        imie = s1;
        nazwisko = s2;
        email = s3;
        h_nazwa = s4;
        opis = s5;

    }

    public String toString() {

        return "Imię: " + imie + "   Nazwisko: " + nazwisko + "   Email: "
                + email + "   Zarobki: " + pensja + "   Pracuje w hotelu: " + h_nazwa + "   Stanowisko: "
                + opis;

    }

    public String getImie() {
        return imie;
    }

    public int getIdPrac() {
        return prac_id;
    }

}