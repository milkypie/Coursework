package Control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;






import java.util.concurrent.TimeUnit;

import javax.swing.*;

import Stages.StageHandler;
import Visuals.BaseJpanel;
import Visuals.FrameHandler;
import Cards.CardCreation;
import Cards.Dealer;


public class CardMain{
	static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int ScreenWidth = ScreenSize.width ;
	public static int ScreenHeight = ScreenSize.height;
	public static JPanel TotalGUI = new JPanel();
	public static Boolean[][] CardOut = new Boolean[4][13];
	public FrameHandler[] Hand = new FrameHandler[30];
	static final int DiamondID = 0, SpadeID = 1, HeartID = 2, ClubID = 3;
	public static Point[][] PictureSet = new Point[9][10]; 
	public static BaseJpanel Menu_Button_Base = new BaseJpanel();
	public static JButton BlackJackButton = new JButton(),CheatButton = new JButton(),TexasButton = new JButton();
	
	
	
	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true); //this needs to be false on release
		JFrame frame = new JFrame ("[=] Do A Card [=]");
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(ScreenWidth,ScreenHeight);
		frame.setVisible(true);
		frame.setResizable(false);
		
		//create and set up the content pane
		CardMain demo = new CardMain();
		frame.setContentPane(demo.CreateContentPane());
		//add this to frame loop?
		TotalGUI.setVisible(true);
		TotalGUI.setOpaque(true);
		TotalGUI.repaint();
		
	}
	
	public static void main(String[] args){
		//schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI
		int x,y;
		for(x=0;x<4;x++){
			for(y=0;y<13;y++){
				CardOut[x][y] = false;
			}
		}
		try {
			CardCreation.LoadIcons();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(x = 0;x<=8;x++){
			for(y = 0;y<=x+1;y++){
				CardMain.PictureSet[x][y] = null;
			}
		}
		PictureSet[0][0] = new Point(36,26);
		PictureSet[0][1] = new Point(36,112);
		PictureSet[1][0] = new Point(36,16);
		PictureSet[1][1] = new Point(36,64);
		PictureSet[1][2] = new Point(36,112);
		PictureSet[2][0] = new Point(16,26);
		PictureSet[2][1] = new Point(16,112);
		PictureSet[2][2] = new Point(56,26);
		PictureSet[2][3] = new Point(56,112);
		PictureSet[3][0] = new Point(16,26);
		PictureSet[3][1] = new Point(16,112);
		PictureSet[3][2] = new Point(56,26);
		PictureSet[3][3] = new Point(56,112);
		PictureSet[3][4] = new Point(36,69);
		PictureSet[4][0] = new Point(16,26);
		PictureSet[4][1] = new Point(16,112);
		PictureSet[4][2] = new Point(16,69);
		PictureSet[4][3] = new Point(56,26);
		PictureSet[4][4] = new Point(56,112);
		PictureSet[4][5] = new Point(56,69);
		PictureSet[5][0] = new Point(6,26);
		PictureSet[5][1] = new Point(6,112);
		PictureSet[5][2] = new Point(6,69);
		PictureSet[5][3] = new Point(66,26);
		PictureSet[5][4] = new Point(66,112);
		PictureSet[5][5] = new Point(66,69);
		PictureSet[5][6] = new Point(36,69);
		PictureSet[6][0] = new Point(16,16);
		PictureSet[6][1] = new Point(16,112);
		PictureSet[6][2] = new Point(16,49);
		PictureSet[6][3] = new Point(16,79);
		PictureSet[6][4] = new Point(56,16);
		PictureSet[6][5] = new Point(56,112);
		PictureSet[6][6] = new Point(56,49);
		PictureSet[6][7] = new Point(56,79);
		PictureSet[7][0] = new Point(16,16);
		PictureSet[7][1] = new Point(16,112);
		PictureSet[7][2] = new Point(16,49);
		PictureSet[7][3] = new Point(16,79);
		PictureSet[7][4] = new Point(56,16);
		PictureSet[7][5] = new Point(56,112);
		PictureSet[7][6] = new Point(56,49);
		PictureSet[7][7] = new Point(56,79);
		PictureSet[7][8] = new Point(36,64);
		PictureSet[8][0] = new Point(16,16);
		PictureSet[8][1] = new Point(16,112);
		PictureSet[8][2] = new Point(16,49);
		PictureSet[8][3] = new Point(16,79);
		PictureSet[8][4] = new Point(56,16);
		PictureSet[8][5] = new Point(56,112);
		PictureSet[8][6] = new Point(56,49);
		PictureSet[8][7] = new Point(56,79);
		PictureSet[8][8] = new Point(36,35);
		PictureSet[8][9] = new Point(36,95);
		
		
		
		StageHandler.Initialize();
		createAndShowGUI();
		StageHandler.Timer();
		

	}
	public JPanel CreateContentPane(){
		
		TotalGUI = StageHandler.StageArray[StageHandler.CurrentStage];
		
		return TotalGUI;
	}

	
}

