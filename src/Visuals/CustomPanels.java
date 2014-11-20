package Visuals;

import java.awt.Color;

import javax.swing.JPanel;

public class CustomPanels {
	public static JPanel CreatePanel(int Height, int Width, int XPos, int YPos, Color BackgroundColor){
		JPanel NewPanel = new JPanel();
		NewPanel.setSize(Width,Height);
		NewPanel.setLocation(XPos,YPos);
		NewPanel.setBackground(BackgroundColor);
		NewPanel.setLayout(null);
		return NewPanel;
		
	}
}
