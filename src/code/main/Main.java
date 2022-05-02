package code.main;

import javax.swing.JFrame;

/**
 *
 * @author a21iagopl
 */
public class Main {

    public static void main(String[] args) {
        
        System.out.println("SI LEES ESTO RECUERDAME DARLE UN PUÑETAZO A REBECA");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("JAJA si hay que cambiar esto");

        GameFrame gameFrame = new GameFrame();
        window.add(gameFrame);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameFrame.startGameThread();

    }
}
