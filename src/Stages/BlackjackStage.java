package Stages;

import Control.CardMain;
import CustomComponents.*;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BlackjackStage extends CustomComponents.Stage{
	
	public Button[] Buttons = new Button[7];
	public int[] Colours = new int[3];
	public String[] contents = new String[3];
	public Background BJBackground = new Background(null);
	public Color BackgroundColour = new Color(0,114,0);
	public BlackjackStage(){
		ID = 1;
		
		BJBackground.setColour(BackgroundColour);
		this.AddComponent(BJBackground);
		System.out.print("testing is:");
		System.out.println(CardMain.testing);
		int x;
			
		for(x=0;x<3;x++){
				Colours[x]=1;
			
		}
		for(x=0;x<7;x++){
			Buttons[x] = new Button();
		}
		//1366 wide
		//768 tall
		
		Buttons[0].setLocation((768-70),100);
		Buttons[0].setContent("Hit");
		
		
		Buttons[6].setColour(Color.BLACK);
		Buttons[6].setTextColour(Color.red);
		Buttons[6].setLocation(1000, 500);
		Buttons[6].setWidth(100);
		Buttons[6].setHeight(50);
		Buttons[6].setStyle(1);
		Buttons[6].setText(10, 30, "exit");
		Buttons[6].setAction(new Action(){

			@Override
			public void run() {
				System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width);
				System.out.println(Toolkit.getDefaultToolkit().getScreenSize().height);
				System.exit(0);
				
			}
			
		});
		this.AddComponent(Buttons[6]);
		Colours[1]=114;
	}
	

	
	public void Update() {
		
	}


	


	@Override
	public void MousePressed(MouseEvent MouseArg) {
		
		System.out.println("Menu Stage recieved mouse event");
		int i;
		for(i=0;i<7;i++){
			//if event took place inside x
			if(MouseArg.getLocationOnScreen().x>Buttons[i].getXpos()&&MouseArg.getLocationOnScreen().x<Buttons[i].getXpos()+Buttons[i].getWidth()){
				//if event took place inside y
				if(MouseArg.getLocationOnScreen().y>Buttons[i].getYpos()&&MouseArg.getLocationOnScreen().y<Buttons[i].getYpos()+Buttons[i].getHeight()){
					//execute action
					System.out.println("Button:"+i+" will execute its action");
					Buttons[i].runAction();
				}
			}
		}
		
	}



	@Override
	public void Prepare() {
		// TODO Auto-generated method stub
		
	}

}
