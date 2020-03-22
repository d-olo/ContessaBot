package coup;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.object.entity.Member;
import game.CardPlayer;
import game.Game;
import game.Player;

public class CoupGame extends Game {
	
	private List<CoupCard> deck;
	
	public CoupGame() {
		this.setName("Coup");
		deck = new ArrayList<CoupCard>();
		for(CoupCardType type : CoupCardType.values()) {
			for(int i = 0; i < 3; i++) {
				deck.add(new CoupCard(type));
			}
		}
	}

	@Override
	public boolean addPlayer(Member member) {
		CoupPlayer player = new CoupPlayer(member);
		if (!players.contains(player)) {
			players.add(player);
			return true;
		}
		else {
			return false;
		}
	}
	
}
