package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import coup.CoupGame;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class Driver {
	
	// Constants
	private static final String TOKEN = "token.properties";  
	private static final String PREFIX = "c!";  
	
	private static final Map<String, Command> cmds = new HashMap<String, Command>();
	private static final Map<MessageChannel, Game> games = new HashMap<MessageChannel, Game>();
	
	// Add new commands to static block.
	static {
		cmds.put("help", event -> {  
			
			String[] args = event.getMessage().getContent().get().split(" ");
			// Send a general message to the channel.
			event.getMessage().getChannel().block().createMessage(Messages.getString("Message.HelpNotif")).block();  
			
			if(args.length == 1) { // No parameter, send default help message.
				// Send more detailed help to user.
				event.getMember().get().getPrivateChannel().block().createMessage(Messages.getString("Message.HelpMain")).block(); 
			}
			else {
				switch(args[1]) {
				case "coup":
					// Send coup command help to user
					event.getMember().get().getPrivateChannel().block().createMessage(Messages.getString("Message.HelpCoup")).block(); 
					break;
				}
			}
		});
		cmds.put("coup", event -> {
			String[] args = event.getMessage().getContent().get().split(" ");
			if(args.length == 1) { // No parameter, send default invalid input message.
				event.getMessage().getChannel().block().createMessage(Messages.getString("Message.CoupInvalid")).block();
			}
			else {
				switch(args[1]) {
				case "announce":
					// Send announcement messages to the channel.
					String msg = Messages.getString("Message.CoupAnnounce1") + event.getMessage().getChannel().block().getMention()
					+ Messages.getString("Message.CoupAnnounce2");
					event.getMessage().getChannel().block().createMessage(msg).block(); 
					CoupGame newGame = new CoupGame();
					newGame.addPlayer(event.getMember().get());
					games.put(event.getMessage().getChannel().block(), newGame);
					break;
				case "join":
					MessageChannel currentChannel = event.getMessage().getChannel().block();
					Game currentGame = games.get(currentChannel);
					if(currentGame == null) { // Case where the game does not exist.
						currentChannel.createMessage(Messages.getString("Message.NoGameFound")).block();
					}
					else if(!(currentGame instanceof CoupGame)) { // Case where the game is not a game of Coup
						msg = Messages.getString("Message.IncorrectGame") + currentGame.getName() + "!";
					}
					else {
						currentGame.addPlayer(event.getMember().get());
					}
					break;
				default:
					event.getMessage().getChannel().block().createMessage(Messages.getString("Message.CoupInvalidCommand")).block();  
					break;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		
		// Get token from config file
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(TOKEN));
		} catch (FileNotFoundException e) {
			System.err.println("[ERROR] (main) .properties not found");  
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("[ERROR] (main) IO Failure");  
			e.printStackTrace();
		}
		System.out.println("[INFO] (main) Properties loaded");  
		
		
		// Create and log in the bot.
		final DiscordClient bot = new DiscordClientBuilder(props.getProperty("token")).build();  
		System.out.println("[INFO] (main) Bot created");  
		
		bot.getEventDispatcher().on(MessageCreateEvent.class)
			.subscribe(event -> {
				final String content = event.getMessage().getContent().orElse("");  
				for (final Map.Entry<String, Command> entry : cmds.entrySet()) {
					if(content.startsWith(PREFIX + entry.getKey())) {
						entry.getValue().execute(event);
						break;
					}
				}
			});
		System.out.println("[INFO] (main) Event dispatcher initialized");  
		
		bot.login().block();
		
	}

}
