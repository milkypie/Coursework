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
			 tempValue = (int) Math.round(Math.random()*14) ;
			 tempSuit = (int) Math.round(Math.random()*5);
			 if(tempValue==14){
				 tempValue = 0;
			 }
			 if(tempSuit==5){
				 tempSuit=0;
			 }
		 }
		 Value = tempValue;
		 Suit = tempSuit;
		 //testing force result
		 Face = Control.CardMain.Faces[Suit][Value];
	}
	
	@Override
	public void Draw(Graphics g) {
		// x,y,width,height,arcwidth,archeight
		//10 is an untested number, might need to be re-calibrated
		
		//creates card border
		g.setColor(Color.blue);
		g.fillRoundRect(Xpos, Ypos, width, height, 10, 10);
		//creates card backing
		g.setColor(Color.white);
		g.fillRoundRect(Xpos+2, Ypos+2, width-4, height-4, 8, 8);
		//creates card image
		g.setColor(null);
		g.drawImage(Face, Xpos+4, Ypos+4, null);
	}

	@Override
	public void Update() {
		
	}

}
