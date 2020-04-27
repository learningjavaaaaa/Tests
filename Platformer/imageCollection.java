import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageCollection {
	File dir = new File(System.getProperty("user.dir")+"/Libraries/image.png");
	Image img;
	
	public imageCollection(String filename) {
		dir = new File(System.getProperty("user.dir")+"/Libraries/"+filename+".png");
		try {
			img = ImageIO.read(this.dir);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
