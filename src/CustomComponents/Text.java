package CustomComponents;


import java.awt.Font;
import java.awt.Graphics;

public class Text extends GUIComponent{


	private String Content;
	private Font coverFont;


	public Font getCoverFont() {
		return coverFont;
	}

	public void setCoverFont(Font coverFont) {
		this.coverFont = coverFont;
	}

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
		Font OldFont = g.getFont();
		if(coverFont!=null){
			g.setFont(coverFont);
		}
		if(Content!=null){
			g.drawString(Content, Xpos, Ypos);
		}else{
			System.out.println("///"+"Content has not been properly initialized"+"\\\n ");
			
		}
		g.setFont(OldFont);
	}
	
}
