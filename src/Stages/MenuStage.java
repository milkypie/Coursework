package Stages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import CustomComponents.Action;
import CustomComponents.Background;
import CustomComponents.Button;
import CustomComponents.Card;

@SuppressWarnings("serial")
public class MenuStage extends CustomComponents.Stage {
	
	public static Button[] buttons = new Button[4];
	public String BlackjackContent,TexasContent,CheatContent,ExitContent;
	public static Card TestCard = new Card();
	public Image backgroundImage;
	public Background testbackground;
	public int blue,green,looping;
	//public Graphics2D testGraphcis = Control.CardMain.TotalGUI.
	
	public MenuStage(){
		blue = 154;
		green = 154;
		looping=0;
		ID = 0;
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setBackground(Color.blue);
		this.setLayout(null);
		
		
		try {
			backgroundImage = ImageIO.read(new File(Control.CardMain.ResourceDir+"\\Background.jpg"));
			System.out.println("found backgroundimage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		testbackground = new Background(null);
		testbackground.RenderWithImage = false;
		testbackground.setColour(new Color(0,green,blue));
		
		this.AddComponent(testbackground);
		
		int i;
		for(i=0;i<4;i++){
			buttons[i] = new Button();
		}
		//blackjack button
		BlackjackContent = "BlackJack";
		buttons[0].setColour(Color.black);
		buttons[0].setTextColour(Color.RED);
		buttons[0].setLocation(100, 100);
		buttons[0].setWidth(100);
		buttons[0].setHeight(50);
		buttons[0].setStyle(1);
		buttons[0].setText(20, 30, BlackjackContent);
		buttons[0].setAction(new Action(){
			
			
			public void run() {
				//change to blackjack stage
				System.out.println("blackjackbuton pressed");
				Control.CardMain.GameLoop.CurrentStage = StageHandler.STAGE_BLACKJACK;
				System.out.println(StageHandler.STAGE_BLACKJACK);
				System.out.println(Control.CardMain.GameLoop.CurrentStage);
				Control.CardMain.GameLoop.StageArray[Control.CardMain.GameLoop.CurrentStage].Update();
				Control.CardMain.GameLoop.StageArray[Control.CardMain.GameLoop.CurrentStage].updateUI();
			}
			
		});
		
		this.AddComponent(buttons[0]);
		
		//Texas Button
		TexasContent = "Texas Hold 'em";
		buttons[1].setColour(Color.black);
		buttons[1].setTextColour(Color.RED);
		buttons[1].setLocation(100, 200);
		buttons[1].setWidth(100);
		buttons[1].setHeight(50);
		buttons[1].setStyle(1);
		buttons[1].setText(10, 30, TexasContent);
		buttons[1].setAction(new Action(){
			
			
			public void run() {
				//change to blackjack stage
				System.out.println("Texasbuttttttton pressed");
				Control.CardMain.GameLoop.CurrentStage = StageHandler.STAGE_TEXAS;
				
			}
			
		});
		
		this.AddComponent(buttons[1]);
		
		//Cheat Button
		CheatContent = "Cheat";
		buttons[2].setColour(Color.black);
		buttons[2].setTextColour(Color.RED);
		buttons[2].setLocation(100, 300);
		buttons[2].setWidth(100);
		buttons[2].setHeight(50);
		buttons[2].setStyle(1);
		buttons[2].setText(10, 30, CheatContent);
		buttons[2].setAction(new Action(){
			
			
			public void run() {
				//change to blackjack stage
				System.out.println("cheatbutton pressed");
				Control.CardMain.GameLoop.CurrentStage = StageHandler.STAGE_CHEAT;
				
			}
			
		});
		
		this.AddComponent(buttons[2]);
		
		//exit Button
		ExitContent = "Quit";
		buttons[3].setColour(Color.black);
		buttons[3].setTextColour(Color.RED);
		buttons[3].setLocation(900, 600);
		buttons[3].setWidth(100);
		buttons[3].setHeight(50);
		buttons[3].setStyle(1);
		buttons[3].setText(10, 30, ExitContent);
		buttons[3].setAction(new Action(){
			
			
			public void run() {
				//change to blackjack stage
				System.out.println("exitbutt pressed");
				System.exit(0);
				
			}
			
		});
		
		this.AddComponent(buttons[3]);
	}
	
	public void Initialize(){
		//Declare all variables, create JPanel & JButtons
		
	}
	
	public void Prepare() {
		
	}

	
	public void Update() {
		//background colour changing
		switch(looping){
		case 0:
			if(green<255){
				green++;
			}if(green==255){
				green --;
				looping = 1;
			}
		break;
		case 1:
			if(green>154){
				green--;
			}if(green == 154){
				looping = 2;
			}
		break;
		case 2:
			if(blue<255){
				blue++;
			}if(blue==255){
				blue--;
				looping = 3;
			}
		break;
		case 3:
			if(blue>154){
				blue--;
			}if(blue==154){
				blue--;
				looping = 0;
			}
		break;
		default:
			System.out.println("you broke something");
		break;
		}
			

		testbackground.setColour(new Color(0,green,blue));
	}
	public void Draw(Graphics g){
		
	}
	
	public void MousePressed(MouseEvent MouseArg) {
		System.out.println("Menu Stage recieved mouse event");
		int i;
		for(i=0;i<4;i++){
			//if event took place inside x
			if(MouseArg.getLocationOnScreen().x>buttons[i].getXpos()&&MouseArg.getLocationOnScreen().x<buttons[i].getXpos()+buttons[i].getWidth()){
				//if event took place inside y
				if(MouseArg.getLocationOnScreen().y>buttons[i].getYpos()&&MouseArg.getLocationOnScreen().y<buttons[i].getYpos()+buttons[i].getHeight()){
					//execute action
					System.out.println("Button:"+i+" will execute its action");
					buttons[i].runAction();
				}
			}
		}
	}

	
	
}
