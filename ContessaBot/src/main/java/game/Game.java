package game;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.object.entity.Member;

public abstract class Game {

	private String gameName;
	@SuppressWarnings("unused")
	private List<Player> players = new ArrayList<Player>();
	
	public String getName() {
		return gameName;
	}
	
	public void setName(String gameName) {
		this.gameName = gameName;
	}
	
	public abstract boolean addPlayer(Member player);
}
