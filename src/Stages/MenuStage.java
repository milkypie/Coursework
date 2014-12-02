package Stages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import CustomComponents.Button;

@SuppressWarnings("serial")
public class MenuStage extends CustomComponents.Stage{
	
	public static Button BlackjackButton = new Button();
	public String BlackjackContent;
	//public Graphics2D testGraphcis = Control.CardMain.TotalGUI.
	
	public void Initialize(){
		//Declare all variables, create JPanel & JButtons
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setBackground(Color.blue);
		this.setLayout(null);
		
		BlackjackContent = "test";
		
		BlackjackButton.setColour(Color.black);
		BlackjackButton.setTextColour(Color.RED);
		BlackjackButton.setLocation(100, 100);
		BlackjackButton.setWidth(100);
		BlackjackButton.setHeight(50);
		BlackjackButton.setStyle(1);
		BlackjackButton.setText(20, 30, BlackjackContent);
		
		this.AddComponent(BlackjackButton);
		
		
		
	}
	
	public void Prepare() {
		
	}

	
	public void Update() {
		
	}
	public void Draw(Graphics g){
		
	}

	
	
	
}
