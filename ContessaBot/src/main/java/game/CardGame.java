package game;

import java.util.List;

import discord4j.core.object.entity.Member;

public abstract class CardGame extends Game {

	@SuppressWarnings("unused") // Unused 
	private List<Card> deck;
	
	/**
	 * Adds a player to the game's list of players.
	 */
	public abstract boolean addPlayer(Member player);
	
	/**
	 * Builds a fresh deck of cards.
	 */
	public abstract void buildDeck();

}
