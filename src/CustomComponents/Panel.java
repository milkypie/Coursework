package CustomComponents;

import java.awt.Graphics;

public class Panel extends GUIComponent{
	
	public void setDimensions(int x,int y){
		width = x;
		height = y;
		
	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Colour);
		g.fillRect(Xpos, Ypos, width, height);
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
