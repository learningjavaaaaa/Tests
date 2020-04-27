import java.awt.Color;

class MainJFrame extends javax.swing.JFrame{
    public MainJFrame(){
        GamePanel panel = new GamePanel();
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
        this.add(panel);
        
        this.addKeyListener(new KeyChecker(panel));
    }
}