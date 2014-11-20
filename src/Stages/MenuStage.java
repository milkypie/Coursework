package Stages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import CustomComponents.Button;

@SuppressWarnings("serial")
public class MenuStage extends CustomComponents.Stage{
	
	public static Button TestButton = new Button();
	public String testContent;
	//public Graphics2D testGraphcis = Control.CardMain.TotalGUI.
	
	public void Create(){
		//Initialises all JObjects & initiates a Prepare instance
		
		Prepare();
		
	}
	
	public void Initialize(){
		//Declare all variables, create JPanel & JButtons
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setBackground(Color.blue);
		this.setLayout(null);
		testContent = "test";
		
		TestButton.setColour(Color.black);
		TestButton.setTextColour(Color.RED);
		TestButton.setText(50, 50, testContent);
		
		
		
		Control.CardMain.Menu_Button_Base.setSize(300,300);
		Control.CardMain.Menu_Button_Base.setLocation(600, 300);
		Control.CardMain.Menu_Button_Base.setLayout(null);
		Control.CardMain.Menu_Button_Base.setBackground(Color.blue);
		
		Control.CardMain.BlackJackButton.setSize(80, 30);
		Control.CardMain.CheatButton.setSize(80, 30);
		Control.CardMain.TexasButton.setSize(80, 30);
		
		Control.CardMain.BlackJackButton.setLocation(50,30);
		Control.CardMain.CheatButton.setLocation(50,130);
		Control.CardMain.TexasButton.setLocation(50,230);
		
		Control.CardMain.BlackJackButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg1){
				System.out.println(StageHandler.CurrentStage);
				StageHandler.CurrentStage = StageHandler.STAGE_BLACKJACK;
				StageHandler.StageArray[StageHandler.CurrentStage].Prepare();
				System.out.println(StageHandler.CurrentStage);
				System.out.println("BlackJackButton Pressed");
			}});
		
		Control.CardMain.TexasButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg1){
				System.out.println(StageHandler.CurrentStage);
				StageHandler.CurrentStage = StageHandler.STAGE_TEXAS;
				StageHandler.StageArray[StageHandler.CurrentStage].Prepare();
				System.out.println(StageHandler.CurrentStage);
				System.out.println("Texas Button Pressed");
			}});
		Control.CardMain.CheatButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg2){
				System.out.println(StageHandler.CurrentStage);
				StageHandler.CurrentStage = StageHandler.STAGE_CHEAT;
				StageHandler.StageArray[StageHandler.CurrentStage].Prepare();
				System.out.println(StageHandler.CurrentStage);
				System.out.println("Cheat Button Pressed");
			}});
		
		Control.CardMain.Menu_Button_Base.add(Control.CardMain.TexasButton);
		Control.CardMain.Menu_Button_Base.add(Control.CardMain.CheatButton);
		Control.CardMain.Menu_Button_Base.add(Control.CardMain.BlackJackButton);
		Control.CardMain.Menu_Button_Base.AddComponent(TestButton);
		
		
	}
	
	public void Prepare() {
		
		Control.CardMain.TotalGUI.add(Control.CardMain.Menu_Button_Base);
	}

	
	public void Update() {
		this.updateUI();
	}


	
	
	
}
