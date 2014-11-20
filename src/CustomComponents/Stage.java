package CustomComponents;

import javax.swing.JPanel;

import Visuals.BaseJpanel;

@SuppressWarnings("serial")
public abstract class Stage extends BaseJpanel{
	

	public abstract void Prepare();
	
	public abstract void Update();
	
	public abstract void Create();
	
	public abstract void Initialize();
}
