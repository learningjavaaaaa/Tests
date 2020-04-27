
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Player{
	File dir = new File(System.getProperty("user.dir")+"/Libraries/player.png");
	Image img;
	imageCollection playerImg = new imageCollection("player");
	
	
	GamePanel panel;
	
	int x;
	int y;
	
	int width = 50;
	int height = 85;
	
	double xspeed;
	double yspeed;
	int maxSpeed;
	
	Rectangle hitbox;
	
	boolean keyRight;
	boolean keyLeft;
	boolean keyUp;
	boolean keyDown;
	
	boolean flyMode = false;
	
	int offset;
	
	int borderValue;
	
	public Player(int x, int y, GamePanel panel) {
		offset = 0;
		this.panel = panel;
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x, y, width, height);
		
		borderValue = 150;
		maxSpeed = 5;
	}
	
	
	public void set() {
		//Movements
		if(keyLeft&&keyRight || !keyLeft&&!keyRight) xspeed *= 0.8;
		else if(keyRight&&!keyLeft) xspeed ++;
		else if(keyLeft&&!keyRight)xspeed --;
		
		//Max speed
		if(xspeed>maxSpeed) xspeed = maxSpeed;
		if(xspeed<-maxSpeed) xspeed = -maxSpeed;
		
		//Anti sliding effect
		if(Math.abs(xspeed)<0.75) xspeed = 0;
			
			
		//Jump
		if(keyUp) {
			hitbox.y ++;
			for(Wall wall : panel.walls) {
				if(hitbox.intersects(wall.hitbox) || flyMode) yspeed=-5.5;		
			}
			hitbox.y --;
		}
		
		//(fly mode only) Go down
		if(keyDown && flyMode)yspeed=5.5;
		
		//Gravity
		if(!flyMode)yspeed += 0.3;
		
		//Horizontal collision
		hitbox.x += xspeed;
		for(Wall wall : panel.walls) {
			if(hitbox.intersects(wall.hitbox)) {
				hitbox.x -= xspeed;
				while(!wall.hitbox.intersects(hitbox)) hitbox.x += Math.signum(xspeed);
				hitbox.x -= Math.signum(xspeed);
				
				xspeed = 0;
				x = hitbox.x;
				}
		}
		
		//Vertical collision
		hitbox.y += yspeed;
		for(Wall wall : panel.walls) {
			if(hitbox.intersects(wall.hitbox)) {
				hitbox.y -= yspeed;
				while(!wall.hitbox.intersects(hitbox)) hitbox.y += Math.signum(yspeed);
				hitbox.y -= Math.signum(yspeed);
				yspeed = 0;
				y = hitbox.y;
			}
		}
		
		
		x+=xspeed;
		y+=yspeed;
		if(flyMode) yspeed=0;
		
		
		//Respawn
		if(y>800) panel.reset();
		
		//Scrolling
		if(x>700-(borderValue+width)) {scrollRight(xspeed);}
		if(x<borderValue) {scrollLeft(xspeed);}
		
		//Setting the hitbox position to the player position
		hitbox.x =x;
		hitbox.y = y;
		
	}
	
	public void scrollRight(double xspeed) {
		x = 700-(borderValue+width);
		panel.cameraX -= xspeed;
		panel.clearList();
	}
	
	public void scrollLeft(double xspeed) {
		x = borderValue;
		panel.cameraX -= xspeed;
		panel.clearList();
	}
	
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString("x "+x+" y "+y,50,50);
		g2d.drawString("cameraX "+panel.cameraX, 50, 70);
		
		/*try {
			Image img = ImageIO.read(dir);
			g2d.drawImage(img,x, y,null);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		g2d.drawImage(playerImg.img, x, y, null);
		
		g2d.setColor(Color.green);
        for(int i = -700; i<=700;i += 400)g2d.drawLine(panel.cameraX+i, 0, panel.cameraX+i, 700);
	}
	
}
