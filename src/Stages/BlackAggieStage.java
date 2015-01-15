package Stages;

import java.awt.Point;
import java.awt.event.MouseEvent;

import CustomComponents.*;

@SuppressWarnings("serial")
public class BlackAggieStage extends CustomComponents.Stage{
	Card[] UserHand = new Card[13];
	Action CardAction;
	Card[][] AIHand = new Card[3][13];
	Button[] Buttons = new Button[3];
	Boolean SwapStage = true;
	
	public BlackAggieStage(){
		ID = 3;
		
		CardAction = new Action(){

			@Override
			public void run() {}
			public void run(GUIComponent x){
				
			}
			
		};
		for(int x=0;x<3;x++){
			Buttons[x] = new Button();
		}
		
		for(int x=0;x<13;x++){
			UserHand[x] = new Card();
			UserHand[x].DealThis();
			UserHand[x].setLocation(400+(50*(x-1)), 643);
			UserHand[x].setAction(CardAction);
			AddItem(UserHand[x]);
		}
		
		Buttons[0].setLocation(1200,600);
		Buttons[0].setText(10,30,"Re-set");
		Buttons[0].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("resetting game");
				
			}
			
		});
		
		this.AddComponent(Buttons[0]);
		Buttons[1].setLocation(1200, 700);
		Buttons[1].setText(10, 30, "exit");
		Buttons[1].setAction(new Action(){

			@Override
			public void run() {
				System.exit(0);
				
			}
			
		});
		this.AddComponent(Buttons[1]);
		
		Buttons[2].setLocation(1200, 500);
		Buttons[2].setText(10,30,"Done");
		Buttons[2].setAction(new Action(){
			public void run(){
				System.out.println("done");
			}
		});
	}
	
	public void Prepare() {
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
					System.out.println(i);
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