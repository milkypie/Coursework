package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Stages.StageHandler;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Control.CardMain.testing){
			System.out.println("Mouse Click Detected");
		}
		StageHandler.StageArray[StageHandler.CurrentStage].MousePressed(e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
