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
	private Boolean InitialSection = true;
	private int[][] SelectedLocations = new int[4][3];
	private Card[][] MovedCards = new Card[4][3];
	Background AggieBJ = new Background(null);
	
	public BlackAggieStage(){
		ID = 3;
		for(int x = 0;x<4;x++){
			for(int y=0;y<3;y++){
				SelectedLocations[x][y]=-1;
				MovedCards[x][y] = new Card();
			}
		}
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
			AIHand[1][x].setFrontFacing(true);
			

			AIHand[2][x]= new Card();
			AIHand[2][x].setLocation(1225, 200+(30*(x-1)));
			AIHand[2][x].setFrontFacing(true);
			AIHand[2][x].setRotated(true);
			
			AIHand[0][x]= new Card();
			AIHand[0][x].setLocation(100, 200+(30*(x-1)));
			AIHand[0][x].setFrontFacing(true);
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
				if(InitialSection){
					Swap();
					InitialSection = false;
				}else{
					
				}
				
			}
		});
		
		this.AddComponent(Buttons[2]);
	}
	
	public void Swap(){
		System.out.println("swapping");
		if(CardsSelected()==3){
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
						UserHand[SelectedLocations[x][y]].updateFace();
						
					}else if(x==0){
						AIHand[x][SelectedLocations[x][y]].setSuit(MovedCards[3][y].getSuit());
						AIHand[x][SelectedLocations[x][y]].setValue(MovedCards[3][y].getValue());
						AIHand[x][SelectedLocations[x][y]].updateFace();
						
					}else{
						AIHand[x][SelectedLocations[x][y]].setSuit(MovedCards[x-1][y].getSuit());
						AIHand[x][SelectedLocations[x][y]].setValue(MovedCards[x-1][y].getValue());
						AIHand[x][SelectedLocations[x][y]].updateFace();
						
					}
					
				}
				
			}
			
			
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
						System.out.println("*********ERROR: BLACKAGGIESTAGE LINE 189****************");
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
		for(i=0;i<3;i++){
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