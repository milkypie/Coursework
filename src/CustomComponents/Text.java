package CustomComponents;


import java.awt.Graphics;

public class Text extends GUIComponent{


	private String Content = null;


	public String getContent() {
		return Content;
	}
	
	public void setContent(String content){
		Content = content;
	}

	public void Update() {
		
	}


	public void Draw(Graphics g) {
		
		g.setColor(Colour);
		g.drawString(Content, Xpos, Ypos);
		
	}
	
}
