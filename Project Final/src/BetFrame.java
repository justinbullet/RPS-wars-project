import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetFrame extends JFrame implements ActionListener {
    public
    int money;
    private
    JButton betButton;
    JTextField bet;
    JTextField troop;
    String chosenObject;

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static void setMiddle(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
    
    public BetFrame(int money, String chosenObject) {
        //initialize money
        this.money = money;
        this.chosenObject = chosenObject;

        //set Bet frame
        setTitle("RPS Wars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 800);
        ImageIcon image = new ImageIcon("KannaNom.png");
        this.setIconImage(image.getImage());
        this.setLayout(null);

        //pick your side label
        JLabel betFont = new JLabel();
        betFont.setText("Pick Your Bet");
        betFont.setFont(new Font("MV Boli", Font.BOLD, 50));
        betFont.setVerticalAlignment(JLabel.TOP);
        betFont.setHorizontalAlignment(JLabel.CENTER);
        betFont.setBounds(230, 150, 500, 100);
        this.add(betFont);

        //Show your money label
        JLabel showMoney = new JLabel();
        showMoney.setText("Your money : " + getMoney());
        showMoney.setFont(new Font("MV Boli", Font.BOLD, 30));
        showMoney.setVerticalAlignment(JLabel.TOP);
        showMoney.setHorizontalAlignment(JLabel.CENTER);
        showMoney.setBounds(180, 300, 500, 100);
        this.add(showMoney);

        //Show your troops label
        JLabel showTroops = new JLabel();
        showTroops.setText("<html><body>Troops in each faction:" + //
                        "<br>(Limit: 100)</body></html>");
        showTroops.setFont(new Font("MV Boli", Font.BOLD, 30));
        showTroops.setVerticalAlignment(JLabel.TOP);
        showTroops.setHorizontalAlignment(JLabel.CENTER);
        showTroops.setBounds(75, 350, 500, 100);
        this.add(showTroops);

        //Troop textfield
        troop = new JTextField(20);
        troop.setFont(new Font("MV Boli", Font.BOLD, 24));
        troop.setBounds(525, 380, 100, 50);
        this.add(troop);

        //Show bet label
        JLabel showbet = new JLabel();
        showbet.setText("Bet:");
        showbet.setFont(new Font("MV Boli", Font.BOLD, 30));
        showbet.setVerticalAlignment(JLabel.TOP);
        showbet.setHorizontalAlignment(JLabel.CENTER);
        showbet.setBounds(210, 450, 500, 100);
        this.add(showbet);

        //bet money textfield
        bet = new JTextField(20);
        bet.setFont(new Font("MV Boli", Font.BOLD, 24));
        bet.setBounds(525, 455, 100, 50);
        this.add(bet);

        //Place your bet
        betButton = new JButton();
        betButton.setBounds(360, 525, 240, 75);
        betButton.setText("Place your bet");
        betButton.setFont(new Font("MV Boli", Font.BOLD, 24));
        betButton.setFocusable(false);
        betButton.addActionListener(this); // Add ActionListener to the button
        this.add(betButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == betButton) {
            String betAmountText = bet.getText();
            String troopAmountText = troop.getText();
            try {
                int betAmount = Integer.parseInt(betAmountText); // Parse text to integer
                int troopAmount = Integer.parseInt(troopAmountText);
                boolean checkTroop = troopAmount<101 && troopAmount>0;
                if (betAmount >= 0 && checkTroop) { // Check if bet is non-negative
                    if (betAmount <= this.money) { // Check if bet is within available money
                        this.money -= betAmount; // Deduct bet amount from available money
                        dispose();
                        Frame game = new Frame(troopAmount, this.money, betAmount, this.chosenObject);
                        setMiddle(game);
                        game.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "You don't have enough money!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (troopAmount<1){
                        JOptionPane.showMessageDialog(this, "Troop amount cannot be less than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (troopAmount>100){
                        JOptionPane.showMessageDialog(this, "Troop amount cannot be more than 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Invalid input! Please enter a non-negative number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
