package Stages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import Control.CardMain;

public class StageHandler {
	public static final int REFRESH_RATE = 40;
	
	public static final int STAGE_MENU = 0;
	public static final int STAGE_BLACKJACK = 1;
	public static final int STAGE_TEXAS = 2;
	public static final int STAGE_CHEAT = 3;
	public static int CurrentStage = 0;
	
	public static CustomComponents.Stage[] StageArray = new CustomComponents.Stage[4];
	
	public static void Initialize(){
		
		
		StageArray[STAGE_MENU] = new MenuStage();
		StageArray[STAGE_BLACKJACK] = new BlackjackStage();
		StageArray[STAGE_TEXAS] = new TexasStage();
		StageArray[STAGE_CHEAT] = new CheatStage();
		
		StageArray[STAGE_MENU].Initialize();
		StageArray[STAGE_BLACKJACK].Initialize();
		StageArray[STAGE_TEXAS].Initialize();
		StageArray[STAGE_CHEAT].Initialize();
		
		StageArray[STAGE_MENU].Prepare();
		
		
	}
	
	public static void Timer(){
		long startTime = 0;
		final int RATE = (1000/REFRESH_RATE)*1000000;
		
		while(true){
			//refreshes the current stage panel
			StageArray[CurrentStage].Update();
			/*
			 * what should g be?
			 * Sam says it should be the previous frame
			 * But then where do i get the first frame?
			 */
			StageArray[CurrentStage].updateUI();
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
