package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

import Control.CardMain;

public class Card extends GUIComponent{
	
	private Image Face;
	public int Value,Suit;
	private boolean FrontFacing = true, Rotated = false;
	private Action OnClick;
	
	public Card(){
		this.setWidth(100);
		this.setHeight(500);
		Rotation = 90;
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
	public void CopyCard(Card x){
		Value = x.getValue();
		Suit = x.getSuit();
		UpdateFace();
	}
	
	@Override
	public void Draw(Graphics g) {
		// x,y,width,height,arcwidth,archeight
		//creates card image
		if(Active){
			g.setColor(Colour);
			
			if(FrontFacing){	
				g.drawImage(Face, Xpos, Ypos, null);	
			}else if(Rotated){
				g.drawImage(Control.CardMain.BackRot, Xpos, Ypos, null);
			}else{
				g.drawImage(Control.CardMain.Back, Xpos, Ypos, null);
			}
		}
		
	}
	public void UpdateFace(){
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


	public Action getAction() {
		return OnClick;
	}


	public void setAction(Action onClick) {
		OnClick = onClick;
	}
	
	public void RunAction(){
		OnClick.run();
	}
	
	public void RunAction(Card x){
		OnClick.run(x);
	}


	public boolean isRotated() {
		return Rotated;
	}


	public void setRotated(boolean rotated) {
		Rotated = rotated;
	}
}
