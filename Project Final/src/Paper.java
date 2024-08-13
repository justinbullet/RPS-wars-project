import java.awt.*;
import javax.swing.*;
public class Paper extends Object {
    protected ImageIcon RockImage = new ImageIcon("rock.png");
    protected ImageIcon PaperImage = new ImageIcon("paper.png");
    protected ImageIcon ScissorImage = new ImageIcon("scissor.png");
    JLabel RockLabel = new JLabel(RockImage);
    JLabel PaperLabel = new JLabel(PaperImage);
    JLabel ScissorLabel = new JLabel(ScissorImage);
    public Paper(int width, int height, double speed) {
        super(width, height, speed);
        this.setBackground(Color.GREEN); // Paper color
        this.add(PaperLabel);
    }

    @Override
    public void detectAndHandleCollision(Object[] otherObjects, Frame e) {
        for (Object otherObject : otherObjects) {
            if (this != otherObject && isColliding(otherObject)) {
                Color thisColor = this.getBackground();
                Color otherColor = otherObject.getBackground();
                if ((thisColor == Color.RED && otherColor == Color.BLUE) ||
                    (thisColor == Color.BLUE && otherColor == Color.RED)) {
                    // Red beats Blue
                    swapColorsAndLabels(this, otherObject, Color.RED, RockLabel, RockImage);
                } else if ((thisColor == Color.RED && otherColor == Color.GREEN) ||
                            (thisColor == Color.GREEN && otherColor == Color.RED)) {
                    // Green beats Red
                    swapColorsAndLabels(this, otherObject, Color.GREEN, PaperLabel, PaperImage);
                } else if ((thisColor == Color.GREEN && otherColor == Color.BLUE) ||
                            (thisColor == Color.BLUE && otherColor == Color.GREEN)) {
                    // Blue beats Green
                    swapColorsAndLabels(this, otherObject, Color.BLUE, ScissorLabel, ScissorImage);
                }
            }
        }
    }
}

