package game;

import java.util.ArrayList;

import discord4j.core.object.entity.Member;

public abstract class CardPlayer extends Player {
	
	public CardPlayer(Member player) {
		super(player);
	}

	private ArrayList<Card> hand;

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void addCardToHand(Card card) {
		hand.add(card);
	}
}
