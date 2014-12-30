package Stages;

import CustomComponents.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BlackjackStage extends CustomComponents.Stage{
	
	public Button Buttons[] = new Button[4];
	
	public BlackjackStage(){
		ID = 1;
		
		Background BJBackground = new Background(null);
		BJBackground.setColour(Color.GREEN);
		
		this.AddComponent(BJBackground);

		Buttons[0] = new Button();
		Buttons[0].setColour(Color.black);
		
	}
	

	
	public void Update() {
		Prepare();
	}


	


	@Override
	public void MousePressed(MouseEvent MouseArg) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void Prepare() {
		// TODO Auto-generated method stub
		
	}

}
