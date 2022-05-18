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

    public dbController() {

        String url = "jdbc:mysql://localhost:3306/match_it";
        String user = "root";
        String pass = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(dbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
