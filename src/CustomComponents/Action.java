package CustomComponents;

public interface Action {
	public void run();
	
	public default void run(Card x){}
}
