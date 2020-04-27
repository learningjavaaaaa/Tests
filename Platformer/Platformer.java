import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Dimension;


class Platformer{
    public static void main(String[] args){
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        MainJFrame frame = new MainJFrame();
        frame.setSize(700,700);
        frame.setTitle("Platformer");
        frame.setLocation((int) screenSize.getWidth()/2-frame.getWidth()/2, (int) screenSize.getHeight()/2-frame.getHeight()/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        

    }
}





