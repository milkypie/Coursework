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
	public static final int STAGE_AGGIE = 3;
	public static int CurrentStage = 0;
	private Thread GameThread;
	
	public static CustomComponents.Stage[] StageArray = new CustomComponents.Stage[4];
	
	public StageHandler(){
		
		
		StageArray[STAGE_MENU] = new MenuStage();
		StageArray[STAGE_BLACKJACK] = new BlackjackStage();
		StageArray[STAGE_TEXAS] = new TexasStage();
		StageArray[STAGE_AGGIE] = new BlackAggieStage();

		StageArray[STAGE_MENU].addMouseListener(CardMain.MouseHandler);
		StageArray[STAGE_BLACKJACK].addMouseListener(CardMain.MouseHandler);
		StageArray[STAGE_TEXAS].addMouseListener(CardMain.MouseHandler);
		StageArray[STAGE_AGGIE].addMouseListener(CardMain.MouseHandler);
		
		System.out.println("created stagehandler");
	}
	public static void reset(int x){
		switch(x){
		case 0:
			StageArray[0] = new MenuStage();
		break;
		case 1:
			StageArray[1] = new BlackjackStage();
		break;
		case 2:
			StageArray[2] = new TexasStage();
		break;
		case 3:
			StageArray[3] = new CheatStage();
		break;
		default:
			System.out.println("that number does not reffer to a stage");
		break;
		}
	}
	
	public void Start(){
		System.out.println("Starting game thread");
		if(GameThread==null){
			GameThread = new Thread(this,"GameThread");
		}
		
		GameThread.start();
	}
	
	public void ChangeStage(int x){
		StageArray[x].Prepare();
		CurrentStage = x;
	}

	public void run() {
		long startTime = 0;
		final int RATE = (1000/REFRESH_RATE)*1000000;
		
		while(true){
			startTime = System.nanoTime();
			//refreshes the current stage panel
			StageArray[CurrentStage].Update();
			//updateUI will activate the Draw function
			StageArray[CurrentStage].updateUI();
			//this completely refreshes totalGUI and removes and mouse listener it had
			if(CardMain.frame.getContentPane()!=StageArray[CurrentStage]){
				CardMain.frame.setContentPane(StageArray[CurrentStage]);
			}
			
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
