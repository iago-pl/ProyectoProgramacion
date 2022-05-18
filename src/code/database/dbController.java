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
        //getHash(ReferenceController.mapReader.files[0]);
        test();
    }

    private void test() {
        String mysqlUrl = "jdbc:mysql://localhost/peliculas";
        Connection mysqlCon = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            System.out.println("Failed to load MySQL driver.");
            return;
        }
        Statement insertFilm = null;
        String insertString = "INSERT INTO match_it.niveles VALUES ('hola','adios')";
        try {
            mysqlCon = DriverManager.getConnection(url, user, pass);
            insertFilm = mysqlCon.createStatement();
            int inseridos = insertFilm.executeUpdate(insertString);
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
            Connection con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(dbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getHash(File file) {
        MessageDigest md5Digest;

        try {
            md5Digest = MessageDigest.getInstance("MD5");
            String checksum = getFileChecksum(md5Digest, file);
            System.out.println(checksum);
        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(dbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }
}
