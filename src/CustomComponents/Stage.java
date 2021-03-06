package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Control.CardMain;
import Visuals.BaseJpanel;

@SuppressWarnings("serial")
public abstract class Stage extends JPanel{
	
	private ArrayList<GUIComponent> Components = new ArrayList<GUIComponent>();
	protected int  ID;
	
	public void AddComponent(GUIComponent C){
		Components.add(C);
	}
	public void RemoveComponent(GUIComponent C){
		Components.remove(C);
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
	
	public abstract void Prepare();
	
	public abstract void MousePressed(MouseEvent MouseArg);
	
	public abstract void Update();


}
