package main;

import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 * Command implementation recommended by the Discord4J team and found on their Github wiki.
 * This interface allows the use of lambda commands to map Discord message inputs to certain functions.
 * Author: Discord4J Team
 * Source: https://github.com/Discord4J/Discord4J/wiki/Music-Bot-Tutorial#adding-commands
 */
public interface Command {
	void execute(MessageCreateEvent event);
}
