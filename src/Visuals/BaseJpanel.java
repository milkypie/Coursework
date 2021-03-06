package Visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import CustomComponents.GUIComponent;

@SuppressWarnings("serial")
public class BaseJpanel extends JPanel{
	protected ArrayList<GUIComponent> Components = new ArrayList<GUIComponent>();
	
	public void AddComponent(GUIComponent C){
		Components.add(C);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		if(Components!=null){
			
			for(GUIComponent x: Components){
				x.Update();
				x.Draw(g);
			}
		}
	}
	
}
