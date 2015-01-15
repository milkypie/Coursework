package Stages;

import java.awt.event.MouseEvent;
import CustomComponents.*;

@SuppressWarnings("serial")
public class BlackAggieStage extends CustomComponents.Stage{
	public BlackAggieStage(){
		ID = 3;
		Card[] UserHand = new Card[13];
		Card[][] AIHand = new Card[3][13];
		Button[] Buttons = new Button[4];
		Boolean SwapStage = true;
		
	}
	
	public void Prepare() {
	}
	
	

	public void Update() {
		
		
	}

	@Override
	public void MousePressed(MouseEvent MouseArg) {
		// TODO Auto-generated method stub
		
	}
}