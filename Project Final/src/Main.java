import javax.swing.*;
import java.awt.*;

public class Main {
    public static void setMiddle(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerFrame player = new PlayerFrame(50);
            setMiddle(player);
            player.setVisible(true);
        });
    }
}
