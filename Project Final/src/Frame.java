import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Frame extends JFrame {
    private int troopNo;
    private int currentMoney;
    private int betAmount;

    private Object[] RockArray; // Define arrays
    private Object[] PaperArray;
    private Object[] ScissorsArray;
    private int w = 50;
    private int h = 50;
    private int s = 10;
    Random random = new Random();
    public String winingObject;
    public String chosenObject;
    private JLabel moneyLabel;

    public Frame(int troopNo, int currentMoney, int betAmount, String chosenObject) {
        setTitle("RPS Wars");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("KannaNom.png");
        this.setIconImage(image.getImage());

        this.troopNo = troopNo;
        this.currentMoney = currentMoney;
        this.betAmount = betAmount;
        this.chosenObject = chosenObject;

        RockArray = new Object[this.troopNo]; // Initialize arrays
        PaperArray = new Object[this.troopNo];
        ScissorsArray = new Object[this.troopNo];
        setLayout(null);

        // Money label
        moneyLabel = new JLabel("Money: $" + this.currentMoney);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        moneyLabel.setBounds(10, 10, 200, 30); // Set the position and size of the label
        this.add(moneyLabel); // Add the label to the frame

        // Populate arrays with troops placed in random positions
        for (int i = 0; i < this.troopNo; i++) {
            int x = random.nextInt(getWidth() - 100);
            int y = random.nextInt(getHeight() - 100);
            int z = random.nextInt(getWidth() - 100);
            int t = random.nextInt(getHeight() - 100);
            int a = random.nextInt(getWidth() - 100);
            int b = random.nextInt(getHeight() - 100);

            RockArray[i] = new Rock(w, h, s); // Initialize Objects at index i
            PaperArray[i] = new Paper(w, h, s);
            ScissorsArray[i] = new Scissors(w, h, s);
            final int index = i; // Create a final variable to capture the current value of i
            RockArray[index].setBounds(x, y, w, h);
            PaperArray[index].setBounds(z, t, w, h);
            ScissorsArray[index].setBounds(a, b, w, h);
            this.add(RockArray[index]);
            this.add(PaperArray[index]);
            this.add(ScissorsArray[index]);
        }

        // Add mouse listener for adding new objects
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && currentMoney >= 10) {
                    addNewObject(e.getX(), e.getY());
                }
            }
        });

        run();
    }

    public int getTroopNo() {
        return troopNo;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void run() {
        final Timer timer = new Timer(10, null);
        timer.addActionListener(new FrameActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Frame frame = this.getFrame();
                for (Object object1 : RockArray) {
                    object1.moveObject(frame.getWidth(), frame.getHeight());
                    object1.detectAndHandleCollision(PaperArray, frame);
                    object1.detectAndHandleCollision(ScissorsArray, frame);
                }

                for (Object object2 : PaperArray) {
                    object2.moveObject(frame.getWidth(), frame.getHeight());
                    object2.detectAndHandleCollision(RockArray, frame);
                    object2.detectAndHandleCollision(ScissorsArray, frame);
                }

                for (Object object3 : ScissorsArray) {
                    object3.moveObject(frame.getWidth(), frame.getHeight());
                    object3.detectAndHandleCollision(RockArray, frame);
                    object3.detectAndHandleCollision(PaperArray, frame);
                }
                repaint();
                if (frame.checkWin()) {
                    timer.stop();
                    frame.showResult();
                }
            }
        });
        timer.start();
    }

    // Check if a factor has won and output corresponding message
    public boolean checkWin() {
        boolean rWin = true;
        boolean bWin = true;
        boolean gWin = true;
        for (Object object : RockArray) {
            Color color = object.getBackground();
            if (color != Color.RED) {
                rWin = false;
                break;
            }
            if (color != Color.BLUE) {
                bWin = false;
                break;
            }
            if (color != Color.GREEN) {
                gWin = false;
                break;
            }
        }

        for (Object object : PaperArray) {
            Color color = object.getBackground();
            if (color != Color.RED) {
                rWin = false;
            }
            if (color != Color.BLUE) {
                bWin = false;
            }
            if (color != Color.GREEN) {
                gWin = false;
            }
        }

        for (Object object : ScissorsArray) {
            Color color = object.getBackground();
            if (color != Color.RED) {
                rWin = false;
            }
            if (color != Color.BLUE) {
                bWin = false;
            }
            if (color != Color.GREEN) {
                gWin = false;
            }
        }

        if (rWin) {
            winingObject = "Rock";
            return true;
        }
        if (bWin) {
            winingObject = "Scissors";
            return true;
        }
        if (gWin) {
            winingObject = "Paper";
            return true;
        }
        return false;
    }

    public static void setMiddle(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    public void showResult() {
        String message = chosenObject.equals(winingObject) ? "You win!" : "You lose!";
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        if (chosenObject.equals(winingObject)) {
            currentMoney += 2 * betAmount;
        }
        this.dispose();
        winingFrame win = new winingFrame(this.currentMoney);
        win.setVisible(true);
        setMiddle(win);
    }

    private void addNewObject(int x, int y) {
        if (currentMoney < 10) {
            return; // Do nothing if there's not enough money
        }

        Object newObject;
        switch (chosenObject) {
            case "Rock":
                newObject = new Rock(w, h, s);
                addToArray(RockArray, newObject);
                break;
            case "Paper":
                newObject = new Paper(w, h, s);
                addToArray(PaperArray, newObject);
                break;
            case "Scissors":
                newObject = new Scissors(w, h, s);
                addToArray(ScissorsArray, newObject);
                break;
            default:
                return;
        }

        newObject.setBounds(x, y, w, h);
        this.add(newObject);
        this.repaint();

        // Deduct the cost and update the money label
        currentMoney -= 10;
        moneyLabel.setText("Money: $" + currentMoney);
    }

    private void addToArray(Object[] array, Object newObject) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = newObject;
                return;
            }
        }
        // If the array is full, expand it
        Object[] newArray = new Object[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = newObject;
        if (newObject instanceof Rock) {
            RockArray = newArray;
        } else if (newObject instanceof Paper) {
            PaperArray = newArray;
        } else if (newObject instanceof Scissors) {
            ScissorsArray = newArray;
        }
    }
}
