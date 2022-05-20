package code.database;

import java.sql.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public ArrayList<String> hashList;

    public DbController() {
        connect();
        //getHash(ReferenceController.mapReader.files[0]);
        insert("INSERT INTO match_it.niveles VALUES ('hoala','adios')");
    }

    private void insertLevel(String code) {
        Statement insert;
        try {
            insert = mysqlCon.createStatement();
            int inseridos = insert.executeUpdate(code);
            System.out.println("Resultado: " + inseridos + " inserido");
        } catch (SQLException e) {
            while (e != null) { //bucle que trata a cadea de excepcións
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println(" Code: " + e.getErrorCode());
                System.err.println(" Message:");
                System.err.println(e.getMessage());
                e = e.getNextException();
            }
        } finally {
            if (mysqlCon != null) {
                try {
                    mysqlCon.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insert(String code) {
        Statement insert;
        try {
            insert = mysqlCon.createStatement();
            int inseridos = insert.executeUpdate(code);
            System.out.println("Resultado: " + inseridos + " inserido");
        } catch (SQLException e) {
            while (e != null) { //bucle que trata a cadea de excepcións
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println(" Code: " + e.getErrorCode());
                System.err.println(" Message:");
                System.err.println(e.getMessage());
                e = e.getNextException();
            }
        } finally {
            if (mysqlCon != null) {
                try {
                    mysqlCon.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void connect() {

        try {
            mysqlCon = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            try {
                mysqlCon = DriverManager.getConnection(url2, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
