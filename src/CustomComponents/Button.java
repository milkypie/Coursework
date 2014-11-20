package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;

public class Button extends GUIComponent{

	//text stuff
	private Text Text = new Text();
	private int TextX = 0 ,TextY = 0,TextWidth,TextHeight;
	private Color TextColour = Color.black;
	private String Content = "";
	
	public int getTextWidth() {
		return TextWidth;
	}


	public void setTextWidth(int textWidth) {
		TextWidth = textWidth;
		this.Text.setWidth(TextWidth);
	}


	public int getTextHeight() {
		return TextHeight;
	}


	public void setTextHeight(int textHeight) {
		TextHeight = textHeight;
		this.Text.setHeight(TextHeight);
	}
	
	
	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
		this.Text.setContent(Content);
	}


	public int getTextX() {
		return TextX;
	}


	public void setTextX(int textX) {
		TextX = textX;
		this.Text.setXpos(TextX);
		
	}


	public int getTextY() {
		return TextY;
	}


	public void setTextY(int textY) {
		TextY = textY;
		this.Text.setYpos(textY);
	}

	public Color getTextColour() {
		return TextColour;
	}


	public void setTextColour(Color textColour) {
		this.TextColour = textColour;
		this.Text.setColour(TextColour);
	}


	public Text getText() {
		return Text;
	}


	public void setText(Text text) {
		this.Text = text;
	}
	
	public void setText(int textx,int texty,String content){
		this.Text.setXpos(textx);
		this.Text.setYpos(texty);
		this.Text.setContent(content);
	}
	
	public void Draw(Graphics g) {
		g.setColor(Colour);
		g.fillRect(Xpos, Ypos, width, height);
		g.setColor(TextColour);
		this.Text.setContent(Content);
		this.Text.setXpos(TextX);
		this.Text.setYpos(TextY);
		this.Text.Draw(g);
	}


	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
