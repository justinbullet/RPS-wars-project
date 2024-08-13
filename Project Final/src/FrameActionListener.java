import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameActionListener implements ActionListener{
    Frame frame;
    public FrameActionListener(Frame frame){
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
