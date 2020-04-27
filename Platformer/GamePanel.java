import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class GamePanel extends javax.swing.JPanel implements ActionListener{
	int cameraX;
	
	int level = 1;
	
	Player player;
    Timer gameTimer;
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Wall> wallsCopy = walls;

    public GamePanel(){
    	player = new Player(400,300,this);
    	
        reset();
        
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                player.set();
                repaint();
                
                
            }

        },0,1000/70);
    }
    
    public void reset() {
    	player.x = 400;
    	player.y = 300;
    	player.hitbox.x = player.x;
    	player.hitbox.y = player.y;
    	player.xspeed = 0;
    	player.yspeed = 0;
    	cameraX =0;
    	clearList();
    }
    
    public void clearList() {
    	walls.clear();
    	makeWalls(cameraX);
    }
    
    public void makeWalls(int cameraX){
    	
        for(int i = 0; i<Wall.level1x.length; i++) {
        	if(Math.abs(player.x-(Wall.level1x[i]+cameraX))<600) walls.add(new Wall(Wall.level1x[i]+cameraX, Wall.level1y[i], 50, 50));
    	}
        
        }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        for(int i=0; i<walls.size();i++){
            walls.get(i).draw(g2d);
        }
        
        player.draw(g2d);
    }
   
	void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='d') player.keyRight=true;
        if(e.getKeyChar()=='q') player.keyLeft=true;
        if(e.getKeyChar()=='z') player.keyUp=true;
        if(e.getKeyChar()=='s') player.keyDown=true; 
        if(e.getKeyChar()=='f') player.flyMode = !player.flyMode;
	}

	void keyReleased(KeyEvent e) {
        if(e.getKeyChar()=='d') player.keyRight=false;
        if(e.getKeyChar()=='q') player.keyLeft=false;
        if(e.getKeyChar()=='z') player.keyUp=false;
        if(e.getKeyChar()=='s') player.keyDown=false;
	}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

