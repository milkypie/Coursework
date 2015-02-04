package CustomComponents;

import java.awt.Graphics;

public class Panel extends GUIComponent{

	@Override
	public void Draw(Graphics g) {
		g.fillRect(Xpos, Ypos, width, height);
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
