package coup;

import java.util.ArrayList;
import java.util.List;

import main.Game;

public class CoupGame extends Game {
	
	private List<CoupCard> deck;
	
	public CoupGame() {
		this.setName("Coup");
		deck = new ArrayList<CoupCard>();
		for(CoupCard type : CoupCard.values()) {
			for(int i = 0; i < 3; i++) {
				deck.add(type);
			}
		}
	}
	
}
