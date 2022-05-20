package code.main;
import code.Map.MapController;
import code.Map.MapReader;
import code.database.DbController;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author a21iagopl
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getenv("APPDATA"));
        
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Match It");

        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getClassLoader().getResource("resources/img/icon/icon.png")));

        ReferenceController.mapReader = new MapReader();
        
        ReferenceController.keyHandler = new KeyHandler();
        ReferenceController.gameFrame = new GameFrame();
        ReferenceController.mapController = new MapController();
        ReferenceController.infoController = new InfoController();
        ReferenceController.audioController = new AudioController();
        
        DbController test = new DbController();

        window.add(ReferenceController.gameFrame);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        ReferenceController.gameFrame.startGameThread();

    }
}
