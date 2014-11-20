package Visuals;


import Cards.CardCreation;
import Control.CardMain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FrameHandler extends JLabel  {
	

	private static final long serialVersionUID = 1L;
	


		public FrameHandler(int Xpos, int Ypos, int Width, int Height, int Icon){
			this.setLocation(Xpos,Ypos);
			this.setSize(Width,Height);
			this.setIcon(CardCreation.Icon[Icon]);
			this.setVisible(true);
		}
		public FrameHandler(String text, int Suit){
			//super
			this.setLocation(1, 1);
			this.setSize(98,198);
			
			BufferedImage BackPanelImage = new BufferedImage(98,198,BufferedImage.TYPE_INT_ARGB);
			Graphics2D ValueMaker = BackPanelImage.createGraphics();
			ValueMaker.setColor(Color.white);
			ValueMaker.fillRoundRect(0,0,98,198,10,10);
			ValueMaker.setColor(new Color(255,0,255,255));
			ValueMaker.fillRoundRect(10, 30, 78, 138, 10, 10);
			//ValueMaker.fillRect(10,30,78,138);
			
			int x;
			
			
			
			switch(Suit){
			case 0:
				ValueMaker.setColor(Color.red);
			break;
			case 1:
				ValueMaker.setColor(Color.black);
			break;
			case 2:
				ValueMaker.setColor(Color.red);		
			break;
			case 3:
				ValueMaker.setColor(Color.black);
			break;
			}

			ValueMaker.drawString(text, 3, 12);
			
			if(text == "Q" ){
				//draw a queen
				switch(Suit){
				case 0:
					
				break;
				case 1:
					
				break;
				case 2:
					
				break;
				case 3:
					
				break;
				default:
					
				break;
				}
			}else if(text == "K" ){
				//draw a king
			}else if(text == "J" ){
				//draw a Jack
			}else if(text == "A" ){
				//Draw an Ace
			}else{
				try{
					int place = Integer.valueOf(text)-2;
					for(x = 0;x<=9;x++){
						if(CardMain.PictureSet[place][x]==null){
							break;
						}else{
							ValueMaker.drawImage(CardCreation.Image[Suit],CardMain.PictureSet[place][x].x,CardMain.PictureSet[place][x].y+15,null);
						}
					}if(text.length()==1){
						ValueMaker.drawString(text, 88, 188);
						ValueMaker.drawImage(CardCreation.Image[Suit],10,0,20,20,null);
						ValueMaker.rotate(Math.PI);
						ValueMaker.drawImage(CardCreation.Image[Suit],-88,-194,20,20,null);
						
					}else{
						ValueMaker.drawString(text, 80, 188);
						ValueMaker.drawImage(CardCreation.Image[Suit],16,0,20,20,null);
						ValueMaker.rotate(Math.PI);
						ValueMaker.drawImage(CardCreation.Image[Suit],-82,-194,20,20,null);
						
					}
					ValueMaker.dispose();
					this.setIcon(new ImageIcon(BackPanelImage));
					CardMain.TotalGUI.add(this);
				}catch(NumberFormatException e){
					System.out.println("you fuckwit");
				}
			}
		}
		
		
	

}
