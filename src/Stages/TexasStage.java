package Stages;

import java.awt.event.MouseEvent;

import Control.CardMain;
import CustomComponents.*;

@SuppressWarnings("serial")
public class TexasStage extends CustomComponents.Stage {
	private Card[][] Hand = new Card[4][2];
	private Button[] Buttons = new Button[6];
	private Background TexBJ = new Background(null);
	
	public TexasStage(){
		ID=2;
		AddItem(TexBJ);
		for(int x = 0; x<6; x++){
			Buttons[x] = new Button();
			if(x<4){
				Hand[x][0] = new Card();
				Hand[x][1] = new Card();
			}
		}

		Hand[0][0].setLocaiton(30,(int)Math.round((CardMain.ScreenHeight/2)-75));
		Hand[0][1].setLocaiton(30,(int)Math.round((CardMain.ScreenHeight/2)-25));
		Hand[0][0].setRotated(true);
		Hand[0][1].setRotated(true);
		Hand[0][0].setFrontFacing(false);
		Hand[0][1].setFrontFacing(false);
		
		Hand[1][0].setLocaiton((int)Math.round((CardMain.ScreenWidth/2)-75),30);
		Hand[1][1].setLocaiton((int)Math.round((CardMain.ScreenWidth/2)-25),30);
		Hand[1][0].setFrontFacing(false);
		Hand[1][1].setFrontFacing(false);

		Hand[2][0].setLocaiton(CardMain.ScreenWidth - 125,(int)Math.round((CardMain.ScreenHeight/2)-75));
		Hand[2][1].setLocaiton(CardMain.ScreenWidth - 125,(int)Math.round((CardMain.ScreenHeight/2)-25));
		Hand[2][0].setRotated(true);
		Hand[2][1].setRotated(true);
		Hand[2][0].setFrontFacing(false);
		Hand[2][1].setFrontFacing(false);

		Hand[3][0].setLocaiton((int)Math.round((CardMain.ScreenWidth/2)-75),CardMain.ScreenHeight-125);
		Hand[3][1].setLocaiton((int)Math.round((CardMain.ScreenWidth/2)-25),CardMain.ScreenHeight-125);
		
		Buttons[3].setLocation(1050,700);
		Buttons[3].setText(10,30,"Back To Menu");
		Buttons[3].setAction(new Action(){

			@Override
			public void run() {
				Buttons[4].runAction();
				Control.CardMain.GameLoop.ChangeStage(StageHandler.STAGE_MENU);
			}
			
		});
		
		AddItem(Buttons[3]);
		
		Buttons[4].setLocation(1200,600);
		Buttons[4].setText(10,30,"Re-set");
		Buttons[4].setAction(new Action(){

			@Override
			public void run() {
				
			}
			
		});
		
		AddItem(Buttons[4]);
		Buttons[5].setLocation(1200, 700);
		Buttons[5].setText(10, 30, "exit");
		Buttons[5].setAction(new Action(){

			@Override
			public void run() {
				System.exit(0);
				
			}
			
		});
		AddItem(Buttons[5]);
	}
	private void AddItem(GUIComponent x) {
		this.AddComponent(x);
	}
	public void Prepare(){
		CardMain.resetDeck();
		for(int x = 0;x<4;x++){
			for(int y = 0;y<2;y++){
				Hand[x][y].DealThis();
				AddItem(Hand[x][y]);
			}
		}
	}
	public void Update(){
		
	}
	@Override
	public void MousePressed(MouseEvent MouseArg) {
		int i;
		for(i=0;i<6;i++){
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
	
}
