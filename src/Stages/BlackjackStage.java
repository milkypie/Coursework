
package Stages;

import Control.CardMain;
import CustomComponents.Panel;
import CustomComponents.Text;
import CustomComponents.Button;
import CustomComponents.Card;
import CustomComponents.Background;
import CustomComponents.GUIComponent;
import CustomComponents.Action;

import java.awt.Color;
import java.awt.event.MouseEvent;


@SuppressWarnings("serial")
public class BlackjackStage extends CustomComponents.Stage{
	
	public Panel Indicator = new Panel();
	public Text WinnerText = new Text(), FirstStickText = new Text();
	public Button[] Buttons = new Button[6];
	public int[] Colours = new int[3];
	public String[] contents = new String[3];
	public static Card[] UserHand = new Card[7];
	public static Card[] UserSplitHand = new Card[7];
	public static Card[] AIHand = new Card[7], AISplitHand = new Card[7];
	public Background BJBackground = new Background(null);
	public Color BackgroundColour = new Color(0,114,0);
	private int NextUserCard, NextSplitCard;
	public boolean UserHasSplit = false,AIHasSplit = false,CanHit=true,SplitStuck = false;
	public Card tester = new Card();
	
	public BlackjackStage(){
		ID = 1;
		//setting up the background
		BJBackground.setColour(BackgroundColour);
		this.AddComponent(BJBackground);
		//throw away variable to manage for loops
		int x;
		
		for(x=0;x<7;x++){
			//instantiating the hands 
			AIHand[x] = new Card();
			UserHand[x] = new Card();
			UserSplitHand[x] = new Card();
			AISplitHand[x] = new Card();
			UserSplitHand[x].setLocation(600+(113*x), 543);
			AIHand[x].setFrontFacing(false);
			if(x>1){
				UserHand[x].setValue(-1);
				AIHand[x].setValue(-1);
			}
		}
		//63 width of a card
		//95 height of a card
		
		//sets location for user hand
		UserHand[0].setLocation(600, 643);
		UserHand[1].setLocation(713, 643);
		//sets the next card to be drawn to both possible user hands
		NextUserCard=2;
		NextSplitCard=2;
		//sets location for computer hand
		AIHand[0].setLocation(500,50);
		AIHand[1].setLocation(613,50);
		
		
		for(x=0;x<3;x++){
			//instantiates the colours
			Colours[x]=1;
			
		}
		for(x=0;x<6;x++){
			//instantiates the buttons
			Buttons[x] = new Button();
		}
		//1366 wide
		//768 tall

		//defining the Hit button
		Buttons[0].setLocation(100,698);
		Buttons[0].setText(10,30,"Hit");
		Buttons[0].setAction(new Action(){
			//hit action
			@Override
			public void run() {
				if(CanHit){
					//if the user is able to hit
					if(UserHasSplit){
						//if the user has split
						if(SplitStuck){
							//if the user has split and has stuck on their first hand
							//deal another card to the user's second hand
							UserSplitHand[NextSplitCard].DealThis();
							UserSplitHand[NextSplitCard].setLocation(713+(113*(NextSplitCard-1)),543);
							AddItem(UserSplitHand[NextSplitCard]);
							NextSplitCard++;
							if(ScoreHand(UserSplitHand)==0){
								//if the score of the user's second hand is over 21
								//run the stick button's action
								Buttons[1].runAction();
							}
						}else{
							//if the user has split and they have not stuck on their first hand
							if(NextUserCard<7){
								//if the user has less than the maximum number of cards
								//deal a card
								UserHand[NextUserCard].DealThis();
								UserHand[NextUserCard].setLocation(713+(113*(NextUserCard-1)),643);
								AddItem(UserHand[NextUserCard]);
								NextUserCard++;
							}
						}
					}else{
						//if the user has not split
						if(NextUserCard<7){
							//if the user has less than the maximum number of cards
							//deal a card
							UserHand[NextUserCard].DealThis();
							UserHand[NextUserCard].setLocation(713+(113*(NextUserCard-1)),643);
							AddItem(UserHand[NextUserCard]);
							NextUserCard++;
							System.out.println("*****************"+UserHand[NextUserCard-1].Suit+UserHand[NextUserCard-1].Value+"******");
						}
						if(ScoreHand(UserHand)==0){
							//if the score of the user's hand is over 21
							//stick the user's hand
							Buttons[1].runAction();
						}
					}
				}
			}
			
		});
		
		this.AddComponent(Buttons[0]);
		
		//Defining the stick button
		Buttons[1].setLocation(250,698);
		Buttons[1].setText(10,30,"Stick");
		Buttons[1].setAction(new Action(){
			//Stick action
			@Override
			public void run() {
				if(UserHasSplit){
					//if the user has split
					if(SplitStuck){
						//if the user has split and stuck once
						//ensure the user doesn't stick again
						CanHit=false;
						System.out.println("user has Stuck");
						//Computer's turn
						AIPlay();
						//Decide who wins
						DecideWhoWins();
					}else{
						//if the user has split and not stuck
						//ensure the user doesn't try to draw to this hand again
						SplitStuck = true;
						//move the indicator to the other hand
						Indicator.setLocation(530,600);
						
						//defines the piece of text that appears to inform the user that they are now drawing to their second hand
						FirstStickText.setLocation(200,670);
						FirstStickText.setColour(Color.YELLOW);
						FirstStickText.setWidth(100);
						FirstStickText.setContent("You are now drawing to your second hand");
						AddItem(FirstStickText);
					}
				}else{
				//if the user has not plit
				System.out.println("user has Stuck");
				//ensure the user can't draw any more cards
				CanHit=false;
				//Computer's turn
				AIPlay();
				//Decide the winner
				DecideWhoWins();
				
				}
			}
			
		});
		
		this.AddComponent(Buttons[1]);
		
		//defining the split button
		Buttons[2].setLocation(400,698);
		Buttons[2].setText(10,30,"Split");
		Buttons[2].setAction(new Action(){
			//split action
			@Override
			public void run() {
				System.out.println("Split");
				if(UserHand[0].Value==UserHand[1].Value){
					//if the user is capable of splitting
					if(!UserHasSplit){
						//if the user hasn't already split
						System.out.println("----------");
						UserHasSplit=true;
						//copies the second card in the user's hand to be the first hand in the second hand
						UserSplitHand[0].setValue(UserHand[1].getValue());
						UserSplitHand[0].setSuit(UserHand[1].getSuit());
						UserSplitHand[0].UpdateFace();
						//re-deals the second card in the user's first hand to appear as a new card
						UserHand[1].DealThis();
						//deals a second card to the user's second hand
						UserSplitHand[1].DealThis();
						
						//defines the indicator which ensures the user knows which hand they are dealing to
						Indicator.setWidth(50);
						Indicator.setHeight(10);
						Indicator.setColour(Color.red);
						Indicator.setLocation(530,670);
						
						
						AddItem(Indicator);
						AddItem(UserSplitHand[0]);
						AddItem(UserSplitHand[1]);
						
					}
				}
				
				
			}
			
		});
		
		this.AddComponent(Buttons[2]);
		
		//defining the menu button
		Buttons[3].setLocation(1050,700);
		Buttons[3].setText(10,30,"Back To Menu");
		Buttons[3].setAction(new Action(){
			//menu action
			@Override
			public void run() {
				//reset this game, so that if the  user return it will be a new game
				Buttons[4].runAction();
				//changes the stage that is being shown to the user to the menu stage
				Control.CardMain.GameLoop.ChangeStage(StageHandler.STAGE_MENU);
			}
			
		});
		
		this.AddComponent(Buttons[3]);
		
		//defining the reset button
		Buttons[4].setLocation(1200,600);
		Buttons[4].setText(10,30,"Re-set");
		Buttons[4].setAction(new Action(){
			//reset action
			@Override
			public void run() {
				System.out.println("resetting game");
				//resets the deck, so that none of the cards are marked as dealt
				CardMain.resetDeck();
				for(int x=0;x<7;x++){
					//ensures the computer cards are in the correct position
					AIHand[x].setLocation(500+(113*x), 50);
					//ensures the computer cards have their backs to the user
					AIHand[x].setFrontFacing(false);
					if(UserHasSplit){
						//if the user has split
						//change the value of the cards so that they don't confuse the scoring system in a later game
						UserSplitHand[x].setValue(-1);
					}
					if(x>1){
						//if not the first two loops
						//cards that shouldn't be in the original setup are removed
						AIHand[x].setValue(-1);
						UserHand[x].setValue(-1);
						RemoveItem(AIHand[x]);
						RemoveItem(UserHand[x]);
					}else{
						//if in the first two loops
						//re-deal the original two cards
						AIHand[x].DealThis();
						UserHand[x].DealThis();
					}
				}
				if(UserHasSplit){
					//if the user split in the previous game
					for(int x=0;x<7;x++){
						//removes all possible cards that could have been added
						RemoveItem(UserSplitHand[x]);
					}
					//removes the indicator and text help
					RemoveItem(FirstStickText);
					RemoveItem(Indicator);
				}
				//removes the text that tells the user who has won
				RemoveItem(WinnerText);
				//resets varables
				NextUserCard = 2;
				NextSplitCard = 2;
				CanHit=true;
				UserHasSplit=false;
				SplitStuck=false;
				
			}
			
		});
		
		this.AddComponent(Buttons[4]);
		//defining exit button
		Buttons[5].setLocation(1200, 700);
		Buttons[5].setText(10, 30, "exit");
		Buttons[5].setAction(new Action(){
			//exit action
			@Override
			public void run() {
				//terminate the program
				System.exit(0);
				
			}
			
		});
		this.AddComponent(Buttons[5]);
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
		//placeholder variable
		int x=0;
		System.out.println("AI is playing");
		System.out.println(ScoreHand(AIHand));
		while(ScoreHand(AIHand)<16&&ScoreHand(AIHand)>0){
			//while the computer has a score less than 16 but has not gone bust
			System.out.println("first loop of AIplay");
			while(AIHand[x].Value!=-1){
				//while the card has been dealt
				System.out.println(x);
				x++;
				//this while loop finds the position in the array that will
				//hold the next card to be dealt to the computer
			}
			System.out.println("AI Has "+x+"cards");
			//deal the next card for the computer
			AIHand[x].DealThis();
			//position the next card for the computer
			AIHand[x].setLocation(613 + ((x-1)*113), 50);
			AddItem(AIHand[x]);
			if(x==6){
				//if the computer now has seven cards stop giving it the oportunity to get more
				break;
			}
		}
		System.out.println("end of ai play");
	}
	
	public void DecideWhoWins(){
		//variable for that account for if either the user or computer have split
		int AIHighestScore = 0;
		int UserHighestScore = 0;
		System.out.println("scoring AI");
		//scores the computer's first hand
		int AIScore = ScoreHand(AIHand);
		System.out.println("scoring user");
		//scores the user's first hand
		int UserScore = ScoreHand(UserHand);
		int UserSplitScore = 0;
		if(AIHasSplit){
			//if the computer has split
			//scores the computer's second hand
			int AIScore2 = ScoreHand(AISplitHand);
			if(AIScore2>AIScore){
				//if the computer's second hand is better than the computer's first hand
				//sets the highest score indicator to show the computer's highest score
				AIHighestScore = AIScore2;
			}else{
				//if the computer's second hand is not better than the computer's first hand
				//sets the highest score indicator to show the computer's highest score
				AIHighestScore = AIScore;
			}
		}else{
			//if the computer hasn't split
			//the computer's highest score will be it's only score
			AIHighestScore = AIScore;
		}
		if(UserHasSplit){
			//if the user has split
			//score the user's second hand
			UserSplitScore = ScoreHand(UserSplitHand);
			if(UserSplitScore>UserScore){
				//if the user's second hand is better than the user's first hand
				//sets the highest score indicator to show the user's highest score
				UserHighestScore = UserSplitScore;
			}else{
				//if the user's second hand is not better than the user's first hand
				//sets the highest score indicator to show the user's highest score
				UserHighestScore = UserScore;
			}
		}else{
			//if the user has not split
			//user's highest score will be their only score
			UserHighestScore = UserScore;
		}
		//defining the text that tells the user who has won
		WinnerText.setLocation(400, 400);
		WinnerText.setWidth(100);
		WinnerText.setHeight(50);
		WinnerText.setColour(Color.RED);
		for(int x=0;x<7;x++){
			//shows the user the computer's cards
			AIHand[x].setLocation(AIHand[x].getXpos(), 100);
			AIHand[x].setFrontFacing(true);
		}
		if(AIHighestScore>UserHighestScore){
			//if the computer wins
			//lose
			WinnerText.setContent("You lose");
		}else if(UserHighestScore>AIHighestScore){
			//if the player wins
			//win
			WinnerText.setColour(Color.BLUE);
			WinnerText.setContent("You Win");
		}else {
			//if neither wins
			//draw
			WinnerText.setColour(Color.BLUE);
			WinnerText.setContent("You Draw");
		}
		//removes any previous winning texts
		RemoveItem(WinnerText);
		//shows the user WinnerText
		AddItem(WinnerText);
		System.out.println("===========");
		System.out.println("ai scored: "+AIHighestScore+"\n User scored"+UserHighestScore);
		System.out.println("=============");
		
		
	}
	public int ScoreHand(Card[] Hand){
		int Score=0;
		int Aces =0;
		for(int x=0;x<Hand.length;x++){
			//for every card in the hand that is being scored
			//-1 is the default value for undealt cards
			if(Hand[x].getValue()!=-1){
				//if the card has been dealt
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
					//non-special card
					Score = Score + Hand[x].getValue()+1;
				break;
				}
			}
		}
		
		while(Aces!=0){
			//while the player has a choice about their score (because aces can be 11 or 1
			if(Score>21){
				//if the choice of 11 would make the hand bust
				Score = Score-10;
				//remove one of the choices
				Aces--;
			}else{
				//if the choices mean the user is not bust
				break;
			}
		}
		if(Score>21){
			//if the hand being scores is bust
			Score=0;
		}
		
		return Score;
	}

	


	@Override
	public void MousePressed(MouseEvent MouseArg) {
		
		System.out.println("Blackjack Stage recieved mouse event");
		int i;
		for(i=0;i<7;i++){
			//test against all the buttons
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
		//resets the deck so that none of the cards are marked as dealt
		CardMain.resetDeck();
		//gives a value to the initial four cards
		UserHand[0].DealThis();
		UserHand[1].DealThis();
		AIHand[0].DealThis();
		AIHand[1].DealThis();
		//shows the user the initial four cards
		AddItem(UserHand[0]);
		AddItem(UserHand[1]);
		AddItem(AIHand[0]);
		AddItem(AIHand[1]);
	}

}
