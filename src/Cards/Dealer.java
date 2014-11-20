package Cards;

import Control.CardMain;
import Visuals.FrameHandler;


public class Dealer {
	public static FrameHandler Deal(){
		int Suit = (int) Math.round(Math.random()*3);
		int Card = (int) Math.round(Math.random()*13);
		while(CardMain.CardOut[Suit][Card]){
			Suit = (int) Math.round(Math.random()*3);
			Card = (int) Math.round(Math.random()*13);
		}
		String Value = "F";
		if(Card>10||Card<2){
			switch(Card){
			case 11:
				Value = "J";
			break;
			case 12:
				Value = "Q";
			break;
			case 1:
				Value = "A";
			break;
			default:
				Value = "K";
			break;
			}
		}else{
			Value = String.valueOf(Card);
		}
		CardMain.CardOut[Suit][Card] = true;
		FrameHandler Dealt = new FrameHandler(Value,Suit);
		System.out.println(Card+" "+Value+" "+Suit);
		return Dealt;
	}
}

