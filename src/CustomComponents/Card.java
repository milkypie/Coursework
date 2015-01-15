package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import Control.CardMain;

public class Card extends GUIComponent{
	
	private Image Face;
	public int Value,Suit;
	public boolean FrontFacing = true;
	
	public Card(){
		this.setWidth(100);
		this.setHeight(500);
		Value = -1;
	}
		
	
	public int getValue() {
		return Value;
	}

	public void setValue(int value) {
		Value = value;
	}

	public boolean isFrontFacing() {
		return FrontFacing;
	}

	public void setFrontFacing(boolean frontFacing) {
		FrontFacing = frontFacing;
	}

	public void DealThis(){
		 width = 136;
		 height=222;
		 int tempValue = (int) Math.round(Math.random()*13) ;
		 int tempSuit = (int) Math.round(Math.random()*4);
		 if(tempValue==13){
			 tempValue = 0;
		 }
		 if(tempSuit==4){
			 tempSuit=0;
		 }

		 while(Control.CardMain.CardOut[tempSuit][tempValue]){
			 tempValue = (int) Math.round(Math.random()*13) ;
			 tempSuit = (int) Math.round(Math.random()*4);
			 if(tempValue==13){
				 tempValue = 0;
			 }
			 if(tempSuit==4){
				 tempSuit=0;
			 }
		 }
		 Value = tempValue;
		 Suit = tempSuit;
		 //set the picture for display
		 Face = Control.CardMain.Faces[Suit][Value];
		 Control.CardMain.CardOut[Suit][Value] = true;
	}
	
	@Override
	public void Draw(Graphics g) {
		// x,y,width,height,arcwidth,archeight
		
		//creates card image
		g.setColor(null);
		if(FrontFacing){	
			g.drawImage(Face, Xpos, Ypos, null);	
		}else{
			g.drawImage(Control.CardMain.Back, Xpos, Ypos, null);
		}
		
	}
	public void updateFace(){
		Face = Control.CardMain.Faces[Suit][Value];
	}

	public int getSuit() {
		return Suit;
	}


	public void setSuit(int suit) {
		Suit = suit;
	}


	@Override
	public void Update() {
		
	}

}
