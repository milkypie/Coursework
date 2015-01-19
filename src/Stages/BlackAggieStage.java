package Stages;

import java.awt.Point;
import java.awt.event.MouseEvent;

import Control.CardMain;
import CustomComponents.*;

@SuppressWarnings("serial")
public class BlackAggieStage extends CustomComponents.Stage{
	private Card[] UserHand = new Card[13];
	private Action CardAction;
	private Card[][] AIHand = new Card[3][13];
	private Button[] Buttons = new Button[3];
	private Boolean SwapStage = true;
	Background AggieBJ = new Background(null);
	
	public BlackAggieStage(){
		ID = 3;
		this.AddComponent(AggieBJ);
		
		CardAction = new Action(){

			@Override
			public void run() {}
			public void run(GUIComponent x){
				if(x.getYpos()==643){
					x.setLocation(x.getXpos(),603);
				}else{
					x.setLocation(x.getXpos(),643);
				}
			}
			
		};
		for(int x=0;x<3;x++){
			Buttons[x] = new Button();
		}
		
		for(int x=0;x<13;x++){
			UserHand[x] = new Card();
			UserHand[x].setLocation(400+(50*(x-1)), 643);
			UserHand[x].setAction(CardAction);
			
			AIHand[1][x] = new Card();
			AIHand[1][x].setLocation(400+(50*(x-1)), 50);
			AIHand[1][x].setFrontFacing(false);
			

			AIHand[2][x]= new Card();
			AIHand[2][x].setLocation(1225, 200+(30*(x-1)));
			AIHand[2][x].setFrontFacing(false);
			AIHand[2][x].setRotated(true);
			
			AIHand[0][x]= new Card();
			AIHand[0][x].setLocation(100, 200+(30*(x-1)));
			AIHand[0][x].setFrontFacing(false);
			AIHand[0][x].setRotated(true);
			

			
		}

		Buttons[0].setLocation(1050,675);
		Buttons[0].setText(10,30,"Re-set");
		Buttons[0].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("resetting game");
				
				for(int y = 0;y<4;y++){
					for(int x=0;x<13;x++){
						CardMain.CardOut[y][x] = false;
					}
				}
				
			}
			
		});
		
		this.AddComponent(Buttons[0]);
		Buttons[1].setLocation(1200, 675);
		Buttons[1].setText(10, 30, "exit");
		Buttons[1].setAction(new Action(){

			@Override
			public void run() {
				System.exit(0);
				
			}
			
		});
		this.AddComponent(Buttons[1]);
		
		Buttons[2].setLocation(150, 675);
		Buttons[2].setText(10,30,"Done");
		Buttons[2].setAction(new Action(){
			public void run(){
				System.out.println("done");
			}
		});
		
		this.AddComponent(Buttons[2]);
	}
	
	public void Prepare() {
		for(int x=0;x<4;x++){
			for(int y=0;y<13;y++){
				CardMain.CardOut[x][y] = false;
			}
		}
		for(int x=0;x<13;x++){
			UserHand[x].DealThis();
			AIHand[0][x].DealThis();
			AIHand[1][x].DealThis();
			AIHand[2][x].DealThis();
			
			AddItem(AIHand[2][x]);
			AddItem(AIHand[1][x]);
			AddItem(AIHand[0][x]);
			AddItem(UserHand[x]);
			
		}
	}
	
	

	public void Update() {
		
		
	}
	public void AddItem(GUIComponent x){
		this.AddComponent(x);
	}
	public void RemoveItem(GUIComponent x){
		this.RemoveComponent(x);
	}

	@Override
	public void MousePressed(MouseEvent MouseArg) {
		int i;
		Boolean FoundSource = false;
		Point MouseLocation = MouseArg.getLocationOnScreen();
		for(i=0;i<2;i++){
			if(!FoundSource){
				//if event took place inside x
				if(MouseLocation.x>Buttons[i].getXpos()&&MouseLocation.x<Buttons[i].getXpos()+Buttons[i].getWidth()){
					//if event took place inside y
					if(MouseLocation.y>Buttons[i].getYpos()&&MouseLocation.y<Buttons[i].getYpos()+Buttons[i].getHeight()){
						//execute action
						System.out.println("Button:"+i+" will execute its action");
						FoundSource = true;
						Buttons[i].runAction();
					}
				}
			}
		}
		if(!FoundSource){
			for(i=12;i>=0;i--){
				if(!FoundSource){
					if(MouseLocation.x>UserHand[i].getXpos()&&MouseLocation.x<UserHand[i].getXpos()+UserHand[i].getWidth()){
						if(MouseLocation.y>UserHand[i].getYpos()&&MouseLocation.y<UserHand[i].getYpos()+UserHand[i].getHeight()){
							System.out.println("clicked on Card " + i);
							FoundSource = true;
							UserHand[i].RunAction(UserHand[i]);
						}
					}
				}
			}
		}
	}
}