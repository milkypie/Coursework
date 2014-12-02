package CustomComponents;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Background extends GUIComponent{
	public Image backgroundImage;
	public void Draw(Graphics g) {
		
		g.setColor(Colour);
		g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		if(backgroundImage!=null){
			g.drawImage(backgroundImage, 0, 0, null);
		}
	}

	public void Update() {
		
	}
}
