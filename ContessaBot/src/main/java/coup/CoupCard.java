package coup;

import game.Card;

public class CoupCard extends Card {
	
	private CoupCardType type;

	public CoupCard(CoupCardType type) {
		this.type = type;
	}
	
	public CoupCardType getType() {
		return type;
	}
}
