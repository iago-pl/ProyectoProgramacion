package code.main;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author a21iagopl
 */
public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Match It");

        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getClassLoader().getResource("resources/img/icon/icon.png")));

        GameFrame gameFrame = new GameFrame();
        window.add(gameFrame);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameFrame.startGameThread();

    }
}
