package code.main;

import code.database.DbController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a21iagopl
 */
public class Hasher {

    public static String getHash(File file) {
        MessageDigest md5Digest;

        try {
            md5Digest = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);

            //Create byte array to read data in chunks
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;

            //Read file data and update in message digest
            while ((bytesCount = fis.read(byteArray)) != -1) {
                md5Digest.update(byteArray, 0, bytesCount);
            };

            //close the stream; We don't need it now.
            fis.close();

            //Get the hash's bytes
            byte[] bytes = md5Digest.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            //return complete hash
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
