package CustomComponents;

import java.awt.Graphics;

public class Panel extends GUIComponent{
	public int Style = 0;
	
	public int getStyle() {
		return Style;
	}

	public void setStyle(int style) {
		Style = style;
	}

	public void setDimensions(int x,int y){
		width = x;
		height = y;
		
	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Colour);
		if(Style == 0){
			g.fillRect(Xpos, Ypos, width, height);
		}else if(Style == 1){
			g.fillRoundRect(Xpos, Ypos, width, height, 10, 10);
		}
		
	}

	@Override
	public void Update() {
		
	}

}
