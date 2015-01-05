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
	public Card[] UserHand = new Card[5];
	public Card[] AIHand = new Card[5];
	public Background BJBackground = new Background(null);
	public Color BackgroundColour = new Color(0,114,0);
	private int NextUserCard;
	public boolean UserHadSplit = false,AIHasSplit = false;
	public BlackjackStage(){
		ID = 1;
		
		BJBackground.setColour(BackgroundColour);
		this.AddComponent(BJBackground);
		
		
		int x;
		
		for(x=0;x<5;x++){
			AIHand[x] = new Card();
			UserHand[x] = new Card();
			AIHand[x].setFrontFacing(false);
			if(x>1){
				UserHand[x].setActive(false);
				AIHand[x].setActive(false);
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
				UserHand[NextUserCard].DealThis();
				UserHand[NextUserCard].setLocation(713+(113*(NextUserCard-1)),643);
				DisplayItem(UserHand[NextUserCard]);
				NextUserCard++;
				System.out.println("hit");
				
			}
			
		});
		
		this.AddComponent(Buttons[0]);
		
		Buttons[1].setLocation(250,698);
		Buttons[1].setText(10,30,"Stick");
		Buttons[1].setAction(new Action(){

			@Override
			public void run() {
				AIPlay();
				DecideWhoWins();
				
				System.out.println("Stick");
				
			}
			
		});
		
		this.AddComponent(Buttons[1]);
		
		Buttons[2].setLocation(400,698);
		Buttons[2].setText(10,30,"Split");
		Buttons[2].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("Split");
				
			}
			
		});
		
		this.AddComponent(Buttons[2]);
		
		
		Buttons[6].setColour(Color.BLACK);
		Buttons[6].setTextColour(Color.red);
		Buttons[6].setLocation(1200, 700);
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

	public void DisplayItem(GUIComponent x){
		this.AddComponent(x);
	}
	public void AIPlay(){
		int x=0;
		while(ScoreHand(AIHand)<16){
			while(AIHand[x].Value!=-1){
				x++;
			}
			AIHand[x].DealThis();
			AIHand[x].setLocation(613 + ((x-1)*113), 50);
			DisplayItem(AIHand[x]);
		}
	}
	
	public void DecideWhoWins(){
		int AIScore = ScoreHand(AIHand);
		int UserScore = ScoreHand(UserHand);
		WinnerText.setLocation(400, 400);
		WinnerText.setWidth(100);
		WinnerText.setHeight(50);
		WinnerText.setColour(Color.RED);
		for(int x=0;x<5;x++){
			AIHand[x].setLocation(AIHand[x].getXpos(), AIHand[x].getYpos()+50);
			AIHand[x].setFrontFacing(true);
		}
		if(AIScore>21){
			AIScore=0;
		}
		if(UserScore>21){
			UserScore=0;
		}
		if(AIScore>UserScore){
			//lose
			WinnerText.setContent("You lose");
		}else if(UserScore>AIScore){
			//win
			WinnerText.setColour(Color.BLUE);
			WinnerText.setContent("You Win");
		}else {
			//draw
			WinnerText.setColour(Color.BLUE);
			WinnerText.setContent("You Draw");
		}
		DisplayItem(WinnerText);
	}
	public int ScoreHand(Card[] Hand){
		int Score=0;
		int Aces =0;
		for(int x=0;x<Hand.length;x++){
			//-1 is the default value for undealt cards
			if(Hand[x].getValue()!=-1){
				switch(Hand[x].getValue()){
				case 0:
					//Ace
					Score=Score+11;
					Aces++;
				break;
				case 10:
					//Jack
					Score = Score+10;				
				break;
				case 11:
					//Queen
					Score=Score+10;
				break;
				case 12:
					//King
					Score=Score+10;
				break;
				default:
					Score = Score + Hand[x].getValue()+1;
				break;
				}
			}
		}
		while(Aces!=0){
			if(Score>21){
				Score = Score-10;
				Aces--;
			}
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
