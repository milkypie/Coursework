package Stages;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import Control.CardMain;
import CustomComponents.*;

@SuppressWarnings("serial")
public class BlackAggieStage extends CustomComponents.Stage {
	private Card[] UserHand = new Card[13];
	private Action CardAction;
	private Card[][] AIHand = new Card[3][13];
	private Button[] Buttons = new Button[5];
	private Boolean InitialSection = true, UserPlayed = false, WaitingForUserPlay = false;
	private int[][] SelectedLocations = new int[4][3];
	private Card[][] MovedCards = new Card[4][3];
	private int Lead = 3, LeadSuit = -1, MostRecentHasPosition;
	private int[] Score = new int[4];
	private Card[] TrickCards = new Card[4];
	private Text[] ScoreReadout = new Text[4],ScoreLabels = new Text[4];
	private Panel[] Marker = new Panel[4];
	private Text LayToSuit = new Text(),WinText = new Text();
	private Panel WinPanel = new Panel();
	private Background AggieBJ = new Background(null);
	
	public BlackAggieStage(){
		ID = 3;
		
		WinPanel.setLocation(200,200);
		WinPanel.setStyle(1);
		WinPanel.setWidth(400);
		WinPanel.setHeight(400);
		WinPanel.setColour(Color.white);
		
		WinText.setWidth(200);
		WinText.setHeight(50);
		WinText.setLocation(300,350);
		
		for(int x = 0;x<4;x++){
			for(int y=0;y<3;y++){
				SelectedLocations[x][y]=-1;
				MovedCards[x][y] = new Card();
			}
			TrickCards[x] = new Card();
			Score[x] = 0;
			ScoreLabels[x] = new Text();
			ScoreReadout[x] = new Text();
			Marker[x] = new Panel();
		}
		AddItem(AggieBJ);
		
		ScoreLabels[0].setContent("Jimmy Bob's Score :");
		ScoreLabels[0].setLocation(650,300);
		ScoreLabels[0].setColour(Color.RED);
		ScoreLabels[1].setContent("Retro Al's Score :");
		ScoreLabels[1].setLocation(650,340);
		ScoreLabels[1].setColour(Color.BLUE);
		ScoreLabels[2].setContent("Quazar the Annihilator's Score :");
		ScoreLabels[2].setLocation(650,380);
		ScoreLabels[2].setColour(Color.PINK);
		ScoreLabels[3].setContent("your Score :");
		ScoreLabels[3].setLocation(650,420);
		ScoreLabels[3].setColour(Color.YELLOW);
		
		AddItem(ScoreLabels[0]);
		AddItem(ScoreLabels[1]);
		AddItem(ScoreLabels[2]);
		AddItem(ScoreLabels[3]);
		
		ScoreReadout[0].setContent(String.valueOf(Score[0]));
		ScoreReadout[0].setLocation(770, 300);
		ScoreReadout[0].setColour(Color.RED);
		ScoreReadout[1].setContent(String.valueOf(Score[1]));
		ScoreReadout[1].setLocation(750, 340);
		ScoreReadout[1].setColour(Color.BLUE);
		ScoreReadout[2].setContent(String.valueOf(Score[2]));
		ScoreReadout[2].setLocation(830, 380);
		ScoreReadout[2].setColour(Color.PINK);
		ScoreReadout[3].setContent(String.valueOf(Score[3]));
		ScoreReadout[3].setLocation(720, 420);
		ScoreReadout[3].setColour(Color.YELLOW);
		
		AddItem(ScoreReadout[0]);
		AddItem(ScoreReadout[1]);
		AddItem(ScoreReadout[2]);
		AddItem(ScoreReadout[3]);
		
		Marker[0].setLocation(10,300);
		Marker[0].setDimensions(30,50);
		Marker[0].setColour(Color.RED);
		Marker[1].setLocation(500,10);
		Marker[1].setDimensions(50,30);
		Marker[1].setColour(Color.BLUE);
		Marker[2].setLocation(1330,300);
		Marker[2].setDimensions(30,50);
		Marker[2].setColour(Color.PINK);
		Marker[3].setLocation(500,750);
		Marker[3].setDimensions(50,30);
		Marker[3].setColour(Color.YELLOW);
		
		AddItem(Marker[0]);
		AddItem(Marker[1]);
		AddItem(Marker[2]);
		AddItem(Marker[3]);
		CardAction = new Action(){

			@Override
			public void run() {}
			public void run(Card x){
				if(x.isActive()){
						if(x.getYpos()==643){
							x.setLocation(x.getXpos(),603);
						}else{
							x.setLocation(x.getXpos(),643);
						}
					if(WaitingForUserPlay){
						WaitingForUserPlay = false;
						UserPlay(x);
					}
				}
			}
			
		};
		TrickCards[0].setLocation(300,300);
		TrickCards[1].setLocation(500,200);
		TrickCards[2].setLocation(1000,300);
		TrickCards[3].setLocation(500,400);
		for(int x=0;x<5;x++){
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

		Buttons[0].setLocation(1200,650);
		Buttons[0].setText(10,30,"Reset");
		Buttons[0].setAction(new Action(){

			@Override
			public void run() {
				System.out.println("resetting game");
				CardMain.resetDeck();
				LeadSuit = -1;
				Lead = 3;
				InitialSection = true;
				WaitingForUserPlay = false;
				for(int y = 0;y<4;y++){
					TrickCards[y].setActive(false);
					Score[y] = 0;
					UpdateScoreReadouts();
					TrickCards[y] = new Card();
					TrickCards[0].setLocation(300,300);
					TrickCards[1].setLocation(500,200);
					TrickCards[2].setLocation(1000,300);
					TrickCards[3].setLocation(500,400);
					
				}
				
				for(int x=0;x<13;x++){
					RemoveItem(UserHand[x]);
					UserHand[x] = new Card();
					UserHand[x].setLocation(400+(50*(x-1)), 643);
					UserHand[x].setAction(CardAction);

					RemoveItem(AIHand[1][x]);
					AIHand[1][x] = new Card();
					AIHand[1][x].setLocation(400+(50*(x-1)), 50);
					AIHand[1][x].setFrontFacing(false);

					RemoveItem(AIHand[2][x]);
					AIHand[2][x]= new Card();
					AIHand[2][x].setLocation(1225, 200+(30*(x-1)));
					AIHand[2][x].setFrontFacing(false);
					AIHand[2][x].setRotated(true);

					RemoveItem(AIHand[0][x]);
					AIHand[0][x]= new Card();
					AIHand[0][x].setLocation(100, 200+(30*(x-1)));
					AIHand[0][x].setFrontFacing(false);
					AIHand[0][x].setRotated(true);
				}
				Prepare();
				RemoveItem(Buttons[3]);
				RemoveItem(WinText);
				RemoveItem(WinPanel);
				AddItem(Buttons[2]);
				System.out.println("ResetFinished");
			}
		});
		
		this.AddComponent(Buttons[0]);
		Buttons[1].setLocation(1200, 715);
		Buttons[1].setText(10, 30, "exit");
		Buttons[1].setAction(new Action(){

			@Override
			public void run() {
				System.out.println(Score[0]);
				System.out.println(Score[1]);
				System.out.println(Score[2]);
				System.out.println(Score[3]);
				System.exit(0);
				
			}
			
		});
		this.AddComponent(Buttons[1]);
		
		Buttons[2].setLocation(150,675);
		Buttons[2].setText(10,30,"Swap");
		Buttons[2].setAction(new Action(){
			public void run(){
				System.out.println("Swap");
				
				if(InitialSection){
					Swap();
				}				
				
			}
		});
		
		this.AddComponent(Buttons[2]);
		
		Buttons[3].setLocation(150,675);
		Buttons[3].setText(10,30,"Next Trick");
		Buttons[3].setAction(new Action(){

			@Override
			public void run() {
				UpdateScoreReadouts();
				for(int x=0;x<4;x++){
					TrickCards[x].setActive(false);
				}
				if(ActiveInHand(UserHand)!=ActiveInHand(AIHand[0])||ActiveInHand(UserHand)!=ActiveInHand(AIHand[1])||ActiveInHand(UserHand)!=ActiveInHand(AIHand[2])){
					System.out.println("*******************SOMETHING HAS GONE WRONG WITH SOMETHING**************");
				}
				if(ActiveInHand(UserHand)==0){
					System.out.println("finished game");
					DecideGameWinner();
				}else{
				
					LeadSuit = -1;
					switch(Lead){
					case 0:
						AIPlay(0);
						AIPlay(1);
						AIPlay(2);
					break;
					case 1:
						AIPlay(1);
						AIPlay(2);
					break;
					case 2:
						AIPlay(2);
					break;
					}
					WaitingForUserPlay = true;
				}
			}
			
		});
		
		Buttons[4].setLocation(1090,680);
		Buttons[4].setText(10,30,"Back To Menu");
		Buttons[4].setAction(new Action(){
			
			@Override
			public void run() {
				Buttons[0].runAction();
				CardMain.GameLoop.ChangeStage(CardMain.GameLoop.STAGE_MENU);
			}
			
		});
		AddItem(Buttons[4]);
	}
	
	
	public void DecideGameWinner(){
		int i = 0;
		for(int x=1;x<4;x++){
			if(Score[x]<Score[i]){
				i=x;
			}
		}
		switch(i){
		case 3:
			System.out.println("User Has Won");
			WinText.setContent("You Have Won!");
			WinText.setColour(Color.green);
		break;
		case 2:
			System.out.println("Quazar has won");
			WinText.setContent("Quazar has annihilated you");
			WinText.setColour(Color.red);
		break;
		case 1:
			System.out.println("Al has won");
			WinText.setContent("Al has beat you back to the stone age");
			WinText.setColour(Color.red);
		break;
		case 0:
			System.out.println("Jimmy has won");
			WinText.setContent("Jim won");
			WinText.setColour(Color.red);
		break;
		}
		AddItem(WinPanel);
		AddItem(WinText);
		
	}
	public void UpdateScoreReadouts(){
		for(int x=0;x<4;x++){
			ScoreReadout[x].setContent(String.valueOf(Score[x]));
		}
	}
	public int ScoreTrick(){
		int score = 0;
		for(int x=0;x<4;x++){
			if(TrickCards[x].getSuit()==2){
				switch(TrickCards[x].getValue()){
				case 0:
					score = score + 15;
				break;
				case 10:
					score = score + 10;
				break;
				case 11:
					score = score + 10;
				break;
				case 12:
					score = score + 10;
				break;
				default:
					score = score + TrickCards[x].getValue()+1;
				break;
				}
			}else if(TrickCards[x].getValue()==11){
				if(TrickCards[x].getSuit()==3){
					score = score + 25;
				}
			}
		}
	return score;
	}
	public int DecideTrickWinner(){
		int i = 0;
		for(int x=0;x<4;x++){
			if(TrickCards[i].getSuit()!=LeadSuit){
				i = x;
			}if(TrickCards[x].getSuit()==LeadSuit){
				if(TrickCards[x].getValue()==0){
					return x;
				}else{
					if(TrickCards[x].getValue()>TrickCards[i].getValue()){
						i = x;
					}
				}
			}
		}
		return i;
	}
	public void AIPlay(int PlayerID){
		int i = 0;
		//if this is the first play in a trick
		if(LeadSuit==-1){
			//play the card with the lowest value
			for(int x=1;x<13;x++){
				if(AIHand[PlayerID][x].isActive()){
					if(!AIHand[PlayerID][i].isActive()){
						i = x;
					}
					if(AIHand[PlayerID][i].getValue()>AIHand[PlayerID][x].getValue()){
						i = x;
					}
				}
			}
			LeadSuit = AIHand[PlayerID][i].getSuit();
		}else if(HasSuit(PlayerID,LeadSuit)){//if they can lay to suit		
			for(int x=1;x<13;x++){
				if(AIHand[PlayerID][x].isActive()){
					if(AIHand[PlayerID][x].getSuit()==LeadSuit){
						if(AIHand[PlayerID][i].getSuit()!=LeadSuit||!AIHand[PlayerID][i].isActive()){
							i=x;
						}else if(AIHand[PlayerID][i].getValue()>AIHand[PlayerID][x].getValue()){
							i = x;
						}
					}
				}
			}
			
		}else if(HasCard(PlayerID,11,3)){//if they have the queen of spades
			if(AIHand[PlayerID][MostRecentHasPosition].isActive()){
				i = MostRecentHasPosition;
			}
		}else if(HasSuit(PlayerID,2)){//if they have a heart
			for(int x=1;x<13;x++){
				if(AIHand[PlayerID][x].isActive()){
					if(AIHand[PlayerID][x].getSuit()==2){
						if(!AIHand[PlayerID][i].isActive()){
							i = x;
						}
						if(AIHand[PlayerID][i].getValue()>AIHand[PlayerID][x].getValue()){
							i = x;
						}
					}
				}
			}
		}else{
			int RandomCard = (int) Math.round(Math.random()*12);
			while(!AIHand[PlayerID][RandomCard].isActive()){
				RandomCard = (int) Math.round(Math.random()*12);
			}
			i = RandomCard;
		}
		TrickCards[PlayerID].CopyCard(AIHand[PlayerID][i]);
		AIHand[PlayerID][i].setActive(false);
		AddItem(TrickCards[PlayerID]);
		MoveCards(PlayerID,i);
	}
	public void MoveCards(int PlayerID,int movedCard){
		switch(PlayerID){
		case 0:
			for(int x = movedCard+1;x<13;x++){
				AIHand[PlayerID][x].setYpos(AIHand[PlayerID][x].getYpos()-30);
			}
		break;
		case 1:
			for(int x = movedCard+1;x<13;x++){
				AIHand[PlayerID][x].setXpos(AIHand[PlayerID][x].getXpos()-50);
			}
		break;
		case 2:
			for(int x = movedCard+1;x<13;x++){
				AIHand[PlayerID][x].setYpos(AIHand[PlayerID][x].getYpos()-30);
			}
		break;
		case 3:
			for(int x = movedCard+1;x<13;x++){
				UserHand[x].setXpos(UserHand[x].getXpos()-50);
			}
		break;
		default:
			System.out.println("tried to re-organise cards of a non-existant hand");
		break;
		}
	}
	public boolean HasSuit(int ID, int Suit){
		if(ID==3){
			for(int x=0;x<13;x++){
				if(UserHand[x].isActive()){
					if(UserHand[x].getSuit()==Suit){
						return true;
					}
				}
			}
			return false;
		}else{
			for(int x=0;x<13;x++){
				if(AIHand[ID][x].isActive()){
					if(AIHand[ID][x].getSuit()==Suit){
						return true;
					}
				}
			}
			return false;
		}
	}
	public int GetLocation(Card ToFind,Card[] Hand){
		for(int x = 0;x<13;x++){
			if(Hand[x].getValue()==ToFind.getValue()){
				if(Hand[x].getSuit()==ToFind.getSuit()){
					return x;
				}
			}
		}
		
		return -1;
	}
	public int ActiveInHand(Card[] Hand){
		int count = 0;
		for(int x=0;x<13;x++){
			if(Hand[x].isActive()){
				count++;
			}
		}
		return count;
	}
	
	public void UserPlay(Card SelectedCard){
		System.out.println("User Play");
		int TrickScore;
		boolean LegalPlay = true;
		switch(Lead){
		case 3:
			System.out.println("User Lead");
			TrickCards[3].CopyCard(SelectedCard);
			AddItem(TrickCards[3]);
			SelectedCard.setActive(false);
			MoveCards(3,GetLocation(SelectedCard,UserHand));
			LeadSuit = SelectedCard.getSuit();
			AIPlay(0);
			AIPlay(1);
			AIPlay(2);
		break;
		case 2:
			//if i have the right suit
			if(HasSuit(3,LeadSuit)){
				System.out.println("2: User can Lay to suit");
				//if i tried to play the right suit
				if(SelectedCard.getSuit()==LeadSuit){
					TrickCards[3].CopyCard(SelectedCard);
					AIPlay(0);
					AIPlay(1);
				}else{
					LegalPlay = false;
					System.out.println("Wrong Suit");
				}
			}else{
				System.out.println("2: User can't Lay to suit");
				TrickCards[3].CopyCard(SelectedCard);
				AIPlay(0);
				AIPlay(1);
			}
			if(LegalPlay){
				SelectedCard.setActive(false);
				MoveCards(3,GetLocation(SelectedCard,UserHand));
				AddItem(TrickCards[3]);
			}
		break;
		case 1:
			//if i have the right suit
			if(HasSuit(3,LeadSuit)){
				System.out.println("1: User can Lay to suit");
				//if i tried to play the right suit
				if(SelectedCard.getSuit()==LeadSuit){
					TrickCards[3].CopyCard(SelectedCard);
					AIPlay(0);
				}else{
					LegalPlay = false;
					System.out.println("Wrong Suit");
				}
			}else{
				System.out.println("1: User can't Lay to suit");
				TrickCards[3].CopyCard(SelectedCard);
				AIPlay(0);
			}
			if(LegalPlay){
				SelectedCard.setActive(false);
				MoveCards(3,GetLocation(SelectedCard,UserHand));
				AddItem(TrickCards[3]);
			}
		break;
		case 0:
			//if i have the right suit
			if(HasSuit(3,LeadSuit)){
				System.out.println("0: User can Lay to suit");
				//if i tried to play the right suit
				if(SelectedCard.getSuit()==LeadSuit){
					TrickCards[3].CopyCard(SelectedCard);
				}else{
					LegalPlay = false;
					System.out.println("Wrong Suit");
				}
			}else{
				System.out.println("1: User can't Lay to suit");
				TrickCards[3].CopyCard(SelectedCard);
			}
			if(LegalPlay){
				System.out.println("Illegal Play");
				SelectedCard.setActive(false);
				MoveCards(3,GetLocation(SelectedCard,UserHand));
				AddItem(TrickCards[3]);
			}
		break;
		default:
			System.out.println("****Something Failed in User Play****");
		break;
		}
		if(LegalPlay){
			TrickScore = ScoreTrick();
			Lead = DecideTrickWinner();
			LeadSuit = -1;
			Score[Lead] = Score[Lead] + TrickScore;
		}
	}
	
	public void Swap(){
		System.out.println("swapping");
		if(CardsSelected()==3){
			InitialSection = false;
			SelectAISwap();
			System.out.println("Decided swaps");
			int i=0;
			for(int x = 0;x<4;x++){
				for(int y=0;y<3;y++){
					if(x==3){
						MovedCards[3][i].setSuit(UserHand[SelectedLocations[x][y]].getSuit());
						MovedCards[3][i].setValue(UserHand[SelectedLocations[x][y]].getValue());
					}else{
						MovedCards[x][i].setSuit(AIHand[x][SelectedLocations[x][y]].getSuit());
						MovedCards[x][i].setValue(AIHand[x][SelectedLocations[x][y]].getValue());
					}
					
					i++;
					System.out.println(SelectedLocations[x][y]);
				}
				i=0;
				System.out.println("new Hand");
			}
			
			for(int x=0;x<4;x++){
				for(int y=0;y<3;y++){
					if(x==3){
						UserHand[SelectedLocations[x][y]].setSuit(MovedCards[2][y].getSuit());
						UserHand[SelectedLocations[x][y]].setValue(MovedCards[2][y].getValue());
						UserHand[SelectedLocations[x][y]].UpdateFace();
						UserHand[SelectedLocations[x][y]].setYpos(643);
					}else if(x==0){
						AIHand[x][SelectedLocations[x][y]].setSuit(MovedCards[3][y].getSuit());
						AIHand[x][SelectedLocations[x][y]].setValue(MovedCards[3][y].getValue());
						AIHand[x][SelectedLocations[x][y]].UpdateFace();
						AIHand[x][SelectedLocations[x][y]].setXpos(100);
						
					}else{
						AIHand[x][SelectedLocations[x][y]].setSuit(MovedCards[x-1][y].getSuit());
						AIHand[x][SelectedLocations[x][y]].setValue(MovedCards[x-1][y].getValue());
						AIHand[x][SelectedLocations[x][y]].UpdateFace();
						if(x==1){
							AIHand[x][SelectedLocations[x][y]].setYpos(50);
						}else{
							AIHand[x][SelectedLocations[x][y]].setXpos(1225);
							
						}
						
					}
					
				}
				
			}
			for(int x = 0;x<4;x++){
				for(int y = 0;y<3;y++){
					SelectedLocations[x][y]=-1;
				}
			}
			RemoveItem(Buttons[2]);
			AddItem(Buttons[3]);
			LeadSuit = -1;
			WaitingForUserPlay=true;
		}else{
			System.out.println("wrong number of selected cards");
			SelectedLocations[3][0]=-1;
			SelectedLocations[3][1]=-1;
			SelectedLocations[3][2]=-1;
		}
	}
	
	public void SelectAISwap(){
			int[] Selected =new int[3];
			Selected[0] = 0;
			Selected[1] = 0;
			Selected[2] = 0;
			try{
				//Queen of spades
				Selected[FindCard(11,3)]++;
			}catch( ArrayIndexOutOfBoundsException e){}
			try{
				//ace of hearts
				Selected[FindCard(0,2)]++;
			}catch( ArrayIndexOutOfBoundsException e){}
			try{
				//ace of spades
				Selected[FindCard(0,3)]++;
			}catch( ArrayIndexOutOfBoundsException e){}
			try{
				//king of spades
				Selected[FindCard(12,3)]++;
			}catch( ArrayIndexOutOfBoundsException e){}
			int Value = 12, Suit;
			//Hearts
			for(int x=0;x<3;x++){
				while(Selected[x]!=3){
					if(Value!=0){
						if(HasCard(x,Value,2)){
							Selected[x]++;
							
						}
						Value--;
					}else{
						break;
					}
				}
				Suit = 0;
				Value = 0;
				//Ace and king of Clubs and Diamonds
				while(Selected[x]!=3){
					if(HasCard(x,Value,Suit)){
						Selected[x]++;
					}
					if(Suit==0){
						Suit=1;
					}else{
						if(Value==0){
							Suit = 0;
							Value = 12;
						}else if(Value == 12){
							Suit = 0;
							Value = 11;
						}else{
							break;
						}
					}
				}
				Suit = 0;
				Value = 10;
				//High card of any suit
				while(Selected[x]!=3){
					if(HasCard(x,Value,Suit)){
						Selected[x]++;
					}
					switch(Suit){
					case 0:
						Suit = 1;
					break;
					case 1:
						Suit = 3;
					break;
					case 3:
						Suit = 0;
						Value--;
					break;
					default:
						System.out.println("*********ERROR:****************");
					break;
					}
				}
				
			}
		}
	
	public void TryToSelect(int Hand,int Position){
		for(int i=0;i<3;i++){
			if(SelectedLocations[Hand][i]==-1){
				SelectedLocations[Hand][i]=Position;
				break;
			}
		}
	}
	public int FindCard(int Value,int Suit){
		for(int x=0;x<3;x++){
			for(int y=0;y<13;y++){
				if(AIHand[x][y].Value==Value){
					if(AIHand[x][y].Suit==Suit){
						System.out.print("=======\nFound card:"+Value+"in suit"+Suit+"\n at"+x+","+y+"\n++++++++\n");
						switch(x){
						case 0:
							AIHand[x][y].setXpos(150);
						break;
						case 1:
							AIHand[x][y].setYpos(AIHand[x][y].getYpos()+50);
						break;
						default:
							AIHand[x][y].setXpos(1175);
						break;
						}
						TryToSelect(x,y);
						return x;
					}
				}
			}
		}
		
		return 3;
	}
	public boolean HasCard(int Hand,int Value,int Suit){
		
		for(int x = 0;x<AIHand[Hand].length;x++){
			if(AIHand[Hand][x].getSuit()==Suit){
				if(AIHand[Hand][x].getValue()==Value){
					if(AIHand[Hand][x].getXpos()==100){
						AIHand[Hand][x].setXpos(150);
					}else if(AIHand[Hand][x].getXpos()==1225){
						AIHand[Hand][x].setXpos(1175);
					}else{
						AIHand[Hand][x].setYpos(AIHand[Hand][x].getYpos()+50);
					}
					TryToSelect(Hand,x);
					MostRecentHasPosition = x;
					return true;
				}
			}
		}
		
		return false;
	}
	public int CardsSelected(){
		int x,y=0;
		for(x=0;x<13;x++){
			if(UserHand[x].getYpos()==603){
				TryToSelect(3,x);
				y++;
			}
		}
		return y;
	}
	public void Prepare() {
		CardMain.resetDeck();
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
		x.setActive(true);
		this.AddComponent(x);
	}
	public void RemoveItem(GUIComponent x){
		x.setActive(false);
		this.RemoveComponent(x);
	}

	@Override
	public void MousePressed(MouseEvent MouseArg) {
		int i;
		Boolean FoundSource = false;
		Point MouseLocation = MouseArg.getLocationOnScreen();
		for(i=0;i<5;i++){
			if(!FoundSource){
				//if event took place inside x
				if(MouseLocation.x>Buttons[i].getXpos()&&MouseLocation.x<Buttons[i].getXpos()+Buttons[i].getWidth()){
					//if event took place inside y
					if(MouseLocation.y>Buttons[i].getYpos()&&MouseLocation.y<Buttons[i].getYpos()+Buttons[i].getHeight()){
						//execute action
						if(Buttons[i].isActive()){
							System.out.println("Button:"+i+" will execute its action");
							FoundSource = true;
							Buttons[i].runAction();
						}
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