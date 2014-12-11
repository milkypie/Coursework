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
	
	public static Button BlackjackButton = new Button();
	public String BlackjackContent;
	public static Card TestCard = new Card();
	//public Graphics2D testGraphcis = Control.CardMain.TotalGUI.
	
	public MenuStage(){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setBackground(Color.blue);
		this.setLayout(null);
		
		BlackjackContent = "BlackJack";
		
		BlackjackButton.setColour(Color.black);
		BlackjackButton.setTextColour(Color.RED);
		BlackjackButton.setLocation(100, 100);
		BlackjackButton.setWidth(100);
		BlackjackButton.setHeight(50);
		BlackjackButton.setStyle(1);
		BlackjackButton.setText(20, 30, BlackjackContent);
		BlackjackButton.setAction(new Action(){

			
			public void run(int ActionID) {
				//change to blackjack stage
				
			}
			
		});

		
		
		this.AddComponent(BlackjackButton);
				
		
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
