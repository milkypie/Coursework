package Cards;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CardCreation {
	static int Width = 30, Height = 30;
	static File SaveLocation ;
	static File[] Suit = new File[4];
	public static ImageIcon[] Icon = new ImageIcon[4];
	public static BufferedImage[] Image = new BufferedImage[4];
	
	
	public static void LoadIcons() throws IOException{
		File SuperDir = new File("C:\\Users\\James\\Desktop\\CardGames"), ResourceDir = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources");
		Suit[0] = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Diamond.png");
		Suit[1] = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Spade.png");
		Suit[2] = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Heart.png");
		Suit[3] = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Club.png");
		
		SuperDir.mkdir();
		ResourceDir.mkdir();
		int y;
		for(y=0;y<4;y++){
			try{
				
				Image[y] = ImageIO.read(Suit[y]);
				System.out.println(Icon[y]);
				System.out.println("Found Suit["+y+"]");
			}catch(IOException e){
				Color TransparentWhite = new Color(255, 255, 255, 0);
				switch(y){
				case 0:
					System.out.println("had to create a Diamond");
					//creates the diamond picture
					SaveLocation = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Diamond.png");
					BufferedImage DiamondPanel = new BufferedImage(Width,Height, BufferedImage.TYPE_INT_ARGB);
					
					Graphics2D DiamondCreator = DiamondPanel.createGraphics();
					
					DiamondCreator.setColor(TransparentWhite);
					DiamondCreator.fillRect(0, 0, Width, Height);
					
					DiamondCreator.setColor(Color.red);
					int x;
					for(x=0;x<13;x++){
						DiamondCreator.fillRect(15-x, 1+x, 2*(x-2)+2, 2);
					}
					x=14;
					for(x=12;x>=0;x--){
						DiamondCreator.fillRect(15-x, 25-x, 2*(x-2)+2, 2);
					}
					
					DiamondCreator.dispose();
					ImageIO.write(DiamondPanel, "png", SaveLocation);
					Image[y] = DiamondPanel;
				break;
				case 1:
					System.out.println("had to create a Spade");
					//creates a spade
					
					SaveLocation = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Spade.png");
					BufferedImage SpadePanel = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
					
					Graphics2D SpadeCreator = SpadePanel.createGraphics();
					SpadeCreator.setColor(TransparentWhite);
					SpadeCreator.fillRect(0, 0, Width, Height);
					SpadeCreator.setColor(Color.black);
					
					for(x=0;x<13;x++){
						SpadeCreator.fillRect(15-x, 1+x, 2*(x-2)+2, 2);
					}
					
					SpadeCreator.fillRect(4, 15, 8, 1);
					SpadeCreator.fillRect(5, 16, 6, 1);
					SpadeCreator.fillRect(6, 17, 4, 1);
					
					SpadeCreator.fillRect(16, 15, 8, 1);
					SpadeCreator.fillRect(17, 16, 6, 1);
					SpadeCreator.fillRect(18, 17, 4, 1);
					
					SpadeCreator.fillRect(13,15,2,10);
					SpadeCreator.fillRect(11, 24, 6, 2);
					
					
					SpadeCreator.dispose();
					ImageIO.write(SpadePanel, "png", SaveLocation);
					Image[y] = SpadePanel;
				break;
				case 2:
					System.out.println("had to create a Heart");
					//Creates a Heart
					SaveLocation = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Heart.png");
					BufferedImage HeartPanel = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
					
					Graphics2D HeartCreator = HeartPanel.createGraphics();
					HeartCreator.setColor(TransparentWhite);
					HeartCreator.fillRect(0, 0, Width, Height);
					HeartCreator.setColor(Color.red);
					
					for(x=12;x>=0;x--){
						HeartCreator.fillRect(15-x, 25-x, 2*(x-2)+2, 2);
					}
					HeartCreator.fillRect(3,12,22,1);
					HeartCreator.fillRect(4,11,20,1);
										
					HeartCreator.fillRect(5,10,8,1);
					HeartCreator.fillRect(6,9,6,1);
					HeartCreator.fillRect(7,8,4,1);
					
					HeartCreator.fillRect(15,10,8,1);
					HeartCreator.fillRect(16,9,6,1);
					HeartCreator.fillRect(17,8,4,1);
					
					HeartCreator.setColor(TransparentWhite);
					HeartCreator.fillRect(3,9,1,10);
					HeartCreator.fillRect(24,9,1,10);
					
					HeartCreator.dispose();
					ImageIO.write(HeartPanel, "png", SaveLocation);
					Image[y] = HeartPanel;
				break;
				case 3:
					System.out.println("had to create a Club");
					//Creates a Club
					SaveLocation = new File("C:\\Users\\James\\Desktop\\CardGames\\Resources\\Club.png");
					BufferedImage ClubPanel = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
					
					Graphics2D ClubCreator = ClubPanel.createGraphics();
					ClubCreator.setColor(TransparentWhite);
					ClubCreator.fillRect(0, 0, Width, Height);
					
					ClubCreator.setColor(Color.black);
					ClubCreator.fillRect(14, 4, 3, 1 );
					ClubCreator.fillRect(12, 5, 7, 2 );
					ClubCreator.fillRect(11, 7, 9, 2 );
					ClubCreator.fillRect(12, 9, 7, 1 );
					ClubCreator.fillRect(9, 10, 13, 1 );
					ClubCreator.fillRect(7, 11, 17, 2 );
					ClubCreator.fillRect(6, 13, 19, 3 );
					ClubCreator.fillRect(7, 16, 7, 2 );
					ClubCreator.fillRect(17, 16, 7, 2 );
					ClubCreator.fillRect(9, 18, 3, 1 );
					ClubCreator.fillRect(19, 18, 3, 1 );
					ClubCreator.fillRect(15, 16, 1, 6 );
					ClubCreator.fillRect(13, 21, 5, 1 );
					
					ClubCreator.dispose();
					ImageIO.write(ClubPanel,"png", SaveLocation);
					Image[y] = ClubPanel;
				break;
				default:
					System.out.println("failed to find corresponding fix for Suit["+y+"]");
				break;
				
				}
			}
			
		}
		
		
	}
	
}

