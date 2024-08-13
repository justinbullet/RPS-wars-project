import javax.swing.*;
import java.awt.*;
import java.util.Random;

abstract class Object extends JPanel {
    protected int width;
    protected int height;
    protected double speed;
    protected int delay;
    Random random = new Random();
    private double angle = random.nextDouble() * 360;
    
    public Object(int width, int height, double speed) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        setPreferredSize(new Dimension(width, height));
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public double getSpeed(){
        return this.speed;
    }

    public void moveObject(int frameWidth, int frameHeight) {
        Random random = new Random();
        
        // Randomly decide to add or subtract 5 degrees
        int angleChange = random.nextBoolean() ? 5 : -5;
        
        // Update the angle by ±5 degrees
        this.angle = this.angle + angleChange;
        double direction = Math.toRadians(this.angle);
        
        // Calculate the change in position
        int dx = (int) (speed * Math.cos(direction));
        int dy = (int) (speed * Math.sin(direction));
        
        // Get current position
        int x = this.getX();
        int y = this.getY();
        
        // Move the object
        x += dx;
        y += dy;
        
        // Boundary conditions to ensure the object stays within the frame
        if (x >= frameWidth - this.getWidth()) {
            x = frameWidth - this.getWidth();
            this.angle = 180 - this.angle;  // Reflect the angle horizontally
        } else if (x <= 0) {
            x = 0;
            this.angle = 180 - this.angle;  // Reflect the angle horizontally
        }
        
        if (y >= frameHeight - this.getHeight()) {
            y = frameHeight - this.getHeight();
            this.angle = -this.angle;  // Reflect the angle vertically
        } else if (y <= 0) {
            y = 0;
            this.angle = -this.angle;  // Reflect the angle vertically
        }
        
        // Set new location
        setLocation(x, y);
    }

    
    
    protected abstract void detectAndHandleCollision(Object[] otherObjects, Frame e);

    protected boolean isColliding(Object otherObject) {
        Rectangle rect1 = this.getBounds();
        Rectangle rect2 = otherObject.getBounds();
        return rect1.intersects(rect2);
    }

    protected void swapColorsAndLabels(Component winningComponent, Component losingComponent, Color newColor, JLabel newLabel, ImageIcon image) {
        if (winningComponent instanceof Container && losingComponent instanceof Container) {
            Container winningContainer = (Container) winningComponent;
            Container losingContainer = (Container) losingComponent;

            winningContainer.removeAll();
            losingContainer.removeAll();

            winningContainer.setBackground(newColor);
            losingContainer.setBackground(newColor);

            JLabel winningLabel = new JLabel(image);
            winningContainer.add(winningLabel);

            JLabel losingLabel = new JLabel(image);
            losingContainer.add(losingLabel);

            winningContainer.revalidate();
            winningContainer.repaint();
            losingContainer.revalidate();
            losingContainer.repaint();
        }
    }
}