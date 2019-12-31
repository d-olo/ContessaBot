package ContessaBot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class Driver {
	
	private static final String TOKEN = "token.properties";  
	private static final String PREFIX = "c!";  
	private static final Map<String, Command> CMDS = new HashMap<String, Command>();
	
	// Add new commands to static block.
	static {
		CMDS.clear();
		CMDS.put("help", event -> {  
			// Send to channel.
			event.getMessage().getChannel().block().createMessage("Help has been sent to your DMs.").block();  
			// Send more detailed help to user.
			event.getMember().get().getPrivateChannel().block().createMessage(Messages.getString("Message.Help")).block();  
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
				for (final Map.Entry<String, Command> entry : CMDS.entrySet()) {
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
