package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends GUIComponent implements MouseListener{

	//text stuff
	public Action action;
	private Text Text = new Text();
	private int TextX = 0 ,TextY = 0,TextWidth = 80,TextHeight = 30;
	private Color TextColour = Color.RED;
	private String Content = "";
	private int Style = 1;
	
	public Button(){
		this.width = 100;
		this.height = 50;
		this.Colour = Color.BLACK;
	}
	
	public void setAction(Action newAction){
		action = newAction;
	}
	
	public void runAction(){
		if(action!=null){
			action.run();
		}
	}
		
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
		TextX=Xpos+textx;
		TextY = Ypos+texty;
		Content=content;
		Text.setXpos(textx);
		Text.setYpos(texty);
		Text.setContent(content);
	}
	
	public void Draw(Graphics g) {
		g.setColor(Colour);
		switch(Style){
		case 1:
			g.fillRoundRect(Xpos, Ypos, width, height, 10, 10);
		break;
		default:
			g.fillRect(Xpos, Ypos, width, height);
		break;
		}
		
		g.setColor(TextColour);
		Text.setColour(TextColour);
		Text.setContent(Content);
		Text.setXpos(TextX);
		Text.setYpos(TextY);
		Text.Draw(g);
	}


	public int getStyle() {
		return Style;
	}


	public void setStyle(int style) {
		Style = style;
	}


	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
