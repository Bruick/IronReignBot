package com.arjvik.robotics.ironreignbot;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;

public class App {

	public static void main(String[] args) throws IOException {

		try {
			Properties auth = new Properties();
			
			//TODO: fix this please - should not need target/classes/
			auth.load(App.class.getResourceAsStream("/auth.properties"));
			
			String token = auth.getProperty("token");
			
			
			final DiscordClient client = new DiscordClientBuilder(token).build();

			for (Class<? extends Handler> c : Handlers.handlers) {
				Handler handler = c.getConstructor().newInstance();
				handler.setupRoute(client);
			}
			
			client.login().block();

		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}

	}

}