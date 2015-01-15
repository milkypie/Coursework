package CustomComponents;

public interface Action {
	public void run();
	
	public default void run(GUIComponent x){}
}
