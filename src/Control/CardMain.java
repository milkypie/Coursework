package Control;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Stages.StageHandler;
import Input.MouseInput;

/*
 * TODO
 * Find out why the stage doesn't change (possibly fixed) [fixed]
 * 
 * fix why the stage doesn't change (possibly fixed) [fixed]
 * 
 * the possible fix for the previous problem has created a new problem
 * i believe the new problem is created by totalGUI not having a mouse listener
 * this could be rectified by creating a previous stage or next stage variable
 * then only refreshing totalGUI when the two differ (wrong)
 * 
 * [Fixed] by removing TotalGUI from the update sequence and simply calling setContentPane() on the base frame and using a
 * value in Stage array to pass through 
 * 
 */

public class CardMain{
	public static boolean testing = false; //needs to be false when released
	static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int ScreenWidth = ScreenSize.width ;
	public static MouseInput MouseHandler = new MouseInput();
	public static int ScreenHeight = ScreenSize.height;
	public static JPanel TotalGUI = new JPanel(),MousePanel = new JPanel();
	public static Boolean[][] CardOut = new Boolean[4][13];
	static final int DiamondID = 0, SpadeID = 1, HeartID = 2, ClubID = 3;
	public static JButton BlackJackButton = new JButton(),CheatButton = new JButton(),TexasButton = new JButton();
	public static Image[][] Faces = new Image[4][13];
	public static Image Back, BackRot;
	public static File ResourceDir = new File("Resources");
	public static StageHandler GameLoop;
	public static JFrame frame = new JFrame("[=] do a card[=]");
	
	private static void createAndShowGUI() {
		frame.setDefaultLookAndFeelDecorated(false); //this needs to be false on release
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(ScreenWidth,ScreenHeight);
		frame.setVisible(true);
		frame.setResizable(false);
		CardMain demo = new CardMain();	
		//create and set up the content pane
		
		frame.setContentPane(demo.CreateContentPane());
		//add this to frame loop?
		TotalGUI.setVisible(true);
		TotalGUI.setOpaque(true);
		TotalGUI.repaint();
		TotalGUI.addMouseListener(MouseHandler);
		
		
	}
	
	public static void main(String[] args){
		//schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI
		if(testing){
			System.out.println("Testing active");
		}
		
		int x=0;
		int y=0;
		for(x=0;x<4;x++){
			for(y=0;y<13;y++){
				CardOut[x][y] = false;
			}
		}
		
		//finds card faces
		try {
			for(y=0;y<4;y++){
				for(x=1;x<14;x++){
					Faces[y][x-1] = ImageIO.read(new File(ResourceDir+"\\"+String.valueOf(y)+String.valueOf(x)+".png"));
				}
			}
			Back = ImageIO.read(new File(ResourceDir+"\\Back.png"));
			BackRot = ImageIO.read(new File(ResourceDir+"\\Back_Rot.png"));
			if(testing){
				System.out.println("found all images");
			}
		} catch (IOException e) {
			System.out.println(String.valueOf(y));
			System.out.println(String.valueOf(x));
			e.printStackTrace();
		}
		GameLoop = new StageHandler();
		createAndShowGUI();
		GameLoop.Start();


	}
	public static void resetDeck(){
		int x,y;
		for(x=0;x<4;x++){
			for(y=0;y<13;y++){
				CardOut[x][y] = false;
			}
		}
	}
	public JPanel CreateContentPane(){
		
		TotalGUI = StageHandler.StageArray[StageHandler.STAGE_MENU];
		
		return TotalGUI;
	}

	
}

