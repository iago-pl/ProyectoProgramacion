package code.main;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author a21iagopl
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("SI LEES ESTO RECUERDAME DARLE UN PUÑETAZO A REBECA");
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("SI LEES ESTO RECUERDAME DARLE UN PUÑETAZO A REBECA");

        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getClassLoader().getResource("resources/img/icon/icon.png")));

        GameFrame gameFrame = new GameFrame();
        window.add(gameFrame);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameFrame.startGameThread();

    }
}
