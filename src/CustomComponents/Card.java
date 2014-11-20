package CustomComponents;

import java.awt.Graphics;

import javax.imageio.ImageIO;

public class Card extends GUIComponent{
	
	protected ImageIO Face;
	public int Value;
	
	
	public void DealThis(){
		 int temp = (int) Math.round(Math.random()*14) ;
		 if(temp==14){
			 temp = 0;
		 }
		 Value = temp;
	}
	
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
