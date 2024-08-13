import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class winingFrame extends JFrame implements ActionListener{
    private JLabel showMoney;
    public int currentMoney;
    JButton PlayAgain, Continue;
    int w =1600;
    int h =900;

    public static void setMiddle(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    winingFrame(int money){
        this.currentMoney = money;
        setTitle("RPS Wars");
        //setSize(width, height)(do it in the main function, or the gamble function)
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("KannaNom.png");
        this.setIconImage(image.getImage());
        setLayout(null);

        //pick your side label
        JLabel DoYouWantToPlayAgain = new JLabel();
        DoYouWantToPlayAgain.setText("Do you want to play again?");
        DoYouWantToPlayAgain.setFont(new Font("MV Boli", Font.BOLD, 50));
        DoYouWantToPlayAgain.setVerticalAlignment(JLabel.TOP);
        DoYouWantToPlayAgain.setHorizontalAlignment(JLabel.CENTER);
        DoYouWantToPlayAgain.setBounds(w/4, h/3, w/2, h/6);
        this.add(DoYouWantToPlayAgain);

        // Money label
        showMoney = new JLabel("Your money is: $" + this.currentMoney);
        showMoney.setFont(new Font("Arial", Font.BOLD, 20));
        showMoney.setBounds(650,375 , 300, 100);
        showMoney.setVerticalAlignment(JLabel.CENTER);
        showMoney.setHorizontalAlignment(JLabel.CENTER);
        this.add(showMoney);

        //Cotntinue button
        Continue = new JButton("Continue");
        Continue.setBounds(400, 500, 350, 100);
        Continue.addActionListener(this);
        Continue.setText("Continue");
        Continue.setFocusable(false);
        Continue.setFont(new Font("MV Boli", Font.BOLD, 24));

        //Play again button
        PlayAgain = new JButton("PlayAgain");
        PlayAgain.setBounds(800, 500, 350, 100);
        PlayAgain.addActionListener(this);
        PlayAgain.setText("Play again");
        PlayAgain.setFocusable(false);
        PlayAgain.setFont(new Font("MV Boli", Font.BOLD, 24));

        if (currentMoney != 0){
            this.add(Continue);
            this.add(PlayAgain);
        } else if (currentMoney == 0){
            PlayAgain.setBounds(625, 500, 350, 100);
            this.add(PlayAgain);
        }

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == PlayAgain){
            SwingUtilities.invokeLater(() -> {
                dispose();
                PlayerFrame play = new PlayerFrame(50);
                setMiddle(play);
                play.setVisible(true);
            });
        }else if(e.getSource() == Continue){
            SwingUtilities.invokeLater(() -> {
                dispose();
                PlayerFrame play = new PlayerFrame(this.currentMoney);
                setMiddle(play);
                play.setVisible(true);
            });
        }
    }
}
