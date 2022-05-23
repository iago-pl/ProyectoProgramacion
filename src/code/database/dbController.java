package code.database;

import code.gameObjects.Hasher;
import java.io.File;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a21iagopl
 */
public class DbController {

    private final String url = "jdbc:mysql://localhost:4550/match_it";
    private final String url2 = "jdbc:mysql://localhost:3306/match_it";
    private final String user = "root";
    private final String pass = "root";
    private Connection mysqlCon = null;
    private String playerName = "";

    private ArrayList<String> hashList = new ArrayList<>();

    public void addHash(String hash) {
        getHashList().add(hash);
    }

    public DbController(String name) {
        connect();

        for (int i = 0; i < 8; i++) {
            playerName += name.charAt(i);
        }

        insertPlayer(playerName);

        if (mysqlCon != null) {
            try {
                mysqlCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertLevelCompleted(String levelHash, int steps) {
        connect();
        Statement insert;
        try {
            insert = mysqlCon.createStatement();
            int codeInsert = insert.executeUpdate("call introducir_nivel_completado('" + playerName + "','" + levelHash + "','" + steps + "')");
            System.out.println("Resultado: " + codeInsert + " insertado " + steps + " en el nivel " + levelHash);
        } catch (SQLException e) {
            while (e != null) {
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println(" Code: " + e.getErrorCode());
                System.err.println(" Message:");
                System.err.println(e.getMessage());
                e = e.getNextException();
            }
        }
        if (mysqlCon != null) {
            try {
                mysqlCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertAllLevels() {
        connect();
        for (int i = 0; i < getHashList().size(); i++) {
            insertLevel(getHashList().get(i));
        }
        if (mysqlCon != null) {
            try {
                mysqlCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertLevel(String hashCode) {
        Statement insert;
        try {
            insert = mysqlCon.createStatement();
            int codeInsert = insert.executeUpdate("call introducir_mapa('" + hashCode + "')");
            System.out.println("Resultado: " + codeInsert + " insertado nivel " + hashCode);
        } catch (SQLException e) {
            while (e != null) { //bucle que trata a cadea de excepcións
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println(" Code: " + e.getErrorCode());
                System.err.println(" Message:");
                System.err.println(e.getMessage());
                e = e.getNextException();
            }
        }
    }

    private void insertPlayer(String nametag) {
        Statement insert;
        try {
            insert = mysqlCon.createStatement();
            int codeInsert = insert.executeUpdate("call introducir_jugador('" + nametag + "')");
            System.out.println("Resultado: " + codeInsert + " insertado nivel " + nametag);
        } catch (SQLException e) {
            while (e != null) { //bucle que trata a cadea de excepcións
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println(" Code: " + e.getErrorCode());
                System.err.println(" Message:");
                System.err.println(e.getMessage());
                e = e.getNextException();
            }
        }
    }

    private void connect() {

        try {
            mysqlCon = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            try {
                mysqlCon = DriverManager.getConnection(url2, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the hashList
     */
    public ArrayList<String> getHashList() {
        return hashList;
    }
}
