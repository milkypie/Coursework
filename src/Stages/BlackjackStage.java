package Stages;

import Control.CardMain;
import CustomComponents.*;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BlackjackStage extends CustomComponents.Stage{
	
	public Text WinnerText = new Text();
	public Button[] Buttons = new Button[7];
	public int[] Colours = new int[3];
	public String[] contents = new String[3];
	public Card[] UserHand = new Card[7],UserSplitHand = new Card[7];
	public Card[] AIHand = new Card[7], AISplitHand = new Card[7];
	public Background BJBackground = new Background(null);
	public Color BackgroundColour = new Color(0,114,0);
	private int NextUserCard;
	public boolean UserHasSplit = false,AIHasSplit = false,CanHit=true;
	public BlackjackStage(){
		ID = 1;
		
		BJBackground.setColour(BackgroundColour);
		this.AddComponent(BJBackground);
		
		
		int x;
		
		for(x=0;x<7;x++){
			AIHand[x] = new Card();
			UserHand[x] = new Card();
			AIHand[x].setFrontFacing(false);
			if(x>1){
				UserHand[x].setActive(false);
				AIHand[x].setActive(false);
				UserHand[x].setValue(-1);
				AIHand[x].setValue(-1);
			}else{
				UserHand[x].DealThis();
				AIHand[x].DealThis();
			}
		}
		//63
		//95
		UserHand[0].setLocation(600, 643);
		UserHand[1].setLocation(713, 643);
		
		this.AddComponent(UserHand[0]);
		this.AddComponent(UserHand[1]);
		
		NextUserCard=2;
		
		AIHand[0].setLocation(500,50);
		AIHand[1].setLocation(613,50);
		
		this.AddComponent(AIHand[0]);
		this.AddComponent(AIHand[1]);
		
		for(x=0;x<3;x++){
				Colours[x]=1;
			
		}
		for(x=0;x<7;x++){
			Buttons[x] = new Button();
		}
		//1366 wide
		//768 tall


		Buttons[0].setLocation(100,698);
		Buttons[0].setText(10,30,"Hit");
		Buttons[0].setAction(new Action(){

			@Override
			public void run() {
				if(CanHit){
					UserHand[NextUserCard].DealThis();
					UserHand[NextUserCard].setLocation(713+(113*(NextUserCard-1)),643);
					AddItem(UserHand[NextUserCard]);
					NextUserCard++;
	
					if(ScoreHand(UserHand)==0){
						Buttons[1].runAction();
					}
				}
			}
			
		});
		
		this.AddComponent(Buttons[0]);
		
		Buttons[1].setLocation(250,698);
		Buttons[1].setText(10,30,"Stick");
		Buttons[1].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("user has Stuck");
				CanHit=false;
				AIPlay();
				DecideWhoWins();
				
				
			}
			
		});
		
		this.AddComponent(Buttons[1]);
		
		Buttons[2].setLocation(400,698);
		Buttons[2].setText(10,30,"Split");
		Buttons[2].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("Split");
				if(UserHand[0].Value==UserHand[1].Value){
					if(!UserHasSplit){
						
					}
				}
				
				
			}
			
		});
		
		this.AddComponent(Buttons[2]);
		
		Buttons[5].setLocation(1200,600);
		Buttons[5].setText(10,30,"Re-set");
		Buttons[5].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("resetting game");
				
				RemoveItem(WinnerText);
				for(int x=0;x<7;x++){
					AIHand[x].setLocation(500+(113*x), 50);
					AIHand[x].setFrontFacing(false);
					
					if(x>1){
						AIHand[x].setValue(-1);
						RemoveItem(AIHand[x]);
						RemoveItem(UserHand[x]);
						UserHand[x].setValue(-1);
					}else{
						AIHand[x].DealThis();
						UserHand[x].DealThis();
					}
				}
				RemoveItem(WinnerText);
				NextUserCard = 2;
				CanHit=true;
			}
			
		});
		
		this.AddComponent(Buttons[5]);
		Buttons[6].setLocation(1200, 700);
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

	public void AddItem(GUIComponent x){
		this.AddComponent(x);
	}
	public void RemoveItem(GUIComponent x){
		this.RemoveComponent(x);
	}
	public void AIPlay(){
		int x=0;
		System.out.println("AI is playing");
		System.out.println(ScoreHand(AIHand));
		while(ScoreHand(AIHand)<16&&ScoreHand(AIHand)>0){
			System.out.println("first loop of AIplay");
			while(AIHand[x].Value!=-1){
				System.out.println(x);
				x++;
			}
			System.out.println("AI Has "+x+"cards");
			AIHand[x].DealThis();
			AIHand[x].setLocation(613 + ((x-1)*113), 50);
			AddItem(AIHand[x]);
			if(x==6){
				break;
			}
		}
		System.out.println("end of ai play");
	}
	
	public void DecideWhoWins(){
		int AIHighestScore;
		System.out.println("scoring AI");
		int AIScore = ScoreHand(AIHand);
		System.out.println("scoring user");
		int UserScore = ScoreHand(UserHand);
		int UserSplitScore = 0;
		if(AIHasSplit){
			int AIScore2 = ScoreHand(AISplitHand);
			if(AIScore2>AIScore){
				AIHighestScore = AIScore2;
			}else{
				AIHighestScore = AIScore;
			}
		}else{
			AIHighestScore = AIScore;
		}
		WinnerText.setLocation(400, 400);
		WinnerText.setWidth(100);
		WinnerText.setHeight(50);
		WinnerText.setColour(Color.RED);
		for(int x=0;x<7;x++){
			AIHand[x].setLocation(AIHand[x].getXpos(), 100);
			AIHand[x].setFrontFacing(true);
		}
		if(AIHighestScore>UserScore&&AIHighestScore>UserSplitScore){
			//lose
			WinnerText.setContent("You lose");
		}else if(UserScore>AIHighestScore){
			//win
			WinnerText.setColour(Color.BLUE);
			WinnerText.setContent("You Win");
		}else {
			//draw
			WinnerText.setColour(Color.BLUE);
			WinnerText.setContent("You Draw");
		}
		AddItem(WinnerText);
	}
	public int ScoreHand(Card[] Hand){
		int Score=0;
		int Aces =0;
		System.out.println("---------------");
		System.out.println(Hand[0].Value);
		System.out.println(Hand[1].Value);
		System.out.println(Hand[2].Value);
		System.out.println(Hand[3].Value);
		System.out.println("================");
		for(int x=0;x<Hand.length;x++){
			//-1 is the default value for undealt cards
			if(Hand[x].getValue()!=-1){
				switch(Hand[x].getValue()){
				case 0:
					//Ace
					System.out.println("found an ace");
					Score=Score+11;
					Aces++;
				break;
				case 10:
					//Jack
					System.out.println("found a jack");
					Score = Score+10;				
				break;
				case 11:
					//Queen
					System.out.println("found a queen");
					Score=Score+10;
				break;
				case 12:
					//King
					System.out.println("found a king");
					Score=Score+10;
				break;
				default:
					System.out.println("found a"+Hand[x].getValue()+1);
					Score = Score + Hand[x].getValue()+1;
				break;
				}
			}
		}
		
		while(Aces!=0){
			if(Score>21){
				Score = Score-10;
				Aces--;
			}else{
				break;
			}
		}
		if(Score>21){
			Score=0;
		}
		
		return Score;
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
