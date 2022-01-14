import java.sql.*;
import java.util.*;

public class DatabaseConnection {
    public Connection c = null;

    public DatabaseConnection() {

        try {
            String dbaseURL = "jdbc:postgresql://localhost/u9nizio";
            String username = "u9nizio";
            String password = "9nizio";
            c = DriverManager.getConnection(dbaseURL, username, password);

        } catch (SQLException se) {
            System.out.println("Brak polaczenia z baza danych, wydruk logu sledzenia i koniec.");
            se.printStackTrace();
            System.exit(1);
        }

        if (c != null) {
            System.out.println("Polaczenie z baza danych OK ! ");
          }else{
              System.out.println("Brak polaczenia z baza, dalsza czesc aplikacji nie jest wykonywana.");   
          }
    }
}
