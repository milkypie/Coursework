package Stages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import CustomComponents.Action;
import CustomComponents.Button;
import CustomComponents.Card;

@SuppressWarnings("serial")
public class MenuStage extends CustomComponents.Stage{
	
	public static Button[] buttons = new Button[4];
	public String BlackjackContent,TexasContent,CheatContent,ExitContent;
	public static Card TestCard = new Card();
	//public Graphics2D testGraphcis = Control.CardMain.TotalGUI.
	
	public MenuStage(){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setBackground(Color.blue);
		this.setLayout(null);
		
		BlackjackContent = "BlackJack";
		//blackjack button
		int i;
		for(i=0;i<4;i++){
			buttons[i] = new Button();
		}
		buttons[0].setColour(Color.black);
		buttons[0].setTextColour(Color.RED);
		buttons[0].setLocation(100, 100);
		buttons[0].setWidth(100);
		buttons[0].setHeight(50);
		buttons[0].setStyle(1);
		buttons[0].setText(20, 30, BlackjackContent);
		buttons[0].setAction(new Action(){
			
			
			public void run(int ActionID) {
				//change to blackjack stage
				
			}
			
		});

		TexasContent = "Texas Hold 'em";
		
		
		this.AddComponent(buttons[0]);
				
		
	}
	
	public void Initialize(){
		//Declare all variables, create JPanel & JButtons
		
	}
	
	public void Prepare() {
		
	}

	
	public void Update() {
		
	}
	public void Draw(Graphics g){
		
	}

	public void run(int ActionID){
		
	}
	
	
}
