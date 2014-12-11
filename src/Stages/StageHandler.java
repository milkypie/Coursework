package Stages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import Control.CardMain;

public class StageHandler implements Runnable {
	public static final int REFRESH_RATE = 40;
	
	public static final int STAGE_MENU = 0;
	public static final int STAGE_BLACKJACK = 1;
	public static final int STAGE_TEXAS = 2;
	public static final int STAGE_CHEAT = 3;
	public static int CurrentStage = 0;
	private Thread GameThread;
	
	public static CustomComponents.Stage[] StageArray = new CustomComponents.Stage[4];
	
	public StageHandler(){
		
		
		StageArray[STAGE_MENU] = new MenuStage();
		StageArray[STAGE_BLACKJACK] = new BlackjackStage();
		StageArray[STAGE_TEXAS] = new TexasStage();
		StageArray[STAGE_CHEAT] = new CheatStage();

		
		
		StageArray[STAGE_MENU].Prepare();
		
		System.out.println("created stagehandler");
	}
	
	public void Start(){
		System.out.println("Starting game thread");
		if(GameThread==null){
			GameThread = new Thread(this,"GameThread");
		}
		
		GameThread.start();
	}

	public void run() {
		long startTime = 0;
		final int RATE = (1000/REFRESH_RATE)*1000000;
		
		while(true){
			//refreshes the current stage panel
			StageArray[CurrentStage].Update();
			StageArray[CurrentStage].updateUI();
			//uncomment these once the exit button works
			//CardMain.TotalGUI = StageArray[CurrentStage];
			//CardMain.frame.setContentPane(CardMain.demo.CreateContentPane());
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime <= RATE){
				try{
					//Can change the number to a higher number as it may improve performance
					TimeUnit.NANOSECONDS.sleep(1);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	
}
