package main;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.object.entity.Member;

public class Game {

	private String gameName;
	private List<Member> players = new ArrayList<Member>();
	
	public String getName() {
		return gameName;
	}
	
	public void setName(String gameName) {
		this.gameName = gameName;
	}
	
	public boolean addPlayer(Member player) {
		players.add(player);
		return true;
		//TODO handling adding existing player
	}
}
