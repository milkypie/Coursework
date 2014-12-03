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
import Visuals.BaseJpanel;
import Visuals.FrameHandler;
import Cards.CardCreation;


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
	public static Image[][] Faces = new Image[4][13];
	private static File ResourceDir = new File("C:\\Users\\James\\git\\Coursework\\Resources");
	
	
	
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
		
		//finds card faces
		//these are currently just placeholder
		try {
			for(x=1;x<14;x++){
				Faces[0][x-1] = ImageIO.read(new File(ResourceDir+"\\"+String.valueOf(x)+".jpg"));
				Faces[1][0] = Faces[0][x-1];
				Faces[2][0] = Faces[0][x-1];
				Faces[3][0] = Faces[0][x-1];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StageHandler.Initialize();
		createAndShowGUI();
		StageHandler.Timer();
		

	}
	public JPanel CreateContentPane(){
		
		TotalGUI = StageHandler.StageArray[StageHandler.CurrentStage];
		
		return TotalGUI;
	}

	
}

