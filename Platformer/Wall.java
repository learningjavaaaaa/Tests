import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall {
	//imageCollection wallImg = new imageCollection("grass");
	File dir = new File(System.getProperty("user.dir")+"/Libraries/grass.png");
	static Image img;
	
    int x;
    int y;
    int width;
    int height;
    static int[] level1x = {0,50,100,150,200,250,300,350,400,450,500,550,600,650,700,750,800,850,900,950,1000,150,300,350,400,450,350,400};
    static int[] level1y = {600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,550,550,550,550,550,500,500};
    
    Rectangle hitbox;

    public Wall(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.hitbox = new Rectangle(x, y, width, height);
        try {
			this.img = ImageIO.read(dir);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,width,height);
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(x+1,y+1,width-1,height-1);
        /*try {
			Image img = ImageIO.read(dir);
			g2d.drawImage(img,x, y,null);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        g2d.drawImage(Wall.img, x, y,null);
    }

}