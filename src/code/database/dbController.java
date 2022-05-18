package code.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a21iagopl
 */
public class dbController {

    String url = "jdbc:mysql://localhost:4550/match_it";
    String user = "root";
    String pass = "root";

    public dbController() {
        connect();
    }

    private void connect() {

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(dbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
