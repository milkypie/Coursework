package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GUIComponent {
	
	protected int Xpos = 0,Ypos = 0,width = 80,height = 30,ActionID;
	protected Action action;
	protected Color Colour = Color.black;
	protected boolean Active = true;
	
	public void setLocation(int X,int Y){
		Xpos = X;
		Ypos = Y;
	}
	public int getXpos() {
		return Xpos;
	}

	public void setXpos(int xpos) {
		Xpos = xpos;
	}

	public int getYpos() {
		return Ypos;
	}

	public void setYpos(int ypos) {
		Ypos = ypos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColour() {
		return Colour;
	}

	public void setColour(Color colour) {
		Colour = colour;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}
	
	public abstract void Draw(Graphics g);
	
	public abstract void Update();
}
