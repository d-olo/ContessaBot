package game;

import discord4j.core.object.entity.Member;
import discord4j.core.object.util.Snowflake;

public abstract class Player {
	
	private String name;
	private Snowflake id;

	public Player(Member player) {
		name = player.getNickname().get();
		id = player.getId();
	}
	
	public String getName() {
		return name;
	}

	public Snowflake getId() {
		return id;
	}

	
}
