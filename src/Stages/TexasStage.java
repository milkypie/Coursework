package Stages;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import Control.CardMain;
import CustomComponents.*;

/*
 * TODO
 * 
 * FINAL SCORING:
 * flush
 * straight
 * four of a kind
 * running flush
 * 
 */


@SuppressWarnings("serial")
public class TexasStage extends CustomComponents.Stage {
	private Card[][] Hand = new Card[4][2];
	private Boolean[] StillIn = new Boolean[4];
	private Card[] TableCard = new Card[5];
	private Button[] Buttons = new Button[10];
	private Text RaiseReadout = new Text(),PotReadout = new Text(),LastBetReadout = new Text(), WinnerText = new Text();;
	private Text[] MoneyReadout = new Text[4], FoldedText = new Text[4];
	private Background TexBJ = new Background(null);
	private int Pot = 0,LastBet = 0,RaiseBy = 0, RaisePlayer = 3, GameStage = 0, RaisesThisRound = 0;
	private int[] MoneyLeft = new int[4], HasBetThisRound = new int[4], HandResult = new int[4];
	private final int USER = 3;
	private boolean UserTurn = true;
	private final String FoldText = "Player Has Folded"; 
	
	public TexasStage(){
		ID=2;
		AddItem(TexBJ);
		for(int x = 0; x<10; x++){
			Buttons[x] = new Button();
			if(x==4){
				TableCard[x] = new Card();
			}
			if(x<4){
				StillIn[x] = true;
				HasBetThisRound[x] = 0;
				MoneyLeft[x] = 1000;
				MoneyReadout[x] = new Text();
				FoldedText[x] = new Text();
				Hand[x][0] = new Card();
				Hand[x][1] = new Card();
				TableCard[x] = new Card();
				HandResult[x] = 0;
			}
		}
		
		for(int x=0;x<5;x++){
			TableCard[x].setLocation(500+(70*x), 300);
		}
		Font FoldedFont = new Font("TimesNewRoman", 0, 11);
		
		FoldedText[0].setLocation(30,(int)Math.round((CardMain.ScreenHeight/2)-25));
		FoldedText[0].setContent(FoldText);
		FoldedText[0].setColour(Color.red);
		FoldedText[0].setCoverFont(FoldedFont);
		AddItem(FoldedText[0]);
				
		FoldedText[1].setLocation((int)Math.round((CardMain.ScreenWidth/2)-75),40);
		FoldedText[1].setContent(FoldText);
		FoldedText[1].setColour(Color.red);
		FoldedText[1].setCoverFont(FoldedFont);
		AddItem(FoldedText[1]);
		
		FoldedText[2].setLocation(CardMain.ScreenWidth - 125,(int)Math.round((CardMain.ScreenHeight/2)-55));
		FoldedText[2].setContent(FoldText);
		FoldedText[2].setColour(Color.red);
		FoldedText[2].setCoverFont(FoldedFont);
		AddItem(FoldedText[2]);
		
		FoldedText[3].setLocation((int)Math.round((CardMain.ScreenWidth/2)-75),CardMain.ScreenHeight-105);
		FoldedText[3].setContent(FoldText);
		FoldedText[3].setColour(Color.red);
		FoldedText[3].setCoverFont(FoldedFont);
		AddItem(FoldedText[3]);
		
		Hand[0][0].setLocation(30,(int)Math.round((CardMain.ScreenHeight/2)-75));
		Hand[0][1].setLocation(30,(int)Math.round((CardMain.ScreenHeight/2)-25));
		Hand[0][0].setRotated(true);
		Hand[0][1].setRotated(true);
		Hand[0][0].setFrontFacing(false);
		Hand[0][1].setFrontFacing(false);
		
		Hand[1][0].setLocation((int)Math.round((CardMain.ScreenWidth/2)-75),30);
		Hand[1][1].setLocation((int)Math.round((CardMain.ScreenWidth/2)-25),30);
		Hand[1][0].setFrontFacing(false);
		Hand[1][1].setFrontFacing(false);

		Hand[2][0].setLocation(CardMain.ScreenWidth - 125,(int)Math.round((CardMain.ScreenHeight/2)-75));
		Hand[2][1].setLocation(CardMain.ScreenWidth - 125,(int)Math.round((CardMain.ScreenHeight/2)-25));
		Hand[2][0].setRotated(true);
		Hand[2][1].setRotated(true);
		Hand[2][0].setFrontFacing(false);
		Hand[2][1].setFrontFacing(false);

		Hand[3][0].setLocation((int)Math.round((CardMain.ScreenWidth/2)-75),CardMain.ScreenHeight-125);
		Hand[3][1].setLocation((int)Math.round((CardMain.ScreenWidth/2)-25),CardMain.ScreenHeight-125);
		
		Buttons[0].setLocation(200,680);
		Buttons[0].setText(10,30,"Raise");
		Buttons[0].setAction(new Action(){
			public void run(){
				if(RaiseBy + LastBet<MoneyLeft[USER]){
					if(UserTurn == true){
							UserTurn = false;
							Raise(USER);
							AIPlay(0);
							AIPlay(1);
							AIPlay(2);
							if(RaisePlayer==3){
								FinishRound();
							}
					}
				}
			}
		});
		
		AddItem(Buttons[0]);
		
		Buttons[1].setLocation(320,680);
		Buttons[1].setText(10,30,"Call");
		Buttons[1].setAction(new Action(){
			public void run(){
				if(LastBet<MoneyLeft[USER]){
					if(UserTurn == true){
						UserTurn = false;
						Call(USER);
						if(RaisePlayer == 0){
							FinishRound();
						}else{
							AIPlay(0);
							if(RaisePlayer == 1){
								FinishRound();
							}else{
								AIPlay(1);
								if(RaisePlayer == 2){
									FinishRound();
								}else{
									AIPlay(2);
									if(RaisePlayer == 3){
										FinishRound();
									}
								}
							}
						}
					}
				}
			}
		});
		
		AddItem(Buttons[1]);
		
		Buttons[2].setLocation(440,680);
		Buttons[2].setText(10,30,"Fold");
		Buttons[2].setAction(new Action(){
			public void run(){
				Fold(USER);
				FinishAll();
			}
		});
		
		AddItem(Buttons[2]);
		
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
				Control.CardMain.resetDeck();
				for(int x = 0;x<4;x++){
					Hand[x][0].DealThis();
					Hand[x][0].Update();
					Hand[x][1].DealThis();
					Hand[x][1].Update();
					MoneyLeft[x] = 1000;
					Pot = 0;
					LastBet  = 0;
					RemoveItem(TableCard[x]);
					StillIn[x] = true;
				}
				RemoveItem(TableCard[4]);
			}
			
		});
		
		AddItem(Buttons[4]);
		Buttons[5].setLocation(1200, 700);
		Buttons[5].setText(10, 30, "exit");
		Buttons[5].setAction(new Action(){

			@Override
			public void run() {
				Buttons[4].runAction();
				System.exit(0);
				
			}
			
		});
		AddItem(Buttons[5]);
		
		Buttons[6].setWidth(20);
		Buttons[6].setHeight(20);
		Buttons[6].setLocation(150, 660);
		Buttons[6].setText(8,12,"/\\");
		Buttons[6].setAction(new Action(){
			
			public void run(){
				RaiseBy++;
			}
			
		});
		AddItem(Buttons[6]);

		Buttons[7].setWidth(20);
		Buttons[7].setHeight(20);
		Buttons[7].setLocation(150, 638);
		Buttons[7].setText(5,12,"//\\\\");
		Buttons[7].setAction(new Action(){
			
			public void run(){
				RaiseBy = RaiseBy+10;
			}
			
		});
		AddItem(Buttons[7]);
		
		Buttons[8].setWidth(20);
		Buttons[8].setHeight(20);
		Buttons[8].setLocation(150, 690);
		Buttons[8].setText(8,12,"\\/");
		Buttons[8].setAction(new Action(){
			
			public void run(){
				int oldnum = RaiseBy;
				RaiseBy--;
				if(RaiseBy<LastBet){
					RaiseBy = oldnum;
				}
			}
			
		});
		AddItem(Buttons[8]);

		Buttons[9].setWidth(20);
		Buttons[9].setHeight(20);
		Buttons[9].setLocation(150, 712);
		Buttons[9].setText(5,12,"\\\\//");
		Buttons[9].setAction(new Action(){
			
			public void run(){
				int oldnum = RaiseBy;
				RaiseBy = RaiseBy-10;
				if(RaiseBy<LastBet){
					RaiseBy = oldnum;
				}
			}
			
		});
		AddItem(Buttons[9]);
		
		RaiseReadout.setLocation(50, 690);
		RaiseReadout.setContent("Raise By: "+String.valueOf(RaiseBy));
		RaiseReadout.setColour(Color.white);
		
		AddItem(RaiseReadout);
		
		MoneyReadout[0].setLocation(50,300);
		MoneyReadout[1].setLocation(500,50);
		MoneyReadout[2].setLocation(1250,300);
		MoneyReadout[3].setLocation(800,700);
		
		for(int x=0;x<4;x++){
			MoneyReadout[x].setContent("Money Left: "+String.valueOf(MoneyLeft[x]));
			MoneyReadout[x].setColour(Color.white);
			AddItem(MoneyReadout[x]);
		}
		
		PotReadout.setLocation(600,450);
		PotReadout.setContent("Current Pot: "+ String.valueOf(Pot));
		PotReadout.setColour(Color.white);
		
		AddItem(PotReadout);
		
		LastBetReadout.setLocation(600,500);
		LastBetReadout.setContent("LastBet: "+String.valueOf(LastBet));
		LastBetReadout.setColour(Color.white);
		
		AddItem(LastBetReadout);
		
	}
	private void AIPlay(int PlayingHand){
		if(StillIn[PlayingHand]){
			int choose = (int) Math.round(Math.random()*10);
			switch(GameStage){
			case 0:
				
				if(choose == 10){
					if(RaisesThisRound<2){
						Raise(PlayingHand);
					}else{
						Call(PlayingHand);
					}
				}else if(choose <= 3){
					Fold(PlayingHand);
				}else{
					Call(PlayingHand);
				}
				
			break;
			case 1:
				//after the first three cards are turned
				if(choose >= 8){
					if(RaisesThisRound<2){
						Raise(PlayingHand);
					}else{
						Call(PlayingHand);
					}
				}else if(choose <=2){
					Fold(PlayingHand);
				}else{
					Call(PlayingHand);
				}
			break;
			case 2:
				//after the first four cards are turned
				if(choose >= 8){
					if(RaisesThisRound<2){
						Raise(PlayingHand);
					}else{
						Call(PlayingHand);
					}
				}else if(choose <=2){
					Fold(PlayingHand);
				}else{
					Call(PlayingHand);
				}
			break;
			case 3:
				//after all the cards have been turned
				if(choose >= 8){
					if(RaisesThisRound<2){
						Raise(PlayingHand);
					}else{
						Call(PlayingHand);
					}
				}else if(choose <=2){
					Fold(PlayingHand);
				}else{
					Call(PlayingHand);
				}
			break;
			}
			
		}
		if(PlayingHand==2){
			UserTurn = true;
		}
	}
	private void FinishAll(){
		for(int x=GameStage;x<4;x++){
			FinishRound();
		}
	}
	private void FinishRound(){
		System.out.println("finished a round");
		switch(GameStage){
		case 0:
			//end of no cards portion
			for(int x=0; x<3;x++){
				TableCard[x].DealThis();
				AddItem(TableCard[x]);		
			}
			GameStage++;
			UserTurn = true;
		break;
		case 1:
			//end of three cards portion
			TableCard[3].DealThis();
			AddItem(TableCard[3]);
			GameStage++;
			UserTurn = true;
		break;
		case 2:
			//end of four cards portion
			TableCard[4].DealThis();
			AddItem(TableCard[4]);
			GameStage++;
			UserTurn = true;
		break;
		case 3:
			//end of round
			DecideWinner();
			GameStage++;
			UserTurn = false;
		break;
		}
	}
	private void DecideWinner(){
		int Highest = 0;
		/*
		 * 0 = high card
		 * 1 = pair
		 * 2 = two pair
		 * 3 = three of a kind
		 * 4 = stright
		 * 5 = flush
		 * 6 = full house
		 * 7 = four of a kind
		 * 8 = straight flush
		 */
		for(int x=0;x<4;x++){
			HandResult[x] = ScoreHand(x);	
		}
		for(int x = 0;x<4;x++){
			if(HandResult[x]>HandResult[Highest]){
				Highest = x;
			}
		}
		MoneyLeft[Highest] = MoneyLeft[Highest] + Pot;
		NewRound();
		
	}
	private void NewRound(){
		Pot = 0;
		LastBet = 0;
		for(int x = 0; x<5; x++){
			if(x<4){
				StillIn[x] = true;
				HasBetThisRound[x] = 0;
				Hand[x][0] = new Card();
				Hand[x][1] = new Card();
				TableCard[x] = new Card();
				HandResult[x] = 0;
			}else{
				TableCard[x] = new Card();				
			}
		}
	}
	private boolean HasPair(int ScoringHand){
		if(Hand[ScoringHand][0].Value==Hand[ScoringHand][1].Value){
			return true;
		}else{
			for(int x = 0; x<5;x++){
				if(Hand[ScoringHand][0].Value==TableCard[x].Value){
					return true;
				}
				if(Hand[ScoringHand][1].Value==TableCard[x].Value){
					return true;
				}
			}
		}
		return false;
	}
	private boolean HasTwoPair(int ScoringHand){
		int PairsFound = 0;
		if(Hand[ScoringHand][0].Value==Hand[ScoringHand][1].Value){
			PairsFound++;
		}
		for(int x = 0; x<5;x++){
			if(Hand[ScoringHand][0].Value==TableCard[x].Value){
				PairsFound++;
			}
			if(Hand[ScoringHand][1].Value==TableCard[x].Value){
				PairsFound++;
			}
		}
		if(PairsFound>1){
			return true;
		}
		return false;
	}
	private boolean HasThreeOfAKind(int ScoringHand){
		for(int x=0;x<5;x++){
			if(Hand[ScoringHand][0].Value==Hand[ScoringHand][1].Value&&Hand[ScoringHand][0].Value==TableCard[x].Value){
				return true;
			}
		}
		for(int y = 0;y<5;y++){
			for(int x = 0;x<5;x++){
				if(x!=y){
					if(Hand[ScoringHand][0].Value==TableCard[x].Value&&Hand[ScoringHand][0].Value==TableCard[y].Value){
						return true;
					}
					if(Hand[ScoringHand][1].Value==TableCard[x].Value&&Hand[ScoringHand][1].Value==TableCard[y].Value){
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean HasStraight(int ScoringHand){
		boolean Hand1Used = false, Hand0Used = false, LengthChangedThisLoop = true,FoundStarter = false;
		int Length = 0,LastValue = -2,FirstValue = -2,x,y;
		for(x=0;x<5;x++){
			for(y=0;y<5;y++){
				if(!FoundStarter){
					if(y!=x){
						if(TableCard[x].Value==TableCard[y].Value+1){
							FirstValue = TableCard[x].Value;
							LastValue = TableCard[y].Value;
							FoundStarter = true;
						}else if(TableCard[x].Value==TableCard[y].Value-1){
							FirstValue = TableCard[y].Value;
							LastValue = TableCard[x].Value;
							FoundStarter = true;
						}
					}
				}
			}
			if(!FoundStarter){
				if(Hand[ScoringHand][0].Value==TableCard[x].Value+1){
					FirstValue = Hand[ScoringHand][0].Value;
					LastValue = TableCard[x].Value;
					FoundStarter = true;
					Hand0Used = true;
				}else if(Hand[ScoringHand][0].Value==TableCard[x].Value-1){
					FirstValue = TableCard[x].Value;
					LastValue = Hand[ScoringHand][0].Value;
					FoundStarter = true;
					Hand0Used = true;
				}else if(Hand[ScoringHand][1].Value==TableCard[x].Value+1){
					FirstValue = Hand[ScoringHand][1].Value;
					LastValue = TableCard[x].Value;
					FoundStarter = true;
					Hand1Used = true;					
				}else if(Hand[ScoringHand][1].Value==TableCard[x].Value-1){
					FirstValue = TableCard[x].Value;
					LastValue = Hand[ScoringHand][1].Value;
					FoundStarter = true;
					Hand1Used = true;
				}
			}
		}
		
		if(FoundStarter){
			while(LengthChangedThisLoop){
				LengthChangedThisLoop = false;
				for(x=0;x<5;x++){
					if(!LengthChangedThisLoop){
						if(TableCard[x].Value==FirstValue+1){
							FirstValue = TableCard[x].Value;
							Length++;
							LengthChangedThisLoop = true;
						}else if(TableCard[x].Value==LastValue-1){
							LastValue = TableCard[x].Value;
							Length++;
							LengthChangedThisLoop = true;
						}
					}
				}
				if(!LengthChangedThisLoop){
					if(!Hand1Used){
						if(Hand[ScoringHand][1].Value==FirstValue+1){
							FirstValue = Hand[ScoringHand][1].Value;
							Length++;
							LengthChangedThisLoop = true;
							Hand1Used = true;
						}else if(Hand[ScoringHand][1].Value==LastValue-1){
							LastValue = Hand[ScoringHand][0].Value;
							Length++;
							LengthChangedThisLoop = true;
							Hand1Used = true;
						}
					}
				}
				if(!LengthChangedThisLoop){
					if(!Hand0Used){
						if(Hand[ScoringHand][0].Value==FirstValue+1){
							FirstValue = Hand[ScoringHand][0].Value;
							Length++;
							LengthChangedThisLoop = true;
							Hand0Used = true;
						}else if(Hand[ScoringHand][0].Value==LastValue-1){
							LastValue = Hand[ScoringHand][0].Value;
							Length++;
							LengthChangedThisLoop = true;
							Hand0Used = true;
						}
					}
				}
			}

			if(Length>=5){
			return true;
			}

		}
		return false;
	}
	private boolean HasFlush(int ScoringHand){
		int x;
		int[] NumOfSuit = new int[4];
		for(x=0;x<4;x++){
			NumOfSuit[x] = 0;
		}
		NumOfSuit[Hand[ScoringHand][0].Suit]++;
		NumOfSuit[Hand[ScoringHand][1].Suit]++;
		
		for(x = 0;x<5;x++){
			NumOfSuit[TableCard[x].Suit]++;
		}
		
		for(x=0;x<4;x++){
			if(NumOfSuit[x]>=5){
				return true;
			}
		}
		return false;
	}
	private boolean HasFullHouse(int ScoringHand){
		int[] PairValue = new int[4];
		int[] ThreeValue = new int[3];
		boolean HasPair = false, HasThree = false;
		int x,y,i=0,j=0;
		for(x=0;x<3;x++){
			if(x!=2){
				ThreeValue[x] = -1;
			}
			PairValue[x] = -1;
		}
		if(Hand[ScoringHand][0].Value==Hand[ScoringHand][1].Value){
			HasPair = true;
			PairValue[i] = Hand[ScoringHand][0].Value;
			i++;
		}
		for(x = 0;x<5;x++){
			for(y=0;y<5;y++){
				if(TableCard[x].Value==TableCard[y].Value){
					if(TableCard[x].Value==PairValue[0]||TableCard[x].Value==PairValue[1]||TableCard[x].Value==PairValue[2]){
						HasThree = true;
						ThreeValue[j] = TableCard[x].Value;
						j++;
					}else{
						HasPair = true;
						PairValue[i] = TableCard[x].Value;
						i++;
					}
				}
			}
			if(TableCard[x].Value == Hand[ScoringHand][0].Value){
				if(TableCard[x].Value==PairValue[0]||TableCard[x].Value==PairValue[1]||TableCard[x].Value==PairValue[2]){
					HasThree = true;
					ThreeValue[j] = TableCard[x].Value;
					j++;
				}else{
					HasPair = true;
					PairValue[i] = TableCard[x].Value;
					i++;
				}
			}else if(TableCard[x].Value == Hand[ScoringHand][1].Value){
				if(TableCard[x].Value==PairValue[0]||TableCard[x].Value==PairValue[1]||TableCard[x].Value==PairValue[2]){
					HasThree = true;
					ThreeValue[j] = TableCard[x].Value;
					j++;
				}else{
					HasPair = true;
					PairValue[i] = TableCard[x].Value;
					i++;
				}
			}
		}
		
		if(HasPair&&HasThree){
			for(x = 0;x<3;x++){
				if(PairValue[x]!=ThreeValue[0]){
					if(PairValue[x]!=-1&&ThreeValue[0]!=-1){
						return true;
					}
				}
				if(PairValue[x]!=ThreeValue[1]){
					if(PairValue[x]!=-1&&ThreeValue[1]!=-1){
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean HasFourOfAKind(int ScoringHand){
		int[] Values = new int[7];
		int x,y,z,v;
		Values[0] = Hand[ScoringHand][0].Value;
		Values[1] = Hand[ScoringHand][1].Value;
		for(x = 0;x<5;x++){
			Values[x+2] = TableCard[x].Value;
		}
		
		for(x = 0;x<7;x++){
			for(y = 0;y<7;y++){
				if(y!=x);
				for(z = 0;z<7;z++){
					if(z!=y&&z!=x){
						for(v = 0;v<7;v++){
							if(v!=x&&v!=y&&v!=z){
								if(Values[x]==Values[y]&&Values[x]==Values[z]&&Values[x]==Values[v]){
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	private boolean HasRunningFlush(int ScoringHand){
		if(HasStraight(ScoringHand)&&HasFlush(ScoringHand)){
			return true;
		}
		return false;
	}
	private int ScoreHand(int ScoringHand){
		if(HasRunningFlush(ScoringHand)){
			return 8;
		}else if(HasFourOfAKind(ScoringHand)){
			return 7;
		}else if(HasFullHouse(ScoringHand)){
			return 6;
		}else if(HasFlush(ScoringHand)){
			return 5;
		}else if(HasStraight(ScoringHand)){
			return 4;
		}else if(HasThreeOfAKind(ScoringHand)){
			return 3;
		}else if(HasTwoPair(ScoringHand)){
			return 2;
		}else if(HasPair(ScoringHand)){
			return 1;
		}else{
			return 0;	
		}
		
	}
	private void Raise(int PlayingHand){
		if(StillIn[PlayingHand]){
			//if it's the user
			if(PlayingHand == 3){
				System.out.println("User Has Raised");
				LastBet = LastBet + RaiseBy;
				MoneyLeft[PlayingHand] = MoneyLeft[PlayingHand] + HasBetThisRound[PlayingHand] - LastBet;
				Pot = Pot + LastBet-HasBetThisRound[PlayingHand];
				HasBetThisRound[PlayingHand] = LastBet;
			}else{
				System.out.println(PlayingHand + " AI has Raised");
				if(LastBet==0){
					LastBet = 5;
				}
				LastBet = LastBet*2;
				MoneyLeft[PlayingHand] = MoneyLeft[PlayingHand] + HasBetThisRound[PlayingHand] - LastBet;
				Pot = Pot + LastBet-HasBetThisRound[PlayingHand];
				HasBetThisRound[PlayingHand] = LastBet;
			}
			RaiseBy = 0;
			RaisesThisRound++;
			RaisePlayer = PlayingHand;
		}
	}
	private void Call(int PlayingHand){
		if(StillIn[PlayingHand]){
			if(PlayingHand!=3){
				System.out.println(PlayingHand+ " AI has called");
			}
			MoneyLeft[PlayingHand] = MoneyLeft[PlayingHand] + HasBetThisRound[PlayingHand] - LastBet;
			Pot = Pot + LastBet - HasBetThisRound[PlayingHand];
			HasBetThisRound[PlayingHand] = LastBet;
		}
	}
	private void Fold(int PlayingHand){
		if(PlayingHand!=3){
			System.out.println(PlayingHand+ " AI has folded");
		}
		StillIn[PlayingHand] = false;
		RemoveItem(Hand[PlayingHand][0]);
		RemoveItem(Hand[PlayingHand][1]);
	}
	private void AddItem(GUIComponent x) {
		this.AddComponent(x);
	}
	private void RemoveItem(GUIComponent x){
		this.RemoveComponent(x);
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
		RemoveItem(RaiseReadout);
		RaiseReadout.setContent("Raise By: "+String.valueOf(RaiseBy));
		AddItem(RaiseReadout);
		for(int x=0;x<4;x++){
			RemoveItem(MoneyReadout[x]);
			MoneyReadout[x].setContent("Money Left: "+String.valueOf(MoneyLeft[x]));
			AddItem(MoneyReadout[x]);
		}
		RemoveItem(PotReadout);
		PotReadout.setContent("Current Pot: "+ String.valueOf(Pot));
		AddItem(PotReadout);
		
		RemoveItem(LastBetReadout);
		LastBetReadout.setContent("LastBet: "+String.valueOf(LastBet));
		AddItem(LastBetReadout);
	}
	@Override
	public void MousePressed(MouseEvent MouseArg) {
		int i;
		for(i=0;i<10;i++){
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
