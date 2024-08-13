import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerFrame extends JFrame implements ActionListener {

    JButton Rock, Paper, Scissor;
    int money;
    int w =1600;
    int h =900;
    protected ImageIcon RockImage = new ImageIcon("BIGrock.png");
    protected ImageIcon PaperImage = new ImageIcon("BIGpaper.png");
    protected ImageIcon ScissorImage = new ImageIcon("BIGscissor.png");
    String chosenObject;

    public PlayerFrame(int money) {
        this.money = money;
        //set player frame
        setTitle("RPS Wars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(w, h);
        ImageIcon image = new ImageIcon("KannaNom.png");
        this.setIconImage(image.getImage());
        this.setLayout(null);

        //pick your side label
        JLabel PickYourSideLabel = new JLabel();
        PickYourSideLabel.setText("Pick your side");
        PickYourSideLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        PickYourSideLabel.setVerticalAlignment(JLabel.TOP);
        PickYourSideLabel.setHorizontalAlignment(JLabel.CENTER);
        PickYourSideLabel.setBounds(w/4, h/9, w/2, h/6);
        this.add(PickYourSideLabel);

        //rock button
        Rock = new JButton("Rock");
        Rock.setBackground(Color.RED);
        Rock.setBounds(2*w/21, 4*h/9, 5*w/21, h/3);
        Rock.addActionListener(this);
        Rock.setText("Rock");
        Rock.setFocusable(false);
        Rock.setIcon(RockImage);
        Rock.setFont(new Font("MV Boli", Font.BOLD, 24));
        Rock.setVerticalTextPosition(JButton.TOP);
        Rock.setHorizontalTextPosition(JButton.CENTER);
        Rock.getIcon(); //get icon để playing field làm vc
        this.add(Rock);

        //Paper button
        Paper = new JButton("Paper");
        Paper.setBackground(Color.GREEN);
        Paper.setBounds(8*w/21, 4*h/9, 5*w/21, h/3);
        Paper.addActionListener(this);
        Paper.setText("Paper");
        Paper.setFocusable(false);
        Paper.setIcon(PaperImage);
        Paper.setFont(new Font("MV Boli", Font.BOLD, 24));
        Paper.setVerticalTextPosition(JButton.TOP);
        Paper.setHorizontalTextPosition(JButton.CENTER);
        Paper.getIcon(); //get icon để playing field làm vc
        this.add(Paper);

        //Scissors button
        Scissor = new JButton("Scissors");
        Scissor.setBackground(Color.BLUE);
        Scissor.setBounds(2*w/3, 4*h/9, 5*w/21, h/3);
        Scissor.addActionListener(this);
        Scissor.setText("Scissors");
        Scissor.setFocusable(false);
        Scissor.setIcon(ScissorImage);
        Scissor.setFont(new Font("MV Boli", Font.BOLD, 24));
        Scissor.setVerticalTextPosition(JButton.TOP);
        Scissor.setHorizontalTextPosition(JButton.CENTER);
        Scissor.getIcon(); //get icon để playing field làm vc
        this.add(Scissor);
    }

    public String getChosenObject(){
        return chosenObject;
    }

    public void setChosenObject(String chosenObject) {
        this.chosenObject = chosenObject;
    }

    public int getMoney() {
        return money;
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
    //chon object khi bam nut rock paper scissor, chon xong tat frame
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Rock){
            this.chosenObject = "Rock";
        }else if(e.getSource() == Paper){
            this.chosenObject = "Paper";
        }else if(e.getSource() == Scissor){
            this.chosenObject = "Scissors";
        }
        dispose();
        SwingUtilities.invokeLater(() -> {
            BetFrame bet = new BetFrame(this.money, this.chosenObject);
            setMiddle(bet);
            bet.setVisible(true);
        });
    }
}
