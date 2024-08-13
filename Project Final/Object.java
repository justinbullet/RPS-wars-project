import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
abstract class Object {
    protected int x, y;
    protected Image image;
    protected int width, height;

    public Object(String imagePath, int x, int y) {
        // Load the image from the specified path
        image = new ImageIcon(imagePath).getImage();
        this.x = x;
        this.y = y;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    // Abstract methods for derived classes to implement
    public abstract void move();
    public abstract void transform(Object other);

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    // Method to check collision with another object
    public boolean checkCollision(Object other) {
        Rectangle rect1 = new Rectangle(x, y, width, height);
        Rectangle rect2 = new Rectangle(other.x, other.y, other.width, other.height);
        return rect1.intersects(rect2);
    }
}