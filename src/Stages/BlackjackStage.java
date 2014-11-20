package Stages;

import java.awt.Color;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BlackjackStage extends CustomComponents.Stage{

	
	public void Prepare() {

		
		
		//create blackjack stage
		this.setBackground(Color.green);
		this.setLayout(null);
		JPanel CardBase = Visuals.CustomPanels.CreatePanel(200, 100, 200, 450, new Color(255,255,255,0));
		this.add(CardBase);
		/*Hand[0] = new FrameHandler("10",0);
		
		JPanel CardBase1 = Buttons.CreateButton(200, 100, 400, 450, Color.black);

		Hand[1] = Dealer.Deal();
		
		//CardBase1.add(Hand[1]);;
		Control.CardMain.TotalGUI.add(CardBase1);
		
		CardBase.add(Hand[0]);
		Control.CardMain.TotalGUI.add(CardBase);
		*/
	}

	
	public void Update() {
		Prepare();
	}
	
	public  void Initialize(){
		
	}

	
	public void Create() {
		Initialize();
	}

}
